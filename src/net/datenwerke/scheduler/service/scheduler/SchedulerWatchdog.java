package net.datenwerke.scheduler.service.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.scheduler.service.scheduler.events.SchedulerDaemonRessurectionEvent;

public class SchedulerWatchdog implements Runnable {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final SchedulerService schedulerService;
   private final EventBus eventBus;

   private boolean shutdown = false;

   @Inject
   public SchedulerWatchdog(EventBus eventBus, SchedulerService schedulerService) {
      this.eventBus = eventBus;
      this.schedulerService = schedulerService;
   }

   @Override
   public void run() {
      while (!shutdown) {
         checkDaemonState();

         try {
            Thread.sleep(1000 * 60);
         } catch (InterruptedException e) {
         }
      }
   }

   private void checkDaemonState() {
      try {
         if (!schedulerService.isActive() && !schedulerService.isOrderdShutdown()) {
            eventBus.fireEvent(new SchedulerDaemonRessurectionEvent());

            schedulerService.start();
         }
      } catch (Exception e) {
         logger.warn(e.getMessage(), e);
      }
   }

   public void shutdown() {
      synchronized (SchedulerWatchdog.class) {
         shutdown = true;
      }
   }

   public boolean isShutdown() {
      synchronized (SchedulerWatchdog.class) {
         return shutdown;
      }
   }
}
