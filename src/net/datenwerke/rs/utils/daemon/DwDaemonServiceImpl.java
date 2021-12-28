package net.datenwerke.rs.utils.daemon;

import java.util.concurrent.Executors;

import com.google.inject.Provider;

public abstract class DwDaemonServiceImpl<D extends DwDaemon> implements DwDaemonService {

   private final Provider<D> daemonProvider;
   private D daemon;
   private Thread daemonThread;
   private boolean orderdShutdown;

   private Provider<DwDaemonWatchdog> watchdogProvider;
   private DwDaemonWatchdog watchdog;
   private Thread watchdogThread;

   private int daemonCnt = 1;

   public DwDaemonServiceImpl(Provider<D> daemonProvider) {
      this.daemonProvider = daemonProvider;
   }

   public DwDaemonServiceImpl(Provider<D> daemonProvider, Provider<DwDaemonWatchdog> watchdogProvider) {
      this.daemonProvider = daemonProvider;
      this.watchdogProvider = watchdogProvider;
   }

   @Override
   public boolean isActive() {
      return null != daemonThread && daemonThread.isAlive() && null != daemon && !daemon.isShutdown();
   }

   @Override
   public boolean isShutdown() {
      return null == daemonThread || null == daemon || daemon.isShutdown();
   }

   @Override
   public boolean isTerminated() {
      return null == daemonThread || null == daemon || daemon.isTerminated();
   }

   @Override
   public void shutdown() {
      if (null != daemon && !daemon.isShutdown()) {
         orderdShutdown = true;

         daemon.shutdown();
         daemonThread.interrupt();
      }
   }

   public boolean isHasWatchdog() {
      return null != watchdogProvider;
   }

   public void setWatchdogProvider(Provider<DwDaemonWatchdog> watchdogProvider) {
      this.watchdogProvider = watchdogProvider;
   }

   @Override
   public void start() {
      if (daemon != null) {
         if (isActive())
            return;
         else
            daemon.shutdown();
      }

      daemon = daemonProvider.get();

      orderdShutdown = false;

      daemonThread = Executors.defaultThreadFactory().newThread(daemon);
      daemonThread.setDaemon(true);
      daemonThread.setName("dwdaemon-" + getClass().getSimpleName() + "-" + daemonCnt++);
      daemonThread.start();

      /* start watchdog */
      startWatchdog();
   }

   @Override
   public boolean isOrderdShutdown() {
      return orderdShutdown;
   }

   private void startWatchdog() {
      if (null == watchdogProvider || (null != watchdogThread && watchdogThread.isAlive() && !watchdog.isShutdown()))
         return;

      watchdog = watchdogProvider.get();
      watchdog.setDaemonService(this);

      watchdogThread = Executors.defaultThreadFactory().newThread(watchdog);
      watchdogThread.setDaemon(true);
      watchdogThread.setName(watchdog.getName());
      watchdogThread.start();
   }

   @Override
   public boolean isWatchdogActive() {
      return null != watchdogThread && watchdogThread.isAlive();
   }

   @Override
   public void shutdownWatchdog() {
      if (null != watchdog)
         watchdog.shutdown();
   }

}
