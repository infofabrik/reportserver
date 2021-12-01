package net.datenwerke.rs.legacysaiku.service.saiku.reportengine;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.legacysaiku.service.saiku.OlapQueryService;
import net.datenwerke.rs.legacysaiku.service.saiku.OlapUtilService;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReportVariant;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator.SaikuOutputGenerator;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.generator.SaikuOutputGeneratorManager;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporter;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporterManager;
import net.datenwerke.rs.legacysaiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.security.service.usermanager.entities.User;

import org.apache.commons.io.IOUtils;
import org.olap4j.CellSet;
import org.olap4j.metadata.Cube;
import org.legacysaiku.olap.dto.SaikuDimensionSelection;
import org.legacysaiku.olap.dto.resultset.CellDataSet;
import org.legacysaiku.olap.query.IQuery;
import org.legacysaiku.olap.query.IQuery.QueryType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;


public class SaikuReportEngine extends ReportEngine<Connection, SaikuOutputGenerator, SaikuMetadataExporter> {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private final SaikuReportService saikuReportService;
	private final OlapUtilService olapUtilService;
	private final OlapQueryService olapQueryService;

	@Inject
	public SaikuReportEngine(
			SaikuReportService saikuReportService,
			SaikuOutputGeneratorManager outputGeneratorManager,
			SaikuMetadataExporterManager metadataExporterManager, 
			DatasourceTransformationService datasourceTransformationService,
			
			OlapQueryService olapQueryService, 
			OlapUtilService olapUtilService
	) {
		super(outputGeneratorManager, metadataExporterManager, datasourceTransformationService);

		this.saikuReportService = saikuReportService;
		this.olapQueryService = olapQueryService;
		this.olapUtilService = olapUtilService;
	}

	@Override
	protected CompiledReport doExecute(OutputStream os, Report report,
			User user, ParameterSet parameters, String outputFormat,
			ReportExecutionConfig... configs) throws ReportExecutorException {

		SaikuOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
		if(null == outputGenerator)
			throw new IllegalArgumentException("Could not find output generator for format: " + outputFormat + ". This is very strange and probably a bug in ReportServer."); //$NON-NLS-1$ //$NON-NLS-2$
		
		outputGenerator.initialize((SaikuReport) report);
		
		try {
			IQuery query = createQuery((SaikuReport) report);
			
			CellDataSet cds = olapQueryService.execute(query, outputGenerator.getCellSetFormatter());
			CellSet cs = query.getCellset();
			
			List<SaikuDimensionSelection> filters = new ArrayList<SaikuDimensionSelection>();
			if (query.getType().equals(QueryType.QM)) {
				filters = olapQueryService.getAxisSelection(query, "FILTER");
			}
			
			CompiledRSSaikuReport compiledReport = outputGenerator.exportReport(cds, cs, filters, outputFormat, configs);
			if(null != os) {
				Object reportdata = compiledReport.getReport();
				if(reportdata instanceof byte[]){
					IOUtils.write((byte[]) compiledReport.getReport(), os);
				}else if(reportdata instanceof String){
					IOUtils.write((String)compiledReport.getReport(), os);
				}
				IOUtils.closeQuietly(os);
			}
			
			return compiledReport;
			
		} catch (Exception e) {
			throw new ReportExecutorRuntimeException("Failed to execute report ", e);
		}
	}
	
	
	private IQuery createQuery(SaikuReport report) throws Exception {
		Cube cube = olapUtilService.getCube(report);
		
		String connectionName = report.getUuid();
		String queryName = UUID.randomUUID().toString();
		
		if((report instanceof SaikuReportVariant) && null != ((SaikuReport) report).getQueryXml() && !((SaikuReport) report).getQueryXml().isEmpty()) {
			return olapQueryService.createNewOlapQuery(queryName, report, connectionName, cube, ((SaikuReport) report).getQueryXml());
		} else {
			return olapQueryService.createNewOlapQuery(queryName, report, connectionName, cube);
		}
	}
	

	@Override
	public boolean consumes(Report report) {
		if (! (report instanceof SaikuReport ))
			return false;
		
		DatasourceContainer datasourceContainer = ((SaikuReport)report).getDatasourceContainer();
		
		return ((MondrianDatasource)datasourceContainer.getDatasource()).isMondrian3();
	}

	@Override
	public boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat, ReportExecutionConfig... configs){
		return true;
	}
	

}
