package net.datenwerke.rs.utils.daemon;

public interface DwDaemon extends Runnable {

	public boolean isShutdown();

	public boolean isTerminated();

	public void shutdown();

	
}
