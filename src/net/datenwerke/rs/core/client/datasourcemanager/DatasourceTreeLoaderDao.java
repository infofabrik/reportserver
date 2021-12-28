package net.datenwerke.rs.core.client.datasourcemanager;

import com.google.inject.Inject;

import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

/**
 * 
 *
 */
public class DatasourceTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public DatasourceTreeLoaderDao(DatasourceTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter){
		super(treeLoader, null);
	}
}
