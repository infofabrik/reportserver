package net.datenwerke.rs.utils.eventlogger.jpa;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.datenwerke.rs.utils.eventlogger.AuditLogEventHandler;
import net.datenwerke.rs.utils.eventlogger.EventLoggerEventHandler;
import net.datenwerke.rs.utils.eventlogger.EventLoggerService;
import net.datenwerke.rs.utils.eventlogger.annotations.EventLoggerCheckInterval;

public class JpaEventLoggerModule extends AbstractModule {

   @Override
   protected void configure() {
      bind(Long.class).annotatedWith(EventLoggerCheckInterval.class).toInstance(5000l);
      bind(EventLoggerEventHandler.class).to(AuditLogEventHandler.class).in(Singleton.class);
      bind(EventLoggerService.class).to(JpaEventLoggerServiceImpl.class).in(Singleton.class);

      bind(JpaEventLoggerStartup.class).asEagerSingleton();
   }

}
