package net.datenwerke.rs.teamspace.client.teamspace;

import com.google.gwt.resources.client.ImageResource;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;

import net.datenwerke.gf.client.homepage.modules.ClientMainModuleImpl;
import net.datenwerke.rs.teamspace.client.teamspace.locale.TeamSpaceMessages;
import net.datenwerke.rs.teamspace.client.teamspace.ui.TeamSpaceMainComponent;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
@Singleton
public class TeamSpaceClientMainModule extends ClientMainModuleImpl {
	
	private final Provider<TeamSpaceMainComponent> mainComponentProvider;
	private TeamSpaceMainComponent mainComponent;
	
	@Inject
	public TeamSpaceClientMainModule(
		Provider<TeamSpaceMainComponent> mainComponentProvider
		){
		
		/* store objects */
		this.mainComponentProvider = mainComponentProvider;
		
	}
	
	@Override
	public String getModuleName() {
		return TeamSpaceMessages.INSTANCE.clientModuleName();
	}

	@Override
	public ImageResource getIcon() {
		return BaseIcon.GROUP_EDIT.toImageResource();
	}

	@Override
	public TeamSpaceMainComponent getMainWidget() {
		if(null == mainComponent)
			mainComponent = mainComponentProvider.get();
		return mainComponent;
	}

	@Override
	public void selected() {
		mainComponent.notifyOfSelection();
	}

	@Override
	public boolean isRecyclable() {
		return true;
	}
}
