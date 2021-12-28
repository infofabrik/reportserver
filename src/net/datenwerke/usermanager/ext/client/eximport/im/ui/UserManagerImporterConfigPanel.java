package net.datenwerke.usermanager.ext.client.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;
import net.datenwerke.usermanager.ext.client.eximport.im.dto.UserManagerImportConfigDto;


public class UserManagerImporterConfigPanel extends ImporterConfigPanel<UserManagerImportConfigDto> {

	@Inject
	public UserManagerImporterConfigPanel(
		UserManagerImporterItemsPanel itemsPanel,
		UserManagerImporterMainPropertiesPanel mainPropertiesPanel) {
		super(itemsPanel, mainPropertiesPanel);
	}

	@Override
	protected UserManagerImportConfigDto createConfigObject() {
		return new UserManagerImportConfigDto();
	}

}
