package net.datenwerke.rs.dsbundle.service.dsbundle;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.factory.DatasourceDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;
import net.datenwerke.rs.dsbundle.service.dsbundle.hookers.UsageStatisticsBundleDatasourcesProviderHooker;
import net.datenwerke.rs.utils.entitymerge.service.hooks.EntityMergeHook;

public class DatasourceBundleStartup {

   @Inject
   public DatasourceBundleStartup(
         HookHandlerService hookHandler, 
         final Provider<UsageStatisticsBundleDatasourcesProviderHooker> usageStatsBundleDatasourceProvider,
         final Provider<DatasourceDefaultMergeHookerFactory> datasourceFactory
         ) {

      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsBundleDatasourceProvider,
            HookHandlerService.PRIORITY_LOW + 30);
      
      /* entity merge */
      hookHandler.attachHooker(EntityMergeHook.class, datasourceFactory.get().create(DatabaseBundle.class));
   }
}
