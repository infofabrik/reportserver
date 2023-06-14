package net.datenwerke.rs.transport.client.transport;

import com.google.inject.Inject;

import net.datenwerke.rs.transport.client.transport.rpc.TransportTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

public class TransportTreeLoaderDao extends TreeDbLoaderDao {

   @Inject
   public TransportTreeLoaderDao(TransportTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter) {
      super(treeLoader, null);
   }
}
