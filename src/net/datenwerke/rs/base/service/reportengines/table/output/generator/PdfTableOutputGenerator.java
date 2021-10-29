package net.datenwerke.rs.base.service.reportengines.table.output.generator;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import net.datenwerke.rs.base.service.reportengines.locale.ReportEnginesMessages;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column.ColumnFormatCellFormatter;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatNumber;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledPDFTableReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.utils.misc.PdfUtils;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.google.inject.Inject;
import com.lowagie.text.DocumentException;

public class PdfTableOutputGenerator extends HTMLOutputGenerator {

	protected static final String CONFIG_FILE = "dynamiclists/pdfexport.cf";
	
	@Inject
	protected PdfUtils pdfUtils;

	private ITextRenderer renderer;
	
	@Override
	protected String getConfigFileLocation() {
		return CONFIG_FILE;
	}
	
	@Override
	protected String getBodyClass() {
		return super.getBodyClass() + " rs-reportexport-dl-pdf";
	}
	
	@Override
	protected void initWriter(OutputStream os)
			throws IOException {
		writer = new StringBuffer();
	}
	
	@Override
	protected void doAddCss() throws IOException {
		writer.append(themeServiceProvider.get().getTheme());
		
		if(null != cellFormatters){
			for(int i = 0; i < cellFormatters.length; i++){
				if(cellFormatters[i] instanceof ColumnFormatCellFormatter && ((ColumnFormatCellFormatter)cellFormatters[i]).getColumnFormat() instanceof ColumnFormatNumber){
					writer.append("tr td:nth-child(");
					writer.append("" + (i+1));
					writer.append(") { text-align: right; }");
				}
			}
		}
		
		String style = configFile.getString(STYLE_PROPERTY, "");
		if(null != style && ! "".equals(style.trim())){
			String html = parseTemplate(style);
			writer.append(html);
		} else {
			writer.append("@page {size: A4 landscape;margin-top:1.5cm;");
			writer.append("@top-left { content: \"" + org.apache.commons.text.StringEscapeUtils.unescapeHtml4(report.getName()) + "\"; font-family: DejaVu Sans, Sans-Serif; font-size: 8pt; }");
			writer.append("@top-right {content: \"" + getNowString() + "\"; font-family: DejaVu Sans, Sans-Serif; font-size: 8pt; }");
			writer.append("@bottom-right { content: \"" + ReportEnginesMessages.INSTANCE.page()+ " \" counter(page) \" " + ReportEnginesMessages.INSTANCE.of() + " \" counter(pages); font-family: DejaVu Sans, Sans-Serif; font-size: 8pt; }");
			writer.append("}");
			writer.append("* { font-family: DejaVu Sans, Sans-Serif; }");
		}
	}
	
	
	@Override
	public CompiledReport getTableObject() {
		/* init rendered */
		renderer = new ITextRenderer();
		try {
			pdfUtils.configureFontResolver(renderer.getFontResolver());
			String html = writer.toString();
			renderer.setDocumentFromString(html);
			
			if(null == os)
				os = new ByteArrayOutputStream();
			renderer.layout();
			renderer.createPDF(os);

			byte[] cReport = os instanceof ByteArrayOutputStream ? ((ByteArrayOutputStream)os).toByteArray() : null;

			return new CompiledPDFTableReport(cReport);
		} catch (DocumentException e) {
			throw new ReportExecutorRuntimeException(e);
		} catch (IOException e) {
			throw new ReportExecutorRuntimeException(e);
		}
	}
	
	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledPDFTableReport();
	}
	
	@Override
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_PDF};
	}
	
	@Override
	public boolean supportsStreaming() {
		return false;
	}
}
