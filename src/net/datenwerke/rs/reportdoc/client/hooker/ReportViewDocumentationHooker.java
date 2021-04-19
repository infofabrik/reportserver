package net.datenwerke.rs.reportdoc.client.hooker;

import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.utilityservices.toolbar.ToolbarService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUiService;
import net.datenwerke.rs.reportdoc.client.locale.ReportDocumentationMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

/**
 * 
 *
 */
public class ReportViewDocumentationHooker implements
		ReportExecutorViewToolbarHook {

	private final ToolbarService toolbarService;
	private final ReportDocumentationUiService reportDocService;
	
	@Inject
	public ReportViewDocumentationHooker(
		ToolbarService toolbarService,
		ReportDocumentationUiService reportDocService
		){
		
		this.toolbarService = toolbarService;
		this.reportDocService = reportDocService;
	}
	
	@Override
	public boolean reportPreviewViewToolbarHook_addLeft(ToolBar toolbar, final ReportDto report, ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
		return false;
	}

	@Override
	public boolean reportPreviewViewToolbarHook_addRight(ToolBar toolbar, final ReportDto report, ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
		DwTextButton berichtsdokuBtn = toolbarService.createSmallButtonLeft(ReportDocumentationMessages.INSTANCE.berichtsdokuHelpText(), BaseIcon.BOOK);
		berichtsdokuBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				if(! report.isModified())
					reportDocService.openDocumentationForopen(report);
				else {
					ConfirmMessageBox cmb = new DwConfirmMessageBox(ReportDocumentationMessages.INSTANCE.reportChangedInfoHeader(), ReportDocumentationMessages.INSTANCE.reportChangedInfoMessage());
					cmb.addDialogHideHandler(new DialogHideHandler() {
						@Override
						public void onDialogHide(DialogHideEvent event) {
							if (event.getHideButton() == PredefinedButton.YES) 
								reportDocService.openDocumentationForopen(report);
						}
					});
						
					cmb.show();
				}
			}
		});
		toolbar.add(berichtsdokuBtn);
		
		return true;
	}

	@Override
	public void reportPreviewViewToolbarHook_reportUpdated(ReportDto report,
			ReportExecutorInformation info) {
		
	}
	
}
