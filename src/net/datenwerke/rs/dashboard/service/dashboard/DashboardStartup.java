package net.datenwerke.rs.dashboard.service.dashboard;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardFolder;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DashboardNode;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleDadgetNodeForceRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleDadgetNodeRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleDashboardNodeForceRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleDashboardNodeRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleDiskNodeRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleReportReferenceForceRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleReportReferenceRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleReportRemoveEventHandler;
import net.datenwerke.rs.dashboard.service.dashboard.eventhandler.HandleUserRemoveEventHandler;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.AbstractTsDiskNode;
import net.datenwerke.rs.tsreportarea.service.tsreportarea.entities.TsDiskReportReference;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.ForceRemoveEntityEvent;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.usermanager.entities.User;

public class DashboardStartup {

   @Inject
   public DashboardStartup(HookHandlerService hookHandler, EventBus eventBus,
         final Provider<SecurityService> securityServiceProvider,

         HandleUserRemoveEventHandler handleUserRemoveEvents,
         HandleDiskNodeRemoveEventHandler handleDiskNodeRemoveEvents,
         HandleReportRemoveEventHandler handleReportRemoveEvents,

         HandleReportReferenceRemoveEventHandler handleReportReferenceRemoveEvents,
         HandleReportReferenceForceRemoveEventHandler handleReportReferenceForceRemoveEvents,

         HandleDadgetNodeRemoveEventHandler handleDadgetNodeRemoveEvents,
         HandleDadgetNodeForceRemoveEventHandler handleDadgetNodeForceRemoveEvents,

         HandleDashboardNodeRemoveEventHandler handleDashboardNodeRemoveEvents,
         HandleDashboardNodeForceRemoveEventHandler handleDashboardNodeForceRemoveEvents

   ) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, User.class, handleUserRemoveEvents);
      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, AbstractTsDiskNode.class, handleDiskNodeRemoveEvents);
      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, Report.class, handleReportRemoveEvents);

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, TsDiskReportReference.class,
            handleReportReferenceRemoveEvents);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, TsDiskReportReference.class,
            handleReportReferenceForceRemoveEvents);

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, DadgetNode.class, handleDadgetNodeRemoveEvents);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DadgetNode.class,
            handleDadgetNodeForceRemoveEvents);

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, DashboardNode.class, handleDashboardNodeRemoveEvents);
      eventBus.attachObjectEventHandler(ForceRemoveEntityEvent.class, DashboardNode.class,
            handleDashboardNodeForceRemoveEvents);

      /* register security targets */
      securityServiceProvider.get().registerSecurityTarget(DadgetNode.class);
      securityServiceProvider.get().registerSecurityTarget(DashboardNode.class);
      securityServiceProvider.get().registerSecurityTarget(DashboardFolder.class);
   }
}
