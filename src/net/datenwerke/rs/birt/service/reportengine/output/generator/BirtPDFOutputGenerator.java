package net.datenwerke.rs.birt.service.reportengine.output.generator;

import java.io.ByteArrayOutputStream;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;

import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPDFBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledRSBirtReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;

public class BirtPDFOutputGenerator extends BirtOutputGeneratorImpl {

	@Override
	public CompiledRSBirtReport exportReport(
			Object oRunAndRenderTask, String outputFormat,
			ReportExecutionConfig... configs) {

		try {
			IRunAndRenderTask runAndRenderTask = (IRunAndRenderTask) oRunAndRenderTask;
			
			PDFRenderOption options = new PDFRenderOption();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			options.setOutputStream(bos);
			options.setOutputFormat("pdf");

			/* adapt render options */
			RenderOption renderOptions = adapt(options);

			runAndRenderTask.setRenderOption(renderOptions);
			runAndRenderTask.run();
		
			/* create return object */
			CompiledPDFBirtReport cbReport = new CompiledPDFBirtReport();

			/* add report to object */
			cbReport.setReport(bos.toByteArray());

			/* return compiled report */
			return cbReport;

		} catch (EngineException e) {
			ReportExecutorRuntimeException rere = new ReportExecutorRuntimeException();
			rere.initCause(e);
			throw rere;
		}
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_PDF};
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledPDFBirtReport();
	}

}
