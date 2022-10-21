package net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.NumberTypeDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.NumberType;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.enums.dtogen.NumberType2DtoGenerator;

/**
 * Poso2DtoGenerator for NumberType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class NumberType2DtoGenerator implements Poso2DtoGenerator<NumberType,NumberTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public NumberType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public NumberTypeDto instantiateDto(NumberType poso)  {
		/* Simply return the first enum! */
		NumberTypeDto dto = NumberTypeDto.DEFAULT;
		return dto;
	}

	public NumberTypeDto createDto(NumberType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case DEFAULT:
				return NumberTypeDto.DEFAULT;
			case PERCENT:
				return NumberTypeDto.PERCENT;
			case SCIENTIFIC:
				return NumberTypeDto.SCIENTIFIC;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
