package net.datenwerke.rs.core.client.reportmanager;

import net.datenwerke.gxtdto.client.dtomanager.Dto2PosoMapper;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.rpc.ReportManagerTreeManagerAsync;
import net.datenwerke.treedb.client.treedb.TreeDbManagerDao;

import com.google.inject.Inject;

public class ReportManagerTreeManagerDao extends TreeDbManagerDao {

	@Inject
	public ReportManagerTreeManagerDao(ReportManagerTreeManagerAsync treeManager){
		super(treeManager);
	}
	
	@Override
	public Dto2PosoMapper getBaseNodeMapper() {
		return AbstractReportManagerNodeDto.newPosoMapper();
	}

}
