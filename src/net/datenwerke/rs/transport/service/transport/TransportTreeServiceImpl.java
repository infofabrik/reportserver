package net.datenwerke.rs.transport.service.transport;

import java.util.List;

import javax.inject.Provider;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode;
import net.datenwerke.rs.transport.service.transport.entities.AbstractTransportManagerNode__;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.entities.Transport__;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;

public class TransportTreeServiceImpl extends SecuredTreeDBManagerImpl<AbstractTransportManagerNode>
      implements TransportTreeService {

   private final Provider<TransportService> transportServiceProvider;
   
   @Inject
   public TransportTreeServiceImpl(
         Provider<TransportService> transportServiceProvider
         ) {
      this.transportServiceProvider = transportServiceProvider;
   }

   @Override
   @QueryByAttribute(where = AbstractTransportManagerNode__.parent, type = PredicateType.IS_NULL)
   public List<AbstractTransportManagerNode> getRoots() {
      return null; // magic
   }

   @Override
   @SimpleQuery
   public List<AbstractTransportManagerNode> getAllNodes() {
      return null;
   }

   @Override
   @QueryById
   public AbstractTransportManagerNode getNodeById(long id) {
      return null; // magic
   }
   
   @Override
   @QueryByAttribute(
         select = Transport__.id, 
         from = Transport.class, 
         where = Transport__.key, 
         throwNoResultException = true
   )
   public long getTransportIdFromKey(String key) {
      return -1; // magic
   }

   @Override
   @QueryByAttribute(
         where = Transport__.key
   )
   public Transport getTransportByKey(String key) {
      return null; // by magic
   }
   
   @Override
   protected void afterNodeCopy(AbstractTransportManagerNode copiedNode, AbstractTransportManagerNode parent) {
      if (copiedNode instanceof Transport) {
         final TransportService transportService = transportServiceProvider.get();
         transportService.setInitialProperties((Transport) copiedNode, transportService.createInitialProperties(), false);
      }
   }

   @Override
   @QueryByAttribute(
         from = Transport.class, 
         where = Transport__.status, 
         throwNoResultException = false
   )
   public List<Transport> getTransportsByStatus(String status) {
      return null; // magic
   }

   @Override
   public AbstractTransportManagerNode getNodeByKey(String key) {
      return getTransportByKey(key);
   }

}
