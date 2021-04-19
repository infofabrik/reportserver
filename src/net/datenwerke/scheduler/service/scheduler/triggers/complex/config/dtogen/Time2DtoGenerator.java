package net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.TimeDto;
import net.datenwerke.scheduler.client.scheduler.dto.config.complex.decorator.TimeDtoDec;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.Time;
import net.datenwerke.scheduler.service.scheduler.triggers.complex.config.dtogen.Time2DtoGenerator;

/**
 * Poso2DtoGenerator for Time
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Time2DtoGenerator implements Poso2DtoGenerator<Time,TimeDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Time2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TimeDtoDec instantiateDto(Time poso)  {
		TimeDtoDec dto = new TimeDtoDec();
		return dto;
	}

	public TimeDtoDec createDto(Time poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TimeDtoDec dto = new TimeDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set hour */
			dto.setHour(poso.getHour() );

			/*  set minutes */
			dto.setMinutes(poso.getMinutes() );

		}

		return dto;
	}


}
