package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine;

import java.io.OutputStream;
import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsOutputGenerator;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator.JxlsOutputGeneratorManager;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.metadata.JxlsMetadataExporter;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.metadata.JxlsMetadataExporterManager;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object.CompiledJxlsXlsReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object.CompiledJxlsXlsmReport;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.object.CompiledJxlsXlsxReport;
import net.datenwerke.security.service.usermanager.entities.User;

public class JxlsReportEngine extends ReportEngine<Connection, JxlsOutputGenerator, JxlsMetadataExporter> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());


	@Inject
	public JxlsReportEngine(
			JxlsOutputGeneratorManager outputGeneratorManager,
			JxlsMetadataExporterManager metadataExporterManager, 
			DatasourceTransformationService datasourceTransformationService
			) {
		super(outputGeneratorManager, metadataExporterManager, datasourceTransformationService);
	}

	@Override
	protected CompiledReport doExecute(OutputStream os, Report report,
			User user, ParameterSet parameters, String outputFormat,
			ReportExecutionConfig... configs) throws ReportExecutorException {
		/* validate arguments and cast them */
		if(! consumes(report))
			throw new IllegalArgumentException("Need a report of type JxlsReport."); //$NON-NLS-1$
		JxlsReport bReport = (JxlsReport) report;
		
		if(null == bReport.getReportFile()){
			throw new IllegalArgumentException("No file has been uploaded.");
		}

		Connection con = transformDatasource(Connection.class, bReport, parameters);
		

		return executeReport(os, con, bReport, parameters, outputFormat, user, configs);
	}

	protected CompiledReport executeReport(OutputStream os, Connection connection, JxlsReport jxlsReport,
			ParameterSet parameters, String outputFormat, User user,
			ReportExecutionConfig[] configs) {
		try{
			/* get output generator */
			JxlsOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
			
			/* get template */
			byte[] reportBytes = jxlsReport.getReportFile().getContent();
			
			return outputGenerator.exportReport(os, reportBytes, connection, jxlsReport, parameters, outputFormat, configs);
		}catch(Exception e){
			throw new ReportExecutorRuntimeException(e);
		} finally {
			try {
				if(null != connection && ! connection.isClosed())
					connection.close();
			} catch (SQLException e) {
				logger.info( "Could not close connection after jxls report execution", e);
			}
		}
	}
	
	@Override
	public CompiledReport executeDry(OutputStream os, Report report,
			ParameterSet parameterSet, User user, String outputFormat,
			ReportExecutionConfig[] configs) throws ReportExecutorException {
		/* get output generator */
		JxlsOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
		CompiledReport formatInfo = outputGenerator.getFormatInfo();
		if(null != formatInfo)
			return formatInfo;
		
		JxlsReport jxls = (JxlsReport) report;
		
		/* get template */
		String name = jxls.getReportFile().getName();
		
		if(name.endsWith(".xlsm"))
			return new CompiledJxlsXlsmReport(null);
		else if(name.endsWith(".xls"))
			return new CompiledJxlsXlsReport(null);
		
		return new CompiledJxlsXlsxReport(null);
	}

	@Override
	public boolean consumes(Report report) {
		return report instanceof JxlsReport;
	}
	
	@Override
	public boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat, ReportExecutionConfig... configs){
		return true;
	}


	

}
