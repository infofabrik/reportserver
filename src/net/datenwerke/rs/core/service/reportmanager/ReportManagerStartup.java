package net.datenwerke.rs.core.service.reportmanager;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.lifecycle.hooks.ConfigDoneHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerReportTypes;
import net.datenwerke.rs.core.service.reportmanager.entities.ReportFolder;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.eventhandler.HandleDatasourceRemoveEventHandler;
import net.datenwerke.rs.core.service.reportmanager.hookers.ConfigureBaseReportViaRequestHooker;
import net.datenwerke.rs.core.service.reportmanager.hookers.ReportManagerHistoryUrlBuilderHooker;
import net.datenwerke.rs.core.service.reportmanager.hookers.ReportManagerNodeSearchResultCheckHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHistoryLocationHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHttpRequestHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.VariantCreatorHook;
import net.datenwerke.rs.core.service.reportmanager.metadata.hookers.VariantCreatedAdjustMetadataHooker;
import net.datenwerke.rs.search.service.search.hooks.SearchResultAllowHook;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;

public class ReportManagerStartup {

   @Inject
   public ReportManagerStartup(
         HookHandlerService hookHandler, 
         EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,
         final @ReportServerReportTypes Provider<Set<Class<? extends Report>>> installedReportTypes,

         Provider<ConfigureBaseReportViaRequestHooker> baseReportRequestConfiguration,
         Provider<ReportManagerHistoryUrlBuilderHooker> reportManagerUrlBuilder,
         HandleDatasourceRemoveEventHandler handleDatasourceRemoveHandler,
         VariantCreatedAdjustMetadataHooker adjustMetadataHooker,
         ReportManagerNodeSearchResultCheckHooker reportManagerSearchResultCheckHooker
         ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, DatasourceDefinition.class,
            handleDatasourceRemoveHandler);

      hookHandler.attachHooker(ConfigureReportViaHttpRequestHook.class, baseReportRequestConfiguration);
      hookHandler.attachHooker(ConfigureReportViaHistoryLocationHook.class, baseReportRequestConfiguration);
      hookHandler.attachHooker(VariantCreatorHook.class, adjustMetadataHooker);
      hookHandler.attachHooker(SearchResultAllowHook.class, reportManagerSearchResultCheckHooker);

      hookHandler.attachHooker(HistoryUrlBuilderHook.class, reportManagerUrlBuilder);

      /* register security targets */
      hookHandler.attachHooker(ConfigDoneHook.class, () -> {
         /* secure folder */
         securityServiceProvider.get().registerSecurityTarget(ReportFolder.class);

         /* secure report entities */
         installedReportTypes.get()
            .forEach(rClass -> securityServiceProvider.get().registerSecurityTarget(rClass));
      });
   }

}
