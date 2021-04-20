package net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.dto.DatasinkManagerImportConfigDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.client.datasinkmanager.locale.DatasinksMessages;
import net.datenwerke.rs.core.client.datasinkmanager.provider.annotations.DatasinkTreeFolders;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;

public class DatasinkImporterMainPropertiesPanel extends ImporterMainPropertiesPanel<DatasinkManagerImportConfigDto> {

   private final Provider<UITree> treeProvider;

   protected String parentKey;
   protected String defaultDatasource;

   @Inject
   public DatasinkImporterMainPropertiesPanel(@DatasinkTreeFolders Provider<UITree> treeProvider) {

      /* store objects */
      this.treeProvider = treeProvider;

      /* init */
      initializeUI();
   }

   @Override
   public void populateConfig(DatasinkManagerImportConfigDto config) throws NotProperlyConfiguredException {
      super.populateConfig(config);

      DatasinkFolderDto parent = (DatasinkFolderDto) form.getValue(parentKey);
      config.setParent(parent);

   }

   @Override
   public void validateConfig(DatasinkManagerImportConfigDto config) throws NotProperlyConfiguredException {
      if (null == config.getParent() && !config.getConfigs().isEmpty())
         throw new NotProperlyConfiguredException(DatasinksMessages.INSTANCE.importConfigFailureNoParent());
   }

   @Override
   protected void configureForm() {
      super.configureForm();

      parentKey = form.addField(DatasinkFolderDto.class, DatasinksMessages.INSTANCE.importWhereTo(), new SFFCGenericTreeNode() {
         @Override
         public UITree getTreeForPopup() {
            return treeProvider.get();
         }
      });
   }

   @Override
   protected String getDescription() {
      return DatasinksMessages.INSTANCE.importMainPropertiesDescription();
   }

   @Override
   protected String getHeadline() {
      return DatasinksMessages.INSTANCE.importMainPropertiesHeadline();
   }

}