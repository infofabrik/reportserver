package net.datenwerke.rs.crystal.service.crystal;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.hooks.UsageStatisticsReportEntryProviderHook;
import net.datenwerke.rs.crystal.service.crystal.hookers.UsageStatisticsCrystalProviderHooker;

public class CrystalStartup {

   @Inject
   public CrystalStartup(
         final HookHandlerService hookHandlerService, 
         final Provider<UsageStatisticsCrystalProviderHooker> usageStatsCrystalProvider
         ) {
      hookHandlerService.attachHooker(UsageStatisticsReportEntryProviderHook.class, usageStatsCrystalProvider,
            HookHandlerService.PRIORITY_LOW + 20);
   }

}
