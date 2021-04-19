package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.ui.ReportImporterConfigPanel;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class ReportManagerUIImporterHooker implements ImporterConfiguratorHook {

	private static final String SUPPORTED_EXPORTER_ID = "ReportManagerExporter";
	private static final String IMPORTER_ID = "ReportManagerImporter";
	
	private final Provider<ReportImporterConfigPanel> configPanelProvider;
	
	private ReportImporterConfigPanel configPanel;
	
	@Inject
	public ReportManagerUIImporterHooker(
		Provider<ReportImporterConfigPanel> configPanelProvider	
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
		return BaseIcon.REPORT.toImageResource();
	}

	@Override
	public String getImporterName() {
		return ReportmanagerMessages.INSTANCE.importerName();
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
