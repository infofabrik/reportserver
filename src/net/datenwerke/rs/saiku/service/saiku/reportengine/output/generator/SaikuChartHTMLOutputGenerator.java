package net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator;

import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.olap4j.CellSet;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinHierarchy;
import org.saiku.olap.util.formatter.ICellSetFormatter;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.saiku.server.rest.objects.resultset.QueryResult;
import net.datenwerke.rs.saiku.server.rest.util.RestUtil;
import net.datenwerke.rs.saiku.service.saiku.SaikuChartColorsService;
import net.datenwerke.rs.saiku.service.saiku.SaikuModule;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledHTMLSaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.rs.utils.misc.PdfUtils;

public class SaikuChartHTMLOutputGenerator extends SaikuOutputGeneratorImpl {

   @Inject
   protected PdfUtils pdfUtils;
   
   private final LicenseService licenseService;
   private final SaikuChartColorsService saikuChartColorsService;
   
   @Inject
   public SaikuChartHTMLOutputGenerator(
         HookHandlerService hookHandler,
         LicenseService licenseService,
         SaikuChartColorsService saikuChartColorsService
         ) {
      super(hookHandler);
      this.licenseService = licenseService;
      this.saikuChartColorsService = saikuChartColorsService;
   }

   @Override
   public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet, CellSet cellset, List<ThinHierarchy> filters,
         ICellSetFormatter formatter, String outputFormat, ReportExecutionConfig... configs)
         throws ReportExecutorException {

      RECSaikuChart config = getConfig(RECSaikuChart.class, configs);
      String type = "bar";
      if (null != config) {
         type = config.getType();
      }

      QueryResult queryResult = RestUtil.convert(cellDataSet);

      ObjectMapper om = new ObjectMapper();
      String json;
      try {
         json = om.writeValueAsString(queryResult);
      } catch (Exception e) {
         throw new ReportExecutorException(e);
      }

      try {
         String tplfile = "resources/saiku/charttemplate.html";
         URL resource = getClass().getClassLoader().getResource("reportserver.properties");
         URI tpluri = resource.toURI().resolve("../../" + tplfile);
         
         URI tipsyCss = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/tipsy.css"); 
         URI jQuery = resource.toURI().resolve("../../resources/saiku/js/jquery/jquery.min.js");
         URI jQueryUi = resource.toURI().resolve("../../resources/saiku/js/jquery/jquery-ui.min.js");
         URI tipsyJs = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/jquery.tipsy.js");
         URI purlJs = resource.toURI().resolve("../../resources/js/purl.js");
         URI underscore = resource.toURI().resolve("../../resources/saiku/js/backbone/underscore.js");
         URI json2 = resource.toURI().resolve("../../resources/saiku/js/backbone/json2.js");
         URI backbone = resource.toURI().resolve("../../resources/saiku/js/backbone/backbone.js");

         URI protovis = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/protovis.js");
         URI protovisIE = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/protovis-msie.js");
         URI tipsyPlugin = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/tipsy.js");
         URI defPlugin = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/def.js");
         URI pvcPlugin = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/pvc-d.js");

         
         URI settings = resource.toURI().resolve("../../resources/saiku/js/saiku/Settings.js");
         String settingsString = IOUtils.toString(settings);
     
         if (licenseService.isEnterprise()) {
            String colors = saikuChartColorsService.getChartColors();
            if (null == colors)
               colors = SaikuModule.SAIKU_CHARTS_DEFAULT_COLOR;
            settingsString = settingsString + "Settings.CHART_COLORS = [" + colors + "];";
         }
         
         URI plugin = resource.toURI().resolve("../../resources/saiku/js/saiku/plugins/CCC_Chart/plugin.js");
         
         URI saikuRenderer = resource.toURI().resolve("../../resources/saiku/js/saiku/render/SaikuRenderer.js");
         URI saikuChartRenderer = resource.toURI().resolve("../../resources/saiku/js/saiku/render/SaikuChartRenderer.js");
         
         
         String tpl = IOUtils.toString(tpluri.toURL().openStream());

         tpl = tpl.replace("/*##DATA##*/", json);
         tpl = tpl.replace("/*##TYPE##*/", type);

         tpl = tpl.replace("/*##TIPSY.CSS##*/", IOUtils.toString(tipsyCss));
         tpl = tpl.replace("/*##JQUERY##*/", IOUtils.toString(jQuery));
         tpl = tpl.replace("/*##JQUERY-UI##*/", IOUtils.toString(jQueryUi));
         tpl = tpl.replace("/*##TIPSY.JS##*/", IOUtils.toString(tipsyJs));
         tpl = tpl.replace("/*##PURL##*/", IOUtils.toString(purlJs));
         tpl = tpl.replace("/*##UNDERSCORE##*/", IOUtils.toString(underscore));
         tpl = tpl.replace("/*##JSON2##*/", IOUtils.toString(json2));
         tpl = tpl.replace("/*##BACKBONE##*/", IOUtils.toString(backbone));
         tpl = tpl.replace("/*##PROTOVIS##*/", IOUtils.toString(protovis));
         tpl = tpl.replace("/*##PROTOVIS-IE##*/", IOUtils.toString(protovisIE));
         tpl = tpl.replace("/*##TIPSY.PLUGIN##*/", IOUtils.toString(tipsyPlugin));
         tpl = tpl.replace("/*##DEF##*/", IOUtils.toString(defPlugin));
         tpl = tpl.replace("/*##PVC##*/", IOUtils.toString(pvcPlugin));
         tpl = tpl.replace("/*##SETTINGS##*/", settingsString);
         tpl = tpl.replace("/*##PLUGIN##*/", IOUtils.toString(plugin));

         tpl = tpl.replace("/*##SAIKU-RENDERER##*/", IOUtils.toString(saikuRenderer));
         tpl = tpl.replace("/*##SAIKU-CHART-RENDERER##*/", IOUtils.toString(saikuChartRenderer));

         CompiledHTMLSaikuReport htmlReport = new CompiledHTMLSaikuReport(tpl);
         return htmlReport;

//          System.out.println(htmlReport.getReport());
//          ITextRenderer renderer = new ITextRenderer();
//          try {
//              pdfUtils.configureFontResolver(renderer.getFontResolver());
//              String html = (String) htmlReport.getReport();
//              renderer.setDocumentFromString(html);
//              
//              ByteArrayOutputStream os = new ByteArrayOutputStream();
//              renderer.layout();
//              renderer.createPDF(os);
//
//              byte[] cReport = os instanceof ByteArrayOutputStream ? ((ByteArrayOutputStream)os).toByteArray() : null;
//
//              return new CompiledPDFSaikuReport(cReport);
//          } catch (DocumentException e) {
//              throw new ReportExecutorRuntimeException(e);
//          } catch (IOException e) {
//              throw new ReportExecutorRuntimeException(e);
//          }
//          
      } catch (Exception e) {
         throw new ReportExecutorException(e);
      }
   }

   @Override
   public String[] getFormats() {
      return new String[] { SaikuModule.OUTPUT_FORMAT_CHART_HTML };
   }

   @Override
   public CompiledReport getFormatInfo() {
      return new CompiledHTMLSaikuReport();
   }

}
