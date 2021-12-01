package net.datenwerke.rs.core.client.reportexecutor.objectinfo;

import java.util.Collections;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.MainPanelAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.SelectionAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewSpecialViewHandler;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * 
 *
 */
public class ReportPreviewObjectInfo implements
	UrlViewSpecialViewHandler {

	private final ReportExecutorUIService reportExecutorService;
	private final ReportExecutorDao reDao;
	
	@Inject
	public ReportPreviewObjectInfo(
		ReportExecutorUIService reportExecutorService,
		ReportExecutorDao reDao
		){
		
		/* store objects */
		this.reportExecutorService = reportExecutorService;
		this.reDao = reDao;
	}
	
	@Override
	public boolean consumes(String url) {
		return null != url && url.startsWith("rs:reportpreview://");
	}

	@Override
	public Widget getWidget(String url) {
		final DwContentPanel wrapper = DwContentPanel.newInlineInstance();
		try{
			String strId = url.substring("rs:reportpreview://".length());
			Long reportId = Long.valueOf(strId);
			
			reDao.loadFullReportForExecution(reportId, new RsAsyncCallback<ReportDto>(){
				@Override
				public void onSuccess(ReportDto result) {
					PreviewViewFactory fact = reportExecutorService.getPreviewReportComponent(result);
					ReportExecutorMainPanelView view = fact.newInstance(result, Collections.EMPTY_SET);
					Widget component = view.getViewComponent();
					
					view.setExecuteReportToken(reportExecutorService.createExecuteReportToken(result));
					if(view instanceof MainPanelAwareView)
						((MainPanelAwareView)view).makeAwareOfMainPanel(wrapper);
					if(view instanceof SelectionAwareView)
						((SelectionAwareView)view).makeAwareOfSelection();
					
					
					wrapper.add(component);
					wrapper.forceLayout();
				}
			});
			
		} catch(Exception e){
		}

		return wrapper;
	}

	
}
