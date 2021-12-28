package net.datenwerke.gf.client.dispatcher;

public interface DispatcherService {

   public static final String REPORTSERVER_EVENT_BEFORE_STARTUP = "REPORTSERVER_EVENT_BEFORE_STARTUP"; //$NON-NLS-1$
   public static final String REPORTSERVER_EVENT_DISPATCHING_STARTED = "REPORTSERVER_EVENT_DISPATCHING_STARTED"; //$NON-NLS-1$
   public static final String REPORTSERVER_EVENT_USER_LOGGED_IN_APPLICATION_LOADED = "REPORTSERVER_EVENT_USER_LOGGED_IN_APPLICATION_LOADED"; //$NON-NLS-1$

   public void dispatch();

   public boolean isWarnOnExit();

   public void setWarnOnExit(boolean warnOnExit);

   public void redirect(String url, boolean disableExitWarning);

}
