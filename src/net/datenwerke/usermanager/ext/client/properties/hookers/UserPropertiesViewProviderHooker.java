package net.datenwerke.usermanager.ext.client.properties.hookers;

import java.util.Arrays;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.usermanager.ext.client.properties.ui.UserPropertiesView;

/**
 * 
 *
 */
public class UserPropertiesViewProviderHooker implements MainPanelViewProviderHook {

	private final Provider<UserPropertiesView> propertiesViewProvider;
	
	@Inject
	public UserPropertiesViewProviderHooker(
		Provider<UserPropertiesView> propertiesViewProvider
		){

		/* store objects */
		this.propertiesViewProvider = propertiesViewProvider;
	}
	
	public List<MainPanelView> mainPanelViewProviderHook_getView(AbstractNodeDto node) {
		if(node instanceof UserDto)
			return getView();

		return null;
	}

	private List<MainPanelView> getView() {
		return Arrays.asList(new MainPanelView[]{propertiesViewProvider.get()});
	}

}
