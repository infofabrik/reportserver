package net.datenwerke.rs.utils.daemon;

import javax.inject.Provider;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class DwDaemonImpl implements DwDaemon {

   protected final Logger logger = LoggerFactory.getLogger(getClass().getName());

   protected final Provider<Long> checkInterval;

   protected boolean shutdown;
   protected boolean terminated;

   public DwDaemonImpl() {
      this(60000l);
   }

   public DwDaemonImpl(Provider<Long> checkIntervalProvider) {
      this.checkInterval = checkIntervalProvider;
   }

   public DwDaemonImpl(final Long checkInterval) {
      this.checkInterval = new Provider<Long>() {
         @Override
         public Long get() {
            return checkInterval;
         }
      };
   }

   @Override
   public void run() {
      while (!shutdown) {
         try {
            doWork();
         } catch (Throwable e) {
            try {
               logger.warn(e.getMessage(), e);
            } finally {
               if (e instanceof Error) {
                  shutdown = true;
                  throw (Error) e;
               }
            }
         }

         /* sleep till next fire time */
         try {
            Thread.sleep(getNextSleepTime());
         } catch (InterruptedException e) {
         }
      }

      /* oderly shutdown */
      this.terminated = true;
   }

   abstract protected void doWork();

   protected long getNextSleepTime() {
      return Math.max(0, checkInterval.get());
   }

   @Override
   public synchronized void shutdown() {
      shutdown = true;
   }

   @Override
   public boolean isShutdown() {
      return shutdown;
   }

   @Override
   public boolean isTerminated() {
      return terminated;
   }

}
