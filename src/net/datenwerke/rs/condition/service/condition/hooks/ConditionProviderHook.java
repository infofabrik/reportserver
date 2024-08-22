package net.datenwerke.rs.condition.service.condition.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.hookservices.annotations.HookConfig;
import net.datenwerke.rs.condition.client.condition.dto.SimpleCondition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.scheduler.service.scheduler.jobs.report.ReportExecuteJob;
import net.datenwerke.security.service.usermanager.entities.User;

@HookConfig
public interface ConditionProviderHook extends Hook {

   public SimpleCondition provideConditionFor(Report report);

   public boolean consumes(String key);

   public boolean execute(String key, String expression, User user, ReportExecuteJob rJob);

   public boolean isBeforeActions();

   public boolean isBeforeJob();

}