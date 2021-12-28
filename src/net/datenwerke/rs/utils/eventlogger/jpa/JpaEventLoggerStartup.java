package net.datenwerke.rs.utils.eventlogger.jpa;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Injector;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.rs.utils.eventlogger.EventLoggerEventHandler;
import net.datenwerke.rs.utils.eventlogger.EventLoggerService;
import net.datenwerke.rs.utils.eventlogger.eventbus.LoggedEvent;

public class JpaEventLoggerStartup {

   @Inject
   public JpaEventLoggerStartup(EventBus eventBus, final EventLoggerService eventLoggerService, final Injector injector,

         EventLoggerEventHandler eventHandler) {

      eventBus.attachEventHandler(LoggedEvent.class, eventHandler);

      Thread sStarter = new Thread(new Runnable() {

         @Override
         public void run() {
            while (true) {
               try {
                  injector.getInstance(EntityManager.class);
                  break;
               } catch (Exception e) {

               }
            }
            eventLoggerService.start();
         }
      });
      sStarter.setDaemon(true);
      sStarter.start();
   }
}
