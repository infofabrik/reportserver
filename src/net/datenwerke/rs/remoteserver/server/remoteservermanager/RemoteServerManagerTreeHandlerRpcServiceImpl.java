package net.datenwerke.rs.remoteserver.server.remoteservermanager;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeLoader;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeManager;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;

@Singleton
public class RemoteServerManagerTreeHandlerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractRemoteServerManagerNode>
      implements RemoteServerTreeLoader, RemoteServerTreeManager {

   private static final long serialVersionUID = -455777535667237770L; 

   @Inject
   public RemoteServerManagerTreeHandlerRpcServiceImpl(RemoteServerTreeService remoteserverService, DtoService dtoGenerator,
         SecurityService securityService, EntityClonerService entityClonerService) {
      super(remoteserverService, dtoGenerator, securityService, entityClonerService);
   }

}
