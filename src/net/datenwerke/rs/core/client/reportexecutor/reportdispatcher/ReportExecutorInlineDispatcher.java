package net.datenwerke.rs.core.client.reportexecutor.reportdispatcher;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import com.google.gwt.user.client.ui.Widget;
import com.sencha.gxt.widget.core.client.Component;
import com.sencha.gxt.widget.core.client.ContentPanel;
import com.sencha.gxt.widget.core.client.container.Viewport;

import net.datenwerke.gf.client.dispatcher.Dispatchable;
import net.datenwerke.gf.client.dispatcher.hooks.DispatcherTakeOverHookImpl;
import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.propertywidgets.FilterView;
import net.datenwerke.rs.base.client.reportengines.table.prefilter.propertywidgets.PreFilterView;
import net.datenwerke.rs.computedcolumns.client.computedcolumns.propertywidgets.ComputedColumnsView;
import net.datenwerke.rs.core.client.parameters.propertywidgets.ParameterView;
import net.datenwerke.rs.core.client.reportexecutor.ExecuteReportConfiguration;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorDao;
import net.datenwerke.rs.core.client.reportexecutor.ReportExecutorUIService;
import net.datenwerke.rs.core.client.reportexecutor.events.ExecutorEventHandler;
import net.datenwerke.rs.core.client.reportexecutor.events.ReportExecutorEvent;
import net.datenwerke.rs.core.client.reportexecutor.ui.ReportExecutorMainPanelView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.MainPanelAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.aware.SelectionAwareView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.AbstractReportPreviewView;
import net.datenwerke.rs.core.client.reportexecutor.ui.preview.PreviewViewFactory;
import net.datenwerke.rs.core.client.reportexecutor.variantstorer.NoVariantStorerConfig;
import net.datenwerke.rs.core.client.reportexecutor.variantstorer.VariantStorerConfig;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.scripting.client.scripting.ScriptingUiService;

public class ReportExecutorInlineDispatcher extends DispatcherTakeOverHookImpl {

	public static final String LOCATION = "inlinereport";
	
	public static final String PARAM_KEY = "key";
	public static final String PARAM_ID = "id";
	public static final String PARAM_UUID = "uuid";
	public static final String PARAM_PATH = "path";
	
	public static final String TYPE_FULL = "full";
	public static final String TYPE_PREVIEW = "preview";
	public static final String TYPE_PARAMETER = "parameter";
	public static final String TYPE_COMPUTED_COLUMS = "computedcolumns";
	public static final String TYPE_PREFILTER = "prefilter";
	public static final String TYPE_LIST_CONFIG = "listconfig";
	
	public static final String DEFAULT_VIEW = "defaultview";
	public static final String VIEWS = "views";

	private final ReportExecutorDao reportExecutorDao;
	private final ReportExecutorUIService reportExecutorService;
	
	private final WaitOnEventUIService waitOnEventService;
	
	@Inject
	public ReportExecutorInlineDispatcher(
		ReportExecutorDao reportExecutorDao,
		ReportExecutorUIService reportExecutorService,
		WaitOnEventUIService waitOnEventService
		){
		this.reportExecutorDao = reportExecutorDao;
		this.reportExecutorService = reportExecutorService;
		this.waitOnEventService = waitOnEventService;
	}
	
	@Override
	protected boolean checkParameters(HistoryLocation hLocation) {
		return hLocation.hasParameter(PARAM_ID) || hLocation.hasParameter(PARAM_KEY) || hLocation.hasParameter(PARAM_UUID) || hLocation.hasParameter(PARAM_PATH);
	}
	
	@Override
	public Dispatchable getDispatcheable() {
		final HistoryLocation hLocation = getHistoryLocation();
		
		final Viewport vp = new Viewport();
		
		waitOnEventService.callbackOnEvent(ScriptingUiService.REPORTSERVER_EVENT_AFTER_EXECUTE_LOGIN_SCRIPT, new SynchronousCallbackOnEventTrigger() {
			
			@Override
			public void execute(final WaitOnEventTicket ticket) {
				reportExecutorDao.loadReportForExecutionFrom(hLocation, new RsAsyncCallback<ReportDto>(){
					public void onSuccess(ReportDto result) {
						String type = hLocation.getParameterValue("type");
						if(TYPE_PREVIEW.equals(type))
							displayPreview(result, vp);
						else if (TYPE_PARAMETER.equals(type)) 
							displayView(ParameterView.VIEW_ID, hLocation, result, vp);
						else if (TYPE_COMPUTED_COLUMS.equals(type))
							displayView(ComputedColumnsView.VIEW_ID, hLocation, result, vp);
						else if (TYPE_PREFILTER.equals(type))
							displayView(PreFilterView.VIEW_ID, hLocation, result, vp);
						else if (TYPE_LIST_CONFIG.equals(type))
							displayView(FilterView.VIEW_ID, hLocation, result, vp);
						else
							displayFull(hLocation, result, vp);
						
						waitOnEventService.signalProcessingDone(ticket);
					}
					
					@Override
					public void onFailure(Throwable caught) {
						waitOnEventService.signalProcessingDone(ticket);
					}
				});
				
				
			}
		});
		
		
		return new Dispatchable() {
			
			@Override
			public Widget getWidget() {
				return vp;
			}
		};
	}


