package net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.olap4j.CellSet;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinHierarchy;
import org.saiku.olap.util.formatter.ICellSetFormatter;
import org.saiku.web.export.JSConverter;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.saiku.server.rest.objects.resultset.QueryResult;
import net.datenwerke.rs.saiku.server.rest.util.RestUtil;
import net.datenwerke.rs.saiku.service.saiku.SaikuModule;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledHTMLSaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledPDFSaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.rs.utils.misc.PdfUtils;

public class SaikuChartHTMLOutputGenerator extends SaikuOutputGeneratorImpl {

	@Inject
	protected PdfUtils pdfUtils;

	@Inject
	public SaikuChartHTMLOutputGenerator(HookHandlerService hookHandler) {
		super(hookHandler);
	}

	@Override
	public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet,
			CellSet cellset, List<ThinHierarchy> filters, ICellSetFormatter formatter,
			String outputFormat, ReportExecutionConfig... configs)
			throws ReportExecutorException {
		
		RECSaikuChart config = getConfig(RECSaikuChart.class, configs);
		String type = "bar";
		if(null != config){
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
			InputStream tpluri = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/charttemplate.html");
			
			InputStream tipsyCss = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/tipsy.css"); 
			InputStream jQuery = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/jquery.min.js");
			InputStream jQueryUi = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/jquery-ui.min.js");
			InputStream tipsyJs = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/jquery.tipsy.js");
			InputStream purlJs = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/purl.js");
			InputStream underscore = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/underscore.js");
			InputStream json2 = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/json2.js");
			InputStream backbone = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/backbone.js");

			InputStream protovis = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/protovis.js");
			InputStream protovisIE = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/protovis-msie.js");
			InputStream tipsyPlugin = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/tipsy.js");
			InputStream defPlugin = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/def.js");
			InputStream pvcPlugin = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/pvc-d.js");

			InputStream settings = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/Settings.js");
		
			InputStream plugin = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/plugin.js");
			
			InputStream saikuRenderer = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/SaikuRenderer.js");
			InputStream saikuChartRenderer = getClass().getClassLoader().getResourceAsStream("resources/saiku/render/SaikuChartRenderer.js");
			
			
			String tpl = IOUtils.toString(tpluri);
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
			tpl = tpl.replace("/*##SETTINGS##*/", IOUtils.toString(settings));
			tpl = tpl.replace("/*##PLUGIN##*/", IOUtils.toString(plugin));
			
			tpl = tpl.replace("/*##SAIKU-RENDERER##*/", IOUtils.toString(saikuRenderer));
			tpl = tpl.replace("/*##SAIKU-CHART-RENDERER##*/", IOUtils.toString(saikuChartRenderer));
			
			CompiledHTMLSaikuReport htmlReport = new CompiledHTMLSaikuReport(tpl);
			return htmlReport;
			
//			System.out.println(htmlReport.getReport());
//			ITextRenderer renderer = new ITextRenderer();
//			try {
//				pdfUtils.configureFontResolver(renderer.getFontResolver());
//				String html = (String) htmlReport.getReport();
//				renderer.setDocumentFromString(html);
//				
//				ByteArrayOutputStream os = new ByteArrayOutputStream();
//				renderer.layout();
//				renderer.createPDF(os);
//
//				byte[] cReport = os instanceof ByteArrayOutputStream ? ((ByteArrayOutputStream)os).toByteArray() : null;
//
//				return new CompiledPDFSaikuReport(cReport);
//			} catch (DocumentException e) {
//				throw new ReportExecutorRuntimeException(e);
//			} catch (IOException e) {
//				throw new ReportExecutorRuntimeException(e);
//			}
//			
		} catch (Exception e) {
			throw new ReportExecutorException(e);
		}
	}

	@Override
	public String[] getFormats() {
		return new String[]{SaikuModule.OUTPUT_FORMAT_CHART_HTML};
	}

	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledHTMLSaikuReport();
	}

}
