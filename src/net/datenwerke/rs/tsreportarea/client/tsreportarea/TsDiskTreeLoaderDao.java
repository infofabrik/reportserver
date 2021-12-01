package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import net.datenwerke.rs.tsreportarea.client.tsreportarea.rpc.TsDiskRpcServiceAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

import com.google.inject.Inject;

public class TsDiskTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public TsDiskTreeLoaderDao(
			TsDiskRpcServiceAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter) {
		super(treeLoader, treeDbFtoConverter);
	}

}
