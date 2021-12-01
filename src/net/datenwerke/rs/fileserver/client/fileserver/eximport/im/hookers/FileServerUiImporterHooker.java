package net.datenwerke.rs.fileserver.client.fileserver.eximport.im.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.ui.FileServerImporterConfigPanel;
import net.datenwerke.rs.fileserver.client.fileserver.locale.FileServerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * 
 *
 */
public class FileServerUiImporterHooker implements ImporterConfiguratorHook {

	private static final String SUPPORTED_EXPORTER_ID = "FileServerExporter";
	private static final String IMPORTER_ID = "FileServerImporter";
	
	private final Provider<FileServerImporterConfigPanel> configPanelProvider;
	
	private FileServerImporterConfigPanel configPanel;
	
	@Inject
	public FileServerUiImporterHooker(
		Provider<FileServerImporterConfigPanel> configPanelProvider	
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
		return BaseIcon.IMPORT.toImageResource();
	}

	@Override
	public String getImporterName() {
		return FileServerMessages.INSTANCE.adminLabel();
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
