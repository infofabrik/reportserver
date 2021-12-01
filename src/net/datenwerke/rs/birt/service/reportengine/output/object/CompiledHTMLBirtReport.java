package net.datenwerke.rs.birt.service.reportengine.output.object;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.rs.core.client.reportexecutor.dto.CompiledHtmlReportDto;
import net.datenwerke.rs.core.service.reportmanager.engine.basereports.CompiledHtmlReport;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.birt.client.reportengines.dto",
		dtoImplementInterfaces=CompiledHtmlReportDto.class
	)
public class CompiledHTMLBirtReport extends CompiledRSBirtReport implements CompiledHtmlReport {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3765810696245094226L;
	
	@ExposeToClient
	private String report;
	
	public String getReport() {
		return this.report;
	}

	public void setReport(Object report) {
		if(! (report instanceof String))
			throw new IllegalArgumentException("An HTML Jasper Report should be of type String."); //$NON-NLS-1$
		
		this.report = (String) report;
	}
	
	public String getFileExtension() {
		return "html"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "text/html"; //$NON-NLS-1$
	}

	@Override
	public boolean isStringReport() {
		return true;
	}
}
