package net.datenwerke.rs.dashboard.client.dashboard.dadgets;

import java.util.Collection;
import java.util.Set;

import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.sencha.gxt.widget.core.client.button.IconButton;
import com.sencha.gxt.widget.core.client.button.ToolButton;

import net.datenwerke.gxtdto.client.baseex.widget.DwWindow;
import net.datenwerke.gxtdto.client.baseex.widget.btn.DwTextButton;
import net.datenwerke.gxtdto.client.baseex.widget.tool.DwToolButton;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleForm;
import net.datenwerke.gxtdto.client.locale.BaseMessages;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterInstanceDto;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportmanager.dto.interfaces.ReportContainerDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectionDialog.RepositoryProviderConfig;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.SFFCReportSelection;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardUiService;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.i.LibrarySpeficDrawer;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.LibraryDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.ReportDadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.decorator.ReportDadgetDtoDec;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.ReportDadgetExportHook;
import net.datenwerke.rs.dashboard.client.dashboard.locale.DashboardMessages;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DadgetPanel;
import net.datenwerke.rs.theme.client.icon.BaseIcon;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.TsDiskReportReferenceDto;

public class ReportDadgetProcessor implements DadgetProcessorHook, LibrarySpeficDrawer {

	public static final String REPORT_PROPERTY_KEY = "propertyReport";
	public static final String REFRESH_PROPERTY_KEY = "refreshReport";
	public static final String HEIGHT_KEY = "heightReport";
	public static final String SHOW_EXECUTE_KEY = "showExecute";

	private final HookHandlerService hookHandler;
	private final DashboardUiService dashboardService;
	private final ReportExecutorUIService reportExecutorService;

	@Inject
	public ReportDadgetProcessor(
			HookHandlerService hookHandler, 
			DashboardUiService dashboardService,
			ReportExecutorUIService reportExecutorService
			) {
		this.hookHandler = hookHandler;
		this.dashboardService = dashboardService;
		this.reportExecutorService = reportExecutorService;
	}

	@Override
	public BaseIcon getIcon() {
		return BaseIcon.REPORT_PICTURE;
	}

	@Override
	public boolean isRedrawOnMove() {
		return true;
	}

	@Override
	public String getTitle() {
		return DashboardMessages.INSTANCE.reportDadgetTitle();
	}

	@Override
	public String getDescription() {
		return DashboardMessages.INSTANCE.reportDadgetDescription();
	}

	@Override
	public boolean consumes(DadgetDto dadget) {
		return dadget instanceof ReportDadgetDto;
	}

	@Override
	public DadgetDto instantiateDadget() {
		return new ReportDadgetDtoDec();
	}

	@Override
	public boolean readyToDisplayParameters(DadgetPanel dadgetPanel) {
		ReportDadgetDto rDadget = (ReportDadgetDto)dadgetPanel.getDadget();
		final ReportDto report = getReportOrReference(rDadget); 
		return null != report && ! report.getParameterInstances().isEmpty();
	}

	@Override
	public void draw(DadgetDto dadget, final DadgetPanel panel){
		draw(dadget, panel, dadget.getParameterInstances());
	}
	
	@Override
	public void drawForLibrary(LibraryDadgetDto libraryDadget, DadgetDto dadgetToDraw, DadgetPanel panel) {
		draw(dadgetToDraw, panel, libraryDadget.getParameterInstances());
	}
	
	
	protected void draw(DadgetDto dadget, DadgetPanel panel, Set<ParameterInstanceDto> parameterInstances) {
		final ReportDadgetDto rDadget = (ReportDadgetDto) dadget;
		final ReportDto report = getReportOrReference(rDadget); 

		if(null == report)
			return;

		dashboardService.showHideParameterToolButton(panel, report);
		
		panel.setHeading(report.getName());

		for(ReportDadgetExportHook hooker : hookHandler.getHookers(ReportDadgetExportHook.class)){
			if(hooker.consumes((ReportDadgetDto)dadget)){
				hooker.displayReport(rDadget, report, panel, parameterInstances);
				break;
			}
		}
	}

	private ReportDto getReportOrReference(ReportDadgetDto dadget){
		ReportDto report = dadget.getReport();
		if(null == report && null != dadget.getReportReference())
			return dadget.getReportReference().getReport();
		return report;
	}

