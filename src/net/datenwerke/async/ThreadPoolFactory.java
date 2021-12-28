package net.datenwerke.async;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.TimeUnit;

public class ThreadPoolFactory {

   public static DwThreadPoolExecutor newFixedThreadPool(int nThreads, String threadLabel) {
      return new DwThreadPoolExecutor(nThreads, nThreads, 0L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(), threadLabel);

   }

   public static DwThreadPoolExecutor newCachedThreadPool(String threadLabel) {
      return new DwThreadPoolExecutor(0, Integer.MAX_VALUE, 60L, TimeUnit.SECONDS, new SynchronousQueue<Runnable>(),
            threadLabel);

   }

   public static DwThreadPoolExecutor newSingleThreadExecutor(String threadLabel) {
      return new DwThreadPoolExecutor(1, 1, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(),
            threadLabel);

   }
}
