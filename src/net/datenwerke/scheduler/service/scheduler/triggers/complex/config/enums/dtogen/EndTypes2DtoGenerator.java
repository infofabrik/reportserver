package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.EndTypesDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.EndTypes;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.EndTypes2DtoGenerator;

/**
 * Poso2DtoGenerator for EndTypes
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class EndTypes2DtoGenerator implements Poso2DtoGenerator<EndTypes,EndTypesDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public EndTypes2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public EndTypesDto instantiateDto(EndTypes poso)  {
		/* Simply return the first enum! */
		EndTypesDto dto = EndTypesDto.INFINITE;
		return dto;
	}

	public EndTypesDto createDto(EndTypes poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case INFINITE:
				return EndTypesDto.INFINITE;
			case COUNT:
				return EndTypesDto.COUNT;
			case DATE:
				return EndTypesDto.DATE;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
