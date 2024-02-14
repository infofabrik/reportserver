package net.datenwerke.rs.remoteserver.server.remoteservermanager;

import java.util.List;

import javax.persistence.NoResultException;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.Singleton;
import com.google.inject.name.Named;
import com.google.inject.persist.Transactional;

import groovy.lang.Closure;
import net.datenwerke.gxtdto.client.dtomanager.Dto;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ServerCallFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.keyutils.service.keyutils.KeyNameGeneratorService;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.dto.RemoteServerDefinitionDto;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeLoader;
import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeManager;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
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
         Provider<RemoteServerTreeService> remoteServerTreeServiceProvider,
         KeyNameGeneratorService keyGeneratorService
         ) {
      super(remoteserverService, 
            dtoGenerator, 
            securityService, 
            entityClonerService,
            keyGeneratorService);
      this.remoteServerTreeServiceProvider = remoteServerTreeServiceProvider;
   }
   
   @Override
   protected boolean allowDuplicateNode(AbstractRemoteServerManagerNode node) {
      return node instanceof RemoteServerDefinition;
   }
   
   @Override
   protected void nodeCloned(AbstractRemoteServerManagerNode clonedNode, AbstractRemoteServerManagerNode realNode) {
      if (!(clonedNode instanceof RemoteServerDefinition))
         throw new IllegalArgumentException();
      RemoteServerDefinition clone = (RemoteServerDefinition) clonedNode;
      RemoteServerDefinition real = (RemoteServerDefinition) realNode;

      Closure getAllNodes = new Closure(null) {
         public List<AbstractRemoteServerManagerNode> doCall() {
            return realNode.getParent().getChildren();
         }
      };
      clone.setName(clone.getName() == null
            ? keyGeneratorService.getNextCopyName("", getAllNodes)
            : keyGeneratorService.getNextCopyName(clone.getName(), getAllNodes));
      clone.setKey(keyGeneratorService.getNextCopyKey(real.getKey(), remoteServerTreeServiceProvider.get()));
   }
   
   @Override
   protected void doSetInitialProperties(AbstractRemoteServerManagerNode inserted) {
      if (inserted instanceof RemoteServerDefinition) {
         ((RemoteServerDefinition) inserted)
               .setKey(keyGeneratorService.generateDefaultKey(remoteServerTreeServiceProvider.get()));
      }
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
                  throw new ExpectedException("There is already a remote server with the same key: " + id);

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
