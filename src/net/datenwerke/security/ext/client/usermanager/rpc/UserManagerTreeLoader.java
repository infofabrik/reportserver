package net.datenwerke.security.ext.client.usermanager.rpc;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeLoader;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("usermanager_tree")
public interface UserManagerTreeLoader extends RemoteService, RPCTreeLoader {

}
