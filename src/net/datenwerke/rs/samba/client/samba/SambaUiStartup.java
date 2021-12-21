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
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.samba.client.samba.hookers.ExportToSambaHooker;
import net.datenwerke.rs.samba.client.samba.hookers.FileExportToSambaHooker;
import net.datenwerke.rs.samba.client.samba.hookers.SambaDatasinkConfigProviderHooker;
import net.datenwerke.rs.samba.client.samba.hookers.SambaDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.samba.client.samba.hookers.SambaExportSnippetProvider;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class SambaUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 25;
   
   @Inject
   public SambaUiStartup(
         final HookHandlerService hookHandler, 
         final Provider<ExportToSambaHooker> exportToSambaHooker,
         final Provider<FileExportToSambaHooker> fileExportToDatasinkHooker,
         final Provider<SambaExportSnippetProvider> sambaExportSnippetProvider,
         final Provider<SambaDatasinkConfigProviderHooker> sambaTreeConfiguratorProvider,
         final WaitOnEventUIService waitOnEventService, 
         final SambaDao dao,
         final SambaDatasinkTesterToolbarConfigurator sambaTestToolbarConfigurator,
         final SambaUiService sambaUiService
         ) {
      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, sambaTreeConfiguratorProvider.get(),
            PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToSambaHooker,
            PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker,
            PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, sambaTestToolbarConfigurator,
            PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);
         
         dao.getSambaEnabledConfigs(new RsAsyncCallback<Map<StorageType,Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType,Boolean> result) {
               ((SambaUiServiceImpl)sambaUiService).setEnabledConfigs(result);
               if (result.get(StorageType.SAMBA) && result.get(StorageType.SAMBA_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, sambaExportSnippetProvider,
                        PRIO);
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