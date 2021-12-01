package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator;

import java.io.OutputStream;
import java.sql.Connection;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.output.ReportOutputGenerator;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;


public interface JxlsOutputGenerator extends ReportOutputGenerator {
	
	public CompiledReport exportReport(OutputStream os, byte[] template, Connection connection, JxlsReport jxlsReport, ParameterSet parameters, String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException;

}
