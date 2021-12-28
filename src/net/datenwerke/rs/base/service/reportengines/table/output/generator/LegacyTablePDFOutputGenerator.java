package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.util.Date;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Element;
import com.lowagie.text.Font;
import com.lowagie.text.HeaderFooter;
import com.lowagie.text.PageSize;
import com.lowagie.text.Phrase;
import com.lowagie.text.Rectangle;
import com.lowagie.text.pdf.BaseFont;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfTemplate;
import com.lowagie.text.pdf.PdfWriter;

import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledPDFJasperReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.CellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReport;
import net.datenwerke.rs.base.service.reportengines.table.entities.TableReportVariant;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledPDFTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportserver.ReportServerService;
import net.datenwerke.rs.utils.misc.PdfUtils;
import net.datenwerke.security.service.usermanager.entities.User;

public class LegacyTablePDFOutputGenerator extends TableOutputGeneratorImpl {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	private Document document;
	private PdfPTable table;
	private PdfWriter writer;
	
	private int numberOfColumns;
	private boolean dontStream;

	private BaseFont fontBase;
	private Font fontRegular;
	private Font fontBold;
	
	@Inject
	public LegacyTablePDFOutputGenerator(ReportServerService reportServerService, PdfUtils pdfUtils) {
		try {
			fontBase = pdfUtils.getBaseFont();
			fontRegular = new Font(fontBase, 11);
			fontBold =  new Font(fontBase, 11, Font.BOLD);
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void addField(Object field, CellFormatter formatter) {
		Object val = getValueOf(field);
		formatDefaultCellFor(val);
		
		table.addCell(new Phrase(formatter.format(val), fontRegular));
	}

	private void formatDefaultCellFor(Object val){
		boolean number = val instanceof Number;
		if(number){
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
		}else{
			table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_UNDEFINED);
		}
	}
	
	@Override
	public void close() {
		try {
			/* make sure document is not empty */
			if(table.getRows().size() == 1)
				for(int i = 0; i < numberOfColumns; i++)
					table.addCell("");

			document.add(table);
		} catch (DocumentException e) {
			logger.warn( e.getMessage(), e);
		}		
		document.close();
	}

	@Override
	public CompiledReport getTableObject() {
		CompiledPDFTableReport compiledreport = new CompiledPDFTableReport();
		if(dontStream)
			compiledreport.setReport(((ByteArrayOutputStream)os).toByteArray());
		return compiledreport;
	}
	
	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledPDFJasperReport();
	}

	@Override
	public void initialize(OutputStream os, TableDefinition td, boolean withSubtotals, TableReport report, TableReport orgReport, CellFormatter[] cellFormatters, ParameterSet parameters, User user, ReportExecutionConfig... configs) {
		document = new Document(PageSize.A4.rotate());
		
		numberOfColumns = td.getColumns().size();
		
		if(null == os){
			os = new ByteArrayOutputStream();
			dontStream = true;
		}
		
		/* store os */
		this.os = os;
		
		try {
			writer = PdfWriter.getInstance(document, os);
			writer.setPageEvent(new RSPdfPageEventHelper());
		} catch (DocumentException e) {
			logger.warn( e.getMessage(), e);
		}
		
		String reportName = report.getName();
		if(report instanceof TableReportVariant){
			reportName = (((TableReportVariant) report).getBaseReport().getName() + ": " + report.getName());
		}
		
		HeaderFooter header = new HeaderFooter(new Phrase(reportName, new Font(fontBase, 8)), false);
		header.setBorder(Rectangle.NO_BORDER);
		document.setHeader(header);

		document.open();
		
		
		table = new PdfPTable(td.size());
		
		table.setHorizontalAlignment(Element.ALIGN_LEFT);
		table.setWidthPercentage(100f);
		
		for(String colName : td.getColumnNames()){
			
			Phrase phrase = new Phrase(colName, fontBold);
			PdfPCell c = new PdfPCell(phrase);
			c.setBackgroundColor(Color.LIGHT_GRAY);
			table.addCell(c);
		}
		
		table.setHeaderRows(1);
		
	}

	@Override
	public void nextRow() {

	}

	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_PDF};
	}
	
	private class RSPdfPageEventHelper extends PdfPageEventHelper{
		private PdfTemplate total;
		
		public RSPdfPageEventHelper() {
		}
		
		 public void onCloseDocument(PdfWriter writer, Document document) {
		     	total.beginText();
		        total.setFontAndSize(fontBase, 8);
		        total.setTextMatrix(0, 0);
		        total.showText(String.valueOf(writer.getPageNumber() - 1));
		        total.endText();
		    }
		 
		    public void onEndPage(PdfWriter writer, Document document) {
		        PdfContentByte cb = writer.getDirectContent();
		 
		        cb.saveState();
		        String text = writer.getPageNumber() + "/";
		        float textBase = document.bottom() - 8;
		        float textSize = fontBase.getWidthPoint(text, 8);
		        float adjust = fontBase.getWidthPoint("0", 8);
		 
		        cb.beginText();
		        cb.setFontAndSize(fontBase, 8);
		        cb.setTextMatrix(document.right() - textSize - adjust, textBase);
		        cb.showText(text);
		        cb.setTextMatrix(document.left(), textBase);
		        cb.showText(DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT).format(new Date()));
		        
		        cb.endText();
		        cb.addTemplate(total, document.right() - adjust, textBase);
		        cb.restoreState();
		    }
		 
		    /**
		     * @see com.lowagie.text.pdf.PdfPageEvent#onOpenDocument(com.lowagie.text.pdf.PdfWriter,
		     *      com.lowagie.text.Document)
		     */
		    public void onOpenDocument(PdfWriter writer, Document document) {
		        total = writer.getDirectContent().createTemplate(100, 100);
		        total.setBoundingBox(new Rectangle(-20, -20, 100, 100));
		    }
	};

	public Document getDocument(){
		return document;
	}
	
	@Override
	public void addGroupRow(int[] subtotalIndices, Object[] subtotals, int[] subtotalGroupFieldIndices, Object[] subtotalGroupFieldValues, int rowSize, CellFormatter[] cellFormatters) {
		
		/* add group */
		int aggIndex = 0;
		int groupIndex = 0;
		for(int i = 0; i < rowSize; i ++){
			PdfPCell c = null;
			
			if(aggIndex < subtotalIndices.length && i == subtotalIndices[aggIndex]){
				Object val = getValueOf(subtotals[aggIndex]);
				formatDefaultCellFor(val);
				Phrase phrase = new Phrase(cellFormatters[i].format(val));
				phrase.getFont().setStyle(Font.BOLD);
				c = new PdfPCell(phrase);
				
				aggIndex++;
			} else if(groupIndex < subtotalGroupFieldIndices.length && i == subtotalGroupFieldIndices[groupIndex]){
				Object val = getValueOf(subtotalGroupFieldValues[groupIndex]);
				formatDefaultCellFor(val);
				Phrase phrase = new Phrase(cellFormatters[i].format(val));
				phrase.getFont().setStyle(Font.BOLD);
				c = new PdfPCell(phrase);
				
				groupIndex++;
			} else
				c = new PdfPCell(new Phrase(""));
			
			table.addCell(c);
		}
		
	}

	@Override
	public boolean isCatchAll() {
		return false;
	}
	
}
