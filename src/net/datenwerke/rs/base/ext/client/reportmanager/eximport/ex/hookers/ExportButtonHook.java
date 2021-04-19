package net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.hookers;

import net.datenwerke.gf.client.managerhelper.mainpanel.MainPanelView;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwMessageBox;
import net.datenwerke.gxtdto.client.utilityservices.UtilsUIService;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.base.ext.client.reportmanager.eximport.ex.ReportManagerExportDao;
import net.datenwerke.rs.core.client.reportmanager.dto.ReportFolderDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.AbstractReportManagerNodeDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;
import net.datenwerke.treedb.ext.client.eximport.ex.QuickExportHookerBase;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.MessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;

/**
 * 
 *
 */
public class ExportButtonHook extends QuickExportHookerBase{
	
	final ReportManagerExportDao reDao;
	
	@Inject
	public ExportButtonHook(
		ReportManagerExportDao reDao,
		ToolbarService toolbarUIService,
		UtilsUIService utilsUIService
		) {
		super(toolbarUIService, utilsUIService);
		
		/* store objects */
		this.reDao = reDao;
	}
	
	@Override
	protected boolean viewApplies(MainPanelView view, AbstractNodeDto selectedNode) {
		if(! (selectedNode instanceof ReportDto) && !(selectedNode instanceof ReportFolderDto))
			return false;
		if(! MainPanelView.ID_MAIN_PROPERTIES_VIEW.equals(view.getViewId()))
			return false;
	
		return true;
	}

	@Override
	protected void quickExportClicked(final AbstractNodeDto selectedNode) {
		final MessageBox mb = new DwMessageBox(ReportmanagerMessages.INSTANCE.quickExportIncludeVariantsTitle(), ReportmanagerMessages.INSTANCE.quickExportIncludeVariantsText());
		mb.setPredefinedButtons(PredefinedButton.YES,PredefinedButton.NO, PredefinedButton.CANCEL);
		mb.addDialogHideHandler(new DialogHideHandler() {
			
			@Override
			public void onDialogHide(DialogHideEvent event) {
				if(event.getHideButton() == PredefinedButton.CANCEL)
					return;
				
				boolean includeVariants = event.getHideButton() == PredefinedButton.YES; 
				startProgress();
				reDao.quickExport((AbstractReportManagerNodeDto) selectedNode, includeVariants, getExportCallback());	
			}
		});
		
		mb.show();
	}
	
	@Override
	protected void loadAndDisplayResult(
			AsyncCallback<String> callback) {
		reDao.loadResult(callback);
	}
}
