package net.datenwerke.gf.client.login;

import net.datenwerke.gf.client.dispatcher.Dispatchable;
import net.datenwerke.security.client.login.AuthenticateCallback;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;

import com.google.gwt.event.logical.shared.ValueChangeEvent;

public interface LoginService extends Dispatchable{

	public static final String REPORTSERVER_EVENT_BEFORE_LOGIN = "REPORTSERVER_EVENT_BEFORE_LOGIN"; //$NON-NLS-1$
	public static final String REPORTSERVER_EVENT_AFTER_INITIAL_LOGIN = "REPORTSERVER_EVENT_AFTER_INITIAL_LOGIN"; //$NON-NLS-1$
	public static final String REPORTSERVER_EVENT_AFTER_REAUTHENTICATION = "REPORTSERVER_EVENT_AFTER_REAUTHENTICATION"; //$NON-NLS-1$
	public static final String REPORTSERVER_EVENT_AFTER_ANY_LOGIN = "REPORTSERVER_EVENT_AFTER_ANY_LOGIN"; //$NON-NLS-1$
	
	public static final String REPORTSERVER_EVENT_BEFORE_AUTH_WINDOW_LOAD = "REPORTSERVER_EVENT_BEFORE_AUTH_WINDOW_LOAD"; //$NON-NLS-1$
	
	public static final String REPORTSERVER_EVENT_BEFORE_LOGOUT = "REPORTSERVER_EVENT_BEFORE_LOGOUT"; //$NON-NLS-1$
	public static final String REPORTSERVER_EVENT_AFTER_LOGOUT = "REPORTSERVER_EVENT_AFTER_LOGOUT"; //$NON-NLS-1$
	
	public UserDtoDec getCurrentUser();
	
	public void authenticate(net.datenwerke.security.client.login.AuthToken[] tokens, AuthenticateCallback callback);
	
	public void tryReAuthenticate(AuthenticateCallback callback);
	
	public void logoff();

	public boolean isAuthenticated();

	public void fireHistoryEventOnLogin(ValueChangeEvent<String> event);
	
}
