package net.datenwerke.rs.dsbundle.service.dsbundle;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.dsbundle.service.dsbundle.hookers.UsageStatisticsBundleDatasourcesProviderHooker;

public class DatasourceBundleStartup {

   @Inject
   public DatasourceBundleStartup(
         HookHandlerService hookHandler, 
         final Provider<UsageStatisticsBundleDatasourcesProviderHooker> usageStatsBundleDatasourceProvider
         ) {

      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsBundleDatasourceProvider,
            HookHandlerService.PRIORITY_LOW + 30);
   }
}
