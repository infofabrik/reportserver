package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import java.util.List;

import com.google.inject.Inject;
import com.google.inject.name.Named;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode__;
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
}