	@Override
	public void addTools(final DadgetPanel dadgetPanel) {
		IconButton editParamBtn = dashboardService.addParameterToolButtonTo(dadgetPanel, this);

		/* if it does not have parameter instances, no filter */
		final ReportDadgetDto rDadget = (ReportDadgetDto) dadgetPanel.getDadget();
		final ReportDto report = getReportOrReference(rDadget); 

		if(null == report || report.getParameterInstances().isEmpty())
			editParamBtn.asWidget().setVisible(false);
		else
			editParamBtn.asWidget().setVisible(true);
		
		/* add execute button */
		if (((ReportDadgetDto) dadgetPanel.getDadget()).isShowExecuteButton()) {
			IconButton execBtn = new ToolButton(DwToolButton.PLAY);
			execBtn.addSelectHandler(event -> {
				if (null != ((ReportDadgetDto) dadgetPanel.getDadget()).getReportForDisplay())
					reportExecutorService
							.executeReport(((ReportDadgetDto) dadgetPanel.getDadget()).getReportForDisplay());
			});
			dadgetPanel.addTool(execBtn);
		}
	}

	@Override
	public void displayConfigDialog(final DadgetDto dadget,
			final DadgetConfigureCallback dadgetConfigureCallback) {
		final DwWindow window = new DwWindow();
		window.setHeading(DashboardMessages.INSTANCE.configDadgetTitle());
		window.setHeaderIcon(BaseIcon.COG);
		window.setSize(300, 350);

		final SimpleForm form = SimpleForm.getInlineInstance();
		form.setFocusOnShow(false);
		
		form.addField(Boolean.class, SHOW_EXECUTE_KEY, DashboardMessages.INSTANCE.showExecuteIcon());
		form.setValue(SHOW_EXECUTE_KEY, ((ReportDadgetDto)dadget).isShowExecuteButton());

		form.addField(ReportSelectionDialog.class, REPORT_PROPERTY_KEY, DashboardMessages.INSTANCE.reportSelection(), new SFFCReportSelection() {

			@Override
			public Collection<RepositoryProviderConfig> getRepositoryConfigs() {
				return null;
			}

			@Override
			public boolean showCatalog() {
				return true;
			}

			@Override
			public boolean showVariantsInCatalog() {
				return true;
			}
		});
		if(null != ((ReportDadgetDto)dadget).getReport())
			form.setValue(REPORT_PROPERTY_KEY, ((ReportDadgetDto)dadget).getReport());
		if(null != ((ReportDadgetDto)dadget).getReportReference())
			form.setValue(REPORT_PROPERTY_KEY, ((ReportDadgetDto)dadget).getReportReference());

		form.addField(Long.class, REFRESH_PROPERTY_KEY, DashboardMessages.INSTANCE.reloadIntervalLabel());
		form.setValue(REFRESH_PROPERTY_KEY, dadget.getReloadInterval());

		form.addField(Integer.class, HEIGHT_KEY, DashboardMessages.INSTANCE.heightLabel());
		form.setValue(HEIGHT_KEY, dadget.getHeight());

		for(ReportDadgetExportHook hooker : hookHandler.getHookers(ReportDadgetExportHook.class))
			hooker.configureDisplayConfigDialog((ReportDadgetDto)dadget, form);

		form.loadFields();
		window.add(form);

		DwTextButton cancelBtn = new DwTextButton(BaseMessages.INSTANCE.cancel());
		window.addButton(cancelBtn);
		cancelBtn.addSelectHandler( event -> {
			window.hide();
			dadgetConfigureCallback.cancelled();
		});
				
		DwTextButton submitBtn = new DwTextButton(BaseMessages.INSTANCE.submit());
		window.addButton(submitBtn);
		submitBtn.addSelectHandler( event -> {
			window.hide();
			

			ReportContainerDto container = (ReportContainerDto) form.getValue(REPORT_PROPERTY_KEY);
			if(null == container){
				((ReportDadgetDto)dadget).setReport(null);
				((ReportDadgetDto)dadget).setReportReference(null);
			} else if (container instanceof TsDiskReportReferenceDto){
				TsDiskReportReferenceDto oldReference = ((ReportDadgetDto)dadget).getReportReference();
				if(null == oldReference || ! oldReference.equals(container)){
					Set<ParameterInstanceDto> pInst = ((ReportDadgetDto)dadget).getParameterInstances();
					pInst.clear();
					((ReportDadgetDto)dadget).setParameterInstances(pInst);
				}

				((ReportDadgetDto)dadget).setReport(null);
				((ReportDadgetDto)dadget).setReportReference((TsDiskReportReferenceDto) container);
			} else {
				ReportDto oldReport = ((ReportDadgetDto)dadget).getReport();
				if(null == oldReport || ! oldReport.equals(container.getReport())){
					Set<ParameterInstanceDto> pInst = ((ReportDadgetDto)dadget).getParameterInstances();
					pInst.clear();
					((ReportDadgetDto)dadget).setParameterInstances(pInst);
				}

				((ReportDadgetDto)dadget).setReport(container.getReport());
				((ReportDadgetDto)dadget).setReportReference(null);
			}
			((ReportDadgetDto)dadget).setShowExecuteButton((boolean)form.getValue(SHOW_EXECUTE_KEY));
			dadget.setReloadInterval((long) form.getValue(REFRESH_PROPERTY_KEY));
			dadget.setHeight((int) form.getValue(HEIGHT_KEY));


			for(ReportDadgetExportHook hooker : hookHandler.getHookers(ReportDadgetExportHook.class)){
				if(hooker.consumes((ReportDadgetDto)dadget)){
					hooker.storeConfig((ReportDadgetDto)dadget, form);
					break;
				}
			}

			dadgetConfigureCallback.configuringDone();
		});
				
		window.show();
	}

