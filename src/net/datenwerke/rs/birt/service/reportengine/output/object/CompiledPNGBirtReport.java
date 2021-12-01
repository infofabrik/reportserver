package net.datenwerke.rs.birt.service.reportengine.output.object;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledPngReport;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@GenerateDto(
		dtoPackage="net.datenwerke.rs.birt.client.reportengines.dto"
	)
public class CompiledPNGBirtReport extends CompiledRSBirtReport implements CompiledPngReport {
	
	private final Logger logger = LoggerFactory.getLogger(getClass().getName());

	/**
	 * 
	 */
	private static final long serialVersionUID = -6118888993185691038L;
	
	private BufferedImage[] report;

	public BufferedImage[] getReport() {
		return report;
	}
	
	public String getBase64Report() {
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			ImageIO.write(report[0], "png", baos);
			byte[] bytesOut = baos.toByteArray();
			
			return new String(Base64.encodeBase64(bytesOut));
		} catch (IOException e) {
			logger.warn( "Failed getting Report as base46", e);
		}
		
		return "";
	}
	
	public void setBase64Report(String report){
		// ignore
	}

	public void setReport(Object report) {
		try{
			this.report = (BufferedImage[]) report;
		} catch(ClassCastException e){
			IllegalArgumentException iae = new IllegalArgumentException("Expected BufferedImage array"); //$NON-NLS-1$
			iae.initCause(e);
			throw iae;
		}
	}

	public String getFileExtension() {
		return "png"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "image/png"; //$NON-NLS-1$
	}


}
