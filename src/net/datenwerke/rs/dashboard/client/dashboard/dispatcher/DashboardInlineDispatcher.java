package net.datenwerke.rs.dashboard.client.dashboard.dispatcher;

import javax.inject.Inject;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.RepeatingCommand;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.container.Viewport;

import net.datenwerke.gf.client.dispatcher.Dispatchable;
import net.datenwerke.gf.client.dispatcher.hooks.DispatcherTakeOverHookImpl;
import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.rs.dashboard.client.dashboard.DashboardDao;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardContainer;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardMainComponent;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView;
import net.datenwerke.rs.dashboard.client.dashboard.ui.DashboardView.EditSuccessCallback;

public class DashboardInlineDispatcher extends DispatcherTakeOverHookImpl {

	public static final String LOCATION = "inlinedashboard";

	public static final String TYPE_SINGLE = "single";
	public static final String DASHBOARD_NR = "nr";
	public static final String DASHBOARD_NR_OFFSET = "nrOffset";
	public static final String DASHBOARD_ID = "id";

	public static final String IS_STATIC = "static";
	public static final String CYCLE_INTERVAL = "cycle_interval";

	private static final String P_CLASS_NAME = "css_class";

	private final Provider<DashboardView> dashboardViewProvider;
	private final Provider<DashboardMainComponent> mainComponentProvider;
	private final DashboardDao dashboardDao;

	private boolean isProtected;

	private Viewport viewport;

	private HistoryLocation hLocation;

	private int offset = 1;


	@Inject
	public DashboardInlineDispatcher(
			Provider<DashboardView> dashboardViewProvider,
			Provider<DashboardMainComponent> mainComponentProvider,
			DashboardDao dashboardDao
			){
		this.dashboardViewProvider = dashboardViewProvider;
		this.mainComponentProvider = mainComponentProvider;
		this.dashboardDao = dashboardDao;
	}

	@Override
	public Dispatchable getDispatcheable() {
		hLocation = getHistoryLocation();

		viewport = new Viewport();

		this.isProtected = ! "false".equals(hLocation.getParameterValue(IS_STATIC)); 
		
		if("user".equals(hLocation.getParameterValue("type"))){
			DashboardMainComponent comp = mainComponentProvider.get();
			if(hLocation.hasParameter(P_CLASS_NAME))
				comp.addStyleName(hLocation.getParameterValue(P_CLASS_NAME));
			
//			comp.hideToolbar();
			if(isProtected){
				comp.setProtected();
				comp.hideToolsButton();
				comp.hideAddButton();
			}
			viewport.add(comp);
			comp.selected();
		} else {
			dashboardDao.loadDashboardForDisplayFrom(hLocation, new RsAsyncCallback<DashboardDto>(){
				@Override
				public void onSuccess(DashboardDto dashboard) {
					loadAndDisplay(dashboard);
				}
				@Override
				public void onFailure(Throwable caught) {
					viewport.mask(caught.getMessage());
				}
			});
			
	
			if(hLocation.hasParameter(CYCLE_INTERVAL)){
				Scheduler.get().scheduleFixedDelay(new RepeatingCommand() {
					
					@Override
					public boolean execute() {
						viewport.clear();
						
						hLocation.addParameter(DASHBOARD_NR_OFFSET, "" + offset++);
						
						dashboardDao.loadDashboardForDisplayFrom(hLocation, new RsAsyncCallback<DashboardDto>(){
							@Override
							public void onSuccess(DashboardDto dashboard) {
								loadAndDisplay(dashboard);
							}
							@Override
							public void onFailure(Throwable caught) {
								viewport.mask(caught.getMessage());
							}
						});
						return true;
					}
				}, Integer.parseInt(hLocation.getParameterValue(CYCLE_INTERVAL)) * 1000);
			}
		}
		

		return new Dispatchable() {
			@Override
			public Widget getWidget() {
				return viewport;
			}
		};
	}

	@Override
	public String getLocation() {
		return LOCATION;
	}

	protected void loadAndDisplay(DashboardDto dashboardDto){
		final DashboardView dashboardView = dashboardViewProvider.get();

		/* check for class name */
		if(hLocation.hasParameter(P_CLASS_NAME)){
			dashboardView.addStyleName(hLocation.getParameterValue(P_CLASS_NAME));
		}
		
		dashboardView.init(new DashboardContainer() {
			@Override
			public void resizeDadget(DashboardDto dashboard, DadgetDto dadget,
					int offsetHeight) {
			}

			@Override
			public void remove(DashboardDto dashboard, DadgetDto dadget) {
			}

			@Override
			public void reload(DashboardDto dashboard) {
			}

			@Override
			public void dadgetConfigured(DashboardDto dashboard, DadgetDto dadget,ConfigType type,
					EditSuccessCallback callback) {
				callback.onSuccess(dashboard, dadget);
			}

			@Override
			public void addDadget(DashboardDto dashboard, DadgetDto dadget) {
			}

			@Override
			public void edited(DashboardDto dashboard) {
			}
		}, dashboardDto, isProtected);

		viewport.add(dashboardView);
		dashboardView.makeAwareOfSelection();

		viewport.forceLayout();
	}
}
