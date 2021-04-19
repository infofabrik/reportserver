package net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;

import com.google.inject.Inject;

/**
 * Poso2DtoGenerator for ReportProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportMetadataDtoGenerator implements Poso2DtoGenerator<ReportMetadata,ReportMetadataDto> {

	private final DtoService dtoService;

	@Inject
	public ReportMetadataDtoGenerator(
		DtoService dtoService	){
		this.dtoService = dtoService;
	}

	public ReportMetadataDto instantiateDto(ReportMetadata poso)  {
		ReportMetadataDto dto = new ReportMetadataDto();
		return dto;
	}

	public ReportMetadataDto createDto(ReportMetadata poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		ReportMetadataDto dto = new ReportMetadataDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(poso.getName() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set value */
			dto.setValue(poso.getValue() );

		}

		return dto;
	}


}
