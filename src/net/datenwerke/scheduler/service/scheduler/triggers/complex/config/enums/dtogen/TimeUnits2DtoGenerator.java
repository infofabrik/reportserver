package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeUnitsDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.TimeUnits;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.TimeUnits2DtoGenerator;

/**
 * Poso2DtoGenerator for TimeUnits
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TimeUnits2DtoGenerator implements Poso2DtoGenerator<TimeUnits,TimeUnitsDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TimeUnits2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TimeUnitsDto instantiateDto(TimeUnits poso)  {
		/* Simply return the first enum! */
		TimeUnitsDto dto = TimeUnitsDto.HOURS;
		return dto;
	}

	public TimeUnitsDto createDto(TimeUnits poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case HOURS:
				return TimeUnitsDto.HOURS;
			case MINUTES:
				return TimeUnitsDto.MINUTES;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
