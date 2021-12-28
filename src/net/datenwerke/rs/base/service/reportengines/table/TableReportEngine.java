package net.datenwerke.rs.base.service.reportengines.table;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.gxtdto.client.servercommunication.exceptions.NonFatalException;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.datasources.transformers.DatasourceTransformationService;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.OrderPrecedence;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.hooks.TableExportHook;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.TableOutputGeneratorManager;
import net.datenwerke.rs.base.service.reportengines.table.output.metadata.TableMetadataExporter;
import net.datenwerke.rs.base.service.reportengines.table.output.metadata.TableMetadataExporterManager;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableReportColumnMetadataService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECCountData;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECMetadata;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECPaged;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECReportExecutorToken;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.engine.hooks.ReportEngineTakeOverExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.security.service.usermanager.entities.User;

/**
 * 
 * 
 *
 */
@Singleton
public class TableReportEngine extends ReportEngine<TableDataSource, TableOutputGenerator, TableMetadataExporter> {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final EntityClonerService entityClonerService;
   final private TableReportColumnMetadataService tableReportMetadataService;

   @Inject
   public TableReportEngine(EntityClonerService entityClonerService, TableOutputGeneratorManager outputGeneratorManager,
         TableMetadataExporterManager metadataExporterManager,
         TableReportColumnMetadataService tableReportMetadataService,
         DatasourceTransformationService datasourceTransformationService) {
      super(outputGeneratorManager, metadataExporterManager, datasourceTransformationService);

      /* store objects */
      this.entityClonerService = entityClonerService;
      this.tableReportMetadataService = tableReportMetadataService;
   }

   @Override
   public boolean consumes(Report report) {
      return report instanceof TableReport;
   }

   @Override
   protected CompiledReport doExecute(OutputStream os, Report report, User user, ParameterSet parameters,
         String outputFormat, ReportExecutionConfig... configs) throws ReportExecutorException {
      /* validate arguments and cast them */
      if (!(report instanceof TableReport))
         throw new IllegalArgumentException("Need a report of type TableReport (or TableReportVariant."); //$NON-NLS-1$

      if (((TableReport) report).isEnableSubtotals())
         return doExecuteWithSubtotals(os, (TableReport) report, user, parameters, outputFormat, configs);
      else
         return doExecuteNormal(os, (TableReport) report, user, parameters, outputFormat, configs);
   }

   private CompiledReport doExecuteWithSubtotals(OutputStream os, TableReport report, User user,
         ParameterSet parameters, String outputFormat, ReportExecutionConfig[] configs) throws ReportExecutorException {
      if (0 == report.getNumberOfAggregateColumns() || report.getNumberOfSubtotalGroupColumns() == 0)
         throw new IllegalArgumentException(
               ReportEnginesMessages.INSTANCE.exceptionNeedAggregateAndGroupForSubtotals());
      /* test filters only on allowed columns */
      for (Column col : report.getColumns())
         if (col.hasFilters() && null == col.getAggregateFunction() && !col.isGroupedBy())
            throw new IllegalArgumentException(
                  ReportEnginesMessages.INSTANCE.exceptionNoFilterOnNonAggregateNonGroupingForSubtotals());

      /* clone report twice: 1) to compute subtotals, 2) to compute detail rows */
      TableReport subtotalReport = entityClonerService.cloneEntity(report);
      TableReport detailReport = entityClonerService.cloneEntity(report);

      /* compute subtotals */
      subtotalReport.setEnableSubtotals(false);
      adaptSortForSubtotalReport(subtotalReport);
      /* remove non aggregate columns */
      for (Iterator<Column> iterator = subtotalReport.getColumns().iterator(); iterator.hasNext();) {
         Column col = iterator.next();
         if (null == col.getAggregateFunction() && !col.isSubtotalGroup())
            iterator.remove();
      }

      /* if we are supposed to count .. count the groups */
      if (hasConfig(RECCountData.class, configs))
         return doExecute(os, subtotalReport, user, parameters, outputFormat, configs);

      int[] reducedGroupIndices = subtotalReport.getSubtotalGroupColumnIndices();
      int[] reducedSubtotalIndices = subtotalReport.getAggregateColumnIndices();
      RSTableModel subtotals = (RSTableModel) doExecute(os, subtotalReport, user, parameters,
            ReportExecutorService.OUTPUT_FORMAT_TABLE, configs);

      /* compute details */

      /*
       * remove aggregate functions (and having filters and remove all ordering except
       * for grouping columns
       */
      adaptSortForSubtotalReport(detailReport);
      int[] subtotalIndices = report.getAggregateColumnIndices();
      for (Column col : detailReport.getColumns()) {
         if (null != col.getAggregateFunction()) {
            col.setFilter(null);
            col.setAggregateFunction(null);
         }
      }

      return doExecuteNormal(os, detailReport, report, user, parameters, outputFormat, configs, subtotals,
            subtotalIndices, reducedSubtotalIndices, reducedGroupIndices);
   }

