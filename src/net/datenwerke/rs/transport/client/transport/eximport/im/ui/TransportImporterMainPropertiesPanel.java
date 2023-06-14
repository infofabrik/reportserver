package net.datenwerke.rs.transport.client.transport.eximport.im.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.client.transport.eximport.im.dto.TransportManagerImportConfigDto;
import net.datenwerke.rs.transport.client.transport.locale.TransportMessages;
import net.datenwerke.rs.transport.client.transport.provider.annotations.TransportTreeFolders;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;

public class TransportImporterMainPropertiesPanel extends ImporterMainPropertiesPanel<TransportManagerImportConfigDto> {

   private final Provider<UITree> treeProvider;

   protected String parentKey;

   @Inject
   public TransportImporterMainPropertiesPanel(@TransportTreeFolders Provider<UITree> treeProvider) {

      /* store objects */
      this.treeProvider = treeProvider;

      /* init */
      initializeUI();
   }

   @Override
   public void populateConfig(TransportManagerImportConfigDto config) throws NotProperlyConfiguredException {
      super.populateConfig(config);

      TransportFolderDto parent = (TransportFolderDto) form.getValue(parentKey);
      config.setParent(parent);
   }

   @Override
   public void validateConfig(TransportManagerImportConfigDto config) throws NotProperlyConfiguredException {
      if (null == config.getParent() && !config.getConfigs().isEmpty())
         throw new NotProperlyConfiguredException(TransportMessages.INSTANCE.importConfigFailureNoParent());
   }

   @Override
   protected void configureForm() {
      super.configureForm();

      parentKey = form.addField(TransportFolderDto.class, TransportMessages.INSTANCE.importWhereTo(),
            new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return treeProvider.get();
               }
            });
   }

   @Override
   protected String getDescription() {
      return TransportMessages.INSTANCE.importMainPropertiesDescription();
   }

   @Override
   protected String getHeadline() {
      return TransportMessages.INSTANCE.importMainPropertiesHeadline();
   }

}