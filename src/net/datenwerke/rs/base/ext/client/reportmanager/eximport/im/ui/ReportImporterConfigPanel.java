package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.dto.ReportManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;

public class ReportImporterConfigPanel extends ImporterConfigPanel<ReportManagerImportConfigDto> {

   @Inject
   public ReportImporterConfigPanel(ReportImporterItemsPanel itemsPanel,
         ReportImporterMainPropertiesPanel mainPropertiesPanel) {
      super(itemsPanel, mainPropertiesPanel);
   }

   @Override
   protected ReportManagerImportConfigDto createConfigObject() {
      return new ReportManagerImportConfigDto();
   }

}
