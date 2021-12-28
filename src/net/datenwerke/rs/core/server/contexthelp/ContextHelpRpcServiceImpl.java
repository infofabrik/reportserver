package net.datenwerke.rs.core.server.contexthelp;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.rs.core.client.contexthelp.dto.ContextHelpInfo;
import net.datenwerke.rs.core.client.contexthelp.rpc.ContextHelpRpcService;
import net.datenwerke.rs.core.service.contexthelp.ContextHelpService;
import net.datenwerke.security.server.SecuredRemoteServiceServlet;

@Singleton
public class ContextHelpRpcServiceImpl extends SecuredRemoteServiceServlet implements ContextHelpRpcService {

   /**
    * 
    */
   private static final long serialVersionUID = 8970533441561658684L;

   private final ContextHelpService contextHelpService;

   @Inject
   public ContextHelpRpcServiceImpl(ContextHelpService contextHelpService) {
      super();
      this.contextHelpService = contextHelpService;
   }

   @Override
   public String getContextHelp(ContextHelpInfo info) throws ServerCallFailedException {
      return contextHelpService.getContextHelp(info);
   }

}
