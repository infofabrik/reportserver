package net.datenwerke.async;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import com.google.inject.Inject;

import net.datenwerke.async.annotations.WaitBeforeForcedShutdown;
import net.datenwerke.async.configurations.CachedThreadPoolConfig;

/**
 * 
 *
 */
public class DwAsyncServiceImpl implements DwAsyncService {

   private static final String THREAD_LABEL = "DwAsyncService";

   private final long waitBeforeForcedShutdown;

   private final Map<String, DwAsyncPool> poolMap = new HashMap<String, DwAsyncPool>();

   @Inject
   public DwAsyncServiceImpl(@WaitBeforeForcedShutdown Long waitBeforeForcedShutdown) {

      /* store objects */
      this.waitBeforeForcedShutdown = waitBeforeForcedShutdown;
   }

   @Override
   public void shutdownAll() {
      for (String token : poolMap.keySet())
         shutdownPool(token);
   }

   @Override
   public List<Runnable> shutdownAllNow() {
      List<Runnable> list = new ArrayList<Runnable>();

      for (String token : poolMap.keySet())
         list.addAll(shutdownPoolNow(token));

      return list;
   }

   @Override
   public boolean poolExists(String poolToken) {
      return poolMap.containsKey(poolToken);
   }

   @Override
   public boolean isShutdownPool(String poolToken) {
      if (poolExists(poolToken)) {
         DwAsyncPool pool = getPool(poolToken);
         return pool.isShutdown();
      }
      return true;
   }

   @Override
   public boolean isTerminatedPool(String poolToken) {
      if (poolExists(poolToken)) {
         DwAsyncPool pool = getPool(poolToken);
         return pool.isTerminated();
      }
      return true;
   }

   @Override
   public DwAsyncPool initPool(String poolToken, PoolConfiguration configuration) {
      if (poolExists(poolToken)) {
         if (!isShutdownPool(poolToken))
            shutdownPool(poolToken);
         if (!isTerminatedPool(poolToken)) {
            try {
               if (!awaitTerminationForPool(poolToken, waitBeforeForcedShutdown, TimeUnit.MILLISECONDS)) {
                  shutdownPoolNow(poolToken);
               }
            } catch (Exception e) {
               shutdownPoolNow(poolToken);
            }
         }
      }

      DwAsyncPool pool = getPoolFromConfiguration(configuration);
      poolMap.put(poolToken, pool);
      return pool;
   }

   private DwAsyncPool getPoolFromConfiguration(PoolConfiguration configuration) {
      switch (configuration.getType()) {
      case SINGLE:
         return new DwAsyncPoolImpl(ThreadPoolFactory.newSingleThreadExecutor(THREAD_LABEL));
      case FIXED:
         return new DwAsyncPoolImpl(ThreadPoolFactory.newFixedThreadPool(configuration.getCoreSize(), THREAD_LABEL));
      default:
         return new DwAsyncPoolImpl(ThreadPoolFactory.newCachedThreadPool(THREAD_LABEL));
      }
   }

   @Override
   public DwAsyncPool getPool(String poolToken) {
      return poolMap.get(poolToken);
   }

   @Override
   public void shutdownPool(String poolToken) {
      if (poolExists(poolToken)) {
         DwAsyncPool pool = getPool(poolToken);
         pool.shutdown();
      }
   }

   @Override
   public List<Runnable> shutdownPoolNow(String poolToken) {
      if (poolExists(poolToken)) {
         DwAsyncPool pool = getPool(poolToken);
         return pool.shutdownNow();
      }
      return new ArrayList<Runnable>();
   }

   @Override
   public boolean awaitTerminationForPool(String poolToken, long timeout, TimeUnit unit) throws InterruptedException {
      if (poolExists(poolToken)) {
         DwAsyncPool pool = getPool(poolToken);
         return pool.awaitTermination(timeout, unit);
      }
      return true;
   }

   @Override
   public int getNrOfPools() {
      return poolMap.size();
   }

   @Override
   public int getActiveCountAll() {
      int cnt = 0;
      for (DwAsyncPool pool : poolMap.values())
         cnt += pool.getActiveCount();
      return cnt;
   }

   @Override
   public long getTaskCountAll() {
      long cnt = 0;
      for (DwAsyncPool pool : poolMap.values())
         cnt += pool.getTaskCount();
      return cnt;
   }

   @Override
   public long getCompletedTaskCountAll() {
      long cnt = 0;
      for (DwAsyncPool pool : poolMap.values())
         cnt += pool.getCompletedTaskCount();
      return cnt;
   }

   @Override
   public void submit(Runnable task) {
      DwAsyncPool pool = getPool(THREAD_LABEL);
      if (null == pool)
         pool = initPool(THREAD_LABEL, new CachedThreadPoolConfig());
      pool.submit(task);
   }

   @Override
   public void suhtdownDefault() {
      shutdownPool(THREAD_LABEL);
   }

   @Override
   public List<Runnable> shutdownNowDefault() {
      return shutdownPoolNow(THREAD_LABEL);
   }

}
