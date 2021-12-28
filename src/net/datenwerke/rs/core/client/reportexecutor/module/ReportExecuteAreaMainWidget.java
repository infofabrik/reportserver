package net.datenwerke.rs.core.client.reportexecutor.module;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.user.client.History;
import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.core.client.Style.HideMode;
import com.sencha.gxt.core.client.util.Format;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.Dialog.PredefinedButton;
import com.sencha.gxt.widget.core.client.TabItemConfig;
import com.sencha.gxt.widget.core.client.box.ConfirmMessageBox;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent;
import com.sencha.gxt.widget.core.client.event.DialogHideEvent.DialogHideHandler;

import net.datenwerke.gxtdto.client.baseex.widget.DwTabPanel;
import net.datenwerke.gxtdto.client.baseex.widget.mb.DwConfirmMessageBox;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIModule;
import net.datenwerke.rs.core.client.reportexecutor.locale.ReportexecutorMessages;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanel;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.CloseableAware;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportVariantDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;

public class ReportExecuteAreaMainWidget extends DwTabPanel {

	private ReportExecuteAreaModule module;
	
	private Map<Component, ReportDto> reportLookup = new HashMap<Component, ReportDto>();
	private Map<Component, String> urlParams = new HashMap<Component, String>();

	private Collection<Widget> markedChange = new HashSet<Widget>();

	public ReportExecuteAreaMainWidget(){
		super(GWT.<TabPanelAppearance> create(TabPanelBottomAppearance.class));
		
		setCloseContextMenu(true);
		setTabScroll(true);
		setAnimScroll(true);
		setBorders(false);
		setBodyBorder(false);
		setHideMode(HideMode.OFFSETS);
		
		addSelectionHandler(new SelectionHandler<Widget>() {
			@Override
			public void onSelection(SelectionEvent<Widget> event) {
				Widget selectedItem = event.getSelectedItem();
				if(null != selectedItem){
					ReportDto report = reportLookup.get(selectedItem);
					if(null != report){
						String additionalParams = urlParams.get(selectedItem);
						if(null == additionalParams)
							additionalParams = "";
						else
							additionalParams = "&" + additionalParams;
						
						History.newItem(ReportExecutorUIModule.REPORT_EXECUTOR_HISTORY_TOKEN + "/uuid:" + report.getUuid() + additionalParams, false);
					}
				}
			}
		});
	}
	
	public void addExecutionComponent(ReportDto report, final Component displayComponent, String urlParameters) {
		TabItemConfig tab = new TabItemConfig(shortenReportName(report));
		tab.setClosable(true);

		/* add tab select it and layout */
		add(displayComponent, tab);
		
		reportLookup.put(displayComponent, report);
		if(null != urlParameters)
			urlParams.put(displayComponent, urlParameters);
		
		Scheduler.get().scheduleDeferred(new ScheduledCommand() {
			
			@Override
			public void execute() {
				setActiveWidget(displayComponent);
			}
		});
		
	}


	public String shortenReportName(ReportDto report) {
		int maxlength = 10;
		
		String name = report.getName();
		if(null == name)
			name = BaseMessages.INSTANCE.unknown();
		name = Format.ellipse(name, maxlength);
		
		if(report instanceof ReportVariantDto){
			String baseName = Format.ellipse(((ReportVariantDto)report).getBaseReport().getName(), maxlength);
			return baseName + " : " + name; //$NON-NLS-1$
		} else
			return name;
	}
	
	public String getFullReportName(ReportDto report) {
		String name = report.getName();
		
		if(report instanceof ReportVariantDto){
			String baseName = ((ReportVariantDto)report).getBaseReport().getName();
			return baseName + " : " + name; //$NON-NLS-1$
		} else
			return name;
	}

	public void closeCurrent() {
		Widget currentTab = getActiveWidget();
        close(currentTab);
	}
	

	public void forceCloseCurrent() {
		Widget currentTab = getActiveWidget();
        forceClose(currentTab);
	}
	
	public void markCurrentChanged() {
		Widget currentTab = getActiveWidget();
		if(! markedChange.contains(currentTab)){
			TabItemConfig config = getConfig(currentTab);
			config.setText("*" + config.getText());
			update(currentTab, config);
			markedChange.add(currentTab);
		}
	}
	
	@Override
	protected boolean hasUnchangedChanges(Widget item) {
		ReportDto report = reportLookup.get(item);
		if(null != report && report.isModified())
			return true;
		return false;
	}
	
	@Override
	protected void close(final Widget item) {
		boolean recheckClose = false;
		if(item instanceof CloseableAware)
			recheckClose = ((CloseableAware)item).needToConfirmClose();
		
		ReportDto report = reportLookup.get(item);
		if(!recheckClose && ! report.isModified())
			forceClose(item);
		else {
			ConfirmMessageBox cmb = new DwConfirmMessageBox(ReportexecutorMessages.INSTANCE.closeChangedReportTitle(), ReportexecutorMessages.INSTANCE.closeChangedReportMessage());
			cmb.addDialogHideHandler(new DialogHideHandler() {
				@Override
				public void onDialogHide(DialogHideEvent event) {
					if (event.getHideButton() == PredefinedButton.YES) {
						forceClose(item);
					}	
				}
			});
			cmb.show();
		}
	}
	

	@Override
	protected void forceClose(Widget item) {	
		if(item instanceof ReportExecutorMainPanel){
			((ReportExecutorMainPanel) item).cleanup();
		}
		
		super.close(item);
		reportLookup.remove(item);
		
		if(0 == getWidgetCount())
			module.removeModule();
	}

	public void setModule(ReportExecuteAreaModule reportExecuteAreaModule) {
		this.module = reportExecuteAreaModule;
	}



}
