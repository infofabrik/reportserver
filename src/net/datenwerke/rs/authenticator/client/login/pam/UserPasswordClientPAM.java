package net.datenwerke.rs.authenticator.client.login.pam;

import java.util.List;

import javax.inject.Singleton;

import net.datenwerke.rs.authenticator.client.login.dto.UserPasswordAuthToken;
import net.datenwerke.rs.authenticator.client.login.locale.LoginMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.login.AuthToken;

import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.event.dom.client.KeyDownHandler;
import com.google.gwt.user.client.ui.HTML;
import com.sencha.gxt.core.client.util.Margins;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;
import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.container.VerticalLayoutContainer.VerticalLayoutData;
import com.sencha.gxt.widget.core.client.form.PasswordField;
import com.sencha.gxt.widget.core.client.form.TextField;

@Singleton
public class UserPasswordClientPAM implements ClientPAM {
	
	protected TextField username;
	protected PasswordField password;
	private String lastUsername;
	
	public UserPasswordClientPAM(){
		super();
	}
	
	public AuthToken getResult() {
		UserPasswordAuthToken upDto = new UserPasswordAuthToken();
		
		upDto.setUsername(username.getText());
		upDto.setPassword(password.getText());
		
		this.lastUsername = upDto.getUsername();
		
		return upDto;
	}
	
	@Override
	public void addResult(List<AuthToken> results, List<ClientPAM> next) {
		results.add(getResult());
		next.get(0).addResult(results, next.subList(1, next.size()));
	}
	
	@Override
	public void addFields(FlowLayoutContainer container, final ProceeedCallback callback) {
		username = new TextField();
		username.setWidth(220);
		username.setHeight(30);
		username.getCell().setWidth(200);
		username.addStyleName("rs-login-username");
		username.setEmptyText(LoginMessages.INSTANCE.username());
		if(null != lastUsername)
			username.setValue(lastUsername);
		HBoxLayoutContainer uContainer = new HBoxLayoutContainer();
		uContainer.setWidth(250);
		uContainer.setHeight(30);
		HTML userIcon = new HTML(BaseIcon.USER.toSafeHtml());
		userIcon.setWidth("20px");
		userIcon.addStyleName("rs-login-username-i");
		uContainer.add(userIcon);
		uContainer.add(username);
		container.add(uContainer, new MarginData(10,0,10,100));
		
		password = new PasswordField();
		password.setWidth(220);
		password.setHeight(30);
		password.getCell().setWidth(200);
		password.addStyleName("rs-login-password");
		password.setEmptyText(LoginMessages.INSTANCE.password());

		HBoxLayoutContainer pContainer = new HBoxLayoutContainer();
		pContainer.setWidth(250);
		pContainer.setHeight(30);
		HTML keyIcon = new HTML(BaseIcon.KEY.toSafeHtml());
		keyIcon.setWidth("20px");
		keyIcon.addStyleName("rs-login-password-i");
		pContainer.add(keyIcon);
		pContainer.add(password);
		container.add(pContainer, new MarginData(0,0,10,100));
		
		password.addKeyDownHandler(new KeyDownHandler() {
			@Override
			public void onKeyDown(KeyDownEvent event) {
				if(KeyCodes.KEY_ENTER ==  event.getNativeKeyCode())
					callback.submit();
			}
		});
	}

	public String getModuleName() {
		return this.getClass().getName();
	}
	
}
