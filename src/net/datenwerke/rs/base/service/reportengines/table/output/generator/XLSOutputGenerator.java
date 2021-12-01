package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledXLSTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledXLSXTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.utils.RSTableToXLS;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.utils.localization.LocalizationServiceImpl;

import org.apache.commons.configuration2.Configuration;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.google.inject.Inject;
 
public class XLSOutputGenerator extends RSTableOutputGenerator{

	public static final String CONFIG_FILE = "exportfilemd/excelexport.cf";

	public static final String XLS_EXPORT_FORMAT_PROPERTY = "xls.format"; 
	
	private final ReportEnginesMessages messages = LocalizationServiceImpl.getMessages(ReportEnginesMessages.class);
	
	private final RSTableToXLS rsTableToXLS;

	private ConfigService configService;
	
	private final String dataSheetName;
	
	@Inject
	public XLSOutputGenerator(
		RSTableToXLS rsTableToXLS,
		ConfigService configService
		) {
		super();
		
		/* store objects */
		this.rsTableToXLS = rsTableToXLS;
		this.configService = configService;
		
		Configuration config = configService.getConfigFailsafe(XLSOutputGenerator.CONFIG_FILE);
		dataSheetName = config.getString("xls.datasheet", messages.outputNameDynamicList());
	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_EXCEL};
	}

	
	private String getExportFormat(){
		return configService.getConfigFailsafe(CONFIG_FILE).getString(XLS_EXPORT_FORMAT_PROPERTY, "xlsx");
	}
	
	@Override
	public CompiledReport getTableObject() {
		boolean stream = null != os;
		if(! stream)
			os = new ByteArrayOutputStream();
		
		try {
			boolean excel2008 = "xlsx".equals(getExportFormat());
			
			/* export to excel */
			Workbook wb = null;
			if(! excel2008)
				wb = new HSSFWorkbook();
			else
				wb = new XSSFWorkbook();

			Sheet sheet = wb.createSheet(dataSheetName);
			
			/* export */
			rsTableToXLS.exportToExcel((RSTableModel) super.getTableObject(), wb, sheet, excel2008, originalReport);
			
			/* finalize workbook */
			wb.write(os);
			
		} catch (Exception e) {
			ReportExecutorRuntimeException rere = new ReportExecutorRuntimeException(e.getMessage());
			rere.initCause(e);
			throw rere;
		} finally{
			try {
				os.close();
			} catch (IOException e) {
				throw new ReportExecutorRuntimeException(e);
			}
		}
		
		/* create report object */
		if("xls".equals(getExportFormat()))
			return new CompiledXLSTableReport(stream ? null : ((ByteArrayOutputStream)os).toByteArray());
		
		return new CompiledXLSXTableReport(stream ? null : ((ByteArrayOutputStream)os).toByteArray());
	}


	@Override
	public CompiledReport getFormatInfo() {
		boolean excel2008 = "xlsx".equals(getExportFormat());
		
		if(! excel2008)
			return new CompiledXLSTableReport(null);
		return new CompiledXLSXTableReport(null);
	}
}
