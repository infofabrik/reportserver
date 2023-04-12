package net.datenwerke.rs.remoteserver.server.remoteservermanager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.eximport.ex.ExportConfig;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.AbstractRemoteServerManagerNodeDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.ex.rpc.RemoteServerManagerExportRpcService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerExporter;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.service.eximport.TreeNodeExportItemConfig;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperServiceImpl;

@Singleton
public class RemoteServerManagerExportRpcServiceImpl extends SecuredRemoteServiceServlet
      implements RemoteServerManagerExportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private final DtoService dtoService;
   private final Provider<HttpExportService> httpExportServiceProvider;
   private final TreeNodeExportHelperServiceImpl exportHelper;

   @Inject
   public RemoteServerManagerExportRpcServiceImpl(DtoService dtoService,
         Provider<HttpExportService> httpExportServiceProvider, TreeNodeExportHelperServiceImpl exportHelper) {

      /* store objects */
      this.dtoService = dtoService;
      this.httpExportServiceProvider = httpExportServiceProvider;
      this.exportHelper = exportHelper;
   }

   @Override
   @SecurityChecked(genericTargetVerification = {
         @GenericTargetVerification(target = ExportSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public void quickExport(AbstractRemoteServerManagerNodeDto nodeDto) throws ServerCallFailedException {
      AbstractRemoteServerManagerNode node = (AbstractRemoteServerManagerNode) dtoService.loadPoso(nodeDto);

      String exportXML = exportHelper.export(node, true, RemoteServerManagerExporter.EXPORTER_NAME);

      httpExportServiceProvider.get().storeExport(exportXML, node.getName());
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public String loadResult() throws ServerCallFailedException {
      return httpExportServiceProvider.get().getAndRemoveStoredExport();
   }

}