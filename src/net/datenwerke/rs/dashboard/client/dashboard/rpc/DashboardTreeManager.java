package net.datenwerke.rs.dashboard.client.dashboard.rpc;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

@RemoteServiceRelativePath("dashboardadmin")
public interface DashboardTreeManager extends RemoteService, RPCTreeManager {

}
