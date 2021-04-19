package net.datenwerke.rs.core.client.reportmanager.helper.reportselector;

import com.google.gwt.dom.client.NativeEvent;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.menu.Menu;

import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.gxtdto.client.ui.helper.nav.WestPropertiesDialog;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.core.client.reportmanager.locale.ReportmanagerMessages;
import net.datenwerke.rs.theme.client.icon.BaseIcon;

public class ReportSelectionDialog extends WestPropertiesDialog {

	public interface ReportSelectionDialogEventHandler{
		public void handleDoubleClick(ReportContainerDto dto, ReportSelectionRepositoryProviderHook hooker, NativeEvent event, Object... info);

		public Menu getContextMenuFor(ReportContainerDto dto,
				ReportSelectionRepositoryProviderHook hooker, Object... info);
		
		public boolean handleSubmit(ReportContainerDto container);
	}
	
	public static interface ReportSelectionCardConfig extends CardConfig {
		ReportContainerDto getSelectedReport();
	}
	
	public interface RepositoryProviderConfig{}
	
	private final HookHandlerService hookHandler;
	
	private ReportSelectionDialogEventHandler eventHandler;
	
	@Inject
	public ReportSelectionDialog(
		HookHandlerService hookHandler
		){
	
		this.hookHandler = hookHandler;
		
		init();
	}

	public void init() {
		setHeading(ReportmanagerMessages.INSTANCE.selectReportHeader());
		setHeaderIcon(BaseIcon.REPORT_ADD);
		setClosable(true);
		setOnEsc(true);
		
		/* add close buttons */
		DwTextButton closeBtn = new DwTextButton(BaseMessages.INSTANCE.close());
		closeBtn.addSelectHandler(event -> hide());
		addButton(closeBtn);
	}

	public void initRepositories(final ReportDto selectedReport, final RepositoryProviderConfig... configs) {
	   hookHandler.getHookers(ReportSelectionRepositoryProviderHook.class).forEach(hooker -> hooker.addCards(this, selectedReport, configs));
	}

	public void handleDoubleClick(ReportContainerDto dto, ReportSelectionRepositoryProviderHook hooker, NativeEvent event, Object... info) {
		if(null != eventHandler)
			eventHandler.handleDoubleClick(dto, hooker, event, info);
	}

	public Menu getContextMenuFor(ReportContainerDto dto,
			ReportSelectionRepositoryProviderHook hooker, Object... info) {
		if(null != eventHandler)
			return eventHandler.getContextMenuFor(dto, hooker, info);
		return null;
	}

	public ReportSelectionDialogEventHandler getEventHandler() {
		return eventHandler;
	}

	public void setEventHandler(ReportSelectionDialogEventHandler eventHandler) {
		this.eventHandler = eventHandler;
	}

	public void initSubmitButton() {
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		submitBtn.addSelectHandler(event -> {
			CardConfig card = getSelectedCard();
			if(! (card instanceof ReportSelectionCardConfig))
				return;
			
			ReportContainerDto container = ((ReportSelectionCardConfig)card).getSelectedReport();
			if(eventHandler.handleSubmit(container))
				hide();
		});
		addButton(submitBtn);
	}
	
}
