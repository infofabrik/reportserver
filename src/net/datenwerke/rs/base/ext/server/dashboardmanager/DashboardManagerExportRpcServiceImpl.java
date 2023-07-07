package net.datenwerke.rs.base.ext.server.dashboardmanager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.rpc.DashboardManagerExportRpcService;
import net.datenwerke.rs.base.ext.service.dashboardmanager.eximport.DashboardManagerExporter;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.AbstractDashboardManagerNode;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperServiceImpl;

/**
 * 
 *
 */
@Singleton
public class DashboardManagerExportRpcServiceImpl extends SecuredRemoteServiceServlet
      implements DashboardManagerExportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 1551324209469664301L;

   private final DtoService dtoService;
   private final Provider<HttpExportService> httpExportServiceProvider;
   private final TreeNodeExportHelperServiceImpl exportHelper;

   @Inject
   public DashboardManagerExportRpcServiceImpl(
         DtoService dtoService,
         Provider<HttpExportService> httpExportServiceProvider, 
         TreeNodeExportHelperServiceImpl exportHelper
         ) {

      /* store objects */
      this.dtoService = dtoService;
      this.httpExportServiceProvider = httpExportServiceProvider;
      this.exportHelper = exportHelper;
   }

   @Override
   @SecurityChecked(
         genericTargetVerification = {
               @GenericTargetVerification(
                     target = ExportSecurityTarget.class, 
                     verify = @RightsVerification(
                           rights = Execute.class
                     )
               ) 
         }
   )
   @Transactional(rollbackOn = { Exception.class })
   public void quickExport(AbstractDashboardManagerNodeDto nodeDto) throws ServerCallFailedException {
      AbstractDashboardManagerNode node = (AbstractDashboardManagerNode) dtoService.loadPoso(nodeDto);

      String exportXML = exportHelper.export(node, true, DashboardManagerExporter.EXPORTER_NAME, false);

      httpExportServiceProvider.get().storeExport(exportXML, node.getName());
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public String loadResult() throws ServerCallFailedException {
      return httpExportServiceProvider.get().getAndRemoveStoredExport();
   }

}
