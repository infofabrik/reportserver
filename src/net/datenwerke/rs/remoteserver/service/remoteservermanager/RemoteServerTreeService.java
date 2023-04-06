package net.datenwerke.rs.remoteserver.service.remoteservermanager;

import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.AbstractRemoteServerManagerNode;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerFolder;
import net.datenwerke.treedb.service.treedb.TreeDBManager;

public interface RemoteServerTreeService extends TreeDBManager<AbstractRemoteServerManagerNode> {

   RemoteServerFolder getRemoteServerFolderByName(String name);

}
