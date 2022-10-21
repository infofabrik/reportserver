package net.datenwerke.rs.base.service.reportengines.jasper.output.generator;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledCSVJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECPaged;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRCsvExporter;

public class JasperCsvOutputGenerator extends JasperOutputGeneratorImpl {

   @Inject
   public JasperCsvOutputGenerator(HookHandlerService hookHandler) {
      super(hookHandler);
   }

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Override
   public CompiledRSJasperReport exportReport(JasperPrint jasperPrint, String outputFormat, JasperReport report,
         User user, ReportExecutionConfig... configs) {

      JRAbstractExporter exporter;

      exporter = new JRCsvExporter();

      /* create buffer for output */
      StringBuffer out = new StringBuffer();

      /* configure exporter */
      exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
      exporter.setParameter(JRExporterParameter.OUTPUT_STRING_BUFFER, out);
      /*
       * https://community.jaspersoft.com/questions/1009991/how-set-
       * isusingimagestoalign-htmlexporter-java The new HtmlExporter does not support
       * using images for alignment. So you no longer need to set
       * IS_USING_IMAGES_TO_ALIGN to false, it is false by default.
       */
//		exporter.setParameter(JRHtmlExporterParameter.IS_USING_IMAGES_TO_ALIGN, false);

      /* preview ? */
      if (hasConfig(RECPaged.class, configs)) {
         exporter.setParameter(JRExporterParameter.START_PAGE_INDEX, getConfig(RECPaged.class, configs).getFirstPage());
         exporter.setParameter(JRExporterParameter.END_PAGE_INDEX, getConfig(RECPaged.class, configs).getLastPage());
      }

      callPreHooks(outputFormat, exporter, report, user);

      /* export */
      String htmlReport = ""; //$NON-NLS-1$
      try {
         exporter.exportReport();

         /* create html report */
         htmlReport = out.toString();
      } catch (JRException e) {
         logger.warn(e.getMessage(), e);
      }

      /* create return object */
      CompiledRSJasperReport cjrReport = new CompiledCSVJasperReport();
      cjrReport.setData(jasperPrint);

      /* add report to object */
      cjrReport.setReport(htmlReport);

      callPostHooks(outputFormat, exporter, report, cjrReport, user);

      /* return compiled report */
      return cjrReport;
   }

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_CSV };
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledCSVJasperReport();
   }

}
