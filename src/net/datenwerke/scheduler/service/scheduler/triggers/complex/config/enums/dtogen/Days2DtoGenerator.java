package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DaysDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.Days;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.Days2DtoGenerator;

/**
 * Poso2DtoGenerator for Days
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Days2DtoGenerator implements Poso2DtoGenerator<Days,DaysDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Days2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DaysDto instantiateDto(Days poso)  {
		/* Simply return the first enum! */
		DaysDto dto = DaysDto.MONDAY;
		return dto;
	}

	public DaysDto createDto(Days poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case MONDAY:
				return DaysDto.MONDAY;
			case TUESDAY:
				return DaysDto.TUESDAY;
			case WEDNESDAY:
				return DaysDto.WEDNESDAY;
			case THURSDAY:
				return DaysDto.THURSDAY;
			case FRIDAY:
				return DaysDto.FRIDAY;
			case SATURDAY:
				return DaysDto.SATURDAY;
			case SUNDAY:
				return DaysDto.SUNDAY;
			case DAY:
				return DaysDto.DAY;
			case WORKINGDAY:
				return DaysDto.WORKINGDAY;
			case WEEKENDDAY:
				return DaysDto.WEEKENDDAY;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
