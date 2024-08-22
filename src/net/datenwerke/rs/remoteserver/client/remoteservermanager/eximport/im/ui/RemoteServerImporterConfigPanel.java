package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.ui;

import com.google.inject.Inject;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.dto.RemoteServerManagerImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterConfigPanel;

public class RemoteServerImporterConfigPanel extends ImporterConfigPanel<RemoteServerManagerImportConfigDto> {

   @Inject
   public RemoteServerImporterConfigPanel(RemoteServerImporterItemsPanel itemsPanel,
         RemoteServerImporterMainPropertiesPanel mainPropertiesPanel) {
      super(itemsPanel, mainPropertiesPanel);
   }

   @Override
   protected RemoteServerManagerImportConfigDto createConfigObject() {
      return new RemoteServerManagerImportConfigDto();
   }

}