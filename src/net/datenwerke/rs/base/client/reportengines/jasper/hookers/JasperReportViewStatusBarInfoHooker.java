package net.datenwerke.rs.base.client.reportengines.jasper.hookers;

import net.datenwerke.gxtdto.client.model.DwModel;
import net.datenwerke.gxtdto.client.model.SuccessIndicatorBaseModel;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportDto;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportPreviewViewStatusbarHook;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Status;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

/**
 * 
 *
 */
public class JasperReportViewStatusBarInfoHooker implements ReportPreviewViewStatusbarHook {

	private Status countPages;
	
	@Inject
	public JasperReportViewStatusBarInfoHooker(
		){
	}
	
	@Override
	public void reportPreviewViewStatusbarHook_addLeft(final AbstractReportPreviewView reportPreviewView, ToolBar toolbar, final ReportDto report) {
		if(! (report instanceof JasperReportDto))
			return;
		
		countPages = new Status();
		countPages.setBorders(false);
		toolbar.add(countPages);
	}

	@Override
	public void reportPreviewViewStatusbarHook_addRight(AbstractReportPreviewView reportPreviewView, ToolBar toolbar, ReportDto report) {
	}
	
	@Override
	public void reportPreviewViewStatusbarHook_reportToBeReloaded(ReportDto report, AbstractReportPreviewView abstractReportPreviewView) {
	}

	@Override
	public void reportPreviewViewStatusbarHook_reportUpdated(ReportDto report, final AbstractReportPreviewView abstractReportPreviewView, boolean configChanged) {
	}
	
	@Override
	public void reportPreviewViewStatusbarHook_reportLoaded(ReportDto report,
			AsyncCallback<DwModel> modalAsyncCallback, DwModel result, boolean configChanged) {
		if(! (report instanceof JasperReportDto))
			return;
		
		if(result instanceof SuccessIndicatorBaseModel){
			SuccessIndicatorBaseModel suc = (SuccessIndicatorBaseModel) result;
			if(suc.getData().size() > 0){
				countPages.setText(ReportexecutorMessages.INSTANCE.pages() + suc.getData().get(0).getValue());
			}
		}
	}

	@Override
	public void reportPreviewViewStatusbarHook_cancel(ReportDto report,
			AbstractReportPreviewView abstractReportPreviewView) {
		// TODO Auto-generated method stub
		
	}
}
