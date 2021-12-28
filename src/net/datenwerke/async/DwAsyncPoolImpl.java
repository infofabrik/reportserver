package net.datenwerke.async;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Default implementation of {@link DwAsyncPool}
 * 
 *
 */
public class DwAsyncPoolImpl implements DwAsyncPool {

   private final DwThreadPoolExecutor executorService;

   /**
    * Creates a new pool with the given label. The pool will be created as a
    * {@link ThreadPoolFactory#newCachedThreadPool(String)}.
    * 
    * @see ThreadPoolFactory#newCachedThreadPool(String)
    * @param threadLabel
    */
   public DwAsyncPoolImpl(String threadLabel) {
      this.executorService = ThreadPoolFactory.newCachedThreadPool(threadLabel);
   }

   /**
    * Creates a new thread pool from the given executor.
    * 
    * @param executorService
    */
   public DwAsyncPoolImpl(DwThreadPoolExecutor executorService) {
      this.executorService = executorService;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#submit(java.util.concurrent.Callable)
    */
   @Override
   public <T> Future<T> submit(Callable<T> task) {
      return executorService.submit(task);
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#submit(java.lang.Runnable)
    */
   @Override
   public Future<?> submit(Runnable task) {
      return executorService.submit(task);
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#submit(java.lang.Runnable,
    * java.lang.Object)
    */
   @Override
   public <T> Future<T> submit(Runnable task, T result) {
      return executorService.submit(task, result);
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#awaitTermination(long,
    * java.util.concurrent.TimeUnit)
    */
   @Override
   public boolean awaitTermination(long timeout, TimeUnit unit) throws InterruptedException {
      return executorService.awaitTermination(timeout, unit);
   }

   /**
    * Returns the underlying {@link ExecutorService} object.
    */
   public ExecutorService getExecutorService() {
      return executorService;
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#getActiveCount()
    */
   @Override
   public int getActiveCount() {
      return ((ThreadPoolExecutor) executorService).getActiveCount();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#getPoolSize()
    */
   @Override
   public int getPoolSize() {
      return ((ThreadPoolExecutor) executorService).getPoolSize();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#getCorePoolSize()
    */
   @Override
   public int getCorePoolSize() {
      return ((ThreadPoolExecutor) executorService).getCorePoolSize();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#getTaskCount()
    */
   @Override
   public long getTaskCount() {
      return ((ThreadPoolExecutor) executorService).getTaskCount();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#getCompletedTaskCount()
    */
   @Override
   public long getCompletedTaskCount() {
      return ((ThreadPoolExecutor) executorService).getCompletedTaskCount();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#isShutdown()
    */
   @Override
   public boolean isShutdown() {
      return executorService.isShutdown();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#isTerminated()
    */
   @Override
   public boolean isTerminated() {
      return executorService.isTerminated();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#shutdown()
    */
   @Override
   public void shutdown() {
      executorService.shutdown();
   }

   /*
    * (non-Javadoc)
    * 
    * @see net.datenwerke.async.DwAsyncPool#shutdownNow()
    */
   @Override
   public List<Runnable> shutdownNow() {
      return executorService.shutdownNow();
   }
}
