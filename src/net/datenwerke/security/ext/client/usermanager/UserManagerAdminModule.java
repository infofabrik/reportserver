package net.datenwerke.security.ext.client.usermanager;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.security.ext.client.usermanager.ui.UserManagerPanel;

/**
 * 
 *
 */
public class UserManagerAdminModule implements AdminModule{
	
	final private Provider<UserManagerPanel> userManagerPanelProvider;
	
	@Inject
	public UserManagerAdminModule(
		Provider<UserManagerPanel> userManagerPanel	
		){
		
		/* store objects */
		this.userManagerPanelProvider = userManagerPanel;
	}
	
	@Override
	public ImageResource getNavigationIcon(){
		return BaseIcon.USER.toImageResource();
	}
	
	@Override
	public String getNavigationText() {
		return UsermanagerMessages.INSTANCE.userManagernavtext();
	}

	@Override
	public Widget getMainWidget() {
		return userManagerPanelProvider.get();
	}
	
	@Override
	public void notifyOfSelection() {
	}
}
