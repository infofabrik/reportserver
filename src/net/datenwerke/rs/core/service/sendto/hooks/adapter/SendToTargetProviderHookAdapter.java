package net.datenwerke.rs.core.service.sendto.hooks.adapter;

import java.util.Map;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.client.sendto.SendToClientConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.sendto.hooks.SendToTargetProviderHook;
import net.datenwerke.scheduler.service.scheduler.entities.AbstractJob;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class SendToTargetProviderHookAdapter implements SendToTargetProviderHook {

   @Override
   public SendToClientConfig consumes(Report report) {
      return null;
   }

   @Override
   public String getId() {
      return null;
   }

   @Override
   public String sendTo(Report report, Map<String, String> values, ReportExecutionConfig... executionConfig) {
      return "";
   }

   @Override
   public String sendTo(CompiledReport compiledReport, Report report, String format, Map<String, String> values,
         ReportExecutionConfig... executionConfig) {
      return sendTo(report, values);
   }

   @Override
   public void scheduledSendTo(CompiledReport compiledReport, Report report, AbstractJob reportJob, String format,
         Map<String, String> values) {
      sendTo(compiledReport, report, format, values);
   }

   @Override
   public boolean supportsScheduling() {
      return true;
   }

}
