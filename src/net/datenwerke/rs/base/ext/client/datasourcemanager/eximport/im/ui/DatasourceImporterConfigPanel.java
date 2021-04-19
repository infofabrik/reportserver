package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.ui;

import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.dto.DatasourceManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;

import com.google.inject.Inject;

public class DatasourceImporterConfigPanel extends ImporterConfigPanel<DatasourceManagerImportConfigDto> {

	@Inject
	public DatasourceImporterConfigPanel(
		DatasourceImporterItemsPanel itemsPanel,
		DatasourceImporterMainPropertiesPanel mainPropertiesPanel) {
		super(itemsPanel, mainPropertiesPanel);
	}

	@Override
	protected DatasourceManagerImportConfigDto createConfigObject() {
		return new DatasourceManagerImportConfigDto();
	}

}
