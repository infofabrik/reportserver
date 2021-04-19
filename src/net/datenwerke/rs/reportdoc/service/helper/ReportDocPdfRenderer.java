package net.datenwerke.rs.reportdoc.service.helper;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import org.xhtmlrenderer.pdf.ITextRenderer;

import com.lowagie.text.DocumentException;

import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledPdfReport;
import net.datenwerke.rs.utils.misc.PdfUtils;

public class ReportDocPdfRenderer {
	
	private PdfUtils pdfUtils;

	@Inject
	public ReportDocPdfRenderer(PdfUtils pdfUtils) {
		this.pdfUtils = pdfUtils;
	}

	public CompiledReport render(Object input)  {
		ITextRenderer renderer = new ITextRenderer();
		try {
			pdfUtils.configureFontResolver(renderer.getFontResolver());
		} catch (DocumentException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		renderer.setDocumentFromString((String) input);

		ByteArrayOutputStream os = new ByteArrayOutputStream();
		renderer.layout();
		try {
			renderer.createPDF(os);
			os.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		byte[] cReport = os.toByteArray();

		return new CompiledPdfReport(cReport);
	}

}
