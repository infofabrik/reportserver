package net.datenwerke.rs.core.client.datasinkmanager;

import net.datenwerke.rs.core.client.datasinkmanager.rpc.DatasinkTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

import com.google.inject.Inject;

/**
 * 
 *
 */
public class DatasinkTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public DatasinkTreeLoaderDao(DatasinkTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter){
		super(treeLoader, null);
	}
}
