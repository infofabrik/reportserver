package net.datenwerke.rs.core.client.datasourcemanager;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.rs.core.client.datasourcemanager.rpc.DatasourceTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

import com.google.inject.Inject;

public class DatasourceTreeManagerDao extends TreeDbManagerDao {


	@Inject
	public DatasourceTreeManagerDao(DatasourceTreeManagerAsync treeManager) {
		super(treeManager);
	}

	@Override
	public Dto2PosoMapper getBaseNodeMapper() {
		return AbstractDatasourceManagerNodeDto.newPosoMapper();
	}
	
	
}
