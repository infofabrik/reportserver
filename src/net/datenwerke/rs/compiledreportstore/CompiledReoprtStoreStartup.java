package net.datenwerke.rs.compiledreportstore;

import net.datenwerke.rs.compiledreportstore.eventhandler.RemoveCompiledReportsOnDelete;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.utils.eventbus.EventBus;
import net.datenwerke.security.service.eventlogger.jpa.RemoveEntityEvent;

import com.google.inject.Inject;

public class CompiledReoprtStoreStartup {

	@Inject
	public CompiledReoprtStoreStartup(
		RemoveCompiledReportsOnDelete cleanupTask,
		EventBus eventBus
		){
		
		eventBus.attachObjectEventHandler(RemoveEntityEvent.class, Report.class, cleanupTask);
	}
}
