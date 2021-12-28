package net.datenwerke.rs.scriptreport.client.scriptreport.ui;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.SimpleContainer;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ScriptReportPreviewView extends AbstractReportPreviewView {

	private DwContentPanel wrapper;
	private final UtilsUIService utilsService;

	
	@Inject
	public ScriptReportPreviewView(
		ReportExecutorDao rexService, HookHandlerService hookHandler,
		UtilsUIService utilsService) {
		super(rexService, hookHandler);
		
		this.utilsService = utilsService;
		
		wrapper = DwContentPanel.newInlineInstance();
	}
	
	@Override
	protected boolean isCreateStatusBar() {
		return false;
	}

	@Override
	protected void doLoadReport(final DwModel result) {
		StringBaseModel reportExecutionResult = (net.datenwerke.gxtdto.client.model.StringBaseModel) result;
		if(null != reportExecutionResult && null != reportExecutionResult.getValue()){
			wrapper.clear();
			SimpleContainer container = new SimpleContainer();
			wrapper.setWidget(container);
			
			container.add(utilsService.asIframe((String) reportExecutionResult.getValue()));
			Scheduler.get().scheduleDeferred(new ScheduledCommand() {
				@Override
				public void execute() {
					wrapper.forceLayout();
				}
			});
		}
	}
	
	
	@Override
	protected void cancelExecution(String executeToken) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public Request execute(ReportDto report, String executeToken, AsyncCallback<DwModel> callback) {
		return reportExecutorDao.executeAs("SCRIPT_REPORT_PREVIEW", executeToken, report, null, callback);
	}
	
	@Override
	public Widget doGetViewComponent() {
		return wrapper;
	}
	

}
