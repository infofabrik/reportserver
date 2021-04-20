package net.datenwerke.rs.localfsdatasink.client.localfsdatasink;

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
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.ExportToLocalFileSystemHooker;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemDataSinkTesterToolbarConfigurator;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemDatasinkConfigProviderHooker;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemExportSnippetProvider;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class LocalFileSystemUiStartup {

   @Inject
   public LocalFileSystemUiStartup(final Provider<ExportToLocalFileSystemHooker> exportToLocalFileSystemHooker,
         final HookHandlerService hookHandler,
         final Provider<LocalFileSystemDatasinkConfigProviderHooker> localFileSystemTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, final LocalFileSystemDao dao,
         final Provider<LocalFileSystemExportSnippetProvider> localFileSystemExportSnippetProvider,
         final LocalFileSystemDataSinkTesterToolbarConfigurator localFileSystemTestToolbarConfigurator) {

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, localFileSystemTreeConfiguratorProvider.get(), HookHandlerService.PRIORITY_MEDIUM);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToLocalFileSystemHooker,
            HookHandlerService.PRIORITY_LOW);
      
      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, localFileSystemTestToolbarConfigurator);
      
      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType,Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType,Boolean> result) {
               if (result.get(StorageType.LOCALFILESYSTEM) && result.get(StorageType.LOCALFILESYSTEM_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, localFileSystemExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOWER + 20);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, localFileSystemExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });
         
      });
   }

}