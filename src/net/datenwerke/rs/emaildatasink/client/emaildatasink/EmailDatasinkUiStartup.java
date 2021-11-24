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
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkConfigProviderHooker;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.EmailDatasinkExportSnippetProvider;
import net.datenwerke.rs.emaildatasink.client.emaildatasink.hookers.ExportToEmailDatasinkHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class EmailDatasinkUiStartup {

   @Inject
   public EmailDatasinkUiStartup(
         final Provider<ExportToEmailDatasinkHooker> exportToEmailHooker,
         final HookHandlerService hookHandler,
         final Provider<EmailDatasinkConfigProviderHooker> emailTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, final EmailDatasinkDao dao,
         final EmailDatasinkTesterToolbarConfigurator emailTestToolbarConfigurator,
         final Provider<EmailDatasinkExportSnippetProvider> emailExportSnippetProvider
         ) {

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, emailTreeConfiguratorProvider.get(),
            HookHandlerService.PRIORITY_LOW);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToEmailHooker,
            HookHandlerService.PRIORITY_LOW);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, emailTestToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               if (result.get(StorageType.EMAIL) && result.get(StorageType.EMAIL_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, emailExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOW);
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
