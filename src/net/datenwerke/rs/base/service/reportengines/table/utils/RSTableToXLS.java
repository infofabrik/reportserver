package net.datenwerke.rs.base.service.reportengines.table.utils;

import java.math.BigDecimal;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.configuration2.Configuration;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellReference;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.client.AvailableReportProperties;
import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormat;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatCurrency;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatText;
import net.datenwerke.rs.base.service.reportengines.table.output.generator.XLSOutputGenerator;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ExcelExportException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetFactory;
import net.datenwerke.rs.license.service.LicenseService;
import net.datenwerke.rs.utils.config.ConfigService;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;
import net.datenwerke.rs.utils.oracle.StupidOracleService;
import net.datenwerke.security.service.authenticator.AuthenticatorService;
import net.datenwerke.security.service.usermanager.entities.User;




/**
 * Utility object that transforms an RSTableModel into an Excel Worksheet.
 * 
 *
 */
public class RSTableToXLS {

	private final ReportEnginesMessages messages = LocalizationServiceImpl.getMessages(ReportEnginesMessages.class);
	
	@Inject
	private static StupidOracleService sos;
	
	@Inject
	private static Provider<AuthenticatorService> authenticatorServiceProvider;
	
	@Inject
	private static Provider<LicenseService> licenseServiceProvider;
	
	@Inject
	private static Provider<ConfigService> configServiceProvider;
	
	@Inject
	private static ParameterSetFactory parameterSetFactory;
	
	public static final int EXCEL_MAX_ROWS = 65536;
	public static final int EXCEL_MAX_COLS = 256;
	
	private CellStyle timeFormat;
	private CellStyle fullDateFormat;

	private CellStyle subtotalStyle;

	private CellStyle[] subtotalStyles;
	
	private int rowOffset = 0;
	
	private final String configurationSheetName;
	
	public RSTableToXLS(
			) {
		Configuration config = configServiceProvider.get().getConfigFailsafe(XLSOutputGenerator.CONFIG_FILE);
		configurationSheetName = config.getString("xls.configsheet", messages.configuration());
	}
	
	
	public void exportToExcel(RSTableModel table, Workbook workbook, Sheet sheet, boolean excel2008) throws ExcelExportException{
		exportToExcel(table, workbook, sheet, excel2008, null);
	}

