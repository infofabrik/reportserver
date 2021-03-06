package net.datenwerke.rs.amazons3.client.amazons3;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.amazons3.client.amazons3.hookers.AmazonS3DatasinkConfigProviderHooker;
import net.datenwerke.rs.amazons3.client.amazons3.hookers.AmazonS3DatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.amazons3.client.amazons3.hookers.AmazonS3ExportSnippetProvider;
import net.datenwerke.rs.amazons3.client.amazons3.hookers.AmazonS3SendToFormConfiguratorHooker;
import net.datenwerke.rs.amazons3.client.amazons3.hookers.ExportToAmazonS3Hooker;
import net.datenwerke.rs.amazons3.client.amazons3.hookers.FileExportToAmazonS3Hooker;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class AmazonS3UiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 55;

   @Inject
   public AmazonS3UiStartup(final Provider<ExportToAmazonS3Hooker> exportToAmazonS3Hooker,
         final Provider<FileExportToAmazonS3Hooker> fileExportToDatasinkHooker, final HookHandlerService hookHandler,
         final WaitOnEventUIService waitOnEventService, final AmazonS3Dao dao,
         final Provider<AmazonS3DatasinkConfigProviderHooker> amazonS3TreeConfiguratorProvider,
         final AmazonS3DatasinkTesterToolbarConfigurator amazonS3TestToolbarConfigurator,
         final Provider<AmazonS3ExportSnippetProvider> amazonS3ExportSnippetProvider,
         final AmazonS3UiService amazonS3UiService,
         final Provider<AmazonS3SendToFormConfiguratorHooker> sendToConfigHookProvider) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, amazonS3TreeConfiguratorProvider.get(),
            PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToAmazonS3Hooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, amazonS3TestToolbarConfigurator, PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((AmazonS3UiServiceImpl) amazonS3UiService).setEnabledConfigs(result);
               if (result.get(StorageType.AMAZONS3) && result.get(StorageType.AMAZONS3_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, amazonS3ExportSnippetProvider,
                        PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, amazonS3ExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }
}
