package net.datenwerke.rs.base.ext.service.reportmanager.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.im.dto.ReportManagerImportConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.entities.AbstractReportManagerNode;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportItemConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportNodeConfigDto;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.http.HttpImportConfigurationProviderHookImplForTrees;

public class HttpReportManagerImportConfigurationHooker
      extends HttpImportConfigurationProviderHookImplForTrees<AbstractReportManagerNode, AbstractReportManagerNodeDto> {

   private static final String UUID_FIELD = "uuid";

   @Inject
   public HttpReportManagerImportConfigurationHooker(DtoService dtoService,
         Provider<HttpImportService> httpImportServiceProvider, SecurityService securityService) {
      super(dtoService, httpImportServiceProvider, securityService);

   }

   @Override
   public boolean consumes(String id) {
      return ReportManagerImporter.IMPORTER_ID.equals(id);
   }

   @Override
   protected TreeNodeImportItemConfig initItemConfig(ImportTreeModel model) {
      return new ImportReportItemConfig(model.getId());
   }

   @Override
   protected void doConfigureNodeConfig(TreeNodeImportItemConfig realConfigNode, ImportTreeModel model,
         TreeImportConfigDto<AbstractReportManagerNodeDto> treeConfig) {
      ReportManagerImportConfigDto rmConfig = (ReportManagerImportConfigDto) treeConfig;

      if (rmConfig.isRemoveKey())
         ((ImportReportItemConfig) realConfigNode).setCleanKeys(true);

      /* always ignore uuids */
      realConfigNode.addIgnoredField(UUID_FIELD);
   }

   @Override
   public void validate(ImportConfigDto config) throws IllegalImportConfigException {
      TreeImportConfigDto<AbstractReportManagerNodeDto> treeConfig = (TreeImportConfigDto<AbstractReportManagerNodeDto>) config;

      AbstractReportManagerNodeDto parent = treeConfig.getParent();
      if (null != parent) {
         if (parent instanceof ReportVariantDto)
            throw new IllegalImportConfigException("tried to use report variant as base for import.");

         if (parent instanceof ReportDto) {
            for (ImportItemConfigDto itemConfig : treeConfig.getConfigs()) {
               TreeImportNodeConfigDto treeNodeConfig = (TreeImportNodeConfigDto) itemConfig;

               String type = treeNodeConfig.getModel().getType();
               try {
                  Class<?> typeC = Class.forName(type);
                  if (!ReportVariantDto.class.isAssignableFrom(typeC)) {
                     throw new IllegalImportConfigException("tried to import a non report variant beneath a report");
                  }
               } catch (ClassNotFoundException e) {
                  throw new IllegalImportConfigException(e);
               }
            }
         }
      }
   }

   @Override
   protected boolean validateParent(ImportTreeModel model, AbstractReportManagerNode parent) {
      if (parent instanceof ReportFolder) {
         String type = model.getType();
         try {
            Class<?> typeC = Class.forName(type);
            if (ReportVariantDto.class.isAssignableFrom(typeC)) {
               throw new IllegalImportConfigException("tried to importa variant beneath a folder");
            }
         } catch (ClassNotFoundException e) {
            throw new IllegalImportConfigException(e);
         }
      }
      return true;
   }
}