   private void adaptSortForSubtotalReport(TableReport report) {
      for (Column col : report.getColumns()) {
         if (col.isSubtotalGroup()) {
            col.setOrder(Order.ASC);
            col.setOrderPrecedence(OrderPrecedence.HIGH);
         } else if (null != col.getOrder()) {
            col.setOrderPrecedence(OrderPrecedence.LOW);
         }
      }
   }

   private CompiledReport doExecuteNormal(OutputStream os, TableReport report, User user, ParameterSet parameters,
         String outputFormat, ReportExecutionConfig[] configs) throws ReportExecutorException {
      return doExecuteNormal(os, report, report, user, parameters, outputFormat, configs, null, null, null, null);
   }

   /**
    * 
    * @param report
    * @param user
    * @param parameters
    * @param outputFormat
    * @param configs
    * @param subtotals
    * @param subtotalIndices
    * @param reducedSubtotalIndices The indices of subtotal columns for the reduced
    *                               report (the one that was used to compute the
    *                               subtotals, here non grouping, non aggregating
    *                               columns were dropped)
    * @throws ReportExecutorException
    */
   private CompiledReport doExecuteNormal(OutputStream os, TableReport report, TableReport orgReport, User user,
         ParameterSet parameters, String outputFormat, ReportExecutionConfig[] configs, RSTableModel subtotals,
         int[] subtotalIndices, int[] reducedSubtotalIndices, int[] reducedGroupIndices)
         throws ReportExecutorException {
      /* get proper datasource */
//		TableDataSource ds = dataSourceTransformer.transform(report, parameters);
      TableDataSource ds = transformDatasource(TableDataSource.class, report, parameters);

      try {
         if (null == ds)
            throw new IllegalArgumentException("Could not find datasource for report");
         configureDataSource(ds, (TableReport) report, parameters, configs, user);

         /* add comment */
         ds.addQueryComment("user: " + String.valueOf(user.getId()));
         ds.addQueryComment("report: "
               + String.valueOf(null == orgReport.getId() ? orgReport.getOldTransientId() : orgReport.getId()));

         /* get output format generator */
         TableOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
         if (null == outputGenerator)
            throw new IllegalArgumentException("Could not find output generator for format: " + outputFormat //$NON-NLS-1$
                  + ". This is very strange and probably a bug in ReportServer."); //$NON-NLS-1$

         /* execute report */
         boolean hasData = false;
         if (null == subtotals)
            hasData = createReport(os, user, ds, (TableReport) report, outputGenerator, outputFormat, parameters,
                  configs);
         else
            hasData = createReportWithSubtotals(os, user, ds, (TableReport) report, orgReport, outputGenerator,
                  outputFormat, parameters, configs, subtotals, subtotalIndices, reducedSubtotalIndices,
                  reducedGroupIndices);

         CompiledReport compiledReport = outputGenerator.getTableObject();
         if (compiledReport instanceof CompiledTableReport)
            ((CompiledTableReport) compiledReport).setHasData(hasData);

         callPostHook(report, user, compiledReport, outputGenerator, outputFormat);

         return compiledReport;
      } finally {
         ds.close();
      }
   }

