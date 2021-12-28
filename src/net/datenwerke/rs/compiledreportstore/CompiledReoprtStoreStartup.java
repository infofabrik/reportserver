package net.datenwerke.rs.compiledreportstore;

import com.google.inject.Inject;

import net.datenwerke.rs.compiledreportstore.eventhandler.RemoveCompiledReportsOnDelete;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

public class CompiledReoprtStoreStartup {

   @Inject
   public CompiledReoprtStoreStartup(RemoveCompiledReportsOnDelete cleanupTask, EventBus eventBus) {

      eventBus.attachObjectEventHandler(RemoveEntityEvent.class, Report.class, cleanupTask);
   }
}
