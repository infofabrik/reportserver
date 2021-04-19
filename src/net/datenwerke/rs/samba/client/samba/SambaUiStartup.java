package net.datenwerke.rs.samba.client.samba;

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
import net.datenwerke.rs.samba.client.samba.hookers.ExportToSambaHooker;
import net.datenwerke.rs.samba.client.samba.hookers.SambaDataSinkTesterToolbarConfigurator;
import net.datenwerke.rs.samba.client.samba.hookers.SambaDatasinkConfigProviderHooker;
import net.datenwerke.rs.samba.client.samba.hookers.SambaExportSnippetProvider;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class SambaUiStartup {

   @Inject
   public SambaUiStartup(
         final HookHandlerService hookHandler, 
         final Provider<ExportToSambaHooker> exportToSambaHooker,
         final Provider<SambaExportSnippetProvider> sambaExportSnippetProvider,
         final Provider<SambaDatasinkConfigProviderHooker> sambaTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, 
         final SambaDao dao,
         final SambaDataSinkTesterToolbarConfigurator sambaTestToolbarConfigurator
         ) {

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, sambaTreeConfiguratorProvider.get(),
            HookHandlerService.PRIORITY_HIGH + 20);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToSambaHooker,
            HookHandlerService.PRIORITY_MEDIUM + 20);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, sambaTestToolbarConfigurator);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);
         
         dao.getSambaEnabledConfigs(new RsAsyncCallback<Map<StorageType,Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType,Boolean> result) {
               if (result.get(StorageType.SAMBA) && result.get(StorageType.SAMBA_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, sambaExportSnippetProvider,
                        HookHandlerService.PRIORITY_LOWER + 30);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, sambaExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });

      });

   }
}