package net.datenwerke.rs.utils.eventlogger;

import java.util.concurrent.ConcurrentLinkedQueue;

import com.google.inject.Singleton;

import net.datenwerke.rs.utils.eventlogger.eventbus.LoggedEvent;

@Singleton
public class AuditLogEventHandler implements EventLoggerEventHandler {

   private final ConcurrentLinkedQueue<LoggedEvent> eventQueue = new ConcurrentLinkedQueue<LoggedEvent>();

   @Override
   public void handle(LoggedEvent event) {
      eventQueue.add(event);
   }

   public LoggedEvent poll() {
      return eventQueue.poll();
   }

   public boolean hasWork() {
      return !eventQueue.isEmpty();
   }

}
