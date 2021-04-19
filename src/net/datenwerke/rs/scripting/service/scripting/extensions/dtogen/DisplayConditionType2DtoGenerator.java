package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayConditionType;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.DisplayConditionType2DtoGenerator;

/**
 * Poso2DtoGenerator for DisplayConditionType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DisplayConditionType2DtoGenerator implements Poso2DtoGenerator<DisplayConditionType,DisplayConditionTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DisplayConditionType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DisplayConditionTypeDto instantiateDto(DisplayConditionType poso)  {
		/* Simply return the first enum! */
		DisplayConditionTypeDto dto = DisplayConditionTypeDto.EQUALS;
		return dto;
	}

	public DisplayConditionTypeDto createDto(DisplayConditionType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case EQUALS:
				return DisplayConditionTypeDto.EQUALS;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
