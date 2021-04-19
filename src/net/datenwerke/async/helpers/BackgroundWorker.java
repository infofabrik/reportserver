package net.datenwerke.async.helpers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BackgroundWorker implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final Runnable runnable;
	private final long delay;
	
	private boolean cancel = false;
	
	public BackgroundWorker(long delay, Runnable runnable) {
		this.runnable = runnable;
		this.delay = delay;
	}


	@Override
	public void run() {
		while(! cancel){
			try{
				runnable.run();
			} catch(RuntimeException e){
				logger.warn( "Background task failed", e);
				throw e;
			}
			
			try {
				Thread.sleep(delay);
			} catch (InterruptedException e) {
			} 
		}
	}
	
	public void cancel(){
		this.cancel = true;
	}

}
