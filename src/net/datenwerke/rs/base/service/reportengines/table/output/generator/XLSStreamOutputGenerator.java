package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
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
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.ColumnFormatCellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BlockType;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterBlock;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.PreFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledXLSXTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ExcelExportException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.oracle.StupidOracleService;
import net.datenwerke.security.service.usermanager.entities.User;
 
public class XLSStreamOutputGenerator extends TableOutputGeneratorImpl{

	private final ReportEnginesMessages messages = LocalizationServiceImpl.getMessages(ReportEnginesMessages.class);
	
	private CellStyle timeFormat;
	private CellStyle fullDateFormat;
	
	private int rowOffset = 0;
	private int column = 0;
	
	private SXSSFWorkbook workbook;

	private Sheet dataSheet;
	private Sheet configSheet;
	
	private static final int CELL_INDENT = 6;
	
	private CellStyle[] styles;

	private int[] columnWidths;

	private Object[] nullReplacemenets;

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
	
	@Inject
	public XLSStreamOutputGenerator(
		XLSOutputGenerator basicXlsGenerator,
		StupidOracleService sos,
		LicenseService licenseService,
		ConfigService configService
		) {
		super();
		this.basicXlsGenerator = basicXlsGenerator;
		this.sos = sos;
		this.licenseService = licenseService;
		
		Configuration config = configService.getConfigFailsafe(XLSOutputGenerator.CONFIG_FILE);
		dataSheetName = config.getString("xls.datasheet", messages.outputNameDynamicList());
		configurationSheetName = config.getString("xls.configsheet", messages.configuration());
	}
	
	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_EXCEL};
	}
	
	@Override
	public void initialize(OutputStream os, TableDefinition table, boolean withSubtotals, 
			TableReport report, TableReport originalReport,
			CellFormatter[] cellFormatters, ParameterSet parameters, User user, ReportExecutionConfig... configs)
			throws IOException {
		super.initialize(os, table, withSubtotals, report, originalReport, cellFormatters, parameters, user, configs);
		if(withSubtotals){
			basicXlsGenerator.initialize(os, table, withSubtotals, report, originalReport, cellFormatters, parameters, user, configs);
			return;
		}
		
		int columnCount = td.getColumns().size();
		
		/* adjust stream */
		if(null == this.os){
			this.os = new ByteArrayOutputStream();
			dontStream = true;
		}
		
		/* create workbook */
		workbook = new SXSSFWorkbook(1000); // keep 1000 rows in memory, exceeding rows will be flushed to disk
		workbook.setCompressTempFiles(true);
        dataSheet = workbook.createSheet(dataSheetName);

        /* load columns */
        List<Column> columns;
        if(report.isSelectAllColumns()){
        	columns = new ArrayList<Column>();
        	for(Object[] c: td.getColumns()){
	        	Column col = new Column();
				col.setName((String)c[0]);
				col.setType(((Integer)c[3]));
				columns.add(col);
			}
        }else{
        	columns = report.getVisibleColumns();
        }
		nullReplacemenets = new Object[columnCount];
		for(int i = 0; i < columnCount; i++)
			prepareNullFormat(cellFormatters[i], columns.get(i), i);
		
		/* prepare array for widths for columns */
		columnWidths = new int[columnCount];
		
		Row headerRow = dataSheet.createRow(rowOffset);
		
		/* prepare formats */
		styles = new CellStyle[columnCount];
		CreationHelper createHelper = workbook.getCreationHelper();
		for(int i = 0; i < columns.size(); i++){
			Column column = columns.get(i);
			if(null != column && null != column.getFormat()){
				ColumnFormat format = column.getFormat();
				if(format instanceof ColumnFormatCurrency){
					CellStyle style = workbook.createCellStyle();
					String pattern = ((ColumnFormatNumber)format).getPattern().replace("\u00A4", "\"" + ((ColumnFormatCurrency)format).getCurrencyType().getCurrency().getSymbol() + "\"");
					style.setDataFormat(createHelper.createDataFormat().getFormat(pattern));
					styles[i] = style;
				} else if(format instanceof ColumnFormatNumber){
					CellStyle style = workbook.createCellStyle();
					style.setDataFormat(createHelper.createDataFormat().getFormat(((ColumnFormatNumber)format).getPatternForExcel()));
					styles[i] = style;
				}else if(format instanceof ColumnFormatDate){
					CellStyle style = workbook.createCellStyle();
					style.setDataFormat(createHelper.createDataFormat().getFormat(((ColumnFormatDate)format).getTargetFormat()));
					styles[i] = style;
				}else if(format instanceof ColumnFormatText){
					CellStyle style = workbook.createCellStyle();
					style.setWrapText(true);
					styles[i] = style;
				}
			}
		}
		
		/* add header */
		int i = 0;
		for(String name : table.getColumnNames()){
			/* add name to cell */
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(name);
			
			/* column type */
			Class<?> cType = table.getColumnType(i);
			
			/* adjust column width */
			if(cType.equals(Date.class) || cType.equals(Timestamp.class))
				columnWidths[i] = Math.max(Math.max(messages.dateFormat().length(), name.length()), 15);
			else if(cType.equals(Timestamp.class))
				columnWidths[i] = Math.max(Math.max(messages.timeFormat().length(), name.length()), 15);
			else
				columnWidths[i] = Math.max(name.length(), 10);
			
			/* increment counter */
			i++;
		}
		
		/* create first row */
		dataRow = dataSheet.createRow(rowOffset+1);
		
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

		outputCompleteConfigurationProperty = report.getEffectiveReportProperty(
				AvailableReportProperties.PROPERTY_OUTPUT_COMPLETE_CONFIGURATION.getValue());
		if (outputCompleteConfigurationProperty instanceof ReportStringProperty)
			outputCompleteConfiguration = ((ReportStringProperty) outputCompleteConfigurationProperty).toBoolean();

		outputFiltersProperty = report
				.getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_FILTERS.getValue());
		if (outputFiltersProperty instanceof ReportStringProperty)
			outputFilters = ((ReportStringProperty) outputFiltersProperty).toBoolean();

		if (outputParameters || outputCompleteConfiguration) {
			ReportProperty includeHiddenProperty = report.getEffectiveReportProperty(
					AvailableReportProperties.PROPERTY_OUTPUT_INCLUDE_PARAMETERS.getValue());
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
			boolean hasPreFilter = false;
			List<Column> columns = report.getColumns(); // get all columns
			
			boolean hasFilter = columns
					.stream()
					.anyMatch(col -> null != col.getFilter());
			
			if (hasFilter) {
				configSheet = workbook.getSheet(configurationSheetName);
				if (null == configSheet) 
					configSheet = workbook.createSheet(configurationSheetName);
				
				addFiltersOutput();
			} else {
				configSheet = workbook.getSheet(configurationSheetName);
				if (null == configSheet) 
					configSheet = workbook.createSheet(configurationSheetName);
				
				Row filterHeading = configSheet.createRow(configRowOffset);
				Cell cellFilter = filterHeading.createCell(0);
				cellFilter.setCellValue(messages.filters());
				configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
				cellFilter.setCellStyle(configHeaderCellStyle);
				configRowOffset++;
				
				Row noFilter = configSheet.createRow(configRowOffset);
				cellFilter = noFilter.createCell(0);
				cellFilter.setCellValue(messages.nofilters());
				configRowOffset++;	
				configRowOffset++;	// for adding empty line between filter and prefilter			
			}
			
			//prefilter
			PreFilter pf = report.getPreFilter();
			FilterBlock rootBlock = pf.getRootBlock();
			hasPreFilter = null != rootBlock && ((null != rootBlock.getChildBlocks() && !rootBlock.getChildBlocks().isEmpty())
					|| (null != rootBlock.getFilters() && !rootBlock.getFilters().isEmpty()));
			if(! hasPreFilter) {
				Row preFilterHeading = configSheet.createRow(configRowOffset);
				Cell cellFilter = preFilterHeading.createCell(0);
				cellFilter.setCellValue(messages.prefilter());
				configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
				cellFilter.setCellStyle(configHeaderCellStyle);
				configRowOffset++;
				
				Row noPrefilter = configSheet.createRow(configRowOffset);
				cellFilter = noPrefilter.createCell(0);
				cellFilter.setCellValue(messages.noprefilter());
				configRowOffset++;				
			} else
				addPrefilterOutput();
		}
	}

	private void addParameterOutput(ParameterSet parameters) {
		if (outputParameters || outputCompleteConfiguration) {
			ReportProperty includeHiddenProperty = report.getEffectiveReportProperty(
					AvailableReportProperties.PROPERTY_OUTPUT_INCLUDE_PARAMETERS.getValue());
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
			cellParameter.setCellValue(
					outputCompleteConfiguration ? messages.configurationParameter() : messages.parameters());
			configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
			cellParameter.setCellStyle(configHeaderCellStyle);

			configRowOffset++;
			Row headerRow = configSheet.createRow(configRowOffset);
			cellParameter = headerRow.createCell(0);
			cellParameter.setCellValue(
					outputCompleteConfiguration ? messages.configurationParameter() : messages.parameter());
			//configSheet.setColumnWidth(0, CONFIG_SHEET_COL_WIDTH);

			Cell cellParameterValue = headerRow.createCell(1);
			cellParameterValue.setCellValue(messages.value());
			//configSheet.setColumnWidth(1, CONFIG_SHEET_COL_WIDTH);

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
		List<Column> columns = report.getColumns(); // get all columns

		List<Column> colsHavingFilter = columns
				.stream()
				.filter(col -> null != col.getFilter())
				.collect(Collectors.toList());
		
		
		colsHavingFilter.sort((col1, col2) -> col1.getName().compareTo(col2.getName()));
		if (! colsHavingFilter.isEmpty()) {
			addFilterHeading();
			for (Column col : colsHavingFilter) {
				boolean hasInclude = false;
				boolean hasExclude = false;
				boolean hasIncludeRange = false;
				boolean hasExcludeRange = false;
				boolean nullHandling = false;
				if (null != col.getFilter()) {
					int rowSpan = 0;
					if (!col.getFilter().getIncludeValues().isEmpty())
						rowSpan++;
					if (!col.getFilter().getExcludeValues().isEmpty())
						rowSpan++;
					if (!col.getFilter().getIncludeRanges().isEmpty())
						rowSpan++;
					if (!col.getFilter().getExcludeRanges().isEmpty())
						rowSpan++;

					rowSpan++; // case-sensitive
					rowSpan++; // null handling
					
					configRowOffset++;
					Row filterRow = configSheet.createRow(configRowOffset);
					Cell cellFilter = filterRow.createCell(0);
					cellFilter.setCellValue(col.getName());
					configSheet.addMergedRegion(
							new CellRangeAddress(configRowOffset, configRowOffset + (rowSpan - 1), 0, 0));
					CellUtil.setVerticalAlignment(cellFilter, VerticalAlignment.CENTER);
					
					// checking for include filter
					if (!col.getFilter().getIncludeValues().isEmpty()) {
						hasInclude = true;
						cellFilter = filterRow.createCell(1);
						cellFilter.setCellValue(messages.includeTitle());
						List<String> includeFilters = col.getFilter().getIncludeValues();
						cellFilter = filterRow.createCell(2);
						int count = 0;
						String strVal = "";
						for (String includeFilter : includeFilters) {
							if (count == includeFilters.size() - 1)
								strVal = strVal + includeFilter;
							else
								strVal = strVal + includeFilter + ", ";
							count++;
						}
						cellFilter.setCellValue(strVal);
					}

					// checking for include range filter
					if (!col.getFilter().getIncludeRanges().isEmpty()) {
						hasIncludeRange = true;
						if (hasInclude) {
							configRowOffset++;
							filterRow = configSheet.createRow(configRowOffset);
						}
						cellFilter = filterRow.createCell(1);
						cellFilter.setCellValue(messages.includeRangesTitle());
						cellFilter = filterRow.createCell(2);
						List<FilterRange> includeRanges = col.getFilter().getIncludeRanges();
						int count = 0;
						String strVal = "";
						for (FilterRange filterRange : includeRanges) {
							if (count == includeRanges.size() - 1)
								strVal = strVal + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo();
							else
								strVal = strVal + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo() + ", ";
							count++;
						}
						cellFilter.setCellValue(strVal);
					}

					// checking for exclude filter
					if (!col.getFilter().getExcludeValues().isEmpty()) {
						hasExclude = true;
						if (hasInclude || hasIncludeRange) {
							configRowOffset++;
							filterRow = configSheet.createRow(configRowOffset);
						}
						cellFilter = filterRow.createCell(1);
						cellFilter.setCellValue(messages.excludeTitle());
						cellFilter = filterRow.createCell(2);
						List<String> excludeFilters = col.getFilter().getExcludeValues();
						int count = 0;
						String strVal = "";
						for (String excludeFilter : excludeFilters) {
							if (count == excludeFilters.size() - 1)
								strVal = strVal + excludeFilter;
							else
								strVal = strVal + excludeFilter + ", ";
							count++;
						}
						cellFilter.setCellValue(strVal);
					}


					// checking for exclude range filter
					if (!col.getFilter().getExcludeRanges().isEmpty()) {
						hasExcludeRange = true;
						if (hasInclude || hasExclude || hasIncludeRange) {
							configRowOffset++;
							filterRow = configSheet.createRow(configRowOffset);
						}
						cellFilter = filterRow.createCell(1);
						cellFilter.setCellValue(messages.excludeRangeTitle());
						cellFilter = filterRow.createCell(2);
						List<FilterRange> excludeRanges = col.getFilter().getExcludeRanges();
						int count = 0;
						String strVal = "";
						for (FilterRange filterRange : excludeRanges) {
							if (count == excludeRanges.size() - 1)
								strVal = strVal + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo();
							else
								strVal = strVal + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo() + ", ";
							count++;
						}
						cellFilter.setCellValue(strVal);
					}

					// checking for null handling of cells
					nullHandling = true;
					if (hasInclude || hasExclude || hasIncludeRange || hasExcludeRange) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);
					}
					cellFilter = filterRow.createCell(1);
					cellFilter.setCellValue(messages.nullHandlingLabel());
					cellFilter = filterRow.createCell(2);
					if (null == col.getNullHandling())
						cellFilter.setCellValue("--");
					else {
						if (col.getNullHandling() == NullHandling.Include)
							cellFilter.setCellValue(messages.nullInclude());
						if (col.getNullHandling() == NullHandling.Exlude)
							cellFilter.setCellValue(messages.nullExclude());
					}

					// checking for case sensitive
					if (hasInclude || hasExclude || hasIncludeRange || hasExcludeRange || nullHandling) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);
					}
					cellFilter = filterRow.createCell(1);
					cellFilter.setCellValue(messages.caseSensitiveLabel());
					cellFilter = filterRow.createCell(2);
					cellFilter.setCellValue(col.getFilter().isCaseSensitive() ? messages.yes() : messages.no());
				}
			}
			configRowOffset++;
		}
		configRowOffset++;

	}

	private void addFilterHeading() {
		Row filterHeading = configSheet.createRow(configRowOffset);
		Cell cellFilter = filterHeading.createCell(0);
		cellFilter.setCellValue(messages.filters());
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
	
	private void addPrefilterOutput() {
		// Create a CellStyle with foreground color
		configCellColorOdd = workbook.createCellStyle();
		configCellColorOdd.setFillForegroundColor(IndexedColors.GREY_40_PERCENT.getIndex());
		configCellColorOdd.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
		
		configCellColorEven = workbook.createCellStyle();
		configCellColorEven.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
		configCellColorEven.setFillPattern(FillPatternType.SOLID_FOREGROUND);  
				
		boolean hasPreFilter = false;
		PreFilter pf = report.getPreFilter();
		FilterBlock rootBlock = pf.getRootBlock();
		hasPreFilter = null != rootBlock && ((null != rootBlock.getChildBlocks() && !rootBlock.getChildBlocks().isEmpty())
				|| (null != rootBlock.getFilters() && !rootBlock.getFilters().isEmpty()));
		if (hasPreFilter) {
			int blockCnt = 0; 
			addPreFilterHeading();
			int maxBlockDepth = calculateDepth(rootBlock, 1);
			getFilterBlock(rootBlock, blockCnt, maxBlockDepth, false);
		}
	}
	
	private void getFilterBlock(FilterBlock block,int blockCnt, int depth, boolean even) {
		int cellCount = 1;
		configRowOffset++;
		BlockType bt = block.getBlockType();
		Row preFilterRow = configSheet.createRow(configRowOffset);
		Cell cellPreFilter = preFilterRow.createCell(blockCnt);
		if(even) {
			cellPreFilter.setCellStyle(configCellColorEven);
			for(int i=blockCnt;i<depth+1;i++) {
				Cell test = preFilterRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				test.setCellStyle(configCellColorEven);
			}
		} else {
			cellPreFilter.setCellStyle(configCellColorOdd);
			for(int i=blockCnt;i<depth+1;i++) {
				Cell test = preFilterRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				test.setCellStyle(configCellColorOdd);
			}
		}
		even = !even;
		
		if(null == block.getDescription())
			cellPreFilter.setCellValue(bt.name());
		else if (null != block.getDescription() && !block.getDescription().isEmpty()) 
			cellPreFilter.setCellValue(bt.name()+"("+block.getDescription()+")");
		
		Comparator<FilterSpec> filterComp = new Comparator<FilterSpec>() {
			@Override
			public int compare(FilterSpec a, FilterSpec b) {
				int res = 1;
				if (a.getClass() != b.getClass()) {
					res = a.getClass().getName().compareTo(b.getClass().getName());
				} else if (a instanceof BinaryColumnFilter) {
					res = (((BinaryColumnFilter) a).getColumnA().getName()
							+ ((BinaryColumnFilter) a).getColumnB().getName())
									.compareTo((((BinaryColumnFilter) b).getColumnA().getName()
											+ ((BinaryColumnFilter) b).getColumnB().getName()));
				} else if (a instanceof ColumnFilter) {
					res = ((ColumnFilter) a).getColumn().getName().compareTo(((ColumnFilter) b).getColumn().getName());
				}
				if (res == 0)
					return 1;
				return res;
			}
		};
		TreeSet<FilterSpec> fts = new TreeSet<FilterSpec>(filterComp);
		fts.addAll(block.getFilters());
		for (FilterSpec filter : fts) {
			if (filter instanceof ColumnFilter) {
				Column col = ((ColumnFilter) filter).getColumn();
				configRowOffset++;
				Row filterRow = configSheet.createRow(configRowOffset);
				cellPreFilter = filterRow.createCell(blockCnt + cellCount);
				if(null == filter.getDescription())
					cellPreFilter.setCellValue(col.getName());
				else if (null != filter.getDescription() && !filter.getDescription().isEmpty()) 
					cellPreFilter.setCellValue(col.getName()+"("+filter.getDescription()+")");
				
				boolean attSep = false;
				if (null != col.getFilter()) {
					if (col.getFilter().isCaseSensitive() == true) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);
						cellPreFilter = filterRow.createCell(blockCnt + cellCount);
						cellPreFilter.setCellValue(StringUtils.repeat(" ", CELL_INDENT) + messages.caseSensitiveFilter());
						attSep = true;
					}
				}
				if (null != col.getNullHandling()) {
					if (attSep)
						cellPreFilter.setCellValue(StringUtils.repeat(" ", CELL_INDENT) + messages.caseSensitiveFilter()+" | " + messages.emptyCells()+": "+ (NullHandling.Exlude == col.getNullHandling() ? messages.excluded() : messages.included()));
				}
				if (null != col.getFilter()) {
					
					//checking for include filters
					if (null != col.getFilter().getIncludeValues() && !col.getFilter().getIncludeValues().isEmpty()) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);
						cellPreFilter = filterRow.createCell(blockCnt + cellCount);
						List<String> includeFilters = col.getFilter().getIncludeValues();
						int count = 0;
						String strVal = "";
						for (String includeFilter : includeFilters) {
							if (count == includeFilters.size() - 1)
								strVal = strVal + "\"" + includeFilter + "\"";
							else 
								strVal = strVal + "\"" + includeFilter + "\", ";
							count++;
						}
						cellPreFilter.setCellValue(StringUtils.repeat(" ", CELL_INDENT) + "= " + strVal);
					}
					
					//checking for include range filters
					if (null != col.getFilter().getIncludeRanges() && !col.getFilter().getIncludeRanges().isEmpty()) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);	
						cellPreFilter = filterRow.createCell(blockCnt + cellCount);
						List<FilterRange> includeRanges = col.getFilter().getIncludeRanges();
						int count = 0;
						String strVal = "";
						for (FilterRange filterRange : includeRanges) {
							if (count == includeRanges.size() - 1)
								strVal = strVal + "\"" + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo() + "\"";
							else
								strVal = strVal + "\"" + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo() + "\", ";
							count++;
						}
						cellPreFilter.setCellValue(StringUtils.repeat(" ", CELL_INDENT) + "[] "+strVal);
					}
					
					//checking for exclude filters
					if (null != col.getFilter().getExcludeValues() && !col.getFilter().getExcludeValues().isEmpty()) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);
						cellPreFilter = filterRow.createCell(blockCnt + cellCount);
						List<String> excludeFilters = col.getFilter().getExcludeValues();
						int count = 0;
						String strVal = "";
						for (String excludeFilter : excludeFilters) {
							if (count == excludeFilters.size() - 1)
								strVal = strVal + "\"" + excludeFilter + "\"";
							else
								strVal = strVal + "\"" + excludeFilter + "\", ";
							count++;
						}
						cellPreFilter.setCellValue(StringUtils.repeat(" ", CELL_INDENT) + "<> "+strVal);
					}
					
					// checking for exclude range filters
					if (null != col.getFilter().getExcludeRanges() && !col.getFilter().getExcludeRanges().isEmpty()) {
						configRowOffset++;
						filterRow = configSheet.createRow(configRowOffset);
						cellPreFilter = filterRow.createCell(blockCnt + cellCount);
						List<FilterRange> excludeRanges = col.getFilter().getExcludeRanges();
						int count = 0;
						String strVal = "";
						for (FilterRange filterRange : excludeRanges) {
							if (count == excludeRanges.size() - 1)
								strVal = strVal + "\"" + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo() + "\"";
							else
								strVal = strVal + "\"" + filterRange.getRangeFrom() + " - " + filterRange.getRangeTo() + "\", ";
							count++;
						}
						cellPreFilter.setCellValue(StringUtils.repeat(" ", CELL_INDENT) + "][ "+strVal);
					}
					
				}
			}else if (filter instanceof BinaryColumnFilter) {
				configRowOffset++;
				Row binaryColFilterRow = configSheet.createRow(configRowOffset);
				cellPreFilter = binaryColFilterRow.createCell(blockCnt + cellCount);
				cellPreFilter.setCellValue(htmlspecialchars(((BinaryColumnFilter) filter).getColumnA().getName() + " "
						+ ((BinaryColumnFilter) filter).getOperator().getStrOp() + " "
						+ ((BinaryColumnFilter) filter).getColumnB().getName()));
			}
			
		}
		Comparator<FilterBlock> blkComp = (FilterBlock a, FilterBlock b) -> (a.getFilters().size()
				+ a.getChildBlocks().size() * 100 < b.getFilters().size() + b.getChildBlocks().size() * 100) ? -1 : 1;
		
		Set<FilterBlock> ts = new TreeSet<>(blkComp);
		ts.addAll(block.getChildBlocks());
		for (FilterBlock childBlock : ts) {
			getFilterBlock(childBlock,blockCnt + 1, depth, even);
		}
	}
	
	private void addPreFilterHeading() {
		Row filterHeading = configSheet.createRow(configRowOffset);
		Cell cellFilter = filterHeading.createCell(0);
		cellFilter.setCellValue(messages.prefilter());
		configSheet.addMergedRegion(new CellRangeAddress(configRowOffset, configRowOffset, 0, 2));
		cellFilter.setCellStyle(configHeaderCellStyle);
	}
	
	private int calculateDepth(FilterBlock block, int currentBlockDepth) {
		Set<FilterBlock> childBlocks = block.getChildBlocks();

		if (childBlocks.isEmpty())
			return currentBlockDepth;
		
		int max = 0;
		for (FilterBlock childBlock : childBlocks) {
			int depth = calculateDepth(childBlock, currentBlockDepth+1);
			if ( depth > max )
				max = depth;
		}
		
		return max;
	}
	
	private String htmlspecialchars(String str){
		return org.apache.commons.lang.StringEscapeUtils.escapeHtml(str).replace("\r\n", "\n").replace("\n", "<br />");
	}

	@Override
	public void addField(Object field, CellFormatter cellFormatter)
			throws IOException {
		if(withSubtotals){
			basicXlsGenerator.addField(field, cellFormatter);
			return;
		}
		
		Cell cell = dataRow.createCell(column);
			
		/* content */
		Object content = getValueOf(field);
		
		if(cellFormatter instanceof ColumnFormatCellFormatter){
			ColumnFormat columnFormat = ((ColumnFormatCellFormatter) cellFormatter).getColumnFormat();
			
			if(columnFormat instanceof ColumnFormatTemplate)
				content = cellFormatter.format(content);
			
			if(columnFormat instanceof ColumnFormatDate && content instanceof String){
				try {
					content = ((ColumnFormatDate) columnFormat).parse((String) content);
				} catch (ParseException e) {
				}
			}
		}

		/* if content is null then write NULL */
		if(null == content)
			content = nullReplacemenets[column];

		/* column type */
		Class<?> cType = content.getClass();
		
		/* add content to cell */
		try {
			addContentToCell(cType, content, workbook, dataSheet, column, cell, styles, cellFormatter);
		} catch (ExcelExportException e) {
			throw new RuntimeException(e);
		}
		
		column++;
	}
	
	private void prepareNullFormat(CellFormatter cellFormatter, Column column, int col) {
		if(null == cellFormatter){
			nullReplacemenets[col] = null != column.getNullReplacementFormat() ? column.getNullReplacementFormat() : "NULL";
			return;
		}
		
		try{
			if(Integer.class.equals(td.getColumnType(col)) ||
			   Long.class.equals(td.getColumnType(col)) ||
			   Byte.class.equals(td.getColumnType(col)) ||
			   Short.class.equals(td.getColumnType(col))){

			   nullReplacemenets[col] = NumberFormat.getIntegerInstance().parse((String)cellFormatter.format(null));
			} else if(Double.class.equals(td.getColumnType(col)) ||
					  Float.class.equals(td.getColumnType(col)) ||
					  BigDecimal.class.equals(td.getColumnType(col))){
			
				nullReplacemenets[col] = NumberFormat.getInstance().parse((String)cellFormatter.format(null));
			}
		} catch (ParseException e) {
			nullReplacemenets[col] = cellFormatter.format(null);
		}
		
		if(null == nullReplacemenets[col])
			nullReplacemenets[col] = null != column.getNullReplacementFormat() ? column.getNullReplacementFormat() : "NULL";
	}

	@Override
	public void addGroupRow(int[] subtotalIndices, Object[] subtotals,
			int[] subtotalGroupFieldIndices, Object[] subtotalGroupFieldValues,
			int rowSize, CellFormatter[] cellFormatters) throws IOException {
		basicXlsGenerator.addGroupRow(subtotalIndices, subtotals, subtotalGroupFieldIndices, subtotalGroupFieldValues, rowSize, cellFormatters);
	}

	@Override
	public void close() throws IOException {
		if(withSubtotals){
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
		if(withSubtotals){
			basicXlsGenerator.nextRow();
			return;
		}
		
		rowOffset++;
		column = 0;
		
		/* create normal data row */
		dataRow = dataSheet.createRow(rowOffset+1);
	}
	
	@Override
	public CompiledReport getTableObject() {
		if(withSubtotals){
			return basicXlsGenerator.getTableObject();
		}
		
		return new CompiledXLSXTableReport(!dontStream ? null : ((ByteArrayOutputStream)os).toByteArray());
	}


	@Override
	public CompiledReport getFormatInfo() {
		if(withSubtotals){
			return basicXlsGenerator.getFormatInfo();
		}
		
		return new CompiledXLSXTableReport(null);
	}

	@Override
	protected boolean hasConfig(Class<? extends ReportExecutionConfig> type){
		if(withSubtotals)
			return basicXlsGenerator.hasConfig(type);
		return super.hasConfig(type);
	}
	
	@Override
	protected <R extends ReportExecutionConfig> R getConfig(Class<? extends R> type){
		if(withSubtotals)
			return basicXlsGenerator.getConfig(type);
		return super.getConfig(type);
	}

	@Override
	protected ReportExecutionConfig[] getConfigs() {
		if(withSubtotals)
			return basicXlsGenerator.getConfigs();
		return super.getConfigs();
	}

	@Override
	protected Report getReport() {
		if(withSubtotals)
			return basicXlsGenerator.getReport();
		return super.getReport();
	}

	@Override
	protected TableDefinition getTd() {
		if(withSubtotals)
			return basicXlsGenerator.getTd();
		return super.getTd();
	}
	
	@Override
	public boolean isCatchAll() {
		if(withSubtotals)
			return basicXlsGenerator.isCatchAll();
		return super.isCatchAll();
	}
	
	@Override
	protected Object getValueOf(Object field) {
		if(withSubtotals)
			return basicXlsGenerator.getValueOf(field);
		return super.getValueOf(field);
	}
	

	private void addContentToCell(Class<?> cType, Object content, Workbook workbook, Sheet sheet, int column, Cell cell, CellStyle[] styles, CellFormatter cellFormatter) throws ExcelExportException {
		/* string content */
		String sContent = String.valueOf(content);
		if(null != sContent)
			columnWidths[column] = Math.max(sContent.length(), columnWidths[column]);
		
		try{
			/* date */
			if(cType.equals(java.util.Date.class) || cType.equals(java.sql.Date.class)){ 
				addDate((java.util.Date) content, workbook, sheet, column, cell, styles);
			} else if(cType.equals(Time.class)){
				addTime((Time) content, workbook, sheet, column, cell, styles);
			} else if(cType.equals(Timestamp.class)){
				/* special oracle handling */
				if(sos.isOracleTimestamp(content)){
					java.util.Date d = sos.getDateFromOracleDatum(content);
					addDate(d, workbook, sheet, column, cell, styles);
				} else {
					Timestamp ts = (Timestamp)content;
					long time = ts.getTime() + (ts.getNanos() / 1000000);

					java.util.Date d = new java.util.Date(time);
					addDate(d, workbook, sheet, column, cell, styles);
				}
			} //continues
			
			/* binary data */
			else if(cType.equals(Byte[].class)){
				cell.setCellValue( messages.rsTableToXLSBinaryData());
			} // continues
			
			/* integer */
			else if(cType.equals(Integer.class)){
				addInteger((Integer)content, sheet, column, cell, styles);
			} else if(cType.equals(Long.class)){
				/* excel does not support long */
				addDouble(Double.valueOf((Long)content), sheet, column, cell, styles);
			} else if(cType.equals(Byte.class)){
				addInteger(((Byte)content).intValue(), sheet, column, cell, styles);
			} else if(cType.equals(Short.class)){
				addInteger(((Short)content).intValue(), sheet, column, cell, styles);
			} // continues
			
			/* double */
			else if(cType.equals(Double.class)){
				addDouble((Double)content, sheet, column, cell, styles);
			} else if(cType.equals(Float.class)){
				addDouble(((Float)content).doubleValue(), sheet, column, cell, styles);
			} else if(cType.equals(BigDecimal.class)){
				addDouble(((BigDecimal)content).doubleValue(), sheet, column, cell, styles);
			} // continues
			
			/* strings */
			else {
				addString(sContent, sheet, column, cell, styles);
			}
		} catch(NullPointerException e){
			cell.setCellValue(cellFormatter.format(null));
		}
	}

	private void addTime(Time content, Workbook workbook, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
		CellStyle dateFormat = styles[column] == null ? getTimeFormat(workbook) : styles[column];
		cell.setCellStyle(dateFormat);
		cell.setCellValue(content);
	}
	
	private void addDate(java.util.Date content, Workbook workbook, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
		CellStyle dateFormat = styles[column] == null ? getFullDateFormat(workbook) : styles[column];
		cell.setCellStyle(dateFormat);
		cell.setCellValue(content);
	}
	
	private void addString(String content, Sheet sheet, int column, Cell cell,
			CellStyle[] styles) {
		if(null == styles[column])
			cell.setCellValue(content);
		else {
			CellStyle format = styles[column];
			cell.setCellStyle(format);
			cell.setCellValue(content);
		}
		cell.setCellType(CellType.STRING);
	}


	private void addDouble(Double content, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
		if(null == styles[column])
			cell.setCellValue(content);
		else {
			CellStyle format = styles[column];
			cell.setCellStyle(format);
			cell.setCellValue(content);
		}
		cell.setCellType(CellType.NUMERIC);
	}

	private void addInteger(int content, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
		if(null == styles[column])
			cell.setCellValue(content);
		else {
			CellStyle format = styles[column];
			cell.setCellStyle(format);
			cell.setCellValue(content);
		}
		cell.setCellType(CellType.NUMERIC);
	}
	
	private CellStyle getTimeFormat(Workbook workbook){
		if(null == timeFormat){
			CreationHelper createHelper = workbook.getCreationHelper();
			timeFormat = workbook.createCellStyle();
			timeFormat.setDataFormat(createHelper.createDataFormat().getFormat(messages.timeFormat()));
		}
		return timeFormat;
	}
	
	private CellStyle getFullDateFormat(Workbook workbook){
		if(null == fullDateFormat){
			CreationHelper createHelper = workbook.getCreationHelper();
			fullDateFormat = workbook.createCellStyle();
			fullDateFormat.setDataFormat(createHelper.createDataFormat().getFormat(messages.dateFormat()));
		}
		return fullDateFormat;
	}
}
