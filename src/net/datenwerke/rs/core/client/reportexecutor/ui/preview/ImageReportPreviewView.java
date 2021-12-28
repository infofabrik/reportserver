package net.datenwerke.rs.core.client.reportexecutor.ui.preview;

import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.Request;
import com.google.gwt.user.client.Random;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.core.client.dom.ScrollSupport.ScrollMode;
import com.sencha.gxt.widget.core.client.container.FlowLayoutContainer;

import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.model.SuccessIndicatorBaseModel;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ImageReportPreviewView extends AbstractReportPreviewView {

	private FlowLayoutContainer wrapper;

	@Inject
	public ImageReportPreviewView (
			ReportExecutorDao rexService, 
			HookHandlerService hookHandler) {
		super(rexService, hookHandler);
		
		wrapper = new FlowLayoutContainer();
		wrapper.setScrollMode(ScrollMode.AUTO);
	}
	
	@Override
	protected boolean isCreateStatusBar() {
		return false;
	}
	
	@Override
	public Widget doGetViewComponent() {
		return wrapper;
	}

	@Override
	protected void doLoadReport(DwModel reportExecutionResult) {
		if(((SuccessIndicatorBaseModel)reportExecutionResult).isSuccess()){
			wrapper.clear();
			final Image image = new Image(GWT.getModuleBaseURL() + "jasperpreviewprovider?rnd="+Random.nextInt()); //$NON-NLS-1$ //$NON-NLS-2$
			wrapper.add(image);
		}
	}

	@Override
	protected void cancelExecution(String executeToken) {
		// TODO Auto-generated method stub
	}
	
	@Override
	public Request execute(ReportDto report, String executeToken, AsyncCallback<DwModel> callback) {
		return reportExecutorDao.storePNGInSession(executeToken, report, callback);
	}

}
