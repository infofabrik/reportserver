package net.datenwerke.rs.dashboard.client.dashboard;

import net.datenwerke.rs.dashboard.client.dashboard.rpc.DashboardTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

import com.google.inject.Inject;

public class DashboardTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public DashboardTreeLoaderDao(DashboardTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter){
		super(treeLoader, treeDbFtoConverter);
	}
}
