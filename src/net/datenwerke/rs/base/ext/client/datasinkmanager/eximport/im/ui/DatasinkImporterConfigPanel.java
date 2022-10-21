package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.dto.DatasinkManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;

public class DatasinkImporterConfigPanel extends ImporterConfigPanel<DatasinkManagerImportConfigDto> {

   @Inject
   public DatasinkImporterConfigPanel(DatasinkImporterItemsPanel itemsPanel,
         DatasinkImporterMainPropertiesPanel mainPropertiesPanel) {
      super(itemsPanel, mainPropertiesPanel);
   }

   @Override
   protected DatasinkManagerImportConfigDto createConfigObject() {
      return new DatasinkManagerImportConfigDto();
   }

}