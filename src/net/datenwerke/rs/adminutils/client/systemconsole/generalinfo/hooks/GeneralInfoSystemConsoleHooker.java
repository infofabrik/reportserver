package net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.hooks;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.client.systemconsole.generalinfo.ui.GeneralInfoPanel;
import net.datenwerke.rs.adminutils.client.systemconsole.hooks.SystemConsoleViewDomainHook;
import net.datenwerke.rs.adminutils.client.systemconsole.locale.SystemConsoleMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class GeneralInfoSystemConsoleHooker implements
		SystemConsoleViewDomainHook {
	
	private final Provider<GeneralInfoPanel> mainWidgetProvider;
	
	@Inject
	public GeneralInfoSystemConsoleHooker(
			Provider<GeneralInfoPanel> mainWidgetProvider) {
		this.mainWidgetProvider = mainWidgetProvider;
	}
	
	@Override
	public String getNavigationText() {
		return SystemConsoleMessages.INSTANCE.generalInfo();
	}

	@Override
	public ImageResource getNavigationIcon() {
		return BaseIcon.INFO_CIRCLE.toImageResource();
	}

	@Override
	public Widget getMainWidget() {
		return mainWidgetProvider.get();
	}

}
