package net.datenwerke.rs.fileserver.client.fileserver;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeLoaderAsync;
import net.datenwerke.treedb.client.treedb.TreeDbFtoConverter;
import net.datenwerke.treedb.client.treedb.TreeDbLoaderDao;

public class FileServerTreeLoaderDao extends TreeDbLoaderDao {

	@Inject
	public FileServerTreeLoaderDao(FileServerTreeLoaderAsync treeLoader, TreeDbFtoConverter treeDbFtoConverter){
		super(treeLoader, treeDbFtoConverter);
	}
}
