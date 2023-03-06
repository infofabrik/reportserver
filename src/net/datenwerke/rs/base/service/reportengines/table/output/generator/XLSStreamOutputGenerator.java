package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.IntStream;

import javax.inject.Provider;

import org.apache.commons.configuration2.Configuration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.CellUtil;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;

import com.google.inject.Inject;

import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.columnfilter.FilterService;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.ColumnFormatCellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledXLSXTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.client.helper.ObjectHolder;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ExcelExportException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.oracle.StupidOracleService;
import net.datenwerke.security.service.usermanager.entities.User;

public class XLSStreamOutputGenerator extends TableOutputGeneratorImpl {

   private final ReportEnginesMessages messages = LocalizationServiceImpl.getMessages(ReportEnginesMessages.class);

   private CellStyle timeFormat;
   private CellStyle fullDateFormat;

   private int rowOffset = 0;
   private int column = 0;

   private SXSSFWorkbook workbook;

   private Sheet dataSheet;
   private Sheet configSheet;

   private CellStyle[] styles;

   private int[] columnWidths;

   private String[] nullReplacements;
   private Boolean[] exportNullAsString;

   private Row dataRow;

   private boolean dontStream = false;

   private final XLSOutputGenerator basicXlsGenerator;

   private final StupidOracleService sos;

   private final LicenseService licenseService;

   private int configRowOffset = 0;

   private CellStyle configHeaderCellStyle;

   private CellStyle configCellColorOdd;
   private CellStyle configCellColorEven;

   private ReportProperty outputParamsProperty;
   private ReportProperty outputCompleteConfigurationProperty;
   private ReportProperty outputFiltersProperty;

   private boolean outputParameters = false;
   private boolean outputCompleteConfiguration = false;
   private boolean outputFilters = false;

   private static final short EXCEL_COLUMN_WIDTH_FACTOR = 256;
   private static final int UNIT_OFFSET_LENGTH = 7;
   private static final int[] UNIT_OFFSET_MAP = new int[] { 0, 36, 73, 109, 146, 182, 219 };

   private final String dataSheetName;
   private final String configurationSheetName;

   private final Provider<FilterService> filterServiceProvider;

   @Inject
   public XLSStreamOutputGenerator(XLSOutputGenerator basicXlsGenerator, StupidOracleService sos,
         LicenseService licenseService, ConfigService configService, Provider<FilterService> filterServiceProvider) {
      super();
      this.basicXlsGenerator = basicXlsGenerator;
      this.sos = sos;
      this.licenseService = licenseService;
      this.filterServiceProvider = filterServiceProvider;

      Configuration config = configService.getConfigFailsafe(XLSOutputGenerator.CONFIG_FILE);
      dataSheetName = config.getString("xls.datasheet", messages.outputNameDynamicList());
      configurationSheetName = config.getString("xls.configsheet", messages.configuration());
   }

   @Override
   public String[] getFormats() {
      return new String[] { ReportExecutorService.OUTPUT_FORMAT_EXCEL };
   }

