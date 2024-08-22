package net.datenwerke.rs.scp.client.scp;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.client.datasinks.hooks.DatasinkAuthenticatorConfiguratorHook;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scp.client.scp.hookers.ExportToScpHooker;
import net.datenwerke.rs.scp.client.scp.hookers.FileExportToScpHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpDatasinkConfigProviderHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.scp.client.scp.hookers.ScpExportSnippetProvider;
import net.datenwerke.rs.scp.client.scp.hookers.ScpPublicKeyAuthenticatorHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpSendToFormConfiguratorHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpUsernamePasswordAuthenticatorHooker;

public class ScpUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 30;

   @Inject
   public ScpUiStartup(final HookHandlerService hookHandler, final Provider<ExportToScpHooker> exportToScpHooker,
         final Provider<FileExportToScpHooker> fileExportToDatasinkHooker,
         final Provider<ScpDatasinkConfigProviderHooker> scpTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService,
         final Provider<ScpExportSnippetProvider> scpExportSnippetProvider, final ScpDao dao,
         final ScpDatasinkTesterToolbarConfigurator scpTestToolbarConfigurator,
         final ScpUsernamePasswordAuthenticatorHooker scpUsernamePasswordAuthenticator,
         final ScpPublicKeyAuthenticatorHooker scpPublicKeyAuthenticator, final ScpUiService scpUiService,
         final Provider<ScpSendToFormConfiguratorHooker> sendToConfigHookProvider) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, scpTreeConfiguratorProvider.get(), PRIO);

      /* Authenticators */
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, scpUsernamePasswordAuthenticator,
            HookHandlerService.PRIORITY_MEDIUM);
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, scpPublicKeyAuthenticator,
            HookHandlerService.PRIORITY_MEDIUM + 10);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToScpHooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, scpTestToolbarConfigurator, PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getScpEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {

            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((ScpUiServiceImpl) scpUiService).setEnabledConfigs(result);
               if (result.get(StorageType.SCP) && result.get(StorageType.SCP_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, scpExportSnippetProvider, PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, scpExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }

}
