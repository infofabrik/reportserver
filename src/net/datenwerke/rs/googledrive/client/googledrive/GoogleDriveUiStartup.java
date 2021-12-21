package net.datenwerke.rs.googledrive.client.googledrive;

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
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.googledrive.client.googledrive.hookers.ExportToGoogleDriveHooker;
import net.datenwerke.rs.googledrive.client.googledrive.hookers.FileExportToGoogleDriveHooker;
import net.datenwerke.rs.googledrive.client.googledrive.hookers.GoogleDriveDatasinkConfigProviderHooker;
import net.datenwerke.rs.googledrive.client.googledrive.hookers.GoogleDriveDatasinkOAuthToolbarConfigurator;
import net.datenwerke.rs.googledrive.client.googledrive.hookers.GoogleDriveDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.googledrive.client.googledrive.hookers.GoogleDriveExportSnippetProvider;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class GoogleDriveUiStartup {
   
   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 50;
   
   @Inject
   public GoogleDriveUiStartup(
         final Provider<ExportToGoogleDriveHooker> exportToGoogleDriveHooker,
         final Provider<FileExportToGoogleDriveHooker> fileExportToDatasinkHooker,
         final HookHandlerService hookHandler, 
         final WaitOnEventUIService waitOnEventService, 
         final GoogleDriveDao dao,
         final Provider<GoogleDriveDatasinkConfigProviderHooker> googleDriveTreeConfiguratorProvider,
         final GoogleDriveDatasinkTesterToolbarConfigurator googleDriveTestToolbarConfigurator,
         final GoogleDriveDatasinkOAuthToolbarConfigurator googleDriveOauthToolbarConfigurator,
         final Provider<GoogleDriveExportSnippetProvider> googleDriveExportSnippetProvider,
         final GoogleDriveUiService googleDriveUiService
         ) {
      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, googleDriveTreeConfiguratorProvider.get(),
            PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToGoogleDriveHooker,
            PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker,
            PRIO);
      
      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, googleDriveTestToolbarConfigurator,
            PRIO);

      /* Oauth */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, googleDriveOauthToolbarConfigurator);
      
      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((GoogleDriveUiServiceImpl)googleDriveUiService).setEnabledConfigs(result);
               if (result.get(StorageType.GOOGLEDRIVE) && result.get(StorageType.GOOGLEDRIVE_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, googleDriveExportSnippetProvider,
                        PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, googleDriveExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }

}
