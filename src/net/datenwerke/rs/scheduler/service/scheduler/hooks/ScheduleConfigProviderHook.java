package net.datenwerke.rs.scheduler.service.scheduler.hooks;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.scheduler.client.scheduler.dto.ReportScheduleDefinition;
import net.datenwerke.rs.scheduler.service.scheduler.exceptions.InvalidConfigurationException;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;

public interface ScheduleConfigProviderHook extends Hook {

	void adaptJob(ReportExecuteJob job, ReportScheduleDefinition scheduleDTO) throws InvalidConfigurationException;

	void adaptScheduleDefinition(ReportScheduleDefinition rsd,
			ReportExecuteJob job) throws ExpectedException;

}
