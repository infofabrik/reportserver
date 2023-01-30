package net.datenwerke.rs.core.service.datasinkmanager.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.core.service.datasinkmanager.configs.DatasinkConfiguration;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;

public interface DatasinkDispatchNotificationHook extends Hook {

   /**
    * Notifies when a scheduled report has been dispatched to a datasink
    * 
    * @param report   the report scheduled. May be a String, a byte array or a
    *                 TableDBDataSource
    * @param rJob     the report execute job
    * @param datasink the datasink
    * @param config   the configuration of the datasink dispatch
    */
   void notifyOfScheduledReportDispatched(Object report, ReportExecuteJob rJob, DatasinkDefinition datasink,
         DatasinkConfiguration config);

   /**
    * Notifies when a compiled report has been dispatched to a datasink
    * 
    * @param report   the report scheduled. May be a String, a byte array or a
    *                 TableDBDataSource
    * @param datasink the datasink
    * @param config   the configuration of the datasink dispatch
    */
   void notifyOfCompiledReportDispatched(Object report, DatasinkDefinition datasink, DatasinkConfiguration config);
}
