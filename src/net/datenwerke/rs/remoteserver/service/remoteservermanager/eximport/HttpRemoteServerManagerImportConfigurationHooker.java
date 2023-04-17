package net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerFolderDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.dto.RemoteServerManagerImportConfigDto;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.http.HttpImportConfigurationProviderHookImplForTrees;

public class HttpRemoteServerManagerImportConfigurationHooker extends
      HttpImportConfigurationProviderHookImplForTrees<AbstractRemoteServerManagerNode, AbstractRemoteServerManagerNodeDto> {

   @Inject
   public HttpRemoteServerManagerImportConfigurationHooker(DtoService dtoService,
         Provider<HttpImportService> httpImportServiceProvider, SecurityService securityService) {
      super(dtoService, httpImportServiceProvider, securityService);
   }

   @Override
   public boolean consumes(String id) {
      return RemoteServerManagerImporter.IMPORTER_ID.equals(id);
   }
   
   @Override
   protected TreeNodeImportItemConfig initItemConfig(ImportTreeModel model) {
      return new ImportItemWithKeyConfig(model.getId());
   }
   
   @Override
   protected void doConfigureNodeConfig(TreeNodeImportItemConfig realConfigNode, ImportTreeModel model,
         TreeImportConfigDto<AbstractRemoteServerManagerNodeDto> treeConfig) {
      RemoteServerManagerImportConfigDto rmConfig = (RemoteServerManagerImportConfigDto) treeConfig;

      if (rmConfig.isRemoveKey())
         ((ImportItemWithKeyConfig) realConfigNode).setCleanKeys(true);

   }

   @Override
   public void validate(ImportConfigDto config) throws IllegalImportConfigException {
      TreeImportConfigDto<AbstractRemoteServerManagerNodeDto> treeConfig = (TreeImportConfigDto<AbstractRemoteServerManagerNodeDto>) config;

      if (null != treeConfig.getParent() && !(treeConfig.getParent() instanceof RemoteServerFolderDto))
         throw new IllegalImportConfigException("Illegal remote server import destination. Has to be a folder, but was: "
               + treeConfig.getParent().getClass());

   }

}