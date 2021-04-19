package net.datenwerke.rs.core.service.reportmanager.interfaces;

import net.datenwerke.dtoservices.dtogenerator.annotations.ExposeMethodToClient;
import net.datenwerke.dtoservices.dtogenerator.annotations.GenerateDto;
import net.datenwerke.gxtdto.client.dtomanager.stores.marker.NoAutomaticStoreAddableDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;

@GenerateDto(
	dtoPackage="net.datenwerke.rs.core.client.reportmanager.dto.interfaces",
	dtoImplementInterfaces=NoAutomaticStoreAddableDto.class
)
public interface ReportVariant {
	
	@ExposeMethodToClient
	public Report getBaseReport();
	
	@ExposeMethodToClient
	public void setBaseReport(Report report);
	
}
