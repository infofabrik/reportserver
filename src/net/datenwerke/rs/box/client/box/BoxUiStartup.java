package net.datenwerke.rs.box.client.box;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.box.client.box.hookers.BoxDatasinkConfigProviderHooker;
import net.datenwerke.rs.box.client.box.hookers.BoxDatasinkOAuthToolbarConfigurator;
import net.datenwerke.rs.box.client.box.hookers.BoxDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.box.client.box.hookers.BoxExportSnippetProvider;
import net.datenwerke.rs.box.client.box.hookers.BoxSendToFormConfiguratorHooker;
import net.datenwerke.rs.box.client.box.hookers.ExportToBoxHooker;
import net.datenwerke.rs.box.client.box.hookers.FileExportToBoxHooker;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class BoxUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 60;

   @Inject
   public BoxUiStartup(final Provider<ExportToBoxHooker> exportToBoxHooker,
         final Provider<FileExportToBoxHooker> fileExportToDatasinkHooker, final HookHandlerService hookHandler,
         final WaitOnEventUIService waitOnEventService, final BoxDao dao,
         final Provider<BoxDatasinkConfigProviderHooker> boxTreeConfiguratorProvider,
         final BoxDatasinkTesterToolbarConfigurator boxTestToolbarConfigurator,
         final BoxDatasinkOAuthToolbarConfigurator boxOauthToolbarConfigurator,
         final Provider<BoxExportSnippetProvider> boxExportSnippetProvider, final BoxUiService boxUiService,
         final Provider<BoxSendToFormConfiguratorHooker> sendToConfigHookProvider) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, boxTreeConfiguratorProvider.get(), PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToBoxHooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, boxTestToolbarConfigurator, PRIO);

      /* Oauth */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, boxOauthToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((BoxUiServiceImpl) boxUiService).setEnabledConfigs(result);
               if (result.get(StorageType.BOX) && result.get(StorageType.BOX_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, boxExportSnippetProvider, PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, boxExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }

}
