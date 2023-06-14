package net.datenwerke.rs.transport.service.transport;

import java.util.List;

import javax.persistence.NonUniqueResultException;

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

   @Inject
   public TransportTreeServiceImpl() {
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
   public Transport getTransportByKey(String key) {
      try {
         return doGetTransportByKey(key);
      } catch (NonUniqueResultException e) {
         throw new IllegalArgumentException("There seem to be multiple transports with the same key: " + key, e);
      } catch (IllegalStateException e) {
         if (null != e.getCause() && e.getCause() instanceof NonUniqueResultException)
            throw new IllegalArgumentException("There seem to be multiple transports with the same key: " + key, e);
         throw e;
      }
   }
   
   @QueryByAttribute(
         where = Transport__.key
   )
   public Transport doGetTransportByKey(String key) {
      return null; // by magic, must be public for AOP interception to work
   }


}
