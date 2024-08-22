package net.datenwerke.rs.remoteserver.client.remoteservermanager;

import com.google.inject.Inject;

import net.datenwerke.rs.remoteserver.client.remoteservermanager.rpc.RemoteServerTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

public class RemoteServerTreeLoaderDao extends TreeDbLoaderDao {

   @Inject
   public RemoteServerTreeLoaderDao(RemoteServerTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter) {
      super(treeLoader, null);
   }
}
