package net.datenwerke.rs.transport.server.transport;

import java.util.Date;

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
import net.datenwerke.rs.transport.client.transport.dto.TransportDto;
import net.datenwerke.rs.transport.client.transport.rpc.TransportTreeLoader;
import net.datenwerke.rs.transport.client.transport.rpc.TransportTreeManager;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.TransportTreeService;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.server.TreeDBManagerTreeHandler;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.annotation.ArgumentVerification;
import net.datenwerke.security.service.security.annotation.RightsVerification;
import net.datenwerke.security.service.security.annotation.SecurityChecked;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

@Singleton
public class TransportManagerTreeHandlerRpcServiceImpl extends TreeDBManagerTreeHandler<AbstractTransportManagerNode>
      implements TransportTreeLoader, TransportTreeManager {

   private static final long serialVersionUID = -455777535667237770L;
   
   private final Provider<TransportTreeService> treeServiceProvider;
   private final Provider<TransportService> transportServiceProvider;

   @Inject
   public TransportManagerTreeHandlerRpcServiceImpl(
         DtoService dtoGenerator,
         SecurityService securityService, 
         EntityClonerService entityClonerService,
         Provider<TransportTreeService> transportTreeServiceProvider,
         Provider<TransportService> transportServiceProvider
         ) {
      super(transportTreeServiceProvider.get(), dtoGenerator, securityService, entityClonerService);
      this.treeServiceProvider = transportTreeServiceProvider;
      this.transportServiceProvider = transportServiceProvider;
   }
   
   @Override
   protected boolean allowDuplicateNode(AbstractTransportManagerNode node) {
      return true;
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
      /* check if there already is a transport with the same key */
      if (node instanceof TransportDto) {
         String key = ((TransportDto) node).getKey();
         if (null != key && !"".equals(key.trim())) {
            try {
               long id = treeServiceProvider.get()
                     .getTransportIdFromKey(((TransportDto) node).getKey());

               if (id != node.getId())
                  throw new ExpectedException("There already is a transport with the same key");

               /*
                * if the transport id is the same as the id of the transport to be changed do nothing
                * because this is ok
                */
            } catch (NoResultException e) {
               /* do nothing because this is good */
            }
         }
      }

      return super.updateNode(node, state);
   }
   
   @Override
   @Transactional(rollbackOn = { Exception.class })
   protected void doSetInitialProperties(AbstractTransportManagerNode inserted) {
      if (inserted instanceof Transport) {
         ((Transport)inserted).setKey("RS_TEMP_" + new Date());
      }
      setInitialProperties(inserted);
   }
   
   @Override
   protected void nodeCloned(AbstractTransportManagerNode clonedNode) {
      setInitialProperties(clonedNode);
      clonedNode.setCreatedOn(new Date());
   }
   
   private void setInitialProperties(AbstractTransportManagerNode node) {
      if (node instanceof Transport) {
         Transport transport = (Transport) node;
         TransportService transportService = transportServiceProvider.get();
         transportService.setInitialProperties(transport, transportService.createInitialProperties());
      }
   }

}
