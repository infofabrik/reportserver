package net.datenwerke.rs.transport.client.transport.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.client.transport.eximport.im.dto.TransportManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;

public class TransportImporterConfigPanel extends ImporterConfigPanel<TransportManagerImportConfigDto> {

   @Inject
   public TransportImporterConfigPanel(TransportImporterItemsPanel itemsPanel,
         TransportImporterMainPropertiesPanel mainPropertiesPanel) {
      super(itemsPanel, mainPropertiesPanel);
   }

   @Override
   protected TransportManagerImportConfigDto createConfigObject() {
      return new TransportManagerImportConfigDto();
   }

}