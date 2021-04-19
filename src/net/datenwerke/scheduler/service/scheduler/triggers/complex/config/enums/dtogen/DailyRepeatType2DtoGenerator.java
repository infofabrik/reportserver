package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.DailyRepeatTypeDto;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.DailyRepeatType;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.enums.dtogen.DailyRepeatType2DtoGenerator;

/**
 * Poso2DtoGenerator for DailyRepeatType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DailyRepeatType2DtoGenerator implements Poso2DtoGenerator<DailyRepeatType,DailyRepeatTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DailyRepeatType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DailyRepeatTypeDto instantiateDto(DailyRepeatType poso)  {
		/* Simply return the first enum! */
		DailyRepeatTypeDto dto = DailyRepeatTypeDto.ONCE;
		return dto;
	}

	public DailyRepeatTypeDto createDto(DailyRepeatType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case ONCE:
				return DailyRepeatTypeDto.ONCE;
			case BOUNDED_INTERVAL:
				return DailyRepeatTypeDto.BOUNDED_INTERVAL;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
