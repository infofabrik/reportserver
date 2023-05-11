package net.datenwerke.rs.birt.service.datasources;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.birt.service.datasources.birt.eventhandler.HandleReportRemoveEventHandler;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.birt.service.datasources.birtreport.hookers.UsageStatisticsBirtDatasourcesProviderHooker;
import net.datenwerke.rs.birt.service.datasources.eventhandler.HandleBirtDatasourceMergeEvents;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.UsageStatisticsDatasourceEntryProviderHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.MergeEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class BirtDatasourceStartup {

   @Inject
   public BirtDatasourceStartup(
         HookHandlerService hookHandler, 
         EventBus eventBus,
         HandleReportRemoveEventHandler handleReportRemoveEvents,
         HandleBirtDatasourceMergeEvents birtDatasourceMergeHandler,
         
         final Provider<UsageStatisticsBirtDatasourcesProviderHooker> usageStatsBirtDatasourceProvider
         ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, Report.class, handleReportRemoveEvents);

      eventBus.attachObjectEventHandler(MergeEntityEvent.class, BirtReportDatasourceDefinition.class,
            birtDatasourceMergeHandler);
      
      hookHandler.attachHooker(UsageStatisticsDatasourceEntryProviderHook.class, usageStatsBirtDatasourceProvider,
            HookHandlerService.PRIORITY_LOW + 20);
   }
}
