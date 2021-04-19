package net.datenwerke.rs.teamspace.client.teamspace;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.teamspace.client.teamspace.ui.admin.TeamSpaceManagerPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class TeamSpaceAdminModule implements AdminModule {

	final private Provider<TeamSpaceManagerPanel> managerPanelanelProvider;
	
	@Inject
	public TeamSpaceAdminModule(
		Provider<TeamSpaceManagerPanel> managerPanelanelProvider
		){
		
		/* store objects */
		this.managerPanelanelProvider = managerPanelanelProvider;
	}
	
	@Override
	public ImageResource getNavigationIcon(){
		return BaseIcon.GROUP_EDIT.toImageResource();
	}
	
	@Override
	public String getNavigationText() {
		return TeamSpaceMessages.INSTANCE.clientModuleName();
	}

	@Override
	public Widget getMainWidget() {
		return managerPanelanelProvider.get();
	}
	
	@Override
	public void notifyOfSelection() {
		
	}
}