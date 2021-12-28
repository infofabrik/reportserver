package net.datenwerke.rs.fileserver.client.fileserver.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.client.fileserver.eximport.im.dto.FileServerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;


public class FileServerImporterConfigPanel extends ImporterConfigPanel<FileServerImportConfigDto> {

	@Inject
	public FileServerImporterConfigPanel(
		FileServerImporterItemsPanel itemsPanel,
		FileServerImporterMainPropertiesPanel mainPropertiesPanel
		) {
		super(itemsPanel, mainPropertiesPanel);
	}

	@Override
	protected FileServerImportConfigDto createConfigObject() {
		return new FileServerImportConfigDto();
	}

}
