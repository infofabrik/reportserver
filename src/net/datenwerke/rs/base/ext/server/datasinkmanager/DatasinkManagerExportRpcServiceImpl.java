package net.datenwerke.rs.base.ext.server.datasinkmanager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.datasinkmanager.eximport.ex.rpc.DatasinkManagerExportRpcService;
import net.datenwerke.rs.base.ext.service.datasinkmanager.eximport.DatasinkManagerExporter;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperServiceImpl;

@Singleton
public class DatasinkManagerExportRpcServiceImpl extends SecuredRemoteServiceServlet
      implements DatasinkManagerExportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private final DtoService dtoService;
   private final Provider<HttpExportService> httpExportServiceProvider;
   private final TreeNodeExportHelperServiceImpl exportHelper;

   @Inject
   public DatasinkManagerExportRpcServiceImpl(DtoService dtoService,
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
   public void quickExport(AbstractDatasinkManagerNodeDto nodeDto) throws ServerCallFailedException {
      AbstractDatasinkManagerNode node = (AbstractDatasinkManagerNode) dtoService.loadPoso(nodeDto);

      String exportXML = exportHelper.export(node, true, DatasinkManagerExporter.EXPORTER_NAME, false, false);

      httpExportServiceProvider.get().storeExport(exportXML, node.getName());
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public String loadResult() throws ServerCallFailedException {
      return httpExportServiceProvider.get().getAndRemoveStoredExport();
   }

}