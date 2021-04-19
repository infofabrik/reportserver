package net.datenwerke.rs.utils.eventlogger.jpa;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityManager;

import net.datenwerke.rs.utils.daemon.DwDaemonImpl;
import net.datenwerke.rs.utils.eventlogger.AuditLogEventHandler;
import net.datenwerke.rs.utils.eventlogger.annotations.EventLoggerCheckInterval;
import net.datenwerke.rs.utils.eventlogger.entities.AuditLogEntry;
import net.datenwerke.rs.utils.eventlogger.entities.LogProperty;
import net.datenwerke.rs.utils.eventlogger.eventbus.LoggedEvent;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.UnitOfWork;

public class JpaEventLoggerDaemon extends DwDaemonImpl {
	
	private final AuditLogEventHandler eventHandler;
	private final Provider<EntityManager> entityManagerProvider;
	private final Provider<UnitOfWork> unitOfWorkProvider;
	
	@Inject
	public JpaEventLoggerDaemon(
		@EventLoggerCheckInterval Long checkInterval,
		Provider<EntityManager> entityManagerProvider,
		Provider<UnitOfWork> unitOfWorkProvider,
		
		AuditLogEventHandler eventHandler
		){
		super(checkInterval);
		
		/* store objects */
		this.eventHandler = eventHandler;
		this.unitOfWorkProvider = unitOfWorkProvider;
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	protected void doWork() {
		if(! eventHandler.hasWork())
			return;
		
		UnitOfWork uow = unitOfWorkProvider.get();
		uow.begin();
		
		EntityManager em = null;
		try{
			em = entityManagerProvider.get();
			em.getTransaction().begin();
		
			LoggedEvent event = eventHandler.poll();
			while(null != event){
				event.aboutToBeLogged();
				
				AuditLogEntry logEntry = getLogEntry(event);
				try{
					em.persist(logEntry);
				}catch(RuntimeException e){
					logger.warn( e.getMessage(), e);
					throw e;
				}
				
				event = eventHandler.poll();
			}
		} finally {
			try{
				if(null != em)
					em.getTransaction().commit();
			} finally {
				uow.end();
			}
		}
	}

	private AuditLogEntry getLogEntry(LoggedEvent event) {
		AuditLogEntry entry = new AuditLogEntry();
		entry.setUserId(event.getLoggedUserId());
		entry.setAction(event.getLoggedAction());
		entry.setDate(event.getDate() == null ? new GregorianCalendar().getTime() : event.getDate());
		
		Set<LogProperty> properties = new HashSet<LogProperty>();
		for(Entry<String, String> e : event.getLoggedProperties().entrySet()){
			LogProperty prop = new LogProperty(e.getKey(), e.getValue());
			properties.add(prop);
		}
		entry.setLogProperties(properties);
		
		return entry;
	}



}
