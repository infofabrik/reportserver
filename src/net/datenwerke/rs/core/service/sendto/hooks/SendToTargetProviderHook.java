package net.datenwerke.rs.core.service.sendto.hooks;

import java.util.HashMap;
import java.util.Map;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

@HookConfig
public interface SendToTargetProviderHook extends Hook {

	public SendToClientConfig consumes(Report report);

	public String getId();

	public String sendTo(Report report, Map<String, String> values, ReportExecutionConfig... executionConfig);
	
	public String sendTo(CompiledReport compiledReport, Report report, String format, Map<String, String> values, ReportExecutionConfig... executionConfig);
	
	public void scheduledSendTo(CompiledReport compiledReport, Report report, AbstractJob reportJob, String format, HashMap<String, String> values);
	
	public boolean supportsScheduling();
	
}