   private void configureDataSource(TableDataSource ds, TableReport report, ParameterSet parameters,
         ReportExecutionConfig[] configs, User user) throws ReportExecutorException {
      /* handle special modes */
      if (hasConfig(RECPaged.class, configs)) {
         RECPaged paged = getConfig(RECPaged.class, configs);
         ds.paged(paged.getPageSize() * (paged.getFirstPage() - 1),
               (paged.getLastPage() - paged.getFirstPage() + 1) * paged.getPageSize());
      } else if (hasConfig(RECMetadata.class, configs))
         ds.limit(0);
      else if (hasConfig(RECCountData.class, configs))
         ds.countRows();

      /* apply parameters */
      ds.applyParameters(parameters);

      /* make sure that columns are selected */
      if ((null == ((TableReport) report).getColumns() || ((TableReport) report).getColumns().isEmpty())
            && !((TableReport) report).isSelectAllColumns())
         throw new ReportExecutorException(ReportEnginesMessages.INSTANCE.exceptionNoColumnsSelected());

      if (((TableReport) report).isSelectAllColumns() || hasConfig(RECMetadata.class, configs))
         ds.applyColumnConfiguration(null);
      else
         ds.applyColumnConfiguration(((TableReport) report).getColumns());

      if (((TableReport) report).isIgnoreAdditionalColumns())
         ds.setIgnoreAnyColumnConfiguration(true);
      else
         ds.addAdditionalColumnSpecs(((TableReport) report).getAdditionalColumns());

      /* pre filter */
      ds.setPreFilter(((TableReport) report).getPreFilter().getRootBlock());

      try {
         tableReportMetadataService.augmentWithMetadata(((TableReport) report).getColumns(), (TableReport) report,
               user);
      } catch (NonFatalException e) {
         logger.warn(e.getMessage(), e);
      }

      if (null != ((TableReport) report).isDistinctFlag() && ((TableReport) report).isDistinctFlag())
         ds.distinct(true);
   }

   private boolean createReport(OutputStream os, User user, TableDataSource ds, TableReport report,
         TableOutputGenerator outputGenerator, String outputFormat, ParameterSet parameters,
         ReportExecutionConfig... configs) throws ReportExecutorException {
      boolean foundData = false;
      try {
         String executorToken = null;
         for (ReportExecutionConfig cfg : configs) {
            if (cfg instanceof RECReportExecutorToken)
               executorToken = ((RECReportExecutorToken) cfg).getToken();
         }

         /* open datasource */
         ds.open(executorToken);

         /* create TableModel */
         TableDefinition td = ds.getTableDefinition();
         Class<?>[] colTypes = td.getColumnTypes().toArray(new Class<?>[td.getColumnTypes().size()]);
         Integer[] colSqlTypes = td.getSqlColumnTypes().toArray(new Integer[td.getSqlColumnTypes().size()]);

         /* initialize output generator */
         CellFormatter[] cellFormatters = report.getCellFormatter(user);

         /* clean formatters */
         cellFormatters = cleanFormatters(cellFormatters, outputGenerator, td.size());

         /* init output */
         outputGenerator.initialize(os, td, false, report, report, cellFormatters, parameters, user, configs);

         /* notify hooks */
         callPreHook(report, user, td, outputGenerator, outputFormat);

         if (ds.next()) {
            /* add Fields */
            for (int i = 0; i < td.size(); i++) {
               outputGenerator.addField(ds.getFieldValue(i + 1), cellFormatters[i]);
            }

            foundData = true;
         }

         /* gather data */
         while (ds.next()) {
            outputGenerator.nextRow();

            /* add Fields */
            for (int i = 0; i < td.size(); i++) {
               outputGenerator.addField(ds.getFieldValue(i + 1), cellFormatters[i]);
            }
         }

         outputGenerator.close();
      } catch (IOException e) {
         throw new ReportExecutorException(e);
      } finally {
         ds.close();
      }
      return foundData;
   }