	protected void displayPreview(ReportDto result, Viewport vp) {
		PreviewViewFactory factory = reportExecutorService.getPreviewReportComponent(result);
		ReportExecutorMainPanelView view = factory.newInstance(result, Collections.EMPTY_SET);
		
		if(view instanceof AbstractReportPreviewView)
			((AbstractReportPreviewView)view).setDelayModalWindowOnExecution(10000);
		
		Widget component = view.getViewComponent();
		
		view.setExecuteReportToken(reportExecutorService.createExecuteReportToken(result));
		if(view instanceof MainPanelAwareView)
			((MainPanelAwareView)view).makeAwareOfMainPanel(vp);
		if(view instanceof SelectionAwareView)
			((SelectionAwareView)view).makeAwareOfSelection();
		
		vp.add(component);
		vp.forceLayout();
	}
	
	
	protected void displayView(final String viewId, HistoryLocation hLocation, final ReportDto result, Viewport vp) {
		final Set<String> views = new HashSet<String>();
		if(hLocation.hasParameter(VIEWS)){
			String viewParam = hLocation.getParameterValue(VIEWS);
			for(String view : viewParam.split("\\|")){
				views.add(view.trim().toLowerCase());
			}
		}
		
		Component executeReportComponent = reportExecutorService.getExecuteReportComponent(
				result,
				new ExecutorEventHandler() {
					@Override
					public void handleEvent(ReportExecutorEvent event) {
					}
				}, new ExecuteReportConfiguration() {
					@Override
					public VariantStorerConfig getVariantStorerConfig() {
						return new NoVariantStorerConfig();
					}

					@Override
					public String getViewId() {
						return viewId;
					}

					@Override
					public boolean handleError(Throwable t) {
						return false;
					}
					
					@Override
					public boolean acceptView(String viewId) {
						if (result.isConfigurationProtected()) {
							if (!viewId.equals(TYPE_PREVIEW)) 
								return false;
						}
						return views.isEmpty() || null == viewId || views.contains(viewId.toLowerCase());
					}
					
					@Override
					public String getUrlParameters() {
						return null;
					}
				}, new InlineReportView()
				);
		if(executeReportComponent instanceof ContentPanel)
			((ContentPanel) executeReportComponent).setHeaderVisible(false);

		vp.add(executeReportComponent);
		vp.forceLayout();
	}
	
	protected void displayFull(HistoryLocation hLocation, final ReportDto result, Viewport vp) {
		final String defaultView = hLocation.hasParameter(DEFAULT_VIEW) ? hLocation.getParameterValue(DEFAULT_VIEW) : AbstractReportPreviewView.VIEW_ID; 
		
		final Set<String> views = new HashSet<String>();
		if(hLocation.hasParameter(VIEWS)){
			String viewParam = hLocation.getParameterValue(VIEWS);
			for(String view : viewParam.split("\\|")){
				views.add(view.trim().toLowerCase());
			}
		}
		
		Component executeReportComponent = reportExecutorService.getExecuteReportComponent(
				result,
				new ExecutorEventHandler() {
					@Override
					public void handleEvent(ReportExecutorEvent event) {
					}
				}, new ExecuteReportConfiguration() {
					@Override
					public VariantStorerConfig getVariantStorerConfig() {
						return new NoVariantStorerConfig();
					}

					@Override
					public String getViewId() {
						return defaultView;
					}

					@Override
					public boolean handleError(Throwable t) {
						return false;
					}
					
					@Override
					public boolean acceptView(String viewId) {
						if (result.isConfigurationProtected()) {
							if (!viewId.equals(TYPE_PREVIEW))
								return false;
						}
						return views.isEmpty() || null == viewId || views.contains(viewId.toLowerCase());
					}
					
					@Override
					public String getUrlParameters() {
						return null;
					}
					
				}, new InlineReportView()
				);
		if(executeReportComponent instanceof ContentPanel)
			((ContentPanel) executeReportComponent).setHeaderVisible(false);

		vp.add(executeReportComponent);
		vp.forceLayout();
	}

	@Override
	public String getLocation() {
		return LOCATION;
	}

}