	public void exportToExcel(RSTableModel table, Workbook workbook, Sheet sheet, boolean excel2008, TableReport report) throws ExcelExportException{
		if(! excel2008){
			if(table.getRowCount() > EXCEL_MAX_ROWS)
				throw new ExcelExportException("Excel spreadsheets can't contain more than " + EXCEL_MAX_ROWS + " rows."); //$NON-NLS-1$ //$NON-NLS-2$
			if(table.getColumnCount() > EXCEL_MAX_COLS)
				throw new ExcelExportException("Excel spreadsheets can't contain more than " + EXCEL_MAX_COLS + " columns."); //$NON-NLS-1$ //$NON-NLS-2$
		}
		
		/* load columns */
		List<Column> columns = new ArrayList<>();
		if(null != report)
			columns = report.getVisibleColumns();
		else{
			TableDefinition td = table.getTableDefinition();
			for(int i = 0; i < td.getColumnCount(); i++){
				Column col = new Column();
				col.setName(td.getColumnNames().get(i));
				col.setType(td.getSqlColumnType(i));
				columns.add(col);
			}
		}
		
		/* prepare array for widths for columns */
		int[] columnWidths = new int[table.getColumnCount()];
		
		AuthenticatorService authenticatorService = authenticatorServiceProvider.get();
		User user = authenticatorService.getCurrentUser();
		ParameterSet ps = parameterSetFactory.create(user, report);
		if (null != report)
		   ps.addAll(report.getParameterInstances());
		
		Row headerRow = sheet.createRow(rowOffset);
		
		/* prepare formats */
		CellStyle[] styles = new CellStyle[table.getColumnCount()];
		subtotalStyles = new CellStyle[table.getColumnCount()];
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
				
				/* create styles for subtotals */
				if(null != column.getAggregateFunction()){
					if(format instanceof ColumnFormatCurrency){
						CellStyle style = workbook.createCellStyle();
						String pattern = ((ColumnFormatNumber)format).getPattern().replace("\u00A4", "\"" + ((ColumnFormatCurrency)format).getCurrencyType().getCurrency().getSymbol() + "\"");
						style.setDataFormat(createHelper.createDataFormat().getFormat(pattern));
						Font font = workbook.createFont();
						font.setBold(true);
						style.setFont(font);
						subtotalStyles[i] = style;
					} else if(format instanceof ColumnFormatNumber){
						CellStyle style = workbook.createCellStyle();
						style.setDataFormat(createHelper.createDataFormat().getFormat(((ColumnFormatNumber)format).getPatternForExcel()));
						Font font = workbook.createFont();
						font.setBold(true);
						style.setFont(font);
						subtotalStyles[i] = style;
					}else if(format instanceof ColumnFormatDate){
						CellStyle style = workbook.createCellStyle();
						style.setDataFormat(createHelper.createDataFormat().getFormat(((ColumnFormatDate)format).getTargetFormat()));
						Font font = workbook.createFont();
						font.setBold(true);
						style.setFont(font);
						subtotalStyles[i] = style;
					}else if(format instanceof ColumnFormatText){
						CellStyle style = workbook.createCellStyle();
						style.setWrapText(true);
						Font font = workbook.createFont();
						font.setBold(true);
						style.setFont(font);
						subtotalStyles[i] = style;
					}
				}
			}
		}
		
		/* add header */
		int i = 0;
		for(String name : table.getTableDefinition().getColumnNames()){
			/* add name to cell */
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(name);
			
			/* column type */
			Class<?> cType = table.getColumnType(i);
			
			/* adjust column width */
			if(cType.equals(java.sql.Date.class) || cType.equals(Timestamp.class) || cType.equals(java.util.Date.class))
				columnWidths[i] = Math.max(Math.max(messages.dateFormat().length(), name.length()), 15);
			else if(cType.equals(Timestamp.class))
				columnWidths[i] = Math.max(Math.max(messages.timeFormat().length(), name.length()), 15);
			else if(cType.equals(Time.class))
				columnWidths[i] = Math.max(name.length(), 10);
			
			/* increment counter */
			i++;
		}

		/* add data */
		int lastGroupStart = 1+rowOffset;
		int[] aggregateRowIndices = null == report ? new int[]{} : report.getAggregateColumnIndices();
		int[] subtotalGroupIndices = null == report ? new int[]{} : report.getSubtotalGroupColumnIndices();
		List<Integer> subTotalRows = new ArrayList<Integer>();
		for(int currentRow = 0; currentRow < table.getRowCount(); currentRow++){
			if(table.getData().get(currentRow).isSubtotalRow()){
				int endGroup = currentRow+rowOffset;
				
				/* add subtotal row */
				Row dataRow = sheet.createRow(currentRow+1+rowOffset);
				subTotalRows.add(currentRow+1+rowOffset);
				
				int aggIndex = 0;
				int subIndex = 0;
				for(int column = 0; column < table.getColumnCount(); column++){
					Cell cell = dataRow.createCell(column);
					
					if(aggIndex < aggregateRowIndices.length && column == aggregateRowIndices[aggIndex]){
						Column col = report.getVisibleColumnByPos(column);
						addSubtotal(col.getAggregateFunction(), lastGroupStart, endGroup, workbook, sheet, currentRow+1+rowOffset, column, cell, subtotalStyles[column]);
						aggIndex++;
					} else if(subIndex < subtotalGroupIndices.length && column == subtotalGroupIndices[subIndex]){ 
						Object content = table.getValueAt(currentRow-1, column);
						
						/* add content to cell */
						if(null != content)
							addContentToCell(content.getClass(), content, workbook, sheet, column, cell, styles);
						else
							cell.setCellType(CellType.BLANK);
						
						subIndex++;
					} else
						cell.setCellType(CellType.BLANK);
				}
				
				/* group rows */
				sheet.groupRow(lastGroupStart, endGroup);
				sheet.setRowGroupCollapsed(lastGroupStart, true);
				
				lastGroupStart = currentRow+2+rowOffset;
			} else {
				/* create normal data row */
				Row dataRow = sheet.createRow(currentRow+1+rowOffset);
				for(int column = 0; column < table.getColumnCount(); column++){
					Cell cell = dataRow.createCell(column);
					
					/* content */
					Object content = table.getValueAt(currentRow, column);
	
					/* if content is null then write NULL */
					if(null == content){
						cell.setCellValue(messages.rsTableToXLSNullValue());
						continue;
					}
					
					/* column type */
					Class<?> cType = null == styles[column] || null == content ? table.getColumnType(column) : content.getClass();
					
					/* add content to cell */
					addContentToCell(cType, content, workbook, sheet, column, cell, styles);
				}
			}
		}
		
		/* overal result */
		if(! subTotalRows.isEmpty()){
			sheet.groupRow(1+rowOffset, table.getRowCount()+rowOffset);
			
			Row dataRow = sheet.createRow(table.getRowCount()+1+rowOffset);
			
			int aggIndex = 0;
			for(int column = 0; column < table.getColumnCount(); column++){
				Cell cell = dataRow.createCell(column);
				
				if(aggIndex < aggregateRowIndices.length && column == aggregateRowIndices[aggIndex]){
					Column col = report.getVisibleColumnByPos(column);
					addOveralResult(col.getAggregateFunction(), subTotalRows, workbook, sheet, column, cell);
					aggIndex++;
				} else
					cell.setCellType(CellType.BLANK);
			}
		}
		
		/* adjust column sizes */
		for(int column = 0; column < table.getColumnCount(); column++){
			/* adjust column widths */
			Class<?> cType = table.getColumnType(column);
			if(! cType.equals(java.util.Date.class) &&
			   ! cType.equals(Time.class) &&
			   ! cType.equals(Timestamp.class)){
				sheet.autoSizeColumn(column);
			} else /* autosize does not work for date .. */
				sheet.setColumnWidth(column, 256*columnWidths[column]);
		}
		
		if (null != report && licenseServiceProvider.get().isEnterprise())
			addParameterOutput(report, ps, workbook);
	}
	
	private void addParameterOutput(Report report, ParameterSet parameters, Workbook workbook) {
	    int parameterRowOffset = 0;
		
	    ReportProperty outputParamsProperty = report.getEffectiveReportProperty(AvailableReportProperties.PROPERTY_OUTPUT_PARAMETERS.getValue());
        
        boolean outputParameters = false;
        if(outputParamsProperty instanceof ReportStringProperty)
            outputParameters = ((ReportStringProperty) outputParamsProperty).toBoolean();
        
        ReportProperty outputCompleteConfigurationProperty = report.getEffectiveReportProperty(
                AvailableReportProperties.PROPERTY_OUTPUT_COMPLETE_CONFIGURATION.getValue());
        
        boolean outputCompleteConfiguration = false;
        if(outputCompleteConfigurationProperty instanceof ReportStringProperty)
            outputCompleteConfiguration = ((ReportStringProperty) outputCompleteConfigurationProperty).toBoolean();
        
		
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
			
			Sheet parameterSheet = workbook.createSheet(configurationSheetName);
			
			Row headerRow = parameterSheet.createRow(parameterRowOffset);
			Cell cellParameter = headerRow.createCell(0);
			cellParameter.setCellValue(outputCompleteConfiguration? messages.configurationParameter(): messages.parameter());
			cellParameter.setCellType(CellType.STRING);
			
			Cell cellParameterValue = headerRow.createCell(1);
			cellParameterValue.setCellValue(messages.value());
			cellParameterValue.setCellType(CellType.STRING);
			
			parameterRowOffset++;
			
			Iterator<String> paramsIterator = outputConfiguration.keySet().iterator();
			while (paramsIterator.hasNext()) {
				String parameter = paramsIterator.next();
				String paramValue = null != outputConfiguration.get(parameter) ? outputConfiguration.get(parameter).toString(): null;
				Row paramRow = parameterSheet.createRow(parameterRowOffset);
				
				cellParameter = paramRow.createCell(0);
				cellParameter.setCellValue(parameter);
				cellParameter.setCellType(CellType.STRING);
				
				cellParameter = paramRow.createCell(1);
				cellParameter.setCellValue(paramValue);
				cellParameter.setCellType(CellType.STRING);
				
				parameterRowOffset++;
			}
			
			parameterRowOffset++;
		}
	}
	
	private void addContentToCell(Class<?> cType, Object content, Workbook workbook, Sheet sheet, int column, Cell cell, CellStyle[] styles) throws ExcelExportException {
		/* string content */
		String sContent = String.valueOf(content);
		
		/* date */
		try{
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
		   }  else if(cType.equals(Byte.class)){
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
			} else if(cType.equals(Long.class)){
				addDouble(((Long)content).doubleValue(), sheet, column, cell, styles);
			} // continues
			
			/* strings */
			else {
				addString(sContent, sheet, column, cell, styles);
			}
		} catch(NullPointerException e){
			cell.setCellValue( messages.rsTableToXLSNullValue());
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

	private void addInteger(Integer content, Sheet sheet, int column, Cell cell, CellStyle[] styles) {
		if(null == styles[column])
			cell.setCellValue(content);
		else {
			CellStyle format = styles[column];
			cell.setCellStyle(format);
			cell.setCellValue(content);
		}
		cell.setCellType(CellType.NUMERIC);
	}
	
	private void addSubtotal(AggregateFunction aggregateFunction, int startGroup, int endGroup, Workbook workbook, Sheet sheet, int row, int column, Cell cell, CellStyle style) {
		CellStyle format = (null != style) ? style : getSubtotalStyle(workbook);
		switch(aggregateFunction){
		case COUNT:
		case COUNT_DISTINCT:
		case VARIANCE:
			/* do not set style */
			break;
		default:
			cell.setCellStyle(format);
		}
		
		String cStart = new CellReference(startGroup, column).formatAsString();
		String cEnd = new CellReference(endGroup, column).formatAsString();
		
		StringBuilder formBuilder = new StringBuilder();
		switch(aggregateFunction){
		case SUM:
			formBuilder.append("SUM");
			formBuilder.append("(").append(cStart).append(":").append(cEnd).append(")");
			break;
		case AVG:
			formBuilder.append("AVERAGE");
			formBuilder.append("(").append(cStart).append(":").append(cEnd).append(")");
			break;
		case COUNT:
			formBuilder.append("COUNTA");
			formBuilder.append("(").append(cStart).append(":").append(cEnd).append(")");
			break;
		case COUNT_DISTINCT:
			// =SUMMENPRODUKT((A1:A30000<>"")/ZAEHLENWENN(A1:A30000;A1:A30000&""))
			// SUMPRODUCT COUNTIF
			formBuilder.append("SUMPRODUCT((").append(cStart).append(":").append(cEnd).append("<>\"\")")
			           .append("/COUNTIF(").append(cStart).append(":").append(cEnd).append(",")
			           .append(cStart).append(":").append(cEnd).append("&\"\"))");
			break;
		case MAX:
			formBuilder.append("MAX");
			formBuilder.append("(").append(cStart).append(":").append(cEnd).append(")");
			break;
		case MIN:
			formBuilder.append("MIN");
			formBuilder.append("(").append(cStart).append(":").append(cEnd).append(")");
			break;
		case VARIANCE:
			formBuilder.append("VAR");
			formBuilder.append("(").append(cStart).append(":").append(cEnd).append(")");
			break;
		}

		cell.setCellFormula(formBuilder.toString());
		cell.setCellType(CellType.FORMULA);
	}
	
	private void addOveralResult(AggregateFunction aggregateFunction,
			List<Integer> subTotalRows, Workbook workbook, Sheet sheet,
			int column, Cell cell) {
		CellStyle format = null == subtotalStyles[column] ? getSubtotalStyle(workbook) : subtotalStyles[column];
		cell.setCellStyle(format);
		
		StringBuilder formBuilder = new StringBuilder();
		switch(aggregateFunction){
		case SUM:
			formBuilder.append("SUM");
			break;
		case AVG:
			formBuilder.append("AVERAGE");
			break;
		case COUNT:
			formBuilder.append("COUNTA");
			break;
		case COUNT_DISTINCT:
			//formBuilder.append("COUNTA");
			// don't know how to do this
			return;
		case MAX:
			formBuilder.append("MAX");
			break;
		case MIN:
			formBuilder.append("MIN");
			break;
		case VARIANCE:
			formBuilder.append("VAR");
			break;
		}
		
		int last = 1+rowOffset;
		Iterator<Integer> it = subTotalRows.iterator();
		String cStart = new CellReference(last, column).formatAsString();
		last = it.next()-1;
		String cEnd = new CellReference(last, column).formatAsString();
		formBuilder.append("(").append(cStart).append(":").append(cEnd);
		last += 2;
		
		while(it.hasNext()){
			cStart = new CellReference(last, column).formatAsString();
			last = it.next() - 1;
			cEnd = new CellReference(last, column).formatAsString();
			
			formBuilder.append(",").append(cStart).append(":").append(cEnd);
			
			last += 2;
		}
		

		cell.setCellFormula(formBuilder.append(")").toString());
		cell.setCellType(CellType.FORMULA);
	}
	
	private CellStyle getSubtotalStyle(Workbook workbook){
		if(null == subtotalStyle){
			subtotalStyle = workbook.createCellStyle();
			Font font = workbook.createFont();
			font.setBold(true);
			subtotalStyle.setFont(font);
		}
		return subtotalStyle;
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
