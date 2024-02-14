package net.datenwerke.rs.crystal.service.crystal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReport;
import net.datenwerke.rs.crystal.service.crystal.hookers.UsageStatisticsCrystalProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class CrystalStartup {

   @Inject
   public CrystalStartup(
         final HookHandlerService hookHandlerService, 
         final Provider<UsageStatisticsCrystalProviderHooker> usageStatsCrystalProvider,
         final Provider<ReportDefaultMergeHookerFactory> reportFactory
         ) {
      hookHandlerService.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsCrystalProvider,
            HookHandlerService.PRIORITY_LOW + 20);
      
      /* entity merge */
      hookHandlerService.attachHooker(EntityMergeHook.class, reportFactory.get().create(CrystalReport.class));
   }

}
