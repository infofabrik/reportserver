package net.datenwerke.rs.core.client.reportexporter.hookers;

import java.util.ArrayList;

import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.container.MarginData;
import com.sencha.gxt.widget.core.client.event.SelectEvent;
import com.sencha.gxt.widget.core.client.event.SelectEvent.SelectHandler;
import com.sencha.gxt.widget.core.client.menu.Item;
import com.sencha.gxt.widget.core.client.menu.Menu;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwAlertMessageBox;
import net.datenwerke.gxtdto.client.baseex.widget.menu.DwMenuItem;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.forms.simpleform.providers.configs.impl.SFFCTextAreaImpl;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.servercommunication.callback.NotamCallback;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.helper.simpleform.ExportTypeSelection;
import net.datenwerke.rs.core.client.helper.simpleform.config.SFFCExportTypeSelector;
import net.datenwerke.rs.core.client.reportexecutor.hooks.PrepareReportModelForStorageOrExecutionHook;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorInformation;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexporter.ReportExporterDao;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportexporter.locale.ReportExporterMessages;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.security.client.usermanager.dto.UserDto;
import net.datenwerke.security.client.usermanager.dto.ie.StrippedDownUser;

public class ExportViaMailHooker implements ExportExternalEntryProviderHook {
	
	@Inject
	private ReportExporterDao reDao;
	
	@Inject
	private LoginService loginService;
	
	@Inject
	private HookHandlerService hookHandler;
	
	@Override
	public void getMenuEntry(Menu menu, final ReportDto report,
			final ReportExecutorInformation info, ReportExecutorMainPanel mainPanel) {
		
		MenuItem item = new DwMenuItem(ReportExporterMessages.INSTANCE.exportViaMailLabel(), BaseIcon.ENVELOPE_O);
		menu.add(item);
		item.addSelectionHandler(new SelectionHandler<Item>() {
			
			@Override
			public void onSelection(SelectionEvent<Item> event) {
				displayExportDialog(report, info);
			}
		});
	}

	protected void displayExportDialog(final ReportDto report,
			final ReportExecutorInformation info) {
		final DwWindow window = new DwWindow();
		window.setHeaderIcon(BaseIcon.ENVELOPE_O);
		window.setHeading(ReportExporterMessages.INSTANCE.exportViaMailLabel());
		window.setSize(600, 550);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		
		final String formatKey = form.addField(ExportTypeSelection.class, ReportExporterMessages.INSTANCE.exportTypeLabel(), new SFFCExportTypeSelector() {
			
			@Override
			public ReportDto getReport() {
				return report;
			}
			
			@Override
			public String getExecuteReportToken() {
				return info.getExecuteReportToken();
			}
		});
		final String rcptKey = form.addField(StrippedDownUser.class, ReportExporterMessages.INSTANCE.selectUserLabel());
		
		final String subjKey = form.addField(String.class, ReportExporterMessages.INSTANCE.subjectLabel());
		final String msgKey = form.addField(String.class, ReportExporterMessages.INSTANCE.messageLabel(), new SFFCTextAreaImpl());
		window.add(form, new MarginData(10));
		
		UserDto user = loginService.getCurrentUser();
		StrippedDownUser sUser = StrippedDownUser.fromUser(user);
		
		ArrayList<StrippedDownUser> users = new ArrayList<StrippedDownUser>();
		users.add(sUser);
		
		form.setValue(subjKey, "");
		form.setValue(msgKey, "");
		form.setValue(rcptKey, users);
		
		form.loadFields();
		
		window.addCancelButton();

		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		submitBtn.addSelectHandler(new SelectHandler() {
			@Override
			public void onSelect(SelectEvent event) {
				ArrayList<StrippedDownUser> rcptList = (ArrayList<StrippedDownUser>) form.getValue(rcptKey);
				String subject = (String) form.getValue(subjKey);
				String message = (String) form.getValue(msgKey);
				ExportTypeSelection type = (ExportTypeSelection) form.getValue(formatKey);
				
				if(!type.isConfigured()){
					new DwAlertMessageBox(BaseMessages.INSTANCE.error(), ReportExporterMessages.INSTANCE.exportTypeNotConfigured()).show();
					return;
				}
				
				for(PrepareReportModelForStorageOrExecutionHook hooker : hookHandler.getHookers(PrepareReportModelForStorageOrExecutionHook.class)){
					if(hooker.consumes(report)){
						hooker.prepareForExecutionOrStorage(report, info.getExecuteReportToken());
					}
				}
				
				reDao.exportViaMail(
						report, info.getExecuteReportToken(), type.getOutputFormat(), type.getExportConfiguration(),
						subject, message, rcptList,
						new NotamCallback<Void>(ReportExporterMessages.INSTANCE.messageSend())
				);
				
				window.hide();
			}
		});
		window.addButton(submitBtn);
		
		window.show();
	}

	
}
