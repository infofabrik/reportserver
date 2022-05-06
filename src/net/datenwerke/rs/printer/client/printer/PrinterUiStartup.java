package net.datenwerke.rs.printer.client.printer;

import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.login.LoginService;
import net.datenwerke.gf.client.managerhelper.hooks.MainPanelViewToolbarConfiguratorHook;
import net.datenwerke.gxtdto.client.dialog.error.DetailErrorDialog;
import net.datenwerke.gxtdto.client.dtomanager.callback.RsAsyncCallback;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasinkmanager.hooks.DatasinkDefinitionConfigProviderHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ExportExternalEntryProviderHook;
import net.datenwerke.rs.fileserver.client.fileserver.hooks.DatasinkSendToFormConfiguratorHook;
import net.datenwerke.rs.fileserver.client.fileserver.provider.treehooks.FileExportExternalEntryProviderHook;
import net.datenwerke.rs.printer.client.printer.hookers.ExportToPrinterHooker;
import net.datenwerke.rs.printer.client.printer.hookers.FileExportToPrinterHooker;
import net.datenwerke.rs.printer.client.printer.hookers.PrinterDatasinkConfigProviderHooker;
import net.datenwerke.rs.printer.client.printer.hookers.PrinterDatasinkTesterToolbarConfigurator;
import net.datenwerke.rs.printer.client.printer.hookers.PrinterExportSnippetProvider;
import net.datenwerke.rs.printer.client.printer.hookers.PrinterSendToFormConfiguratorHooker;
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.StorageType;
import net.datenwerke.rs.scheduler.client.scheduler.hooks.ScheduleExportSnippetProviderHook;

public class PrinterUiStartup {

   private static final int PRIO = HookHandlerService.PRIORITY_LOW + 60;

   @Inject
   public PrinterUiStartup(
         final Provider<PrinterSendToFormConfiguratorHooker> sendToConfigHookProvider,
         final Provider<PrinterDatasinkConfigProviderHooker> printerTreeConfiguratorProvider,
         final HookHandlerService hookHandler, 
         final Provider<ExportToPrinterHooker> exportToPrinterHooker,
         final Provider<FileExportToPrinterHooker> fileExportToDatasinkHooker,
         final PrinterDatasinkTesterToolbarConfigurator printerTestToolbarConfigurator,
         final WaitOnEventUIService waitOnEventService, 
         final PrinterUiService printerUiService,
         final Provider<PrinterExportSnippetProvider> printerExportSnippetProvider, 
         final PrinterDao dao
         ) {

      /* send to form configurator */
      hookHandler.attachHooker(DatasinkSendToFormConfiguratorHook.class, sendToConfigHookProvider.get());

      /* config tree */
      hookHandler.attachHooker(DatasinkDefinitionConfigProviderHook.class, printerTreeConfiguratorProvider.get(), PRIO);

      /* Send-to hookers */
      hookHandler.attachHooker(ExportExternalEntryProviderHook.class, exportToPrinterHooker, PRIO);
      hookHandler.attachHooker(FileExportExternalEntryProviderHook.class, fileExportToDatasinkHooker, PRIO);

      /* test datasinks */
      hookHandler.attachHooker(MainPanelViewToolbarConfiguratorHook.class, printerTestToolbarConfigurator, PRIO);

      waitOnEventService.callbackOnEvent(LoginService.REPORTSERVER_EVENT_AFTER_ANY_LOGIN, ticket -> {
         waitOnEventService.signalProcessingDone(ticket);

         dao.getStorageEnabledConfigs(new RsAsyncCallback<Map<StorageType, Boolean>>() {
            @Override
            public void onSuccess(final Map<StorageType, Boolean> result) {
               ((PrinterUiServiceImpl) printerUiService).setEnabledConfigs(result);
               if (result.get(StorageType.PRINTER) && result.get(StorageType.PRINTER_SCHEDULING))
                  hookHandler.attachHooker(ScheduleExportSnippetProviderHook.class, printerExportSnippetProvider, PRIO);
               else
                  hookHandler.detachHooker(ScheduleExportSnippetProviderHook.class, printerExportSnippetProvider);
            }

            @Override
            public void onFailure(Throwable caught) {
               super.onFailure(caught);
            }
         });
         
         /* call server to get available printers */
         dao.getAvailablePrinters(new RsAsyncCallback<List<String>>() {
            @Override
            public void onSuccess(List<String> result) {
               ((PrinterUiServiceImpl) printerUiService).setAvailablePrinters(result);
            }

            @Override
            public void onFailure(Throwable caught) {
               new DetailErrorDialog(caught).show();
            }
         });

      });
      
   }

}