	@Override
	public Widget getAdminConfigDialog(final DadgetDto dadget, SimpleForm wrappingForm) {
		if(dadget.getHeight() < 1)
			dadget.setHeight(250);
		
		final SimpleForm form = SimpleForm.getInlineInstance();
		form.setFieldWidth(400);
		form.setLabelWidth(90);
		
		form.addField(Boolean.class, SHOW_EXECUTE_KEY, DashboardMessages.INSTANCE.showExecuteIcon());
		form.setValue(SHOW_EXECUTE_KEY, ((ReportDadgetDto)dadget).isShowExecuteButton());
		
		form.addField(ReportSelectionDialog.class, REPORT_PROPERTY_KEY, DashboardMessages.INSTANCE.reportSelection(),
				new SFFCReportSelection() {

					@Override
					public Collection<RepositoryProviderConfig> getRepositoryConfigs() {
						return null;
					}

					@Override
					public boolean showCatalog() {
						return true;
					}

					@Override
					public boolean showVariantsInCatalog() {
						return true;
					}
				});
		form.setValue(REPORT_PROPERTY_KEY, ((ReportDadgetDto) dadget).getReport());
		
		for (final ReportDadgetExportHook hooker : hookHandler.getHookers(ReportDadgetExportHook.class)) {
			if (hooker.consumes((ReportDadgetDto) dadget)) {
				hooker.configureDisplayConfigDialog((ReportDadgetDto) dadget, form);
				if (hooker.consumes((ReportDadgetDto) dadget)) {
					form.addValueChangeHandler(hooker.getPropertyName(), event -> {
						if (hooker.consumes((ReportDadgetDto) dadget))
							hooker.storeConfig((ReportDadgetDto) dadget, form);
					});
				}
			}
		}
		
		form.bind(dadget);
		
		form.addValueChangeHandler(REPORT_PROPERTY_KEY, event -> {
			ReportContainerDto container = (ReportContainerDto) form.getValue(REPORT_PROPERTY_KEY);
			if (null == container) {
				((ReportDadgetDto) dadget).setReport(null);
				((ReportDadgetDto) dadget).setReportReference(null);
			} else if (container instanceof TsDiskReportReferenceDto) {
				((ReportDadgetDto) dadget).setReport(null);
				((ReportDadgetDto) dadget).setReportReference((TsDiskReportReferenceDto) container);
			} else {
				((ReportDadgetDto) dadget).setReport(container.getReport());
				((ReportDadgetDto) dadget).setReportReference(null);
			}
		});
		
		return form;
	}

	@Override
	public boolean supportsDadgetLibrary() {
		return true;
	}

	@Override
	public boolean hasConfigDialog() {
		return true;
	}
}
