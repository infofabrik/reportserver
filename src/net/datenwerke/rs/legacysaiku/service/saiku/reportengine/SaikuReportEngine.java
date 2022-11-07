package net.datenwerke.rs.legacysaiku.service.saiku.reportengine;

import java.io.OutputStream;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.olap4j.CellSet;
import org.olap4j.metadata.Cube;
import org.saiku.olap.dto.resultset.CellDataSet;
import org.saiku.olap.query2.ThinHierarchy;
import org.saiku.olap.query2.ThinQuery;
import org.saiku.olap.query2.ThinQueryModel.AxisLocation;
import org.saiku.olap.util.OlapResultSetUtil;
import org.saiku.olap.util.formatter.CellSetFormatterFactory;
import org.saiku.olap.util.formatter.FlattenedCellSetFormatter;
import org.saiku.olap.util.formatter.ICellSetFormatter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.inject.Inject;

import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.legacysaiku.service.saiku.OlapUtilService;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasource;
import net.datenwerke.rs.saiku.service.saiku.ThinQueryService;
import net.datenwerke.rs.saiku.service.saiku.entities.SaikuReport;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuOutputGenerator;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.generator.SaikuOutputGeneratorManager;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporter;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.metadata.SaikuMetadataExporterManager;
import net.datenwerke.rs.saiku.service.saiku.reportengine.output.object.CompiledRSSaikuReport;
import net.datenwerke.security.service.usermanager.entities.User;

public class SaikuReportEngine extends ReportEngine<Connection, SaikuOutputGenerator, SaikuMetadataExporter> {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final ThinQueryService thinQueryService;
   private final OlapUtilService olapUtilService;

   @Inject
   public SaikuReportEngine(SaikuOutputGeneratorManager outputGeneratorManager,
         SaikuMetadataExporterManager metadataExporterManager,
         DatasourceTransformationService datasourceTransformationService,

         OlapUtilService olapUtilService, ThinQueryService thinQueryService) {
      super(outputGeneratorManager, metadataExporterManager, datasourceTransformationService);

      this.olapUtilService = olapUtilService;
      this.thinQueryService = thinQueryService;
   }

   @Override
   protected CompiledReport doExecute(OutputStream os, Report report, User user, ParameterSet parameters,
         String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException {

      SaikuOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
      if (null == outputGenerator)
         throw new IllegalArgumentException("Could not find output generator for format: " + outputFormat //$NON-NLS-1$
               + ". This is very strange and probably a bug in ReportServer."); //$NON-NLS-1$

      outputGenerator.initialize((SaikuReport) report, user);

      try {
         Cube cube = olapUtilService.getCube((SaikuReport) report);
         ThinQuery query = createQuery((SaikuReport) report, cube);

         String formatterStr = query.getProperties().get("saiku.olap.result.formatter").toString();
         ICellSetFormatter formatter = new CellSetFormatterFactory().forName(formatterStr);

         CellSet cs = thinQueryService.executeInternalQuery(user, parameters, query, (SaikuReport) report, cube);
         CellDataSet cds = OlapResultSetUtil.cellSet2Matrix(cs, formatter);

         if (ThinQuery.Type.QUERYMODEL.equals(query.getType()) && formatter instanceof FlattenedCellSetFormatter
               && query.hasAggregators())
            thinQueryService.calculateTotals(query, cube, cds, cs, formatter);

         List<ThinHierarchy> filters = new ArrayList<ThinHierarchy>();
         if (ThinQuery.Type.QUERYMODEL.equals(query.getType())) {
            filters = query.getQueryModel().getAxes().get(AxisLocation.FILTER).getHierarchies();
         }

         CompiledRSSaikuReport compiledReport = outputGenerator.exportReport(cds, cs, filters, formatter, outputFormat,
               configs);
         if (null != os) {
            Object reportdata = compiledReport.getReport();
            if (reportdata instanceof byte[]) {
               IOUtils.write((byte[]) compiledReport.getReport(), os);
            } else if (reportdata instanceof String) {
               IOUtils.write((String) compiledReport.getReport(), os);
            }
            IOUtils.closeQuietly(os);
         }

         return compiledReport;

      } catch (Exception e) {
         throw new ReportExecutorRuntimeException("Failed to execute report ", e);
      }
   }

   private ThinQuery createQuery(SaikuReport report, Cube cube) throws Exception {
      /* check if report has a query */
      String reportDescription = report.getQueryXml();
      if (null == reportDescription || "".contentEquals(reportDescription.trim()))
         throw new IllegalStateException("Saiku report description should not be empty for export.");

      ThinQuery tq;
      if (thinQueryService.isOldQuery(reportDescription)) {
         tq = thinQueryService.convertQuery(reportDescription, cube, report);
      } else {
         ObjectMapper om = new ObjectMapper();
         tq = om.readValue(reportDescription, ThinQuery.class);
      }

      return tq;
   }

   @Override
   public boolean consumes(Report report) {
      if (!(report instanceof SaikuReport))
         return false;

      DatasourceContainer datasourceContainer = ((SaikuReport) report).getDatasourceContainer();
      return ((MondrianDatasource) datasourceContainer.getDatasource()).isMondrian3();
   }

   @Override
   public boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) {
      return true;
   }

}