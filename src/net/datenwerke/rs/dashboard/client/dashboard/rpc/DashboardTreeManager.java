package net.datenwerke.rs.dashboard.client.dashboard.rpc;

import net.datenwerke.treedb.client.treedb.rpc.RPCTreeManager;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

@RemoteServiceRelativePath("dashboardadmin")
public interface DashboardTreeManager extends RemoteService, RPCTreeManager {

}
