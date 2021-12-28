package net.datenwerke.usermanager.ext.client.eximport.ex.hookers;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;
import net.datenwerke.usermanager.ext.client.eximport.ex.UserManagerExportDao;


/**
 * 
 *
 */
public class ExportButtonHook extends QuickExportHookerBase{
	
	private final UserManagerExportDao umDao;

	@Inject
	public ExportButtonHook(
		UserManagerExportDao reDao,
		ToolbarService toolbarUIService,
		UtilsUIService utilsUIService
		) {
		super(toolbarUIService, utilsUIService);
		
		/* store objects */
		this.umDao = reDao;
	}
	

	@Override
	protected void quickExportClicked(AbstractNodeDto selectedNode) {
		startProgress();
		umDao.quickExport((AbstractUserManagerNodeDto) selectedNode, getExportCallback());
	}

	@Override
	protected boolean viewApplies(MainPanelView view,
			AbstractNodeDto selectedNode) {
		if(! (selectedNode instanceof AbstractUserManagerNodeDto) )
			return false;
		if(! MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
			return false;
		
		return true;
	}
	
	@Override
	protected void loadAndDisplayResult(
			AsyncCallback<String> callback) {
		umDao.loadResult(callback);
	}
}
