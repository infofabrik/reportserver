package net.datenwerke.rs.core.client.reportexecutor;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.dtomanager.Dao;
import net.datenwerke.gxtdto.client.dtomanager.callback.DaoAsyncCallback;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportexecutor.rpc.ReportExecutorRpcServiceAsync;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ReportExecutorDao extends Dao {

	private final ReportExecutorRpcServiceAsync rpcService;
	private HookHandlerService hookHandler;

	@Inject
	public ReportExecutorDao(ReportExecutorRpcServiceAsync rpcService,
			HookHandlerService hookHandler) {
		super();
		this.rpcService = rpcService;
		this.hookHandler = hookHandler;
	}
	
	public void createNewVariant(ReportDto reportVariantDto, String executeToken, String title, String description, AsyncCallback<ReportDto> callback){
		reportVariantDto = unproxy(reportVariantDto);

		prepareForStorage(reportVariantDto, executeToken);

		rpcService.createNewVariant(reportVariantDto, executeToken, title, description, transformDtoCallback(callback));
	}

	

	public Request executeAs(String format, String executeToken, ReportDto report, DwModel config, AsyncCallback<DwModel> callback){
		report = unproxy(report);
		
		prepareForStorage(report, executeToken);
		
		return rpcService.executeAs(format, executeToken, report, config, transformAndKeepCallback(callback));
	}

	public void editVariant(ReportDto reportVariantDto, String executeToken, String title,
			String description, 
			AsyncCallback<ReportDto> callback){
		reportVariantDto = unproxy(reportVariantDto);
		
		prepareForStorage(reportVariantDto, executeToken);
		
		rpcService.editVariant(reportVariantDto, executeToken, title, description, transformDtoCallback(callback));
	}

	public void deleteVariant(ReportDto reportVariantDto, AsyncCallback<Void> callback){
		DaoAsyncCallback<Void> daoCallback = transformAndKeepCallback(callback);
		daoCallback.addDtoToDetach(reportVariantDto);
		
		rpcService.deleteVariant(reportVariantDto, daoCallback);
	}

	public Request storePNGInSession(String executorToken, ReportDto report, AsyncCallback<DwModel> callback){
		report = unproxy(report);
		return rpcService.storePNGInSession(executorToken, report, transformAndKeepCallback(callback));
	}
	
	public void loadFullReportForExecution(Long reportId, AsyncCallback<ReportDto> callback){
		HistoryLocation location = HistoryLocation.fromString("#dummy/id:" + reportId);
		
		loadReportForExecutionFrom(location, callback);
	}
	
	public void loadFullReportForExecution(ReportDto report, AsyncCallback<ReportDto> callback){
		loadFullReportForExecution(report, false, callback);
	}
	
	public void loadFullReportForExecution(ReportDto report, boolean handleFailuresMyself, AsyncCallback<ReportDto> callback){
		DaoAsyncCallback<ReportDto> transformAndKeepCallback = transformAndKeepCallback(callback);
		if(handleFailuresMyself)
			transformAndKeepCallback.ignoreExpectedExceptions(true);
		rpcService.loadFullReportForExecution(report, transformAndKeepCallback);
	}

	public void loadReportForExecutionFrom(HistoryLocation location,
			AsyncCallback<ReportDto> callback){
		rpcService.loadReportForExecutionFrom(location, transformAndKeepCallback(callback));
	}

	public void loadFullReportUnmanaged(ReportDto report, AsyncCallback<ReportDto> callback){
		rpcService.loadFullReportUnmanaged(report, callback);
	}
	
	public void getPreviewMode(AsyncCallback<String> callback){
		rpcService.getPreviewMode(callback);
	}

	public void setPreviewModeUserProperty(String value, RsAsyncCallback<Void> cb) {
		rpcService.setPreviewModeUserProperty(value, cb);
	}
	
	private void prepareForStorage(ReportDto reportDto, String executeToken) {
		for(PrepareReportModelForStorageOrExecutionHook hooker : hookHandler.getHookers(PrepareReportModelForStorageOrExecutionHook.class)){
			if(hooker.consumes(reportDto)){
				hooker.prepareForExecutionOrStorage(reportDto, executeToken);
			}
		}
	}
	
	public void getDefaultColumnWidth(AsyncCallback<Integer> callback){
		rpcService.getDefaultColumnWidth(transformAndKeepCallback(callback));
	}
	
	public void getMaxColumnWidth(AsyncCallback<Integer> callback){
		rpcService.getMaxColumnWidth(transformAndKeepCallback(callback));
	}
	
	
}
