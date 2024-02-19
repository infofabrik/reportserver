package net.datenwerke.rs.base.ext.service.datasinkmanager.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.im.dto.DatasinkManagerImportConfigDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkFolderDto;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.http.HttpImportConfigurationProviderHookImplForTrees;

public class HttpDatasinkManagerImportConfigurationHooker extends
      HttpImportConfigurationProviderHookImplForTrees<AbstractDatasinkManagerNode, AbstractDatasinkManagerNodeDto> {

   @Inject
   public HttpDatasinkManagerImportConfigurationHooker(DtoService dtoService,
         Provider<HttpImportService> httpImportServiceProvider, SecurityService securityService) {
      super(dtoService, httpImportServiceProvider, securityService);
   }

   @Override
   public boolean consumes(String id) {
      return DatasinkManagerImporter.IMPORTER_ID.equals(id);
   }
   
   @Override
   protected TreeNodeImportItemConfig initItemConfig(ImportTreeModel model) {
      return new ImportItemWithKeyConfig(model.getId());
   }
   
   @Override
   protected void doConfigureNodeConfig(TreeNodeImportItemConfig realConfigNode, ImportTreeModel model,
         TreeImportConfigDto<AbstractDatasinkManagerNodeDto> treeConfig) {
      DatasinkManagerImportConfigDto rmConfig = (DatasinkManagerImportConfigDto) treeConfig;

      if (rmConfig.isGenerateRandomKey())
         ((ImportItemWithKeyConfig) realConfigNode).setCleanKeys(true);
   }


   @Override
   public void validate(ImportConfigDto config) throws IllegalImportConfigException {
      TreeImportConfigDto<AbstractDatasinkManagerNodeDto> treeConfig = (TreeImportConfigDto<AbstractDatasinkManagerNodeDto>) config;

      if (null != treeConfig.getParent() && !(treeConfig.getParent() instanceof DatasinkFolderDto))
         throw new IllegalImportConfigException("Illegal datasink import destination. Has to be a folder, but was: "
               + treeConfig.getParent().getClass());

   }

}