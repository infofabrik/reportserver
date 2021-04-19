package net.datenwerke.rs.fileserver.client.fileserver;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.rpc.FileServerTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

import com.google.inject.Inject;

public class FileServerTreeManagerDao extends TreeDbManagerDao {

	@Inject
	public FileServerTreeManagerDao(FileServerTreeManagerAsync treeManager) {
		super(treeManager);
	}

	@Override
	public Dto2PosoMapper getBaseNodeMapper() {
		return AbstractFileServerNodeDto.newPosoMapper();
	}

}
