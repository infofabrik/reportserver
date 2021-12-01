package net.datenwerke.rs.base.service.reportengines.jasper.output.generator;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.imageio.ImageIO;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledPNGJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledRSJasperReport;
import net.datenwerke.rs.core.service.reportmanager.ReportExecutorService;
import net.datenwerke.rs.core.service.reportmanager.engine.CompiledReport;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECPaged;
import net.datenwerke.rs.core.service.reportmanager.engine.config.ReportExecutionConfig;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.security.service.usermanager.entities.User;
import net.sf.jasperreports.engine.JRAbstractExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.google.inject.Inject;

public class JasperPNGOutputGenerator extends JasperOutputGeneratorImpl {
	
	@Inject
	public JasperPNGOutputGenerator(
		HookHandlerService hookHandler	
		){
		super(hookHandler);
	}
	
	public String[] getFormats() {
		return new String[]{ReportExecutorService.OUTPUT_FORMAT_PNG};
	}
	
	@Override
	public CompiledRSJasperReport exportReport(JasperPrint jasperPrint, String outputFormat, JasperReport report,  User user, ReportExecutionConfig...configs) {
	JRAbstractExporter exporter;
		exporter = new JRPdfExporter();
		
		/* create buffer for output */
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		
		/* configure exporter */
		exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
		exporter.setParameter(JRExporterParameter.OUTPUT_STREAM, out);
		
		/* preview ? */
		if(hasConfig(RECPaged.class, configs)){
			RECPaged paged = getConfig(RECPaged.class, configs);
			if(paged.getFirstPage() != paged.getLastPage()){
				exporter.setParameter(JRExporterParameter.START_PAGE_INDEX,paged.getFirstPage()-1);
				exporter.setParameter(JRExporterParameter.END_PAGE_INDEX, paged.getLastPage()-1);
			} else 
				exporter.setParameter(JRExporterParameter.PAGE_INDEX,paged.getFirstPage()-1);
		}
		
		callPreHooks(outputFormat, exporter, report, user);
		
		/* export */
		try {
			exporter.exportReport();
		} catch (JRException e) {
			// swallow
		}
		
		/* convert to png */
		BufferedImage[] images = pdfToImage(new ByteArrayInputStream(out.toByteArray()));
		
		/* create return object */
		CompiledRSJasperReport cjrReport = new CompiledPNGJasperReport();
		cjrReport.setData(jasperPrint);
		
		/* add report to object */
		cjrReport.setReport(images);
		
		callPostHooks(outputFormat, exporter, report, cjrReport, user);
		
		/* return compiled report */
		return cjrReport;
	}
	
	private BufferedImage[] pdfToImage(InputStream is) {
		BufferedImage[] images = new BufferedImage[1];
		
		PDDocument document;
		try {
			document = PDDocument.load(is);
			
			BufferedImage o = new PDFRenderer(document).renderImage(0);
			images[0] = o;

			document.close();

			return images;

		} catch (IOException e) {
			throw new ReportExecutorRuntimeException(e);
		} 
	}

	private BufferedImage overlayImageAsPreview(BufferedImage img) throws IOException {
		/* scale image */
		BufferedImage thumb = new BufferedImage(90, (int) (90/(double)img.getWidth()*img.getHeight()), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = thumb.createGraphics();
		
		AffineTransform affine = AffineTransform.getScaleInstance(90/(double)img.getWidth(), 90/(double)img.getWidth());

		g.drawRenderedImage(img,affine);
		
		/* combine images */
		BufferedImage outerImg = ImageIO.read(this.getClass().getResource("/resources/crystalclear/Crystal_Clear_mimetype_ascii_white.png")); //$NON-NLS-1$
		
		BufferedImage combined = new BufferedImage(
				outerImg.getWidth(),
				outerImg.getHeight(),
	            BufferedImage.TYPE_INT_RGB);
	 
		g = combined.createGraphics();
		g.drawImage(outerImg,0,0,null);
		g.drawImage(thumb,15,8,105,118,15,8,105,118, null);
		
		g.drawImage(outerImg,80,0,128,30,80,0,128,30,null);
		
		/* get bounds nice */
		g.drawImage(outerImg,0,0,128,10,0,0,128,10,null); // top
		g.drawImage(outerImg,0,0,10,128,0,0,10,128,null); // left
		g.drawImage(outerImg,0,118,128,128,0,118,128,128,null); // bottom
		g.drawImage(outerImg,118,0,128,128,118,0,128,128,null); // right
		
		g.dispose();
		
		return combined;
	}


	@Override
	public CompiledReport getFormatInfo() {
		return new CompiledPNGJasperReport();
	}

}
