package net.datenwerke.rs.core.client.reportmanager.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("reportmanager_tree")
public interface ReportManagerTreeManager extends RPCTreeManager, RemoteService {

}
