package net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.hookers;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.fileserver.client.fileserver.dto.AbstractFileServerNodeDto;
import net.datenwerke.rs.fileserver.client.fileserver.eximport.ex.FileServerExportDao;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;

/**
 * 
 *
 */
public class ExportButtonHook extends QuickExportHookerBase{
	
	final FileServerExportDao fsDao;
	
	@Inject
	public ExportButtonHook(
		FileServerExportDao reDao,
		ToolbarService toolbarUIService,
		UtilsUIService utilsUIService
		) {
		super(toolbarUIService, utilsUIService);
		
		/* store objects */
		this.fsDao = reDao;
	}
	
	@Override
	protected boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode) {
		if(! (selectedNode instanceof AbstractFileServerNodeDto) )
			return false;
		if(! MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
			return false;
	
		return true;
	}

	@Override
	protected void quickExportClicked(final AbstractNodeDto selectedNode) {
		startProgress();
		fsDao.quickExport((AbstractFileServerNodeDto) selectedNode, getExportCallback());
	}
	
	@Override
	protected void loadAndDisplayResult(
			AsyncCallback<String> callback) {
		fsDao.loadResult(callback);
	}
}
