package net.datenwerke.rs.dashboard.client.dashboard;

import java.util.List;

import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.AdministrationUIModule;
import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.dispatcher.Dispatchable;
import net.datenwerke.gf.client.dispatcher.hooks.DispatcherTakeOverHook;
import net.datenwerke.gf.client.history.HistoryCallback;
import net.datenwerke.gf.client.history.HistoryLocation;
import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.homepage.hooks.ClientMainModuleProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreePostSelectAsyncHook;
import net.datenwerke.gf.client.treedb.TreeDBHistoryCallback;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.FavoriteListDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.LibraryDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.ParameterDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.ReportDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.StaticHtmlDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dadgets.UrlDadgetProcessor;
import net.datenwerke.rs.dashboard.client.dashboard.dispatcher.DashboardInlineDispatcher;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DashboardNodeDto;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.DashboardToolbarParameterHooker;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.DashboardToolbarRefreshHooker;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.MarkNodeAsFavoriteHooker;
import net.datenwerke.rs.dashboard.client.dashboard.hookers.UserProfileDashboardPropertiesHooker;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DadgetProcessorHook;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.DashboardToolbarHook;
import net.datenwerke.rs.dashboard.client.dashboard.provider.annotations.DashboardManagerAdminViewTree;
import net.datenwerke.rs.dashboard.client.dashboard.provider.treehooker.DashboardManagerTreeConfigurationHooker;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardAdminGenericTargetIdentifier;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardAdminSecurityTargetDomainHooker;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewSecurityTargetDomainHooker;
import net.datenwerke.rs.dashboard.client.dashboard.ui.admin.DashboardManagerPanel;
import net.datenwerke.rs.dashboard.client.dashboard.ui.helper.DashboardProvider;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks.TsFavoriteMenuHook;
import net.datenwerke.rs.userprofile.client.userprofile.hooks.UserProfileCardProviderHook;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class DashboardUiStartup {

	@Inject
	public DashboardUiStartup(
		final HookHandlerService hookHandler,
		final WaitOnEventUIService waitOnEventService,
		final SecurityUIService securityService,
		
		@DashboardManagerAdminViewTree Provider<UITree> dashboardManagerTree,
		HistoryUiService historyService,
		EventBus eventBus,
		Provider<DashboardManagerPanel> dashboardAdminPanel,
		final DashboardDao dashboardDao,
		
		DashboardViewSecurityTargetDomainHooker securityTargetDomain,
		
		DashboardAdminSecurityTargetDomainHooker adminSecurityTargetDomain,
		
		final UserProfileDashboardPropertiesHooker userProfileProvider,
		
		final MainPanelViewProviderHooker mainPanelViewProvider,
		
		final Provider<DashboardAdminModule> adminModuleProvider,
		final DashboardManagerTreeConfigurationHooker treeConfigurator,
		
		final Provider<ReportDadgetProcessor> reportDadgetProcessor,
		final Provider<UrlDadgetProcessor> urlDadgetProcessor,
		final Provider<FavoriteListDadgetProcessor> favoriteListDadgetProcessor,
		final Provider<StaticHtmlDadgetProcessor> staticHtmlDadgetProcessor,
		final Provider<LibraryDadgetProcessor> libraryDadgetProcessor,
		final Provider<ParameterDadgetProcessor> parameterDadgetProcessor,
		
		final Provider<MarkNodeAsFavoriteHooker> markNodeAsFavoriteHooker,
		
		final DashboardToolbarParameterHooker dashboardToolbarParameterHooker,
		final DashboardToolbarRefreshHooker dashboardToolbarRefreshHooker,
		
		final Provider<DashboardClientMainModule> mainModuleProvider,
		
		final Provider<DashboardInlineDispatcher> dashboardDispatcher,
		
		Provider<DashboardProvider> dashboardProvider
		) {

		/* attach security target domain */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(new DashboardViewGenericTargetIdentifier()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);
		
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(new DashboardAdminGenericTargetIdentifier()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, adminSecurityTargetDomain);
		
		/* dispatch */
		hookHandler.attachHooker(DispatcherTakeOverHook.class, dashboardDispatcher);
		
		
		/* request callback after login and check for rights */
		waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED, new SynchronousCallbackOnEventTrigger() {
			
			public void execute(final WaitOnEventTicket ticket) {
				if(securityService.hasRight(DashboardViewGenericTargetIdentifier.class, ReadDto.class)){
					/* create module provideR */
					final ClientMainModuleProviderHook mainModule = new ClientMainModuleProviderHook(mainModuleProvider);
					
					/* attach hooker */
					hookHandler.attachHooker(ClientMainModuleProviderHook.class, mainModule, HookHandlerService.PRIORITY_HIGH + 5);

					hookHandler.attachHooker(TsFavoriteMenuHook.class, markNodeAsFavoriteHooker, HookHandlerService.PRIORITY_LOW);
					
					/* attach dadgets */
					hookHandler.attachHooker(DadgetProcessorHook.class, reportDadgetProcessor);
					hookHandler.attachHooker(DadgetProcessorHook.class, urlDadgetProcessor);
					hookHandler.attachHooker(DadgetProcessorHook.class, favoriteListDadgetProcessor);
					hookHandler.attachHooker(DadgetProcessorHook.class, staticHtmlDadgetProcessor);
					hookHandler.attachHooker(DadgetProcessorHook.class, libraryDadgetProcessor);
					hookHandler.attachHooker(DadgetProcessorHook.class, parameterDadgetProcessor);
					
					hookHandler.attachHooker(DashboardToolbarHook.class, dashboardToolbarParameterHooker,20);
					hookHandler.attachHooker(DashboardToolbarHook.class, dashboardToolbarRefreshHooker,30);
					
					/* user profile */
					hookHandler.attachHooker(UserProfileCardProviderHook.class, userProfileProvider);
				}
				
				waitOnEventService.signalProcessingDone(ticket);
			}
		});
		
		/* simpleform */
		hookHandler.attachHooker(FormFieldProviderHook.class, dashboardProvider, HookHandlerService.PRIORITY_LOW);
		
		/* config tree */
		hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);
		
		hookHandler.attachHooker(TreePostSelectAsyncHook.class, new TreePostSelectAsyncHook() {
			@Override
			public boolean consumes(AbstractNodeDto node) {
				return node instanceof DashboardNodeDto;
			}
			@Override
			public void postprocessNode(final AbstractNodeDto selectedNode,	final List<TreePostSelectAsyncHook> next) {
				DashboardDto db = ((DashboardNodeDto)selectedNode).getDashboard();
				dashboardDao.reloadDashboard(db, new AsyncCallback<DashboardDto>() {
					public void onSuccess(DashboardDto result) {
						((DashboardNodeDto)selectedNode).setDashboard(result);
						doNext(selectedNode, next);
					}

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
						
					};
					
				});
			}
		});
		
		/* test if user has rights to see datasource manager admin view */
		waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, new SynchronousCallbackOnEventTrigger(){
			public void execute(final WaitOnEventTicket ticket) {
				if(securityService.hasRight(DashboardAdminGenericTargetIdentifier.class, ReadDto.class)){
					hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),  HookHandlerService.PRIORITY_HIGH + 50);
					hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
				}
				waitOnEventService.signalProcessingDone(ticket);
			}
		});
		
		historyService.addHistoryCallback(DashboardUiModule.DASHBOARD_HISTORY_TOKEN,
				new TreeDBHistoryCallback(dashboardManagerTree, eventBus, dashboardAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID));
		
		historyService.addHistoryCallback(DashboardInlineDispatcher.LOCATION, new HistoryCallback() {
			
			@Override
			public void locationChanged(HistoryLocation location) {
				RootPanel.get().clear();
				DashboardInlineDispatcher dispatcher = dashboardDispatcher.get();
				Dispatchable dispatcheable = dispatcher.getDispatcheable();
				RootPanel.get().add(dispatcheable.getWidget());
			}
		});
		
	}
}
