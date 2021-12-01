package net.datenwerke.rs.core.client.reportmanager;

import java.util.ArrayList;

import com.google.gwt.core.client.Scheduler;
import com.google.gwt.core.client.Scheduler.ScheduledCommand;
import com.google.gwt.event.shared.EventBus;
import com.google.inject.Inject;
import com.google.inject.Provider;
import com.sencha.gxt.data.shared.TreeStore;

import net.datenwerke.gf.client.administration.AdministrationUIModule;
import net.datenwerke.gf.client.administration.AdministrationUIService;
import net.datenwerke.gf.client.administration.hooks.AdminModuleProviderHook;
import net.datenwerke.gf.client.history.HistoryUiService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewProviderHook;
import net.datenwerke.gf.client.managerhelper.hooks.TreeConfiguratorHook;
import net.datenwerke.gf.client.managerhelper.tree.ManagerHelperTree;
import net.datenwerke.gf.client.treedb.TreeDBHistoryCallback;
import net.datenwerke.gf.client.treedb.UITree;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.gxtdto.client.objectinformation.hooks.ObjectInfoKeyInfoProvider;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportmanager.helper.reportselector.ReportSelectorSimpleFormProvider;
import net.datenwerke.rs.core.client.reportmanager.hookers.MainPanelViewProviderHooker;
import net.datenwerke.rs.core.client.reportmanager.hookers.ReportCatalogOnDemandRepositoryProvider;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportSelectionRepositoryProviderHook;
import net.datenwerke.rs.core.client.reportmanager.objectinfo.FolderObjectInfo;
import net.datenwerke.rs.core.client.reportmanager.provider.annotations.ReportManagerAdminViewTree;
import net.datenwerke.rs.core.client.reportmanager.provider.treehookers.ReportManagerTreeConfigurationHooker;
import net.datenwerke.rs.core.client.reportmanager.security.ReportManagerGenericTargetIdentifier;
import net.datenwerke.rs.core.client.reportmanager.security.ReportManagerViewSecurityTargetDomainHooker;
import net.datenwerke.rs.core.client.reportmanager.ui.ReportManagerPanel;
import net.datenwerke.rs.core.client.reportvariants.ui.ReportVariantsView;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.client.security.hooks.GenericSecurityViewDomainHook;
import net.datenwerke.security.client.security.hooks.GenericTargetProviderHook;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

public class ReportManagerUIStartup  {

	@Inject
	public ReportManagerUIStartup(
		final WaitOnEventUIService waitOnEventService,
		
		final HookHandlerService hookHandler,
		final Provider<ReportManagerAdminModule> adminModuleProvider,
		
		final ReportManagerUIService reportManagerUIService,
		ReportManagerTreeManagerDao reportManagerTreeHandler,
		
		MainPanelViewProviderHooker mainPanelViewProvider,

		ReportManagerViewSecurityTargetDomainHooker securityTargetDomain,
		
		final SecurityUIService securityService,
		
		final FolderObjectInfo folderObjectInfo,
		
		final ReportManagerTreeConfigurationHooker treeConfigurator,
		
		Provider<ReportSelectorSimpleFormProvider> reportSelectionSsfProvider,
		
		ReportCatalogOnDemandRepositoryProvider catalogOnDemandRepositoryProvider,
		
		@ReportManagerAdminViewTree Provider<UITree> reportManagerTree,
		HistoryUiService historyService,
		EventBus eventBus,
		Provider<ReportManagerPanel> reportManagerAdminPanel
		){
		
		/* config tree */
		hookHandler.attachHooker(TreeConfiguratorHook.class, treeConfigurator);
		
		/* attach object infos */
		hookHandler.attachHooker(ObjectInfoKeyInfoProvider.class, folderObjectInfo);
		
		/* attach views */
		hookHandler.attachHooker(MainPanelViewProviderHook.class, mainPanelViewProvider);
			
		/* simple form */
		hookHandler.attachHooker(FormFieldProviderHook.class, reportSelectionSsfProvider);
		
		/* attach repository provider */
		hookHandler.attachHooker(ReportSelectionRepositoryProviderHook.class, catalogOnDemandRepositoryProvider, HookHandlerService.PRIORITY_HIGH);
		
		/* attach security target domains */
		hookHandler.attachHooker(GenericTargetProviderHook.class, new GenericTargetProviderHook(securityTargetDomain.genericSecurityViewDomainHook_getTargetId()));
		hookHandler.attachHooker(GenericSecurityViewDomainHook.class, securityTargetDomain);
		
		/* test if user has rights to see report manager admin view */
		waitOnEventService.callbackOnEvent(AdministrationUIService.REPORTSERVER_EVENT_HAS_ADMIN_RIGHTS, ticket -> {
			if(securityService.hasRight(ReportManagerGenericTargetIdentifier.class, ReadDto.class))
				hookHandler.attachHooker(AdminModuleProviderHook.class, new AdminModuleProviderHook(adminModuleProvider),  HookHandlerService.PRIORITY_HIGH + 30);

			waitOnEventService.signalProcessingDone(ticket);
		});
		
		/* configureHistory */
		historyService.addHistoryCallback(ReportManagerUIModule.REPORTMANAGER_FAV_HISTORY_TOKEN,
				new TreeDBHistoryCallback(reportManagerTree, eventBus, reportManagerAdminPanel, AdministrationUIModule.ADMIN_PANEL_ID){
			@Override
			protected void doHandleEvent(final ArrayList<Long> nodes, final UITree tree, final TreeStore<AbstractNodeDto> store) {
				Scheduler.get().scheduleDeferred(new ScheduledCommand() {
					@Override
					public void execute() {
						int i = nodes.size() - 1;
						boolean showVariants = false;
						while(i >= 0){
							AbstractNodeDto item = store.findModelWithKey(String.valueOf(nodes.get(i--)));
							if(null != item){
								tree.getSelectionModel().select(item, false);
								tree.scrollIntoView(item);
								break;
							} else {
								showVariants = true;
							}
						}
						
						if(showVariants && tree instanceof ManagerHelperTree)
							((ManagerHelperTree)tree).showTabOnSelection(ReportVariantsView.VIEW_ID);
					}
				});
			}
		});
		
	}


}
