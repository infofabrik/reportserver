package net.datenwerke.rs.eximport.client.eximport.im;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.eximport.client.eximport.locale.ExImportMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ImportAdminModule implements AdminModule{
	
	private final Provider<ImportMainPanel> importMainPanelProvider;
	private ImportMainPanel importMainPanel;

	@Inject
	public ImportAdminModule(
		Provider<ImportMainPanel> importMainPanelProvider) {
		
		/* store objects */
		this.importMainPanelProvider = importMainPanelProvider;
	}
	
	@Override
	public String getNavigationText() {
		return ExImportMessages.INSTANCE.importAdminModuleText();
	}

	@Override
	public ImageResource getNavigationIcon() {
		return BaseIcon.DOWNLOAD.toImageResource();
	}

	@Override
	public Widget getMainWidget() {
		if(null == importMainPanel)
			importMainPanel = importMainPanelProvider.get();
		return importMainPanel;
	}

	@Override
	public void notifyOfSelection() {
	}

}
