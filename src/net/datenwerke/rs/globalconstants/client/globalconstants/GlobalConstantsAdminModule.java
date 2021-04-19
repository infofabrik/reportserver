package net.datenwerke.rs.globalconstants.client.globalconstants;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.globalconstants.client.globalconstants.locale.GlobalConstantsMessages;
import net.datenwerke.rs.globalconstants.client.globalconstants.ui.GlobalConstantsAdminPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class GlobalConstantsAdminModule implements AdminModule {

	private final Provider<GlobalConstantsAdminPanel> mainWidgetProvider;
	
	@Inject
	public GlobalConstantsAdminModule(Provider<GlobalConstantsAdminPanel> mainWidgetProvider){
		this.mainWidgetProvider = mainWidgetProvider;
	}
	
	@Override
	public Widget getMainWidget() {
		return mainWidgetProvider.get();
	}

	@Override
	public ImageResource getNavigationIcon() {
		return BaseIcon.EDIT.toImageResource();
	}

	@Override
	public String getNavigationText() {
		return GlobalConstantsMessages.INSTANCE.viewNavigationTitle();
	}

	@Override
	public void notifyOfSelection() {
		mainWidgetProvider.get().notifyOfSelection();
	}
}
