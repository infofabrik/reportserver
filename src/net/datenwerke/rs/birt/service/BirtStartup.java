package net.datenwerke.rs.birt.service;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.birt.service.datasources.birtreport.hookers.BirtReportDatasourceProviderHooker;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.birt.service.reportengine.hookers.BaseBirtOutputGeneratorProvider;
import net.datenwerke.rs.birt.service.reportengine.hookers.BirtReportEngineProviderHooker;
import net.datenwerke.rs.birt.service.reportengine.hookers.BirtReportTypeProviderHooker;
import net.datenwerke.rs.birt.service.reportengine.hookers.BirtReportUploadHooker;
import net.datenwerke.rs.birt.service.reportengine.hookers.UsageStatisticsBirtProviderHooker;
import net.datenwerke.rs.birt.service.reportengine.hooks.BirtOutputGeneratorProviderHook;
import net.datenwerke.rs.birt.service.reportengine.terminal.BirtCommand;
import net.datenwerke.rs.birt.service.reportengine.terminal.BirtShutdownCommand;
import net.datenwerke.rs.birt.service.reportengine.terminal.BirtSubCommandHook;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class BirtStartup {

   @Inject
   public BirtStartup(
         final HookHandlerService hookHandlerService, 
         final BirtReportUploadHooker birtReportUploadHooker,
         final BirtReportEngineProviderHooker birtReportEngineProviderHooker,

         final Provider<BaseBirtOutputGeneratorProvider> baseOutputGenerators,

         final Provider<BirtCommand> birtCommand, 
         final Provider<BirtShutdownCommand> birtShutdownCommand,
         
         final Provider<UsageStatisticsBirtProviderHooker> usageStatsBirtProvider,
         
         final Provider<ReportDefaultMergeHookerFactory> reportFactory
   ) {
      hookHandlerService.attachHooker(FileUploadHandlerHook.class, birtReportUploadHooker);
      hookHandlerService.attachHooker(ReportEngineProviderHook.class, birtReportEngineProviderHooker);

      hookHandlerService.attachHooker(ReportTypeProviderHook.class, new BirtReportTypeProviderHooker());
      hookHandlerService.attachHooker(DatasourceProviderHook.class, new BirtReportDatasourceProviderHooker());

      hookHandlerService.attachHooker(TerminalCommandHook.class, birtCommand);
      hookHandlerService.attachHooker(BirtSubCommandHook.class, birtShutdownCommand);
      
      /* entity merge */
      hookHandlerService.attachHooker(EntityMergeHook.class, reportFactory.get().create(BirtReport.class));

      /* base exporters */
      hookHandlerService.attachHooker(BirtOutputGeneratorProviderHook.class, baseOutputGenerators,
            HookHandlerService.PRIORITY_LOW);
      
      hookHandlerService.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsBirtProvider,
            HookHandlerService.PRIORITY_LOW + 10);
   }

}
