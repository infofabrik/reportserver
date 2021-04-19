package net.datenwerke.async;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * A thread pool allowing to run asynchronous tasks.
 * 
 *
 */
public interface DwAsyncPool {
	
	/**
	 * Submits a value-returning task for execution and returns a
     * Future representing the pending results of the task. The
     * Future's <tt>get</tt> method will return the task's result upon
     * successful completion.
	 * 
	 * @see ExecutorService#submit(Callable)
	 * @param task
	 */
	public <T> Future<T> submit(Callable<T> task);
	
	/**
	 * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's <tt>get</tt> method will
     * return <tt>null</tt> upon <em>successful</em> completion.
	 * 
	 * @see ExecutorService#submit(Runnable)
	 * @param task
	 */
	public <T> Future<T> submit(Runnable task);
	
	/**
	 * Submits a Runnable task for execution and returns a Future
     * representing that task. The Future's <tt>get</tt> method will
     * return the given result upon successful completion.
	 * 
	 * @see ExecutorService#submit(Runnable, Object)
	 * @param task
	 */
	public <T> Future<T> submit(Runnable task, T result);

	/**
	 * Returns the approximate number of threads that are actively
     * executing tasks.
     *
	 * @see ThreadPoolExecutor#getActiveCount()
	 * @return the number of threads
	 */
	int getActiveCount();
	
	/**
	 * Returns the approximate total number of tasks that have ever been
     * scheduled for execution. Because the states of tasks and
     * threads may change dynamically during computation, the returned
     * value is only an approximation.
     * 
	 * @see ThreadPoolExecutor#getTaskCount()
	 * @return the number of threads
	 */
	long getTaskCount();
	
	/**
	 * Returns the approximate total number of tasks that have
     * completed execution. Because the states of tasks and threads
     * may change dynamically during computation, the returned value
     * is only an approximation, but one that does not ever decrease
     * across successive calls.
	 * 
	 * @see ThreadPoolExecutor#getCompletedTaskCount()
	 * @return the number of threads
	 */
	long getCompletedTaskCount();
	
	/**
	 * Returns the current number of threads in the pool.
     *
     * @see ThreadPoolExecutor#getPoolSize()
     * @return the number of threads
	 */
	int getPoolSize();
	
	/**
	 * Returns the core number of threads.
     *
	 * @see ThreadPoolExecutor#getCorePoolSize()
	 * @return the core number of threads
	 */
	int getCorePoolSize();

	
	
	/**
	 * Returns true if the pool has been shut down (orderly).
	 */
	boolean isShutdown();
	
	/**
	 * Returns true if the pool has been terminated
	 */
	boolean isTerminated();

	/**
	 * Blocks until all tasks have completed execution after a shutdown
     * request, or the timeout occurs, or the current thread is
     * interrupted, whichever happens first.
     * 
	 * @see ExecutorService#awaitTermination(long, TimeUnit)
	 * @param timeout
	 * @param unit
	 * @throws InterruptedException
	 */
	boolean awaitTermination(long timeout, TimeUnit unit)
			throws InterruptedException;

	/**
	 * Initiates an orderly shutdown in which previously submitted
     * tasks are executed, but no new tasks will be accepted.
     * Invocation has no additional effect if already shut down.
     *
     * <p>This method does not wait for previously submitted tasks to
     * complete execution.  Use {@link #awaitTermination awaitTermination}
     * to do that.
	 * 
	 * @see ExecutorService#shutdown()
	 */
	void shutdown();

	/**
	 *  Attempts to stop all actively executing tasks, halts the
     * processing of waiting tasks, and returns a list of the tasks
     * that were awaiting execution.
     *
     * <p>This method does not wait for actively executing tasks to
     * terminate.  Use {@link #awaitTermination awaitTermination} to
     * do that.
     *
     * <p>There are no guarantees beyond best-effort attempts to stop
     * processing actively executing tasks.  For example, typical
     * implementations will cancel via {@link Thread#interrupt}, so any
     * task that fails to respond to interrupts may never terminate.
     * 
	 * @see ExecutorService#shutdownNow()
	 * 
	 * @return list of tasks that never commenced execution
	 */
	List<Runnable> shutdownNow();

}
