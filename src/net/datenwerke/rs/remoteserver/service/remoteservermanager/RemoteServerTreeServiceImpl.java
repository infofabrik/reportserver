package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import groovy.lang.Closure;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode__;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition__;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder__;
import net.datenwerke.rs.utils.simplequery.PredicateType;
import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.security.service.treedb.SecuredTreeDBManagerImpl;

public class RemoteServerTreeServiceImpl extends SecuredTreeDBManagerImpl<AbstractRemoteServerManagerNode>
      implements RemoteServerTreeService {

   @Inject
   public RemoteServerTreeServiceImpl() {
   }

   @QueryByAttribute(where = RemoteServerFolder__.name)
   @Override
   public RemoteServerFolder getRemoteServerFolderByName(@Named("name") String name) {
      return null; // by magic
   }

   @Override
   @QueryByAttribute(where = AbstractRemoteServerManagerNode__.parent, type = PredicateType.IS_NULL)
   public List<AbstractRemoteServerManagerNode> getRoots() {
      return null; // magic
   }

   @Override
   @SimpleQuery
   public List<AbstractRemoteServerManagerNode> getAllNodes() {
      return null;
   }

   @Override
   @QueryById
   public AbstractRemoteServerManagerNode getNodeById(long id) {
      return null; // magic
   }
   
   @Override
   @QueryByAttribute(
         select = RemoteServerDefinition__.id, 
         from = RemoteServerDefinition.class, 
         where = RemoteServerDefinition__.key, 
         throwNoResultException = true
   )
   public long getRemoteServerIdFromKey(String key) {
      return -1; // magic
   }

   @Override
   @QueryByAttribute(
         where = RemoteServerDefinition__.key
   )
   public RemoteServerDefinition getRemoteServerByKey(String key) {
      return null; // by magic
   }
   
   @Override
   protected void afterNodeCopy(AbstractRemoteServerManagerNode copiedNode, AbstractRemoteServerManagerNode parent) {
      if (copiedNode instanceof RemoteServerDefinition) {
         RemoteServerDefinition clone = (RemoteServerDefinition) copiedNode;

         Closure getAllNodes = new Closure(null) {
            public List<AbstractRemoteServerManagerNode> doCall() {
               return parent.getChildren();
            }
         };

         clone.setName(clone.getName() == null
               ? keyNameGeneratorService.getNextCopyName("", getAllNodes)
               : keyNameGeneratorService.getNextCopyName(clone.getName(), getAllNodes));
         clone.setKey(keyNameGeneratorService.getNextCopyKey(clone.getKey(), this));
      }
   }

   @Override
   public AbstractRemoteServerManagerNode getNodeByKey(String key) {
      return getRemoteServerByKey(key);
   }
}
