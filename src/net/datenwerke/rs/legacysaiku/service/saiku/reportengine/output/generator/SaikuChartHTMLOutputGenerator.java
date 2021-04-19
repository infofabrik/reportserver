package net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator;

import java.net.URI;
import java.net.URL;
import java.util.List;

import javax.inject.Inject;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;
import org.olap4j.CellSet;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.CellDataSet;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.legacysaiku.server.rest.objects.resultset.QueryResult;
import net.datenwerke.rs.legacysaiku.server.rest.util.RestUtil;
import net.datenwerke.rs.legacysaiku.service.saiku.SaikuModule;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledHTMLSaikuReport;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;

public class SaikuChartHTMLOutputGenerator extends SaikuOutputGeneratorImpl {

	
	@Inject
	public SaikuChartHTMLOutputGenerator(HookHandlerService hookHandler) {
		super(hookHandler);
	}

	@Override
	public CompiledRSSaikuReport exportReport(CellDataSet cellDataSet,
			CellSet cellset, List<SaikuDimensionSelection> filters,
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
			String tplfile = "resources/legacysaiku/charttemplate.html";
			URL resource = getClass().getClassLoader().getResource("reportserver.properties");
			URI tpluri = resource.toURI().resolve("../../" + tplfile);
			
			String tpl = IOUtils.toString(tpluri.toURL().openStream());
			tpl = tpl.replace("/*##DATA##*/", json);
			tpl = tpl.replace("/*##TYPE##*/", type);
			
			return new CompiledHTMLSaikuReport(tpl);
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
