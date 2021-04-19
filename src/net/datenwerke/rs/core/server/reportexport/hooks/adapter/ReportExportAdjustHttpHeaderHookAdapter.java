package net.datenwerke.rs.core.server.reportexport.hooks.adapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.rs.core.server.reportexport.hooks.ReportExportAdjustHttpHeaderHook;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

/**
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.hookservices.HookAdapterProcessor")
public class ReportExportAdjustHttpHeaderHookAdapter implements ReportExportAdjustHttpHeaderHook {

	@Override
	public void adjustHeaders(Report report, CompiledReport executedReport, HttpServletRequest req, HttpServletResponse resp)  {
	}



}
