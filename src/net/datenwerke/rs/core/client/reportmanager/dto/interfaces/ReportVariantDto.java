package net.datenwerke.rs.core.client.reportmanager.dto.interfaces;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.gxtdto.client.dtomanager.stores.marker.NoAutomaticStoreAddableDto;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;

/**
 * Dto for {@link ReportVariant}
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public interface ReportVariantDto extends NoAutomaticStoreAddableDto {

	public ReportDto getBaseReport();

	public void setBaseReport(ReportDto report);


}
