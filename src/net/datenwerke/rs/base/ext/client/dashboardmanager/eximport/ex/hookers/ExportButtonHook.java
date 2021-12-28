package net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.hookers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.ext.client.dashboardmanager.eximport.ex.DashboardManagerExportDao;
import net.datenwerke.rs.dashboard.client.dashboard.dto.AbstractDashboardManagerNodeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

/**
 * 
 *
 */
public class ExportButtonHook extends QuickExportHookerBase {
	
	private final DashboardManagerExportDao dashboardDao;

	@Inject
	public ExportButtonHook(
		DashboardManagerExportDao reDao,
		ToolbarService toolbarUIService,
		UtilsUIService utilsUIService
		) {
		super(toolbarUIService, utilsUIService);
		
		/* store objects */
		this.dashboardDao = reDao;
	}
	
	@Override
	protected boolean viewApplies(MainPanelView view,
			AbstractNodeDto selectedNode) {
		if(! (selectedNode instanceof AbstractDashboardManagerNodeDto) )
			return false;
		if(! MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
			return false;
		
		return true;
	}

	@Override
	protected void quickExportClicked(AbstractNodeDto selectedNode) {
		startProgress();
		dashboardDao.quickExport((AbstractDashboardManagerNodeDto) selectedNode, getExportCallback());
	}

	@Override
	protected void loadAndDisplayResult(
			AsyncCallback<String> callback) {
		dashboardDao.loadResult(callback);
	}


	
}
