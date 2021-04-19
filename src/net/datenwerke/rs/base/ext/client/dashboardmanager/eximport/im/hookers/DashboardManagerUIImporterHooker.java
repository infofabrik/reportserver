package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.im.hookers;

import java.util.Collection;
import java.util.Collections;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.im.ui.DashboardManagerImporterConfigPanel;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;


/**
 * 
 *
 */
public class DashboardManagerUIImporterHooker implements ImporterConfiguratorHook {

	private static final String SUPPORTED_EXPORTER_ID = "DashboardManagerExporter";
	private static final String IMPORTER_ID = "DashboardManagerImporter";
	
	private final Provider<DashboardManagerImporterConfigPanel> configPanelProvider;
	
	private DashboardManagerImporterConfigPanel configPanel;
	
	@Inject
	public DashboardManagerUIImporterHooker(
		Provider<DashboardManagerImporterConfigPanel> configPanelProvider	
		){
	
		/* store objects */
		this.configPanelProvider = configPanelProvider;
	}
	

	@Override
	public String getImporterId() {
		return IMPORTER_ID;
	}
	
	@Override
	public ImageResource getImporterIcon() {
		return BaseIcon.DASHBOARD.toImageResource();
	}

	@Override
	public String getImporterName() {
		return DashboardMessages.INSTANCE.importerName();
	}

	@Override
	public Collection<String> getSupportedExporters() {
		return Collections.singletonList(SUPPORTED_EXPORTER_ID);
	}

	@Override
	public Widget initConfigPanel(ImportMainPanel importMainPanel) {
		configPanel = configPanelProvider.get();
		return configPanel;
	}

	@Override
	public ImportConfigDto getConfiguration() throws NotProperlyConfiguredException {
		if(null == configPanel)
			return null;
		return configPanel.getConfiguration();
	}

	
	@Override
	public void reset() {
		if(null != configPanel)
			configPanel.resetConfig();

	}

}
