package net.datenwerke.usermanager.ext.client.eximport.im.hookers;

import java.util.Collection;
import java.util.Collections;

import net.datenwerke.gxtdto.client.resources.BaseResources;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.eximport.client.eximport.im.hooks.ImporterConfiguratorHook;
import net.datenwerke.rs.eximport.client.eximport.im.ui.ImportMainPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.ext.client.usermanager.locale.UsermanagerMessages;
import net.datenwerke.usermanager.ext.client.eximport.im.ui.UserManagerImporterConfigPanel;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.inject.Provider;


/**
 * 
 *
 */
public class UserManagerUIImporterHooker implements ImporterConfiguratorHook {

	private static final String SUPPORTED_EXPORTER_ID = "UserManagerExporter";
	private static final String IMPORTER_ID = "UserManagerImporter";
	
	private final Provider<UserManagerImporterConfigPanel> configPanelProvider;
	
	private UserManagerImporterConfigPanel configPanel;
	
	@Inject
	public UserManagerUIImporterHooker(
		Provider<UserManagerImporterConfigPanel> configPanelProvider	
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
		return UsermanagerMessages.INSTANCE.importerName();
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
