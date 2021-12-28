package net.datenwerke.rs.core.service.reportmanager.engine.hooks.adapter;

import java.io.OutputStream;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportEngineTakeOverExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class ReportEngineTakeOverExecutionHookAdapter implements ReportEngineTakeOverExecutionHook {

   @Override
   public boolean takesOver(ReportEngine engine, Report report, ParameterSet additionalParameters, User user,
         String outputFormat,
         net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs) {
      return false;
   }

   @Override
   public CompiledReport executeReport(ReportEngine engine, OutputStream os, Report report,
         ParameterSet additionalParameters, User user, String outputFormat,
         net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs) {
      return null;
   }

   @Override
   public CompiledReport executeReportDry(ReportEngine engine, Report report, ParameterSet additionalParameters,
         User user, String outputFormat,
         net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig[] configs) {
      return null;
   }

   @Override
   public boolean supportsStreaming(ReportEngine reportEngine, Report report, ParameterSet parameterSet, User user,
         String outputFormat, ReportExecutionConfig[] configs) {
      return false;
   }

}
