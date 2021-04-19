package net.datenwerke.scheduler.service.scheduler.helper.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode;
import net.datenwerke.scheduler.service.scheduler.helper.dtogen.VetoJobExecutionMode2DtoGenerator;

/**
 * Poso2DtoGenerator for VetoJobExecutionMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class VetoJobExecutionMode2DtoGenerator implements Poso2DtoGenerator<VetoJobExecutionMode,VetoJobExecutionModeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public VetoJobExecutionMode2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public VetoJobExecutionModeDto instantiateDto(VetoJobExecutionMode poso)  {
		/* Simply return the first enum! */
		VetoJobExecutionModeDto dto = VetoJobExecutionModeDto.SKIP;
		return dto;
	}

	public VetoJobExecutionModeDto createDto(VetoJobExecutionMode poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case SKIP:
				return VetoJobExecutionModeDto.SKIP;
			case RETRY:
				return VetoJobExecutionModeDto.RETRY;
			case CUSTOM:
				return VetoJobExecutionModeDto.CUSTOM;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
