package net.datenwerke.gf.service.maintenance;

import java.util.Date;

import javax.persistence.EntityManager;

import net.datenwerke.gf.service.maintenance.event.MaintenanceJobEnded;
import net.datenwerke.gf.service.maintenance.event.MaintenanceJobFailed;
import net.datenwerke.gf.service.maintenance.event.MaintenanceJobStarted;
import net.datenwerke.gf.service.maintenance.hooks.MaintenanceTask;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.utils.daemon.DwDaemonImpl;
import net.datenwerke.rs.utils.eventbus.EventBus;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

public class MaintenanceDaemon extends DwDaemonImpl {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	private final Provider<EntityManager> entityManagerProvider;
	private final Provider<UnitOfWork> unitOfWorkProvider;
	private Provider<HookHandlerService> hookHandlerProvider;
	private Provider<EventBus> eventLoggerProvider;

	private ConfigService configService;
	
	@Inject
	public MaintenanceDaemon(
		Provider<EntityManager> entityManagerProvider,
		Provider<UnitOfWork> unitOfWorkProvider,
		
		Provider<HookHandlerService> hookHandlerProvider,
		Provider<EventBus> eventLoggerProvider,
		
		final ConfigService configService
		){
		super();
		
		/* store objects */
		this.unitOfWorkProvider = unitOfWorkProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.hookHandlerProvider = hookHandlerProvider;
		this.eventLoggerProvider = eventLoggerProvider;
		this.configService = configService;
	}

	@Override
	protected void doWork() {
		for(MaintenanceTask task : hookHandlerProvider.get().getHookers(MaintenanceTask.class)){
			UnitOfWork uow = unitOfWorkProvider.get();			
			EntityManager em = null;
			boolean success = false;
			
			try{
				uow.begin();

				try{
					eventLoggerProvider.get().fireEvent(new MaintenanceJobStarted("name", task.getClass().getName(), "time", String.valueOf(new Date().getTime())));
					
					em = entityManagerProvider.get();
					em.getTransaction().begin();
					
					task.performMaintenance();
					
					success = true;

					eventLoggerProvider.get().fireEvent(new MaintenanceJobEnded("name", task.getClass().getName(), "time", String.valueOf(new Date().getTime())));
				} catch(Exception e){
					eventLoggerProvider.get().fireEvent(new MaintenanceJobFailed("name", task.getClass().getName(), "error", e.getMessage()));
					logger.warn( e.getMessage(), e);
				}
			} finally {
				try {
					if(null != em && success)
						em.getTransaction().commit();
					else if(null != em)
						em.getTransaction().rollback();
				} finally {
					uow.end();
				}
			}
		}
		
	}
	
	@Override
	protected long getNextSleepTime() {
		EntityManager em = null;
		UnitOfWork uow = unitOfWorkProvider.get();
		try{
			uow.begin();
			em = entityManagerProvider.get();
			em.getTransaction().begin();
			return configService.getConfigFailsafe(MaintenanceModule.CONFIG_FILE).getLong("maintenance.tasks.interval", 60000l);
		} finally {
			try {
				em.getTransaction().rollback();
			} finally {
				uow.end();
			}
		}
	}


}
