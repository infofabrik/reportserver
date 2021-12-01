package net.datenwerke.rs.core.client.reportexporter.exporter;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.info.DefaultInfoConfig;
import com.sencha.gxt.widget.core.client.info.Info;
import com.sencha.gxt.widget.core.client.info.InfoConfig;

import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.dialog.error.SimpleErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.servercommunication.callback.ModalAsyncCallback;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterUIService;
import net.datenwerke.rs.core.client.reportexporter.dto.ReportExecutionConfigDto;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterPreExportHook;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

/**
 * 
 *
 */
public abstract class ReportExporterImpl implements ReportExporter {

	@Inject
	protected static UtilsUIService utilsService;
	
	@Inject
	protected static ReportExporterUIService exporterService;
	
	@Inject
	protected static ReportExporterDao exporterDao;

	@Inject
	protected static HookHandlerService hookHandler;
	
	@Inject
	protected static ReportExecutorUIService executorService;
	
	private boolean performPreExportTasks(final ReportDto report, final String executorToken) {
		if(! consumes(report))
			throw new IllegalArgumentException("unsupported report provided: " + report.getClass()); //$NON-NLS-1$
		
		// TODO: REFACTOR (siehe auch AbstractReportPreviewView)
		
		for(ReportExporterPreExportHook preExport : hookHandler.getHookers(ReportExporterPreExportHook.class)){
			String errorMsg = preExport.isExportable(report);
			if(null != errorMsg){
				new DwAlertMessageBox(ReportExporterMessages.INSTANCE.reportNotExportable(), errorMsg).show();
				return false;
			}
		}
		return true;
	}

	@Override
	public final Request export(final ReportDto report, String executorToken) {
		if(null == report)
			throw new NullPointerException();
		
		if(null == executorToken)
			executorToken = executorService.createExecuteReportToken(report);
		
		if(!performPreExportTasks(report, executorToken)){
			return null;
		}
		
		boolean skipDownload = isSkipDownload();
		
		try{
			InfoConfig infoConfig = new DefaultInfoConfig(ReportExporterMessages.INSTANCE.reportIsBeingExportedTitle(), 
					(skipDownload ? ReportExporterMessages.INSTANCE.reportIsBeingExportedMsgSkipDownload(getOutputFormat()) : 
						ReportExporterMessages.INSTANCE.reportIsBeingExportedMsg(getOutputFormat())));
			infoConfig.setWidth(350);
			infoConfig.setDisplay(3500);
			Info.display(infoConfig);
		}catch(Exception e){}

		if (skipDownload)
			return exportAsSkipDownload(report, executorToken, getOutputFormat());
		else
			return doExport(report, executorToken, getExportAsDownloadCallback(executorToken));	
		
	}
	
	@Override
	public final Request prepareExportForPreview(final ReportDto report, final String executorToken, AsyncCallback<Void> callback) {
		if(!performPreExportTasks(report, executorToken)){
			return null;
		}

		return doExport(report, executorToken, callback);		
	}
	
	@Override
	public void configureFrom(List<ReportExecutionConfigDto> exportConfiguration) {
	}
	
	@Override
	public void displayConfiguration(final ReportDto report, final String executorToken, boolean allowAutomaticConfig, ConfigurationFinishedCallback callback){
		callback.success();
	}
	
	@Override
	public List<ReportExecutionConfigDto> getConfiguration(){
		return new ArrayList<ReportExecutionConfigDto>();
	}
	
	@Override
	public boolean isConfigured(){
		return true;
	}
	
	@Override
	public boolean canBeScheduled(){
		return true;
	}
	
	protected Request doExport(ReportDto report, String executorToken, AsyncCallback<Void> callback) {
		return exporterDao.storeInSessionForExport(report, executorToken, getOutputFormat(), getConfiguration(), callback);
	}

	protected AsyncCallback<Void> getExportCallback(final String executorToken) {
		return new ModalAsyncCallback<Void>(3000) { 
			@Override
			public void doOnSuccess(Void v){
				String url = exporterService.getExportServletPath() + "&tid=" + executorToken;
				utilsService.redirect(url);
			}
		};
	}
	
	protected AsyncCallback<Void> getExportAsDownloadCallback(final String executorToken) {
		return new ModalAsyncCallback<Void>(3000) {
			@Override
			public void doOnSuccess(Void v){
				String url = exporterService.getExportServletPath() + "&tid=" + executorToken + "&download=true";
				ClientDownloadHelper.triggerDownload(url);
			}
		};
	}
	
	protected Request exportAsSkipDownload(final ReportDto reportDto, final String executorToken, String format) {
		RsAsyncCallback<Void> callback = new RsAsyncCallback<Void>() {
			
			@Override
			public void onSuccess(Void result) {
				InfoConfig infoConfig = new DefaultInfoConfig(ReportExporterMessages.INSTANCE.reportSuccessfullyExportedTitle(), 
							ReportExporterMessages.INSTANCE.reportSuccessfullyExported());
				infoConfig.setWidth(350);
				infoConfig.setDisplay(3500);
				Info.display(infoConfig);
			}
			
			@Override
			public void onFailure(Throwable caught) {
				new SimpleErrorDialog(caught.getMessage()).show();
			}
		};
		
		return exporterDao.exportSkipDownload(reportDto, executorToken, format, callback);
		
	}

	public boolean wantsToBeTop(ReportDto report){
		return false;
	}
	
	@Override
	public boolean isSkipDownload() {
		return false;
	}
	
}
