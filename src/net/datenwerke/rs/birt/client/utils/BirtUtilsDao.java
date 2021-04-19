package net.datenwerke.rs.birt.client.utils;

import java.util.List;

import javax.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.client.utils.dto.BirtParameterProposalDto;
import net.datenwerke.rs.birt.client.utils.rpc.BirtUtilsRpcServiceAsync;

public class BirtUtilsDao extends Dao {

	private final BirtUtilsRpcServiceAsync rpcSerice;

	@Inject
	public BirtUtilsDao(BirtUtilsRpcServiceAsync rpcSerice) {
		this.rpcSerice = rpcSerice;
	}
	
	public void proposeParametersFor(BirtReportDto report, RsAsyncCallback<List<BirtParameterProposalDto>> callback) {
		rpcSerice.proposeParametersFor(report, transformAndKeepCallback(callback));
	}

	public void addParametersFor(BirtReportDto report, List<BirtParameterProposalDto> proposalDtos, RsAsyncCallback<BirtReportDto> rsAsyncCallback) {
		rpcSerice.addParametersFor(report, (List<BirtParameterProposalDto>) unproxy(proposalDtos), transformDtoCallback(rsAsyncCallback));
	}

}
