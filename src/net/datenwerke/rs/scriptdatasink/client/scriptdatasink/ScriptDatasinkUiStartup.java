package net.datenwerke.rs.scriptdatasink.client.scriptdatasink;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers.ExportToScriptDatasinkHooker;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers.FileExportToScriptDatasinkHooker;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers.ScriptDatasinkConfigProviderHooker;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers.ScriptDatasinkExportSnippetProvider;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers.ScriptDatasinkSendToFormConfiguratorHooker;
import net.datenwerke.rs.scriptdatasink.client.scriptdatasink.hookers.ScriptDatasinkTesterToolbarConfigurator;

public class ScriptDatasinkUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOWER;

   @Inject
   public ScriptDatasinkUiStartup(
         final Provider<ExportToScriptDatasinkHooker> exportToScriptDatasinkHooker,
         final Provider<FileExportToScriptDatasinkHooker> fileExportToDatasinkHooker,
         final HookHandlerService hookHandler,
         final Provider<ScriptDatasinkConfigProviderHooker> scriptDatasinkTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, 
         final ScriptDatasinkDao dao,
         final Provider<ScriptDatasinkExportSnippetProvider> scriptDatasinkExportSnippetProvider,
         final ScriptDatasinkTesterToolbarConfigurator scriptDatasinkTestToolbarConfigurator,
         final ScriptDatasinkUiService scriptDatasinkUiService,
         final Provider<ScriptDatasinkSendToFormConfiguratorHooker> sendToConfigHookProvider
         ) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class,
            scriptDatasinkTreeConfiguratorProvider.get(), PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToScriptDatasinkHooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, scriptDatasinkTestToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((ScriptDatasinkUiServiceImpl) scriptDatasinkUiService).setEnabledConfigs(result);
               if (result.get(StorageType.SCRIPT) && result.get(StorageType.SCRIPT_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class,
                        scriptDatasinkExportSnippetProvider, PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class,
                        scriptDatasinkExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });
   }

}