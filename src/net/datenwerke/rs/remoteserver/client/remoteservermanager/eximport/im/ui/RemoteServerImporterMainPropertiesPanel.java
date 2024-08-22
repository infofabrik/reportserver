package net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.dto.RemoteServerManagerImportConfigDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.locale.RemoteServerMessages;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.provider.annotations.RemoteServerTreeFolders;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;

public class RemoteServerImporterMainPropertiesPanel extends ImporterMainPropertiesPanel<RemoteServerManagerImportConfigDto> {

   private final Provider<UITree> treeProvider;

   protected String parentKey;
   private String createRandomKeyFieldKey;

   @Inject
   public RemoteServerImporterMainPropertiesPanel(@RemoteServerTreeFolders Provider<UITree> treeProvider) {

      /* store objects */
      this.treeProvider = treeProvider;

      /* init */
      initializeUI();
   }

   @Override
   public void populateConfig(RemoteServerManagerImportConfigDto config) throws NotProperlyConfiguredException {
      super.populateConfig(config);

      RemoteServerFolderDto parent = (RemoteServerFolderDto) form.getValue(parentKey);
      config.setParent(parent);

      boolean createRandomKey = (Boolean) form.getValue(createRandomKeyFieldKey);
      config.setGenerateRandomKey(createRandomKey);
   }

   @Override
   public void validateConfig(RemoteServerManagerImportConfigDto config) throws NotProperlyConfiguredException {
      if (null == config.getParent() && !config.getConfigs().isEmpty())
         throw new NotProperlyConfiguredException(RemoteServerMessages.INSTANCE.importConfigFailureNoParent());
   }

   @Override
   protected void configureForm() {
      super.configureForm();

      parentKey = form.addField(RemoteServerFolderDto.class, RemoteServerMessages.INSTANCE.importWhereTo(),
            new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return treeProvider.get();
               }
            });
      
      createRandomKeyFieldKey = form.addField(Boolean.class, BaseMessages.INSTANCE.createRandomKey());
   }

   @Override
   protected String getDescription() {
      return RemoteServerMessages.INSTANCE.importMainPropertiesDescription();
   }

   @Override
   protected String getHeadline() {
      return RemoteServerMessages.INSTANCE.importMainPropertiesHeadline();
   }

}