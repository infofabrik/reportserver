package net.datenwerke.rs.authenticator.client.login;


import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.dispatcher.DispatcherService;
import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.waitonevent.CallbackOnEventDone;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hooks.ClientPAMHook;
import net.datenwerke.rs.authenticator.client.login.locale.LoginMessages;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM;
import net.datenwerke.security.client.login.AuthResultInfoErrorMsg;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateCallback;
import net.datenwerke.security.client.login.AuthenticateResultDto;
import net.datenwerke.security.client.login.AuthenticateResultInfo;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.decorator.UserDtoDec;

import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Viewport;

public class LoginServiceImpl implements LoginService{

	private final LoginDao loginDao;
	private final WaitOnEventUIService waitOnEventService;
	private final UtilsUIService utilsService;
	private final Provider<DispatcherService> dispatcherServiceProvider;
	private final HookHandlerService hookHandler;
	
	private UserDto currentUser = null;
	private ValueChangeEvent<String> historyEventOnLogin;

	@Inject
	public LoginServiceImpl(
		LoginDao loginDao, 
		WaitOnEventUIService waitOnEventService,
		UtilsUIService utilsService,
		Provider<DispatcherService> dispatcherServiceProvider, 
		HookHandlerService hookHandler
		
		) {
		this.loginDao = loginDao;
		this.waitOnEventService = waitOnEventService;
		this.utilsService = utilsService;
		this.dispatcherServiceProvider = dispatcherServiceProvider;
		this.hookHandler = hookHandler;
	}


	public void isAuthenticated(AsyncCallback<UserDto> callback) {
		loginDao.isAuthenticated(callback);
	}


	public void authenticate(AuthToken[] tokens, final AuthenticateCallback callback) {
		loginDao.authenticate(tokens, new NotamCallback<AuthenticateResultDto>(null, LoginMessages.INSTANCE.loginFailed()) { //$NON-NLS-1$
		
			@Override
			public void doOnSuccess(AuthenticateResultDto result) {

				/* retrieve alle PostAuthenticateClientHookers */
				List<PostAuthenticateClientHook> hookers = hookHandler.getHookers(PostAuthenticateClientHook.class);

				/* Add our own code to the list of hooks */
				PostAuthenticateClientHook selfHookShowError = new PostAuthenticateClientHook() {
					@Override
					public void authenticated(final AuthenticateResultDto result, final List<PostAuthenticateClientHook> chain) {
						if(!result.isValid()){
							
							String title = "";
							String message = LoginMessages.INSTANCE.loginFailed();
							if(null != result.getInfo()){
								for(AuthenticateResultInfo info : result.getInfo()){
									if(info instanceof AuthResultInfoErrorMsg){
										title = ((AuthResultInfoErrorMsg)info).getTitle();
										message = ((AuthResultInfoErrorMsg)info).getMessage();
										break;
									}
								}
							}
							
							SimpleErrorDialog dialog = new SimpleErrorDialog(title, message){
								@Override
								protected void onAnyClose() {
									chain.remove(0).authenticated(result, chain);
								};
							};
							dialog.setWidth(400);
							dialog.show();
							
							
						} else if(chain.size() > 0)
							chain.remove(0).authenticated(result, chain);
					}
				};
				
				PostAuthenticateClientHook selfHookRedispatch = new PostAuthenticateClientHook() {
					@Override
					public void authenticated(AuthenticateResultDto result, List<PostAuthenticateClientHook> chain) {
						dispatcherServiceProvider.get().dispatch();
					}
				};
				
				/* start processing the list of hooks, in an intercepter chain like manner */
				hookers.add(selfHookShowError);
				hookers.add(selfHookRedispatch);
				hookers.remove(0).authenticated(result, hookers);
			}
			
			@Override
			public void doOnFailure(Throwable caught) {
				callback.execute(false);
			}
		});
		
	}
	
	public void tryReAuthenticate(final AuthenticateCallback callback) {
		final boolean isReAuth = isAuthenticated();
		loginDao.isAuthenticated(new RsAsyncCallback<UserDto>() {
			@Override
			public void onSuccess(UserDto user){
				currentUser = user;
				if(null != currentUser){
					/* trigger after login event and dispatch after processing is done */
					waitOnEventService.triggerEvent(
						isReAuth ? REPORTSERVER_EVENT_AFTER_REAUTHENTICATION : REPORTSERVER_EVENT_AFTER_INITIAL_LOGIN, 
						new CallbackOnEventDone() {
						public void execute() {
							/* cascade event */
							waitOnEventService.triggerEvent(REPORTSERVER_EVENT_AFTER_ANY_LOGIN, 
									new CallbackOnEventDone() {
									public void execute() {
										callback.execute(null != currentUser);
										
										if(! isReAuth && null != historyEventOnLogin)
											History.fireCurrentHistoryState();
										historyEventOnLogin = null;
									}
								});		
						}
					});
				}else{
					callback.execute(null != currentUser);
				}
			}
		});		
	
	}

	
	public void logoff() {
		/* trigger event */
		waitOnEventService.triggerEvent(REPORTSERVER_EVENT_BEFORE_LOGOUT, new CallbackOnEventDone() {
			public void execute() {
				currentUser = null;
				
				loginDao.logoff(new ModalAsyncCallback<String>(LoginMessages.INSTANCE.loggedOut(), null, null, null, null, null, null) {
					@Override
					public void doOnSuccess(final String logoutURL){
						/* call after event */
						waitOnEventService.triggerEvent(REPORTSERVER_EVENT_AFTER_LOGOUT, new CallbackOnEventDone() {
							public void execute() {
								if(null == logoutURL || logoutURL.isEmpty()){
									utilsService.reloadPageWithoutAsking();
								} else {
									utilsService.redirectWithoutAsking(logoutURL);
								}
							}
						});
						
					}
				});
			}
		});
		
	}

	public Widget getWidget() {
		final Viewport p = new Viewport();
		
		/* load window */
		waitOnEventService.triggerEvent(REPORTSERVER_EVENT_BEFORE_AUTH_WINDOW_LOAD, new CallbackOnEventDone() {

			@Override
			public void execute() {
				List<ClientPAMHook> hookedPams = hookHandler.getHookers(ClientPAMHook.class);
				List<ClientPAM> pams = new ArrayList<ClientPAM>();
				for(ClientPAMHook hk : hookedPams){
					pams.add(hk.getObject().get());
				}
				
				AuthenticatorWindow aw = new AuthenticatorWindow(pams);
				aw.setViewPort(p);
				p.add(aw);
			}
		});
		
		return p;
	}

	public UserDtoDec getCurrentUser() {
		if(null == currentUser)
			throw new IllegalStateException(LoginMessages.INSTANCE.notLoggedIn());
		return (UserDtoDec)currentUser;
	}
	
	public boolean isAuthenticated(){
		return null != currentUser;
	}


	@Override
	public void fireHistoryEventOnLogin(ValueChangeEvent<String> event) {
		this.historyEventOnLogin = event;
	}
}
