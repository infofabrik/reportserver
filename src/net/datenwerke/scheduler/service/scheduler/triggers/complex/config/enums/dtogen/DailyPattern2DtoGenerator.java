package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyPatternDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyPattern;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.DailyPattern2DtoGenerator;

/**
 * Poso2DtoGenerator for DailyPattern
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DailyPattern2DtoGenerator implements Poso2DtoGenerator<DailyPattern,DailyPatternDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DailyPattern2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DailyPatternDto instantiateDto(DailyPattern poso)  {
		/* Simply return the first enum! */
		DailyPatternDto dto = DailyPatternDto.DAILY_EVERY_Nth_DAY;
		return dto;
	}

	public DailyPatternDto createDto(DailyPattern poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case DAILY_EVERY_Nth_DAY:
				return DailyPatternDto.DAILY_EVERY_Nth_DAY;
			case DAILY_WORKDAY:
				return DailyPatternDto.DAILY_WORKDAY;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
