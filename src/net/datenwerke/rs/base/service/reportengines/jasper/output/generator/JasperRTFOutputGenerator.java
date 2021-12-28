package net.datenwerke.rs.base.service.reportengines.jasper.output.generator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRTFJasperReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRRtfExporter;

/**
 * Exports a jasper report to RTF.
 *
 */
public class JasperRTFOutputGenerator extends JasperOutputGeneratorImpl {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());
	
	@Inject
	public JasperRTFOutputGenerator(
		HookHandlerService hookHandler	
		){
		super(hookHandler);
	}

	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_RTF};
	}
	
	@Override
	public CompiledRSJasperReport exportReport(JasperPrint jasperPrint, String outputFormat, JasperReport report,  User user, ReportExecutionConfig...configs) {
		JRAbstractExporter exporter;
		
		exporter = new JRRtfExporter();
		
		/* create buffer for output */
		StringBuffer out = new StringBuffer();
		
		/* configure exporter */
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, out);
		
		callPreHooks(outputFormat, exporter, report, user);
		
		/* export */
		try {
			exporter.exportReport();
		} catch (JRException e) {
			logger.warn( e.getMessage(), e);
		}
		
		/* create return object */
		CompiledRSJasperReport cjrReport = new CompiledRTFJasperReport();
		cjrReport.setData(jasperPrint);
		
		/* add report to object */
		cjrReport.setReport(out.toString());
		
		callPostHooks(outputFormat, exporter, report, cjrReport, user);
		
		/* return compiled report */
		return cjrReport;
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledRTFJasperReport();
	}
}
