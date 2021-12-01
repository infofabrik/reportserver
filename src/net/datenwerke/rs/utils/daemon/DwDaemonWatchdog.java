package net.datenwerke.rs.utils.daemon;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DwDaemonWatchdog implements Runnable {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private boolean shutdown = false;

	private DwDaemonService daemonService;
	
	protected int sleepTime = 1000*60;

	public void setDaemonService(DwDaemonService daemonService) {
		this.daemonService = daemonService;
	}
	
	public DwDaemonService getDaemonService() {
		return daemonService;
	}
	
	@Override
	public void run() {
		while(! shutdown){
			checkDaemonState();
			
			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
			}
		}
	}

	private void checkDaemonState() {
		try{
			if(! daemonService.isActive() && ! daemonService.isOrderdShutdown()){
				daemonService.start();
				
				logger.warn( "Restarted DwDaemon " + daemonService.getClass().getName());
			}
		}catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	public void shutdown(){
		synchronized (DwDaemonWatchdog.class) {
			shutdown = true;			
		}
	}

	public boolean isShutdown() {
		synchronized (DwDaemonWatchdog.class) {
			return false;
		}
	}

	public String getName() {
		return "dwdaemon-watchdog-" + daemonService.getClass().getSimpleName();
	}
}
