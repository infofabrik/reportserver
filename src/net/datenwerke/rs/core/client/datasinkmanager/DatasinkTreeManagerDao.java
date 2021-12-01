package net.datenwerke.rs.core.client.datasinkmanager;

import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.core.client.datasinkmanager.dto.AbstractDatasinkManagerNodeDto;
import net.datenwerke.rs.core.client.datasinkmanager.rpc.DatasinkTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

public class DatasinkTreeManagerDao extends TreeDbManagerDao {


	@Inject
	public DatasinkTreeManagerDao(DatasinkTreeManagerAsync treeManager) {
		super(treeManager);
	}

	@Override
	public Dto2PosoMapper getBaseNodeMapper() {
		return AbstractDatasinkManagerNodeDto.newPosoMapper();
	}
	
	
}
