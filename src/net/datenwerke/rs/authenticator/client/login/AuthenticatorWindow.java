package net.datenwerke.rs.authenticator.client.login;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.theme.ThemeUiService;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwSplitButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.authenticator.client.login.hooks.LoginMenuHook;
import net.datenwerke.rs.authenticator.client.login.locale.LoginMessages;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM.ProceeedCallback;
import net.datenwerke.security.client.login.AuthToken;
import net.datenwerke.security.client.login.AuthenticateCallback;
import net.datenwerke.security.client.login.hooks.AuthenticatorWindowExtraOptionHook;
import net.datenwerke.security.client.login.hooks.AuthenticatorWindowExtraOptionHook.ExtraOptionPosition;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.AttachEvent;
import com.google.gwt.event.logical.shared.AttachEvent.Handler;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.button.TextButton;
import com.sencha.gxt.widget.core.client.container.Container;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer;
import com.sencha.gxt.widget.core.client.container.Viewport;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.form.Field;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenu;

public class AuthenticatorWindow extends VerticalLayoutContainer {

	@Inject
	private static LoginService loginService;
	
	@Inject
	private static HookHandlerService hookHandlerService;
	
	@Inject
	private static ThemeUiService themeService;

	private DwSplitButton loginButton;

	private Viewport viewport;

	
	public AuthenticatorWindow(List<ClientPAM> clientPAM) {
		super();
		
		/* create top toolbar */
		FlowLayoutContainer topTb = new FlowLayoutContainer();
		topTb.addStyleName("rs-login-tbar");
		add(topTb, new VerticalLayoutData(1, -1));
		
		/* create logo */
		topTb.add(themeService.getLoginLogo());
		
		/* create language */
		for(AuthenticatorWindowExtraOptionHook hooker : hookHandlerService.getHookers(AuthenticatorWindowExtraOptionHook.class)){
			if(hooker.getPosition() == ExtraOptionPosition.TOP){
				hooker.configure(topTb);
			}
		}
		
		/* load fields */
		// for now only a single page
		FlowLayoutContainer fieldWrapper = new FlowLayoutContainer();
		fieldWrapper.addStyleName("rs-login-fields");
		add(fieldWrapper, new VerticalLayoutData(1,-1, new Margins(70,0,0,0)));

		/* load fields */
		clientPAM.get(0).addFields(fieldWrapper, new ProceeedCallback(){
			@Override
			public void submit() {
				loginButton.fireEvent(new SelectEvent());
			}
		});
		
		
		/* load additional fields */
		for(AuthenticatorWindowExtraOptionHook hooker : hookHandlerService.getHookers(AuthenticatorWindowExtraOptionHook.class)){
			if(hooker.getPosition() == ExtraOptionPosition.FIELD){
				hooker.configure(fieldWrapper);
			}
		}
		
		/* load buttons */
		loginButton = new DwSplitButton(LoginMessages.INSTANCE.login());
		loginButton.addStyleName("rs-login-btn");
		loginButton.setWidth(150);
		loginButton.setMenu(createLoginMenu());
		
		fieldWrapper.add(loginButton, new MarginData(0,0,10,190));
		
		/* initialize event handling */
		initEventHandling(loginButton, clientPAM);

		/* display first page */
		addAttachHandler(new Handler() {
			
			@Override
			public void onAttachOrDetach(AttachEvent event) {
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						/* set focus */
						setFocusOnFirstField();						
					}
				});
			}
		});
	}
	

	private void initEventHandling(TextButton finishButton, final List<ClientPAM> clientPAM) {
		finishButton.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				viewport.mask(BaseMessages.INSTANCE.busyMsg());
				List<AuthToken> tokens = new ArrayList<AuthToken>();
				List<ClientPAM> cpams = new ArrayList<ClientPAM>(clientPAM);
				cpams.add(new ClientPAM(){
					@Override
					public void addResult(List<AuthToken> results, List<ClientPAM> next) {
						loginService.authenticate(results.toArray(new AuthToken[]{}), new AuthenticateCallback(){
							public void execute(boolean authenticateSucceeded) {
								viewport.unmask();
								setFocusOnFirstField();
							}
						});
					}
					
					public String getModuleName() {return null;}

					@Override
					public void addFields(FlowLayoutContainer container, ProceeedCallback callback) {}
				});
				
				cpams.get(0).addResult(tokens, cpams.subList(1, cpams.size()));
			}
		});
	}


	private void setFocusOnFirstField() {
		Field firstField = getFirstField(this);
		if(null != firstField){
			firstField.focus();
		}
	}

	private Field getFirstField(Container parent){
		for(Widget c : parent)
			if(c instanceof Field)
				return (Field) c;
		
		for (Widget c : parent){
			if(c instanceof Container){
				Field res = getFirstField((Container) c);
				if(res != null)
					return res;
			}
		}
		
		return null;
	}
	
	private Menu createLoginMenu(){
		List<LoginMenuHook> hookers = hookHandlerService.getHookers(LoginMenuHook.class);
	
		if(hookers.isEmpty())
			return null;
		
		Menu m = new DwMenu();
		for(LoginMenuHook h : hookers)
			m.add(h.getObject());
		
		return m;
	}


	public void setViewPort(Viewport p) {
		this.viewport = p;
	}
	
	

}
