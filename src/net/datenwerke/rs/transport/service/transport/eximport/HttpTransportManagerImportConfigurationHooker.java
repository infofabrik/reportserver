package net.datenwerke.rs.transport.service.transport.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.exceptions.IllegalImportConfigException;
import net.datenwerke.eximport.im.ImportItemWithKeyConfig;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.client.eximport.im.dto.ImportConfigDto;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.rs.transport.client.transport.dto.AbstractTransportManagerNodeDto;
import net.datenwerke.rs.transport.client.transport.dto.TransportFolderDto;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.client.eximport.im.dto.TreeImportConfigDto;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeImportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.http.HttpImportConfigurationProviderHookImplForTrees;

public class HttpTransportManagerImportConfigurationHooker extends
      HttpImportConfigurationProviderHookImplForTrees<AbstractTransportManagerNode, AbstractTransportManagerNodeDto> {

   @Inject
   public HttpTransportManagerImportConfigurationHooker(DtoService dtoService,
         Provider<HttpImportService> httpImportServiceProvider, SecurityService securityService) {
      super(dtoService, httpImportServiceProvider, securityService);
   }

   @Override
   public boolean consumes(String id) {
      return TransportManagerImporter.IMPORTER_ID.equals(id);
   }
   
   @Override
   protected TreeNodeImportItemConfig initItemConfig(ImportTreeModel model) {
      return new ImportItemWithKeyConfig(model.getId());
   }
   
   @Override
   protected void doConfigureNodeConfig(TreeNodeImportItemConfig realConfigNode, ImportTreeModel model,
         TreeImportConfigDto<AbstractTransportManagerNodeDto> treeConfig) {
   }

   @Override
   public void validate(ImportConfigDto config) throws IllegalImportConfigException {
      TreeImportConfigDto<AbstractTransportManagerNodeDto> treeConfig = (TreeImportConfigDto<AbstractTransportManagerNodeDto>) config;

      if (null != treeConfig.getParent() && !(treeConfig.getParent() instanceof TransportFolderDto))
         throw new IllegalImportConfigException("Illegal transport import destination. Has to be a folder, but was: "
               + treeConfig.getParent().getClass());

   }

}