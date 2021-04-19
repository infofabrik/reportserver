package net.datenwerke.gf.client.managerhelper.tree;

import net.datenwerke.gf.client.treedb.stores.EnhancedTreeStore;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

public interface ManagerHelperTreeFactory {

	ManagerHelperTree create(Class<?> guarantor, EnhancedTreeStore store, TreeDbLoaderDao treeLoaderDao, TreeDbManagerDao treeManagerDao);
}
