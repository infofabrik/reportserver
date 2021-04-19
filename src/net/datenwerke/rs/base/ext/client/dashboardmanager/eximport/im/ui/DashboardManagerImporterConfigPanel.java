package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.im.dto.DashboardManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;


public class DashboardManagerImporterConfigPanel extends ImporterConfigPanel<DashboardManagerImportConfigDto> {

	@Inject
	public DashboardManagerImporterConfigPanel(
		DashboardManagerImporterItemsPanel itemsPanel,
		DashboardManagerImporterMainPropertiesPanel mainPropertiesPanel) {
		super(itemsPanel, mainPropertiesPanel);
	}

	@Override
	protected DashboardManagerImportConfigDto createConfigObject() {
		return new DashboardManagerImportConfigDto();
	}

}
