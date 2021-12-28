package net.datenwerke.rs.utils.daemon;

public interface DwDaemonService {

   boolean isActive();

   boolean isShutdown();

   boolean isTerminated();

   void shutdown();

   void start();

   boolean isOrderdShutdown();

   boolean isWatchdogActive();

   void shutdownWatchdog();

}
