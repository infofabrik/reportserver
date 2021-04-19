package net.datenwerke.rs.core.client.datasourcemanager;

import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

import com.google.inject.Inject;

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
