package net.datenwerke.usermanager.ext.server.eximport;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.eximport.service.eximport.ex.http.HttpExportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.usermanager.entities.AbstractUserManagerNode;
import net.datenwerke.treedb.ext.service.eximport.helper.TreeNodeExportHelperService;
import net.datenwerke.usermanager.ext.client.eximport.ex.rpc.UserManagerExportRpcService;

/**
 * 
 *
 */
@Singleton
public class UserManagerExportRpcServiceImpl extends SecuredRemoteServiceServlet
      implements UserManagerExportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = -9053878597686343257L;

   private final DtoService dtoService;
   private final Provider<HttpExportService> httpExportServiceProvider;

   private TreeNodeExportHelperService exportHelper;

   @Inject
   public UserManagerExportRpcServiceImpl(
         DtoService dtoService, 
         Provider<HttpExportService> httpExportServiceProvider,
         TreeNodeExportHelperService exportHelper
         ) {

      /* store objects */
      this.dtoService = dtoService;
      this.httpExportServiceProvider = httpExportServiceProvider;
      this.exportHelper = exportHelper;
   }

   @Override
   @SecurityChecked(genericTargetVerification = {
         @GenericTargetVerification(target = ExportSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public void quickExport(AbstractUserManagerNodeDto nodeDto) throws ServerCallFailedException {
      AbstractUserManagerNode node = (AbstractUserManagerNode) dtoService.loadPoso(nodeDto);

      String exportXML = exportHelper.export(node, true, "User-Export");

      httpExportServiceProvider.get().storeExport(exportXML, node.getName());
   }

   @Override
   @Transactional(rollbackOn = { Exception.class })
   public String loadResult() throws ServerCallFailedException {
      return httpExportServiceProvider.get().getAndRemoveStoredExport();
   }

}