   @Override
   public void initialize(final OutputStream os, final TableDefinition table, final boolean withSubtotals, final TableReport report,
         final TableReport originalReport, final CellFormatter[] cellFormatters, final ParameterSet parameters, final User user,
         final ReportExecutionConfig... configs) throws IOException {
      super.initialize(os, table, withSubtotals, report, originalReport, cellFormatters, parameters, user, configs);
      if (withSubtotals) {
         basicXlsGenerator.initialize(os, table, withSubtotals, report, originalReport, cellFormatters, parameters,
               user, configs);
         return;
      }

      int columnCount = td.getColumns().size();

      /* adjust stream */
      if (null == this.os) {
         this.os = new ByteArrayOutputStream();
         dontStream = true;
      }

      /* create workbook */
      workbook = new SXSSFWorkbook(1000); // keep 1000 rows in memory, exceeding rows will be flushed to disk
      workbook.setCompressTempFiles(true);
      dataSheet = workbook.createSheet(dataSheetName);

      /* load columns */
      List<Column> columns;
      if (report.isSelectAllColumns()) {
         columns = new ArrayList<Column>();
         for (Object[] c : td.getColumns()) {
            Column col = new Column();
            col.setName((String) c[0]);
            col.setType(((Integer) c[3]));
            columns.add(col);
         }
      } else {
         columns = report.getVisibleColumns();
      }
      nullReplacements = new String[columnCount];
      exportNullAsString = new Boolean[columnCount];
      IntStream.range(0, columnCount).forEach(i -> prepareNullFormat(cellFormatters[i], columns.get(i), i));

      /* prepare array for widths for columns */
      columnWidths = new int[columnCount];

      Row headerRow = dataSheet.createRow(rowOffset);

      /* prepare formats */
      styles = new CellStyle[columnCount];
      CreationHelper createHelper = workbook.getCreationHelper();
      for (int i = 0; i < columns.size(); i++) {
         Column column = columns.get(i);
         if (null != column && null != column.getFormat()) {
            ColumnFormat format = column.getFormat();
            if (format instanceof ColumnFormatCurrency) {
               CellStyle style = workbook.createCellStyle();
               String pattern = ((ColumnFormatNumber) format).getPattern().replace("\u00A4",
                     "\"" + ((ColumnFormatCurrency) format).getCurrencyType().getCurrency().getSymbol() + "\"");
               style.setDataFormat(createHelper.createDataFormat().getFormat(pattern));
               styles[i] = style;
            } else if (format instanceof ColumnFormatNumber) {
               CellStyle style = workbook.createCellStyle();
               style.setDataFormat(
                     createHelper.createDataFormat().getFormat(((ColumnFormatNumber) format).getPatternForExcel()));
               styles[i] = style;
            } else if (format instanceof ColumnFormatDate) {
               CellStyle style = workbook.createCellStyle();
               style.setDataFormat(
                     createHelper.createDataFormat().getFormat(((ColumnFormatDate) format).getTargetFormat()));
               styles[i] = style;
            } else if (format instanceof ColumnFormatText) {
               CellStyle style = workbook.createCellStyle();
               style.setWrapText(true);
               styles[i] = style;
            }
         }
      }

      /* add header */
      int i = 0;
      for (String name : table.getColumnNames()) {
         /* add name to cell */
         Cell cell = headerRow.createCell(i);
         cell.setCellValue(name);

         /* column type */
         Class<?> cType = table.getColumnType(i);

         /* adjust column width */
         if (cType.equals(Date.class) || cType.equals(Timestamp.class))
            columnWidths[i] = Math.max(Math.max(messages.dateFormat().length(), name.length()), 15);
         else if (cType.equals(Timestamp.class))
            columnWidths[i] = Math.max(Math.max(messages.timeFormat().length(), name.length()), 15);
         else
            columnWidths[i] = Math.max(name.length(), 10);

         /* increment counter */
         i++;
      }

      /* create first row */
      dataRow = dataSheet.createRow(rowOffset + 1);

      if (licenseService.isEnterprise())
         exportConfiguration(parameters);
   }

   private void exportConfiguration(ParameterSet parameters) {
      Font headerFont = workbook.createFont();
      headerFont.setBold(true);
      headerFont.setFontHeightInPoints((short) 14);
      headerFont.setColor(IndexedColors.BLACK.getIndex());

      // Create a CellStyle with the font
      configHeaderCellStyle = workbook.createCellStyle();
      configHeaderCellStyle.setFont(headerFont);

      outputParamsProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_PARAMETERS.getValue());
      if (outputParamsProperty instanceof ReportStringProperty)
         outputParameters = ((ReportStringProperty) outputParamsProperty).toBoolean();

      outputCompleteConfigurationProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_COMPLETE_CONFIGURATION.getValue());
      if (outputCompleteConfigurationProperty instanceof ReportStringProperty)
         outputCompleteConfiguration = ((ReportStringProperty) outputCompleteConfigurationProperty).toBoolean();

      outputFiltersProperty = report
            .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FILTERS.getValue());
      if (outputFiltersProperty instanceof ReportStringProperty)
         outputFilters = ((ReportStringProperty) outputFiltersProperty).toBoolean();

      if (outputParameters || outputCompleteConfiguration) {
         ReportProperty includeHiddenProperty = report
               .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_INCLUDE_PARAMETERS.getValue());
         boolean includeHidden = false;
         if (includeHiddenProperty instanceof ReportStringProperty)
            includeHidden = ((ReportStringProperty) includeHiddenProperty).toBoolean();

         Map<String, Object> outputConfiguration = null;
         if (outputCompleteConfiguration)
            outputConfiguration = parameters.getCompleteConfiguration(true, includeHidden);
         else
            outputConfiguration = parameters.getParameterMapSimpleFiltered(true, includeHidden);

         if (!outputConfiguration.isEmpty()) {
            configSheet = workbook.createSheet(configurationSheetName);
         } else {
            configSheet = workbook.createSheet(configurationSheetName);
            Row parameterHeading = configSheet.createRow(rowOffset);
            Cell cellParameter = parameterHeading.createCell(0);
            cellParameter.setCellValue(
                  outputCompleteConfiguration ? messages.configurationParameter() : messages.parameters());
            configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
            cellParameter.setCellStyle(configHeaderCellStyle);
            configRowOffset++;

            Row noParameter = configSheet.createRow(configRowOffset);
            cellParameter = noParameter.createCell(0);
            cellParameter.setCellValue(messages.noparameters());
            configRowOffset++;
            configRowOffset++;// for adding empty line between parameter and filter
         }
         addParameterOutput(parameters);
      }
      if (outputFilters || outputCompleteConfiguration) {
         configSheet = workbook.getSheet(configurationSheetName);
         if (null == configSheet)
            configSheet = workbook.createSheet(configurationSheetName);

         addFiltersOutput();
         configRowOffset++;
         addPrefilterOutput();
      }
   }

   private void addParameterOutput(ParameterSet parameters) {
      if (outputParameters || outputCompleteConfiguration) {
         ReportProperty includeHiddenProperty = report
               .getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_INCLUDE_PARAMETERS.getValue());
         boolean includeHidden = false;
         if (includeHiddenProperty instanceof ReportStringProperty)
            includeHidden = ((ReportStringProperty) includeHiddenProperty).toBoolean();

         Map<String, Object> outputConfiguration = null;
         if (outputCompleteConfiguration)
            outputConfiguration = parameters.getCompleteConfiguration(true, includeHidden);
         else
            outputConfiguration = parameters.getParameterMapSimpleFiltered(true, includeHidden);

         if (outputConfiguration.isEmpty())
            return;

         outputConfiguration = new TreeMap<>(outputConfiguration);

         Row parameterHeading = configSheet.createRow(rowOffset);
         Cell cellParameter = parameterHeading.createCell(0);
         cellParameter
               .setCellValue(outputCompleteConfiguration ? messages.configurationParameter() : messages.parameters());
         configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
         cellParameter.setCellStyle(configHeaderCellStyle);

         configRowOffset++;
         Row headerRow = configSheet.createRow(configRowOffset);
         cellParameter = headerRow.createCell(0);
         cellParameter
               .setCellValue(outputCompleteConfiguration ? messages.configurationParameter() : messages.parameter());

         Cell cellParameterValue = headerRow.createCell(1);
         cellParameterValue.setCellValue(messages.value());

         configRowOffset++;

         Iterator<String> paramsIterator = outputConfiguration.keySet().iterator();
         while (paramsIterator.hasNext()) {
            String parameter = paramsIterator.next();
            String paramValue = null != outputConfiguration.get(parameter)
                  ? outputConfiguration.get(parameter).toString()
                  : null;
            Row paramRow = configSheet.createRow(configRowOffset);

            cellParameter = paramRow.createCell(0);
            cellParameter.setCellValue(parameter);

            cellParameter = paramRow.createCell(1);
            cellParameter.setCellValue(paramValue);

            configRowOffset++;
         }

         configRowOffset++;
      }
   }

   private void addFiltersOutput() {
      Map<String, Map<String, Object>> filterMap = filterServiceProvider.get().getFilterMap(report);

      addFilterHeading(!filterMap.isEmpty());

      if (filterMap.isEmpty()) {
         configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
         configRowOffset++;
         Row noFilterRow = configSheet.createRow(configRowOffset);
         Cell noFilterCell = noFilterRow.createCell(0);
         noFilterCell.setCellValue(messages.nofilters());
         configRowOffset++;
      } else {
         configRowOffset++;
         ObjectHolder<Row> columnRow = new ObjectHolder<>();
         columnRow.set(configSheet.createRow(configRowOffset));

         filterMap.forEach((column, filters) -> {
            final int rowSpan = filters.size();
            final Cell columnNameCell = columnRow.get().createCell(0);

            columnNameCell.setCellValue(column);
            configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset + (rowSpan - 1), 0, 0));
            CellUtil.setVerticalAlignment(columnNameCell, VerticalAlignment.CENTER);

            filters.forEach((type, value) -> {
               Cell cell = columnRow.get().createCell(1);

               cell.setCellValue(type);
               cell = columnRow.get().createCell(2);
               cell.setCellValue(value.toString());

               configRowOffset++;
               columnRow.set(configSheet.createRow(configRowOffset));
            });
         });
      }
   }

   private void addFilterHeading(boolean hasFilter) {
      Row filterHeading = configSheet.createRow(configRowOffset);
      Cell cellFilter = filterHeading.createCell(0);
      cellFilter.setCellStyle(configHeaderCellStyle);
      cellFilter.setCellValue(messages.filters());
      if (hasFilter) {
         configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
         cellFilter.setCellStyle(configHeaderCellStyle);

         configRowOffset++;
         Row headerRow = configSheet.createRow(configRowOffset);
         cellFilter = headerRow.createCell(0);
         cellFilter.setCellValue(messages.columns());

         cellFilter = headerRow.createCell(1);
         cellFilter.setCellValue(messages.type());

         cellFilter = headerRow.createCell(2);
         cellFilter.setCellValue(messages.value());
      }
   }

   private void addPrefilterOutput() {
      Map<BlockType, Object> prefilterMap = filterServiceProvider.get().getPrefilterMap(report);

      addPrefilterHeading();

      if (prefilterMap.isEmpty()) {
         configRowOffset++;
         configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
         Row noFilterRow = configSheet.createRow(configRowOffset);
         Cell noFilterCell = noFilterRow.createCell(0);
         noFilterCell.setCellValue(messages.noprefilter());
      } else {
         // Create a CellStyle with foreground color
         configCellColorOdd = workbook.createCellStyle();
         configCellColorOdd.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
         configCellColorOdd.setFillPattern(FillPatternType.SOLID_FOREGROUND);

         configCellColorEven = workbook.createCellStyle();
         configCellColorEven.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
         configCellColorEven.setFillPattern(FillPatternType.SOLID_FOREGROUND);

         int maxBlockDepth = filterServiceProvider.get().calculatePrefilterDepth(report);
         prefilterMap.forEach((blockType, val) -> {
            printPrefilterBlock(blockType, (Set<?>) val, 0, maxBlockDepth);
         });

      }
   }

   private void printPrefilterBlock(final BlockType blockType, Set<?> childElements, final int currentDepth,
         final int maxDepth) {
      configRowOffset++;
      Row preFilterRow = configSheet.createRow(configRowOffset);
      IntStream.range(currentDepth, maxDepth + 1).forEach(i -> {
         Cell cellPreFilter = preFilterRow.createCell(i);
         cellPreFilter.setCellStyle(currentDepth % 2 == 0 ? configCellColorEven : configCellColorOdd);
         if (i == currentDepth)
            cellPreFilter.setCellValue(blockType.name());
      });

      childElements.forEach(child -> {
         if (child instanceof Map) {
            Map<?, ?> childMap = (Map<?, ?>) child;
            childMap.forEach((childKey, childVal) -> {
               if (childKey instanceof BlockType && childVal instanceof Set) {
                  // FilterBlock
                  printPrefilterBlock((BlockType) childKey, (Set<?>) childVal, currentDepth + 1, maxDepth);
               } else if (childKey instanceof String && childVal instanceof List) {
                  // BinaryColumnFilter
                  printBinaryColumnFilter(currentDepth + 1, (String) childKey, (List<?>) childVal);
               } else if (childKey instanceof String && childVal instanceof Map) {
                  // ColumnFilter
                  printColumnFilter(currentDepth + 1, (String) childKey, (Map<?, ?>) childVal);
               }
            });
         }
      });
   }

   private void printBinaryColumnFilter(int offset, String operator, List<?> values) {
      if (2 != values.size())
         throw new IllegalArgumentException("Values of binary column filter must have size 2");

      configRowOffset++;
      Row row = configSheet.createRow(configRowOffset);
      Cell cell = row.createCell(offset);
      cell.setCellValue(values.get(0).toString() + " " + operator + " " + values.get(1).toString());
   }

   private void printColumnFilter(int offset, String columnName, Map<?, ?> columnFilters) {
      configRowOffset++;
      Row r = configSheet.createRow(configRowOffset);
      Cell c = r.createCell(offset);
      c.setCellValue(columnName);
      columnFilters.forEach((filterKey, filterVal) -> {
         configRowOffset++;
         Row row = configSheet.createRow(configRowOffset);
         Cell cell = row.createCell(offset);
         cell.setCellValue("      " + filterKey.toString() + " " + filterVal.toString());
      });
   }

   private void addPrefilterHeading() {
      Row filterHeading = configSheet.createRow(configRowOffset);
      Cell cellFilter = filterHeading.createCell(0);
      cellFilter.setCellValue(messages.prefilter());
      configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
      cellFilter.setCellStyle(configHeaderCellStyle);
   }

   @Override
   public void addField(Object field, CellFormatter cellFormatter) throws IOException {
      if (withSubtotals) {
         basicXlsGenerator.addField(field, cellFormatter);
         return;
      }

      Cell cell = dataRow.createCell(column);

      /* content */
      Object originalContent = getValueOf(field);
      Object content = originalContent;

      if (cellFormatter instanceof ColumnFormatCellFormatter) {
         ColumnFormat columnFormat = ((ColumnFormatCellFormatter) cellFormatter).getColumnFormat();

         if (columnFormat instanceof ColumnFormatTemplate)
            content = cellFormatter.format(content);

         if (columnFormat instanceof ColumnFormatDate && content instanceof String) {
            try {
               content = ((ColumnFormatDate) columnFormat).parse((String) content);
            } catch (ParseException e) {
            }
         }
      }

      /* if content is null then write NULL */
      if (null == content)
         content = nullReplacements[column];

      /* column type */
      Class<?> cType = content.getClass();

      /* add content to cell */
      try {
         addContentToCell(cType, content, originalContent, workbook, dataSheet, column, cell, styles, cellFormatter);
      } catch (ExcelExportException e) {
         throw new RuntimeException(e);
      }

      column++;
   }

   private void prepareNullFormat(CellFormatter cellFormatter, Column column, int col) {
      if (null == cellFormatter) {
         nullReplacements[col] = null != column.getNullReplacementFormat() ? column.getNullReplacementFormat()
               : "NULL";
         exportNullAsString[col] = column.isExportNullAsString();
         return;
      }

      if (Integer.class.equals(td.getColumnType(col)) || Long.class.equals(td.getColumnType(col))
            || Byte.class.equals(td.getColumnType(col)) || Short.class.equals(td.getColumnType(col))) {

         nullReplacements[col] = cellFormatter.format(null);
      } else if (Double.class.equals(td.getColumnType(col)) || Float.class.equals(td.getColumnType(col))
            || BigDecimal.class.equals(td.getColumnType(col))) {

         nullReplacements[col] = cellFormatter.format(null);
      }
      exportNullAsString[col] = column.isExportNullAsString();
      if (null == nullReplacements[col])
         nullReplacements[col] = null != column.getNullReplacementFormat() ? column.getNullReplacementFormat()
               : "NULL";
   }

   @Override
   public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices,
         Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) throws IOException {
      basicXlsGenerator.addGroupRow(subtotalIndices, subtotals, subtotalGroupFieldIndices, subtotalGroupFieldValues,
            rowSize, cellFormatters);
   }

   @Override
   public void close() throws IOException {
      if (withSubtotals) {
         basicXlsGenerator.close();
         return;
      }

      /* adjust column sizes */
      List<Column> columns = report.getVisibleColumns();
      for (int col = 0; col < td.getColumnCount(); col++) {
         Column column = columns.get(col);
         short colWidth;
         if (null != column.getPreviewWidth()) {
            colWidth = pixel2WidthUnits(column.getPreviewWidth());
            dataSheet.setColumnWidth(col, colWidth);
         }
      }

      workbook.write(os);
      os.close();
   }

   // https://stackoverflow.com/a/31837639
   private short pixel2WidthUnits(int pxs) {
      short widthUnits = (short) (EXCEL_COLUMN_WIDTH_FACTOR * (pxs / UNIT_OFFSET_LENGTH));
      widthUnits += UNIT_OFFSET_MAP[(pxs % UNIT_OFFSET_LENGTH)];
      return widthUnits;
   }

   @Override
   public void nextRow() throws IOException {
      if (withSubtotals) {
         basicXlsGenerator.nextRow();
         return;
      }

      rowOffset++;
      column = 0;

      /* create normal data row */
      dataRow = dataSheet.createRow(rowOffset + 1);
   }

   @Override
   public CompiledReport getTableObject() {
      if (withSubtotals) {
         return basicXlsGenerator.getTableObject();
      }

      return new CompiledXLSXTableReport(!dontStream ? null : ((ByteArrayOutputStream) os).toByteArray());
   }

   @Override
   public CompiledReport getFormatInfo() {
      if (withSubtotals) {
         return basicXlsGenerator.getFormatInfo();
      }

      return new CompiledXLSXTableReport(null);
   }

   @Override
   protected boolean hasConfig(Class<? extends ReportExecutionConfig> type) {
      if (withSubtotals)
         return basicXlsGenerator.hasConfig(type);
      return super.hasConfig(type);
   }

   @Override
   protected <R extends ReportExecutionConfig> R getConfig(Class<? extends R> type) {
      if (withSubtotals)
         return basicXlsGenerator.getConfig(type);
      return super.getConfig(type);
   }

   @Override
   protected ReportExecutionConfig[] getConfigs() {
      if (withSubtotals)
         return basicXlsGenerator.getConfigs();
      return super.getConfigs();
   }

   @Override
   protected Report getReport() {
      if (withSubtotals)
         return basicXlsGenerator.getReport();
      return super.getReport();
   }

   @Override
   protected TableDefinition getTd() {
      if (withSubtotals)
         return basicXlsGenerator.getTd();
      return super.getTd();
   }

   @Override
   public boolean isCatchAll() {
      if (withSubtotals)
         return basicXlsGenerator.isCatchAll();
      return super.isCatchAll();
   }

   @Override
   protected Object getValueOf(Object field) {
      if (withSubtotals)
         return basicXlsGenerator.getValueOf(field);
      return super.getValueOf(field);
   }

   private void addContentToCell(Class<?> cType, Object content, Object originalContent, Workbook workbook, Sheet sheet,
         int column, Cell cell, CellStyle[] styles, CellFormatter cellFormatter) throws ExcelExportException {
      /* string content */
      String sContent = String.valueOf(content);
      String originalsContent = null != originalContent? String.valueOf(originalContent): null;
      if (null != sContent)
         columnWidths[column] = Math.max(sContent.length(), columnWidths[column]);

      try {
         /* date */
         if (cType.equals(java.util.Date.class) || cType.equals(java.sql.Date.class)) {
            addDate((java.util.Date) content, (java.util.Date) originalContent, workbook, sheet, column, cell, styles);
         } else if (cType.equals(Time.class)) {
            addTime((Time) content, (Time) originalContent, workbook, sheet, column, cell, styles);
         } else if (cType.equals(Timestamp.class)) {
            /* special oracle handling */
            if (sos.isOracleTimestamp(content)) {
               java.util.Date d = sos.getDateFromOracleDatum(content);
               java.util.Date originald = sos.getDateFromOracleDatum(originalContent);
               addDate(d, originald, workbook, sheet, column, cell, styles);
            } else {
               Timestamp ts = (Timestamp) content;
               Timestamp originalts = (Timestamp) originalContent;
               long time = ts.getTime() + (ts.getNanos() / 1000000);
               long originaltime = originalts.getTime() + (originalts.getNanos() / 1000000);

               java.util.Date d = new java.util.Date(time);
               java.util.Date originald = new java.util.Date(originaltime);
               addDate(d, originald, workbook, sheet, column, cell, styles);
            }
         } // continues

         /* binary data */
         else if (cType.equals(Byte[].class)) {
            if (null != originalContent)
               cell.setCellValue(messages.rsTableToXLSBinaryData());
            else
               setBlank(cell, column);
         } // continues

         /* integer */
         else if (cType.equals(Integer.class)) {
            addInteger((Integer) content, (Integer) originalContent, sheet, column, cell, styles);
         } else if (cType.equals(Long.class)) {
            /* excel does not support long */
            addDouble(Double.valueOf((Long) content),
                  null != originalContent ? Double.valueOf((Long) originalContent) : null, sheet, column, cell, styles);
         } else if (cType.equals(Byte.class)) {
            addInteger(((Byte) content).intValue(),
                  null != originalContent ? ((Byte) originalContent).intValue() : null, sheet, column, cell, styles);
         } else if (cType.equals(Short.class)) {
            addInteger(((Short) content).intValue(),
                  null != originalContent ? ((Short) originalContent).intValue() : null, sheet, column, cell, styles);
         } // continues

         /* double */
         else if (cType.equals(Double.class)) {
            addDouble((Double) content, (Double) originalContent, sheet, column, cell, styles);
         } else if (cType.equals(Float.class)) {
            addDouble(((Float) content).doubleValue(),
                  null != originalContent ? ((Float) originalContent).doubleValue() : null, sheet, column, cell,
                  styles);
         } else if (cType.equals(BigDecimal.class)) {
            addDouble(((BigDecimal) content).doubleValue(),
                  null != originalContent ? ((BigDecimal) originalContent).doubleValue() : null, sheet, column, cell,
                  styles);
         } // continues

         /* strings */
         else {
            addString(sContent, originalsContent, sheet, column, cell, styles);
         }
      } catch (NullPointerException e) {
         cell.setCellValue(cellFormatter.format(null));
      }
   }
   
   private void setBlank(Cell cell, int column) {
      if (!exportNullAsString[column])
         cell.setBlank();
      else {
         String nullReplacement = nullReplacements[column];
         cell.setCellValue(nullReplacement);
      }
   }
   
   private void addTime(Time content, Time originalContent, Workbook workbook, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
      CellStyle dateFormat = styles[column] == null ? getTimeFormat(workbook) : styles[column];
      cell.setCellStyle(dateFormat);
      if (null != originalContent)
         cell.setCellValue(content);
      else
         setBlank(cell, column);
   }

   private void addDate(java.util.Date content, java.util.Date originalContent, Workbook workbook, Sheet sheet, int column, Cell cell,
         CellStyle[] styles) {
      CellStyle dateFormat = styles[column] == null ? getFullDateFormat(workbook) : styles[column];
      cell.setCellStyle(dateFormat);
      if (null != originalContent)
         cell.setCellValue(content);
      else
         setBlank(cell, column);
   }

   private void addString(String content, String originalContent, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
      if (null != styles[column]) {
         CellStyle format = styles[column];
         cell.setCellStyle(format);
      }
      if (null != originalContent)
         cell.setCellValue(content);
      else
         setBlank(cell, column);
   }

   private void addDouble(Double content, Double originalContent, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
      if (null != styles[column]) {
         CellStyle format = styles[column];
         cell.setCellStyle(format);
      }
      if (null != originalContent)
         cell.setCellValue(content);
      else
         setBlank(cell, column);
   }

   private void addInteger(int content, Integer originalContent, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
      if (null != styles[column]) {
         CellStyle format = styles[column];
         cell.setCellStyle(format);
      }
      if (null != originalContent)
         cell.setCellValue(content);
      else
         setBlank(cell, column);
   }

   private CellStyle getTimeFormat(Workbook workbook) {
      if (null == timeFormat) {
         CreationHelper createHelper = workbook.getCreationHelper();
         timeFormat = workbook.createCellStyle();
         timeFormat.setDataFormat(createHelper.createDataFormat().getFormat(messages.timeFormat()));
      }
      return timeFormat;
   }

   private CellStyle getFullDateFormat(Workbook workbook) {
      if (null == fullDateFormat) {
         CreationHelper createHelper = workbook.getCreationHelper();
         fullDateFormat = workbook.createCellStyle();
         fullDateFormat.setDataFormat(createHelper.createDataFormat().getFormat(messages.dateFormat()));
      }
      return fullDateFormat;
   }
}
