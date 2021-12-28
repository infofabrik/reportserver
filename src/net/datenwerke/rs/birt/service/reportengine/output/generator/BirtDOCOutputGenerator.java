package net.datenwerke.rs.birt.service.reportengine.output.generator;

import java.io.ByteArrayOutputStream;

import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.RenderOption;

import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledDOCBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledRSBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledXLSBirtReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;

public class BirtDOCOutputGenerator extends BirtOutputGeneratorImpl {

   @Override
   public CompiledRSBirtReport exportReport(Object oRunAndRenderTask, String outputFormat,
         ReportExecutionConfig... configs) {
      try {
         IRunAndRenderTask runAndRenderTask = (IRunAndRenderTask) oRunAndRenderTask;

         RenderOption options = new RenderOption();

         ByteArrayOutputStream bos = new ByteArrayOutputStream();
         options.setOutputStream(bos);
         options.setOutputFormat("doc");

         /* adapt render options */
         RenderOption renderOptions = adapt(options);

         /* render */
         runAndRenderTask.setRenderOption(renderOptions);
         runAndRenderTask.run();

         /* create return object */
         CompiledDOCBirtReport cbReport = new CompiledDOCBirtReport();

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
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_WORD };
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledXLSBirtReport();
   }

}
