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
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.ExportToLocalFileSystemHooker;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.FileExportToLocalFileSystemHooker;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemDatasinkConfigProviderHooker;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemExportSnippetProvider;
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.hookers.LocalFileSystemSendToFormConfiguratorHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class LocalFileSystemUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 35;

   @Inject
   public LocalFileSystemUiStartup(final Provider<ExportToLocalFileSystemHooker> exportToLocalFileSystemHooker,
         final Provider<FileExportToLocalFileSystemHooker> fileExportToDatasinkHooker,
         final HookHandlerService hookHandler,
         final Provider<LocalFileSystemDatasinkConfigProviderHooker> localFileSystemTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, final LocalFileSystemDao dao,
         final Provider<LocalFileSystemExportSnippetProvider> localFileSystemExportSnippetProvider,
         final LocalFileSystemDatasinkTesterToolbarConfigurator localFileSystemTestToolbarConfigurator,
         final LocalFileSystemUiService localFileSystemUiService,
         final Provider<LocalFileSystemSendToFormConfiguratorHooker> sendToConfigHookProvider) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class,
            localFileSystemTreeConfiguratorProvider.get(), PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToLocalFileSystemHooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, localFileSystemTestToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((LocalFileSystemUiServiceImpl) localFileSystemUiService).setEnabledConfigs(result);
               if (result.get(StorageType.LOCALFILESYSTEM) && result.get(StorageType.LOCALFILESYSTEM_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class,
                        localFileSystemExportSnippetProvider, PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class,
                        localFileSystemExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });
   }

}