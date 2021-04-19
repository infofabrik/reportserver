package net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceTargetType;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.BirtReportDatasourceTargetType2DtoGenerator;

/**
 * Poso2DtoGenerator for BirtReportDatasourceTargetType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BirtReportDatasourceTargetType2DtoGenerator implements Poso2DtoGenerator<BirtReportDatasourceTargetType,BirtReportDatasourceTargetTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public BirtReportDatasourceTargetType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BirtReportDatasourceTargetTypeDto instantiateDto(BirtReportDatasourceTargetType poso)  {
		/* Simply return the first enum! */
		BirtReportDatasourceTargetTypeDto dto = BirtReportDatasourceTargetTypeDto.DATASET;
		return dto;
	}

	public BirtReportDatasourceTargetTypeDto createDto(BirtReportDatasourceTargetType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case DATASET:
				return BirtReportDatasourceTargetTypeDto.DATASET;
			case PARAMETER:
				return BirtReportDatasourceTargetTypeDto.PARAMETER;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
