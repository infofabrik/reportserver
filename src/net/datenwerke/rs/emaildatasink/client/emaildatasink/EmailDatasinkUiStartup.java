package net.datenwerke.rs.emaildatasink.client.emaildatasink;

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
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkConfigProviderHooker;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkExportSnippetProvider;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkSendToFormConfiguratorHooker;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.ExportToEmailDatasinkHooker;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.FileExportToEmailHooker;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class EmailDatasinkUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW;

   @Inject
   public EmailDatasinkUiStartup(final Provider<ExportToEmailDatasinkHooker> exportToEmailHooker,
         final Provider<FileExportToEmailHooker> fileExportToDatasinkHooker, final HookHandlerService hookHandler,
         final Provider<EmailDatasinkConfigProviderHooker> emailTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, final EmailDatasinkDao dao,
         final EmailDatasinkTesterToolbarConfigurator emailTestToolbarConfigurator,
         final Provider<EmailDatasinkExportSnippetProvider> emailExportSnippetProvider,
         final EmailDatasinkUiService emailUiService,
         final Provider<EmailDatasinkSendToFormConfiguratorHooker> sendToConfigHookProvider) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, emailTreeConfiguratorProvider.get(), PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToEmailHooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, emailTestToolbarConfigurator, PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((EmailDatasinkUiServiceImpl) emailUiService).setEnabledConfigs(result);
               if (result.get(StorageType.EMAIL) && result.get(StorageType.EMAIL_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, emailExportSnippetProvider, PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, emailExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });
   }

}
