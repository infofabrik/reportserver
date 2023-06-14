package net.datenwerke.rs.transport.server.transport;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.eximport.service.genrights.ImportSecurityTarget;
import net.datenwerke.rs.transport.client.transport.eximport.im.rpc.TransportManagerImportRpcService;
import net.datenwerke.rs.transport.service.transport.eximport.TransportManagerExporter;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;
import net.datenwerke.security.service.security.annotation.GenericTargetVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.ext.client.eximport.im.dto.ImportTreeModel;
import net.datenwerke.treedb.ext.service.eximport.http.HttpTreeImportService;

@Singleton
public class TransportManagerImportRpcServiceImpl extends SecuredRemoteServiceServlet
      implements TransportManagerImportRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 38635573532715367L;

   private final Provider<HttpTreeImportService> httpImportServiceProvider;

   @Inject
   public TransportManagerImportRpcServiceImpl(Provider<HttpTreeImportService> httpImportServiceProvider) {

      /* store objects */
      this.httpImportServiceProvider = httpImportServiceProvider;
   }

   @Override
   @SecurityChecked(genericTargetVerification = {
         @GenericTargetVerification(target = ImportSecurityTarget.class, verify = @RightsVerification(rights = Execute.class)) })
   @Transactional(rollbackOn = { Exception.class })
   public List<ImportTreeModel> loadTree() throws ServerCallFailedException {
      HttpTreeImportService httpImportService = httpImportServiceProvider.get();

      List<ImportTreeModel> tree = httpImportService.loadTreeDto(TransportManagerExporter.class);

      return tree;
   }

}