package net.datenwerke.rs.birt.service.reportengine.output.generator;

import java.io.ByteArrayOutputStream;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.HTMLRenderOption;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;

import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledHTMLBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledRSBirtReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;

public class BirtHTMLOutputGenerator extends BirtOutputGeneratorImpl {
	
	@Override
	public CompiledRSBirtReport exportReport(
			Object oRunAndRenderTask, String outputFormat,
			ReportExecutionConfig... configs) {
		
		try {
			IRunAndRenderTask runAndRenderTask = (IRunAndRenderTask) oRunAndRenderTask;
			HTMLRenderOption options = new HTMLRenderOption();

			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			options.setOutputStream(bos);
			options.setOutputFormat("html");
			options.setImageHandler(new Base64ImageHandler());

			/* adapt render options */
			RenderOption renderOptions = adapt(options);
			
			/* render */
			runAndRenderTask.setRenderOption(renderOptions);
			runAndRenderTask.run();

			/* create return object */
			CompiledHTMLBirtReport cbReport = new CompiledHTMLBirtReport();

			/* add report to object */
			cbReport.setReport(new String(bos.toByteArray()));

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
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_HTML};
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledHTMLBirtReport();
	}

}
