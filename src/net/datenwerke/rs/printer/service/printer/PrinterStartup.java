package net.datenwerke.rs.printer.service.printer;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.UsageStatisticsDatasinkEntryProviderHook;
import net.datenwerke.rs.printer.service.printer.definitions.PrinterDatasink;
import net.datenwerke.rs.printer.service.printer.hooker.PrinterDatasinkProviderHooker;
import net.datenwerke.rs.printer.service.printer.hooker.ScheduleAsPrinterFileEmailNotificationHooker;
import net.datenwerke.rs.printer.service.printer.hooker.ScheduleConfigAsPrinterDatasinkHooker;
import net.datenwerke.rs.printer.service.printer.hooker.UsageStatisticsProviderHooker;
import net.datenwerke.rs.scheduler.service.scheduler.hooks.ScheduleConfigProviderHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;
import net.datenwerke.scheduler.service.scheduler.hooks.SchedulerExecutionHook;

public class PrinterStartup {

   @Inject
   public PrinterStartup(
         final HookHandlerService hookHandler,
         final Provider<PrinterDatasinkProviderHooker> printerDatasinkProviderHooker,
         final Provider<ScheduleAsPrinterFileEmailNotificationHooker> emailPrinterNotificationHooker,
         final Provider<ScheduleConfigAsPrinterDatasinkHooker> scheduleAsPrinterConfigHooker,
         final Provider<UsageStatisticsProviderHooker> usageStats,
         
         final Provider<DatasinkDefaultMergeHookerFactory> datasinkFactory
         ) {
      hookHandler.attachHooker(DatasinkProviderHook.class, printerDatasinkProviderHooker);
      hookHandler.attachHooker(SchedulerExecutionHook.class, emailPrinterNotificationHooker);
      hookHandler.attachHooker(ScheduleConfigProviderHook.class, scheduleAsPrinterConfigHooker);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasinkFactory.get().create(PrinterDatasink.class));
      
      hookHandler.attachHooker(UsageStatisticsDatasinkEntryProviderHook.class, usageStats,
            HookHandlerService.PRIORITY_MEDIUM + 45);
   }

}
