package net.datenwerke.rs.fileserver.service.fileserver.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.http.HttpImportConfigurationProviderHookImplForTrees;

public class HttpFileServerImportConfigurationHooker
      extends HttpImportConfigurationProviderHookImplForTrees<AbstractFileServerNode, AbstractFileServerNodeDto> {

   @Inject
   public HttpFileServerImportConfigurationHooker(DtoService dtoService,
         Provider<HttpImportService> httpImportServiceProvider, SecurityService securityService) {
      super(dtoService, httpImportServiceProvider, securityService);

   }

   @Override
   public boolean consumes(String id) {
      return FileServerImporter.IMPORTER_ID.equals(id);
   }

   @Override
   protected void doConfigureNodeConfig(TreeNodeImportItemConfig realConfigNode, ImportTreeModel model,
         TreeImportConfigDto<AbstractFileServerNodeDto> treeConfig) {
   }

   @Override
   public void validate(ImportConfigDto config) throws IllegalImportConfigException {
      TreeImportConfigDto<AbstractFileServerNodeDto> treeConfig = (TreeImportConfigDto<AbstractFileServerNodeDto>) config;
      if (null != treeConfig.getParent() && !(treeConfig.getParent() instanceof FileServerFolderDto))
         throw new IllegalImportConfigException(
               "Illegal file import destination. Has to be a folder, but was: " + treeConfig.getParent().getClass());
   }

}
