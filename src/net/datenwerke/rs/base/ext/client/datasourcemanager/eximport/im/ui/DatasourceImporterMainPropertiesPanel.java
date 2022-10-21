package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.im.dto.DatasourceManagerImportConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.client.datasourcemanager.helper.forms.simpleform.SFFCDatasourceSuppressConfig;
import net.datenwerke.rs.core.client.datasourcemanager.locale.DatasourcesMessages;
import net.datenwerke.rs.core.client.datasourcemanager.provider.annotations.DatasourceTreeFolders;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;

public class DatasourceImporterMainPropertiesPanel
      extends ImporterMainPropertiesPanel<DatasourceManagerImportConfigDto> {

   private final Provider<UITree> treeProvider;

   protected String parentKey;
   protected String defaultDatasource;

   @Inject
   public DatasourceImporterMainPropertiesPanel(@DatasourceTreeFolders Provider<UITree> treeProvider) {

      /* store objects */
      this.treeProvider = treeProvider;

      /* init */
      initializeUI();
   }

   @Override
   public void populateConfig(DatasourceManagerImportConfigDto config) throws NotProperlyConfiguredException {
      super.populateConfig(config);

      DatasourceFolderDto parent = (DatasourceFolderDto) form.getValue(parentKey);
      config.setParent(parent);

      DatasourceContainerDto dsContainer = (DatasourceContainerDto) form.getValue(defaultDatasource);
      if (null != dsContainer)
         config.setDefaultDatasource(dsContainer.getDatasource());
   }

   @Override
   public void validateConfig(DatasourceManagerImportConfigDto config) throws NotProperlyConfiguredException {
      if (null == config.getParent() && !config.getConfigs().isEmpty())
         throw new NotProperlyConfiguredException(DatasourcesMessages.INSTANCE.importConfigFailureNoParent());
   }

   @Override
   protected void configureForm() {
      super.configureForm();

      parentKey = form.addField(DatasourceFolderDto.class, DatasourcesMessages.INSTANCE.importWhereTo(),
            new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return treeProvider.get();
               }
            });

      defaultDatasource = form.addField(DatasourceContainerDto.class, DatasourcesMessages.INSTANCE.defaultDatasource(),
            new SFFCDatasourceSuppressConfig() {
            });
   }

   @Override
   protected String getDescription() {
      return DatasourcesMessages.INSTANCE.importMainPropertiesDescription();
   }

   @Override
   protected String getHeadline() {
      return DatasourcesMessages.INSTANCE.importMainPropertiesHeadline();
   }

}
