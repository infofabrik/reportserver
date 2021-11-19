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
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.scp.client.scp.hookers.ExportToScpHooker;
import net.datenwerke.rs.scp.client.scp.hookers.FileExportToScpHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpDataSinkTesterToolbarConfigurator;
import net.datenwerke.rs.scp.client.scp.hookers.ScpDatasinkConfigProviderHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpExportSnippetProvider;
import net.datenwerke.rs.scp.client.scp.hookers.ScpPublicKeyAuthenticatorHooker;
import net.datenwerke.rs.scp.client.scp.hookers.ScpUsernamePasswordAuthenticatorHooker;

public class ScpUiStartup {

   @Inject
   public ScpUiStartup(
         final HookHandlerService hookHandler, 
         final Provider<ExportToScpHooker> exportToScpHooker,
         final Provider<FileExportToScpHooker> fileExportToDatasinkHooker,
         final Provider<ScpDatasinkConfigProviderHooker> scpTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService,
         final Provider<ScpExportSnippetProvider> scpExportSnippetProvider, 
         final ScpDao dao,
         final ScpDataSinkTesterToolbarConfigurator scpTestToolbarConfigurator,
         final ScpUsernamePasswordAuthenticatorHooker scpUsernamePasswordAuthenticator,
         final ScpPublicKeyAuthenticatorHooker scpPublicKeyAuthenticator
         ) {
      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, scpTreeConfiguratorProvider.get(),
            HookHandlerService.PRIORITY_HIGH + 20);
      
      /* Authenticators */
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, scpUsernamePasswordAuthenticator, 
            HookHandlerService.PRIORITY_MEDIUM);
      hookHandler.attachHooker(DatasinkAuthenticatorConfiguratorHook.class, scpPublicKeyAuthenticator, 
            HookHandlerService.PRIORITY_MEDIUM + 10);
      

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToScpHooker,
            HookHandlerService.PRIORITY_MEDIUM + 20);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker,
            HookHandlerService.PRIORITY_MEDIUM + 20);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, scpTestToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getScpEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {

            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               if (result.get(StorageType.SCP) && result.get(StorageType.SCP_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, scpExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOWER + 30);
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
