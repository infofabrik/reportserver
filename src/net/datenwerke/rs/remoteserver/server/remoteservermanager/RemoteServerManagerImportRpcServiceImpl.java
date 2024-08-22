package net.datenwerke.rs.remoteserver.server.remoteservermanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.eximport.service.genrights.ImportSecurityTarget;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.eximport.im.rpc.RemoteServerManagerImportRpcService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.eximport.RemoteServerManagerExporter;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportService;

@Singleton
public class RemoteServerManagerImportRpcServiceImpl extends SecuredRemoteServiceServlet
      implements RemoteServerManagerImportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 38635573532715367L;

   private final Provider<HttpTreeImportService> httpImportServiceProvider;

   @Inject
   public RemoteServerManagerImportRpcServiceImpl(Provider<HttpTreeImportService> httpImportServiceProvider) {

      /* store objects */
      this.httpImportServiceProvider = httpImportServiceProvider;
   }

   @Override
   @SecurityChecked(genericTargetVerification = {
         @GenericTargetVerification(target = ImportSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public List<ImportTreeModel> loadTree() throws ServerCallFailedException {
      HttpTreeImportService httpImportService = httpImportServiceProvider.get();

      List<ImportTreeModel> tree = httpImportService.loadTreeDto(RemoteServerManagerExporter.class);

      return tree;
   }

}