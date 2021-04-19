package net.datenwerke.rs.base.service.reportengines.jasper.output.object;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;

@GenerateDto(
		dtoPackage="net.datenwerke.rs.base.client.reportengines.jasper.dto"
	)
public class CompiledCSVJasperReport extends CompiledRSJasperReport {
	
	private static final long serialVersionUID = 3887891839353567053L;
	
	
	@ExposeToClient
	private String report;
	
	public String getReport() {
		return this.report;
	}

	public void setReport(Object report) {
		if(! (report instanceof String))
			throw new IllegalArgumentException("An CSV Jasper Report should be of type String."); //$NON-NLS-1$
		
		this.report = (String) report;
	}
	
	public String getFileExtension() {
		return "csv"; //$NON-NLS-1$
	}

	public String getMimeType() {
		return "text/csv"; //$NON-NLS-1$
	}
	
	@Override
	public boolean isStringReport() {
		return true;
	}

}