   private boolean createReportWithSubtotals(OutputStream os, User user, TableDataSource ds, TableReport report,
         TableReport orgReport, TableOutputGenerator outputGenerator, String outputFormat, ParameterSet parameters,
         ReportExecutionConfig[] configs, RSTableModel subtotals, int[] subtotalIndices, int[] reducedSubtotalIndices,
         int[] reducedGroupIndices) throws ReportExecutorException {
      /* prepare array to keep group fields */
      Object[] subtotalGroupFieldValues = new Object[report.getNumberOfSubtotalGroupColumns()];
      Object[] lastSubtotalGroupFieldValues = new Object[report.getNumberOfSubtotalGroupColumns()];
      int[] subtotalGroupFieldIndices = report.getSubtotalGroupColumnIndices();
      int nrOfSubtotalGroupFields = subtotalGroupFieldIndices.length;

      boolean foundData = false;
      try {
         /* open datasource */
         ds.open();

         /* create TableModel */
         TableDefinition td = ds.getTableDefinition();
         int rowSize = td.size();
         Class<?>[] colTypes = td.getColumnTypes().toArray(new Class<?>[rowSize]);
         Integer[] colSqlTypes = td.getSqlColumnTypes().toArray(new Integer[rowSize]);

         /* initialize output generator */
         CellFormatter[] cellFormatters = report.getCellFormatter(user);
         CellFormatter[] groupCellFormatters = orgReport.getCellFormatterForGroupRow(user);

         /* clean formatters */
         cellFormatters = cleanFormatters(cellFormatters, outputGenerator, td.size());
         groupCellFormatters = cleanFormatters(groupCellFormatters, outputGenerator, td.size());

         /* init output */
         outputGenerator.initialize(os, td, true, report, orgReport, cellFormatters, parameters, user, configs);

         /* notify hooks */
         callPreHook(report, user, td, outputGenerator, outputFormat);

         /* prepare array to hold row */
         Object[] rowData = new Object[rowSize];

         firstDataRow: while (ds.next()) {
            /* get row and populate group values */
            int groupFieldIndex = 0;
            for (int i = 0; i < rowSize; i++) {
               rowData[i] = ds.getFieldValue(i + 1);
               if (nrOfSubtotalGroupFields > groupFieldIndex && i == subtotalGroupFieldIndices[groupFieldIndex]) {
                  if (!isGroupRow(rowData[i], subtotals.getData().get(0).getRow(),
                        reducedGroupIndices[groupFieldIndex]))
                     continue firstDataRow;

                  subtotalGroupFieldValues[groupFieldIndex] = rowData[i];
                  groupFieldIndex++;
               }
            }

            /* add Fields */
            for (int i = 0; i < rowSize; i++)
               outputGenerator.addField(rowData[i], cellFormatters[i]);

            foundData = true;

            /* we found our first group .. everything is fine */
            break;
         }

         /* gather data */
         int groupCount = 0;
         nextDataRow: while (ds.next()) {
            int groupFieldIndex = 0;
            boolean groupBreak = false;
            lastSubtotalGroupFieldValues = subtotalGroupFieldValues.clone();

            for (int i = 0; i < rowSize; i++) {
               rowData[i] = ds.getFieldValue(i + 1);
               if (nrOfSubtotalGroupFields > groupFieldIndex && i == subtotalGroupFieldIndices[groupFieldIndex]) {
                  if ((null != subtotalGroupFieldValues[groupFieldIndex] || null != rowData[i])
                        && ((null == subtotalGroupFieldValues[groupFieldIndex] && null != rowData[i])
                              || (null != subtotalGroupFieldValues[groupFieldIndex] && null == rowData[i])
                              || !subtotalGroupFieldValues[groupFieldIndex].equals(rowData[i]))) {

                     if (!((groupCount + 1) < subtotals.getData().size() && isGroupRow(rowData[i],
                           subtotals.getData().get(groupCount + 1).getRow(), reducedGroupIndices[groupFieldIndex])))
                        continue nextDataRow;

                     /* new group */
                     subtotalGroupFieldValues[groupFieldIndex] = rowData[i];
                     groupBreak = true;
                  }
                  groupFieldIndex++;
               }
            }

            /* next row */
            outputGenerator.nextRow();

            if (groupBreak && subtotals.getData().size() > groupCount)
               outputGenerator.addGroupRow(subtotalIndices,
                     getCompactRow(reducedSubtotalIndices, subtotals.getData().get(groupCount++).getRow()),
                     subtotalGroupFieldIndices, lastSubtotalGroupFieldValues, rowSize, groupCellFormatters);

            /* add Fields */
            for (int i = 0; i < rowSize; i++)
               outputGenerator.addField(rowData[i], cellFormatters[i]);
         }

         /* did something go wrong */
         if (foundData && subtotals.getData().size() != groupCount + 1)
            throw new ReportExecutorException("Database and ReportServer do not concur on the number of groups ("
                  + (subtotals.getData().size()) + " != " + (groupCount + 1)
                  + "). This may be a problem with your database settings. For example MySQL might compare strings case insensitive and ignore trailing spaces. ReportServer does not!");

         /* last group row */
         if (foundData) {
            lastSubtotalGroupFieldValues = subtotalGroupFieldValues.clone();
            outputGenerator.nextRow();
            outputGenerator.addGroupRow(subtotalIndices,
                  getCompactRow(reducedSubtotalIndices, subtotals.getData().get(groupCount++).getRow()),
                  subtotalGroupFieldIndices, lastSubtotalGroupFieldValues, rowSize, groupCellFormatters);
         }

         outputGenerator.close();
      } catch (IOException e) {
         throw new ReportExecutorException(e);
      } finally {
         ds.close();
      }
      return foundData;
   }

