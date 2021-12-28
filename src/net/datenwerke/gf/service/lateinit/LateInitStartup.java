package net.datenwerke.gf.service.lateinit;

import java.util.concurrent.atomic.AtomicBoolean;

import javax.persistence.EntityManager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class LateInitStartup {

	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	private static AtomicBoolean startupCompleted = new AtomicBoolean(false);

	@Inject
	public LateInitStartup(
			final HookHandlerService hookHandler,
			final Injector injector,
			final Provider<EntityManager> entityManagerProvider,
			final Provider<UnitOfWork> unitOfWorkProvider

			){

		Thread sStarter = new Thread(new Runnable() {

			@Override
			public void run() {
				while(true){
					try{
						injector.getInstance(EntityManager.class);
						break;
					} catch(Exception e){
						try {
							Thread.sleep(10);
						} catch (InterruptedException e1) {
						}
					}
				}
				try{
					/* begin transaction */
					UnitOfWork unitOfWork = unitOfWorkProvider.get();

					EntityManager em = null;
					boolean success = false;

					try{
						/* begin unit of work not necessary, since get Instance of Entity Manager starts uow*/

						/* begin transaction */
						em = entityManagerProvider.get();
						em.getTransaction().begin();

						/* perform steps */
						for(LateInitHook hooker : hookHandler.getHookers(LateInitHook.class))
							hooker.initialize();
						startupCompleted.set(true);
						
						logger.info("Startup completed");

						/* indicate success */
						success = true;
					} finally {
						try {
							if(null != em && success)
								em.getTransaction().commit();
							else if(null != em)
								em.getTransaction().rollback();
						} finally {
							unitOfWork.end();
						}
					}
				} catch(Exception e){
					logger.error( "Error in LateInitHook", e);
				}
			}
		});
		sStarter.setDaemon(true);
		sStarter.start();
	}

	public static boolean isStartupCompleted() {
		return startupCompleted.get();
	}

}
