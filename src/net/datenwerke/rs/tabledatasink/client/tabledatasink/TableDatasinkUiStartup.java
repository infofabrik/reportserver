package net.datenwerke.rs.tabledatasink.client.tabledatasink;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.client.reportengines.table.execute.Table2StreamTable;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterExportReportHook;
import net.datenwerke.rs.enterprise.client.EnterpriseCheckUiModule;
import net.datenwerke.rs.enterprise.client.EnterpriseUiService;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers.ExportToTableDatasinkHooker;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers.TableDatasinkConfigProviderHooker;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers.TableDatasinkExportSnippetProvider;
import net.datenwerke.rs.tabledatasink.client.tabledatasink.hookers.TableDatasinkSendToFormConfiguratorHooker;

public class TableDatasinkUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 5;

   @Inject
   public TableDatasinkUiStartup(
         final Provider<ExportToTableDatasinkHooker> exportToTableDatasinkHooker,
         final HookHandlerService hookHandler, 
         final WaitOnEventUIService waitOnEventService,
         final TableDatasinkDao dao,
         final Provider<TableDatasinkConfigProviderHooker> tableDatasinkTreeConfiguratorProvider,
         final TableDatasinkUiService tableDatasinkUiService,
         final Provider<TableDatasinkSendToFormConfiguratorHooker> sendToConfigHookProvider,
         final Provider<TableDatasinkExportSnippetProvider> tableDatasinkExportSnippetProvider,
         final Provider<EnterpriseUiService> enterpriseServiceProvider,
         final Provider<Table2StreamTable> table2StreamTable
         ) {
      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, tableDatasinkTreeConfiguratorProvider.get(),
            PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToTableDatasinkHooker, PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         waitOnEventService.callbackOnEvent(EnterpriseCheckUiModule.REPORTSERVER_ENTERPRISE_DETERMINED_AFTER_LOGIN,
               ticketEnterpriseCheck -> {
                  dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
                     @Override
                     public void onSuccess(final Map<StorageType, Boolean> result) {
                        ((TableDatasinkUiServiceImpl) tableDatasinkUiService).setEnabledConfigs(result);
                        if (result.get(StorageType.TABLE_DATASINK) && result.get(StorageType.TABLE_DATASINK_SCHEDULING))
                           hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, tableDatasinkExportSnippetProvider,
                                 PRIO);
                        else
                           hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, tableDatasinkExportSnippetProvider);
                        
                        Hook streamTableHook = new ReportExporterExportReportHook(table2StreamTable);
                        if (result.get(StorageType.TABLE_DATASINK_SCHEDULING)) {
                           if (enterpriseServiceProvider.get().isEnterprise()) {
                              hookHandler.attachHooker(ReportExporterExportReportHook.class, streamTableHook,
                                    HookHandlerService.PRIORITY_LOWER + 60);
                           }
                        } else {
                           hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, streamTableHook);
                        }
                     }
                     
                     @Override
                     public void onFailure(Throwable caught) {
                        super.onFailure(caught);
                     }
                  });

                  waitOnEventService.signalProcessingDone(ticket);
               });

      });
      
   }
}
