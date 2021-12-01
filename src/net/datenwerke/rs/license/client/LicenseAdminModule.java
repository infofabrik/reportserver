package net.datenwerke.rs.license.client;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.license.client.locale.LicenseMessages;
import net.datenwerke.rs.license.client.ui.LicenseAdminPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class LicenseAdminModule implements AdminModule {

	private final Provider<LicenseAdminPanel> mainWidgetProvider;
	
	@Inject
	public LicenseAdminModule(Provider<LicenseAdminPanel> mainWidgetProvider){
		this.mainWidgetProvider = mainWidgetProvider;
	}
	
	@Override
	public Widget getMainWidget() {
		return mainWidgetProvider.get();
	}

	@Override
	public ImageResource getNavigationIcon() {
		return BaseIcon.CERTIFICATE.toImageResource();
	}

	@Override
	public String getNavigationText() {
		return LicenseMessages.INSTANCE.viewNavigationTitle();
	}

	@Override
	public void notifyOfSelection() {
		mainWidgetProvider.get().notifyOfSelection();
	}
}