   private CellFormatter[] cleanFormatters(CellFormatter[] cellFormatters, TableOutputGenerator outputGenerator,
         int numberOfColumns) {
      if (numberOfColumns > cellFormatters.length) {
         CellFormatter[] dummy = new CellFormatter[numberOfColumns];
         for (int i = 0; i < dummy.length; i++) {
            if (i < cellFormatters.length)
               dummy[i] = cellFormatters[i];
            else
               dummy[i] = Column.DUMMY_FORMATTER;
         }
         return dummy;
      } else if (numberOfColumns < cellFormatters.length) {
         CellFormatter[] dummy = new CellFormatter[numberOfColumns];
         for (int i = 0; i < dummy.length; i++)
            dummy[i] = Column.DUMMY_FORMATTER;
         return dummy;
      }
      return cellFormatters;
   }

   private boolean isGroupRow(Object data, Object[] subtotals, int index) {
      return null == data ? null == subtotals[index] : data.equals(subtotals[index]);
   }

   private Object[] getCompactRow(int[] subtotalIndices, Object[] row) {
      Object[] cRow = new Object[subtotalIndices.length];

      int index = 0;
      for (int i = 0; i < row.length; i++)
         if (index < subtotalIndices.length && subtotalIndices[index] == i)
            cRow[index++] = row[i];

      return cRow;
   }

   private void callPreHook(Report report, User user, TableDefinition td, TableOutputGenerator outputGenerator,
         String outputFormat) {
      for (TableExportHook exportHook : hookHandler.getHookers(TableExportHook.class))
         if (null != exportHook.getFormats() && exportHook.getFormats().contains(outputFormat))
            exportHook.beforeExport(report, user, td, outputGenerator, outputFormat);
   }

   private void callPostHook(Report report, User user, CompiledReport compiledReport,
         TableOutputGenerator outputGenerator, String outputFormat) {
      for (TableExportHook exportHook : hookHandler.getHookers(TableExportHook.class))
         if (null != exportHook.getFormats() && exportHook.getFormats().contains(outputFormat))
            exportHook.afterExport(report, user, compiledReport, outputGenerator, outputFormat);
   }

   @Override
   protected void exportAdditionalMetadata(Report report, User user, ParameterSet ps, TableMetadataExporter exporter) {
      exporter.beginColumnSection();
      for (Column column : ((TableReport) report).getColumns())
         exporter.visitColumn(column);
   }

   @Override
   public void isExecutable(Report report, ParameterSet additionalParameters, User user, String outputFormat,
         ReportExecutionConfig... configs) throws ReportExecutorException {
      super.isExecutable(report, additionalParameters, user, outputFormat, configs);

      if (((TableReport) report).getColumns().isEmpty() && !((TableReport) report).isCubeFlag())
         throw new ReportExecutorException(ReportEnginesMessages.INSTANCE.exceptionNoColumnsSelected());
   }

   @Override
   public boolean supportsStreaming(Report report, ParameterSet parameterSet, User user, String outputFormat,
         ReportExecutionConfig... configs) {
      for (ReportEngineTakeOverExecutionHook engineHooker : hookHandler
            .getHookers(ReportEngineTakeOverExecutionHook.class)) {
         if (engineHooker.takesOver(this, report, parameterSet, user, outputFormat, configs)) {
            return engineHooker.supportsStreaming(this, report, parameterSet, user, outputFormat, configs);
         }
      }

      try {
         TableOutputGenerator outputGenerator = outputGeneratorManager.getOutputGenerator(outputFormat);
         if (null != outputGenerator)
            return outputGenerator.supportsStreaming();
      } catch (Exception e) {
         logger.warn("Could not load outputFormat: " + outputFormat, e);
      }
      return true;
   }
}
