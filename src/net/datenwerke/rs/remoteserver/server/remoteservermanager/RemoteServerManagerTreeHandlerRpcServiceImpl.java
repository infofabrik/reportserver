package net.datenwerke.rs.remoteserver.server.remoteservermanager;

import javax.persistence.NoResultException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeLoader;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeManager;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@Singleton
public class RemoteServerManagerTreeHandlerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractRemoteServerManagerNode>
      implements RemoteServerTreeLoader, RemoteServerTreeManager {

   private static final long serialVersionUID = -455777535667237770L;
   
   private final Provider<RemoteServerTreeService> remoteServerTreeServiceProvider;

   @Inject
   public RemoteServerManagerTreeHandlerRpcServiceImpl(
         RemoteServerTreeService remoteserverService, 
         DtoService dtoGenerator,
         SecurityService securityService, 
         EntityClonerService entityClonerService,
         Provider<RemoteServerTreeService> remoteServerTreeServiceProvider
         ) {
      super(remoteserverService, dtoGenerator, securityService, entityClonerService);
      this.remoteServerTreeServiceProvider = remoteServerTreeServiceProvider;
   }
   
   @SecurityChecked(
         argumentVerification = {
               @ArgumentVerification(
                     name = "node", 
                     isDto = true, 
                     verify = @RightsVerification(
                           rights = Write.class
                     )
               ) 
         }
   )
   @Override
   @Transactional(rollbackOn = { Exception.class })
   public AbstractNodeDto updateNode(@Named("node") AbstractNodeDto node, Dto state) throws ServerCallFailedException {
      /* check if there already is a remote server with the same key */
      if (node instanceof RemoteServerDefinitionDto) {
         String key = ((RemoteServerDefinitionDto) node).getKey();
         if (null != key && !"".equals(key.trim())) {
            try {
               long id = remoteServerTreeServiceProvider.get()
                     .getRemoteServerIdFromKey(((RemoteServerDefinitionDto) node).getKey());

               if (id != node.getId())
                  throw new ExpectedException("There already is a remote server with the same key");

               /*
                * if the datasource id is the same as the id of the datasource to be changed do nothing
                * because this is ok
                */
            } catch (NoResultException e) {
               /* do nothing because this is good */
            }
         }
      }

      return super.updateNode(node, state);
   }

}
