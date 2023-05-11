package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.adminutils.service.systemconsole.generalinfo.hooks.GeneralInfoCategoryProviderHook;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.ReportServerDatasourceDefinitions;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.datasourcemanager.eventhandlers.HandleDatasourceForceRemoveEventHandler;
import net.datenwerke.rs.core.service.datasourcemanager.history.DatasourceManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.DatasourceCategoryProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.UsageStatisticsDatasourceFoldersProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hookers.UsageStatisticsTotalDatasourcesProviderHooker;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;

public class DatasourceStartup {

   @Inject
   public DatasourceStartup(
         final HookHandlerService hookHandler, 
         final EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,
         final @ReportServerDatasourceDefinitions Provider<Set<Class<? extends DatasourceDefinition>>> installedDataSourceDefinitions,

         final Provider<DatasourceManagerHistoryUrlBuilderHooker> datasourceManagerUrlBuilder,
         final HandleDatasourceForceRemoveEventHandler handleDatasourceForceRemoveHandler,
         
         final Provider<DatasourceCategoryProviderHooker> usageStatistics,
         final Provider<UsageStatisticsTotalDatasourcesProviderHooker> usageStatsTotalProvider,
         final Provider<UsageStatisticsDatasourceFoldersProviderHooker> usageStatsFolderProvider
         ) {

      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DatasourceDefinition.class,
            handleDatasourceForceRemoveHandler);

      /* history */
      hookHandler.attachHooker(HistoryUrlBuilderHook.class, datasourceManagerUrlBuilder);
      
      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(DatasourceFolder.class);

         /* secure datasource definition entities */
         installedDataSourceDefinitions.get()
            .forEach(securityServiceProvider.get()::registerSecurityTarget);
      });
      
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsTotalProvider,
            HookHandlerService.PRIORITY_LOW);
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsFolderProvider,
            HookHandlerService.PRIORITY_LOW + 5);
      hookHandler.attachHooker(GeneralInfoCategoryProviderHook.class, usageStatistics,
            HookHandlerService.PRIORITY_LOW + 56);
   }
}
