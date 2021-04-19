package net.datenwerke.scheduler.service.scheduler.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.MisfireInstructionDto;
import net.datenwerke.scheduler.service.scheduler.entities.MisfireInstruction;
import net.datenwerke.scheduler.service.scheduler.entities.dtogen.MisfireInstruction2DtoGenerator;

/**
 * Poso2DtoGenerator for MisfireInstruction
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MisfireInstruction2DtoGenerator implements Poso2DtoGenerator<MisfireInstruction,MisfireInstructionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MisfireInstruction2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MisfireInstructionDto instantiateDto(MisfireInstruction poso)  {
		/* Simply return the first enum! */
		MisfireInstructionDto dto = MisfireInstructionDto.EXECUTE_ONCE;
		return dto;
	}

	public MisfireInstructionDto createDto(MisfireInstruction poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case EXECUTE_ONCE:
				return MisfireInstructionDto.EXECUTE_ONCE;
			case EXECUTE_ALL:
				return MisfireInstructionDto.EXECUTE_ALL;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
