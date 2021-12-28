package net.datenwerke.rs.utils.eventlogger.console;

import net.datenwerke.rs.utils.eventlogger.EventLoggerService;

public class ConsoleEventLoggerServiceImpl implements EventLoggerService {

   @Override
   public boolean isActive() {
      return true;
   }

   @Override
   public boolean isShutdown() {
      return false;
   }

   @Override
   public boolean isTerminated() {
      return false;
   }

   @Override
   public void shutdown() {
   }

   @Override
   public void start() {

   }

   @Override
   public boolean isOrderdShutdown() {
      return false;
   }

   @Override
   public boolean isWatchdogActive() {
      // TODO Auto-generated method stub
      return false;
   }

   @Override
   public void shutdownWatchdog() {
      // TODO Auto-generated method stub

   }

}
