package net.datenwerke.rs.fileserver.client.fileserver;

import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

import com.google.inject.Inject;

public class FileServerTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public FileServerTreeLoaderDao(FileServerTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter){
		super(treeLoader, treeDbFtoConverter);
	}
}
