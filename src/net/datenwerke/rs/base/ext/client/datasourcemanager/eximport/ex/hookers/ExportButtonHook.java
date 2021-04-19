package net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.hookers;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.ext.client.datasourcemanager.eximport.ex.DatasourceManagerExportDao;
import net.datenwerke.rs.core.client.datasourcemanager.dto.AbstractDatasourceManagerNodeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * 
 *
 */
public class ExportButtonHook extends QuickExportHookerBase {
	
	private final DatasourceManagerExportDao dsDao;

	@Inject
	public ExportButtonHook(
		DatasourceManagerExportDao reDao,
		ToolbarService toolbarUIService,
		UtilsUIService utilsUIService
		) {
		super(toolbarUIService, utilsUIService);
		
		/* store objects */
		this.dsDao = reDao;
	}
	
	@Override
	protected boolean viewApplies(MainPanelView view,
			AbstractNodeDto selectedNode) {
		if(! (selectedNode instanceof AbstractDatasourceManagerNodeDto) )
			return false;
		if(! MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
			return false;
		
		return true;
	}

	@Override
	protected void quickExportClicked(AbstractNodeDto selectedNode) {
		startProgress();
		dsDao.quickExport((AbstractDatasourceManagerNodeDto) selectedNode, getExportCallback());
	}

	@Override
	protected void loadAndDisplayResult(
			AsyncCallback<String> callback) {
		dsDao.loadResult(callback);
	}


	
}
