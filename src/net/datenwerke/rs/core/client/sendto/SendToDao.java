package net.datenwerke.rs.core.client.sendto;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.sendto.rpc.SendToRpcServiceAsync;

public class SendToDao extends Dao {

	private final SendToRpcServiceAsync rpcService;
	private final HookHandlerService hookHandler;

	@Inject
	public SendToDao(SendToRpcServiceAsync rpcService,
			HookHandlerService hookHandler) {
		super();
		this.rpcService = rpcService;
		this.hookHandler = hookHandler;
	}
	
	public void loadClientConfigsFor(ReportDto report,
			RsAsyncCallback<ArrayList<SendToClientConfig>> callback){
		rpcService.loadClientConfigsFor(report, callback);
	}

	public void sendTo(final ReportDto report, final String executorToken, final String id, 
			final String format, final List<ReportExecutionConfigDto> formatConfig, 
			final Map<String, String> values, final AsyncCallback<String> callback){
		
		hookHandler.getHookers(PrepareReportModelForStorageOrExecutionHook.class)
			.stream()
			.filter(hooker -> hooker.consumes(report))
			.forEach(hooker -> hooker.prepareForExecutionOrStorage(report, executorToken));
		
		rpcService.sendTo(report, executorToken, id, format, formatConfig, values, callback);
	}
}
