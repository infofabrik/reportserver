package net.datenwerke.rs.birt.service.reportengine.output.generator;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;
import org.eclipse.birt.report.engine.api.EngineException;
import org.eclipse.birt.report.engine.api.IRunAndRenderTask;
import org.eclipse.birt.report.engine.api.PDFRenderOption;
import org.eclipse.birt.report.engine.api.RenderOption;

import com.google.inject.Inject;

import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPNGBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledRSBirtReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;

public class BirtPNGOutputGenerator extends BirtOutputGeneratorImpl {

   @Inject
   public BirtPNGOutputGenerator() {
   }

   @Override
   public CompiledRSBirtReport exportReport(Object oRunAndRenderTask, String outputFormat,
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

         /* convert to png */
         BufferedImage[] images = pdfToImage(new ByteArrayInputStream(bos.toByteArray()));

         /* create return object */
         CompiledPNGBirtReport cbReport = new CompiledPNGBirtReport();

         /* add report to object */
         cbReport.setReport(images);

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
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_PNG };
   }

   private BufferedImage[] pdfToImage(InputStream is) {
      BufferedImage[] images = new BufferedImage[1];

      PDDocument document;
      try {
         document = PDDocument.load(is);

         BufferedImage o = new PDFRenderer(document).renderImage(0);
         images[0] = o;

         document.close();

         return images;
      } catch (IOException e) {
         throw new ReportExecutorRuntimeException(e);
      }
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledPNGBirtReport();
   }

}
