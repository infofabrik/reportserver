package net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.ui;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gf.client.treedb.simpleform.SFFCGenericTreeNode;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.dto.ReportManagerImportConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerTreeFoldersAndReports;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportItemConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.exceptions.NotProperlyConfiguredException;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportNodeConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.ui.ImporterMainPropertiesPanel;

public class ReportImporterMainPropertiesPanel extends ImporterMainPropertiesPanel<ReportManagerImportConfigDto> {

   private final Provider<UITree> treeProvider;

   protected String parentKey;
   private String removeKeyFieldsKey;

   @Inject
   public ReportImporterMainPropertiesPanel(@ReportManagerTreeFoldersAndReports Provider<UITree> treeProvider) {

      /* store objects */
      this.treeProvider = treeProvider;

      /* init */
      initializeUI();
   }

   public void populateConfig(ReportManagerImportConfigDto config) throws NotProperlyConfiguredException {
      super.populateConfig(config);

      AbstractReportManagerNodeDto parent = (AbstractReportManagerNodeDto) form.getValue(parentKey);
      config.setParent(parent);

      boolean removeKeys = (Boolean) form.getValue(removeKeyFieldsKey);
      config.setRemoveKey(removeKeys);
   }

   @Override
   public void validateConfig(ReportManagerImportConfigDto config) throws NotProperlyConfiguredException {
      if (null == config.getParent() && !config.getConfigs().isEmpty())
         throw new NotProperlyConfiguredException(ReportmanagerMessages.INSTANCE.importConfigFailureNoParent());

      if (null != config.getParent()) {
         if (config.getParent() instanceof ReportVariantDto)
            throw new NotProperlyConfiguredException(ReportmanagerMessages.INSTANCE.importConfigFailureVariantParent());

         if (config.getParent() instanceof ReportDto) {
            for (ImportItemConfigDto itemConfig : config.getConfigs()) {
               TreeImportNodeConfigDto treeItemConfig = (TreeImportNodeConfigDto) itemConfig;
               if (ReportFolderDto.class.getName().equals(treeItemConfig.getModel().getType())) {
                  throw new NotProperlyConfiguredException(
                        ReportmanagerMessages.INSTANCE.importConfigFailureFolderBeneathReport());
               }
            }
         }
      }
   }

   @Override
   protected void configureForm() {
      super.configureForm();

      parentKey = form.addField(AbstractReportManagerNodeDto.class, ReportmanagerMessages.INSTANCE.importWhereTo(),
            new SFFCGenericTreeNode() {
               @Override
               public UITree getTreeForPopup() {
                  return treeProvider.get();
               }
            });

      removeKeyFieldsKey = form.addField(Boolean.class, ReportmanagerMessages.INSTANCE.importRemoveKeyFieldLabel());
   }

   @Override
   protected String getDescription() {
      return ReportmanagerMessages.INSTANCE.importMainPropertiesDescription();
   }

   @Override
   protected String getHeadline() {
      return ReportmanagerMessages.INSTANCE.importMainPropertiesHeadline();
   }

}
