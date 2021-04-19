package net.datenwerke.security.service.security.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.InheritanceTypeDto;
import net.datenwerke.security.service.security.entities.InheritanceType;
import net.datenwerke.security.service.security.entities.dtogen.InheritanceType2DtoGenerator;

/**
 * Poso2DtoGenerator for InheritanceType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class InheritanceType2DtoGenerator implements Poso2DtoGenerator<InheritanceType,InheritanceTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public InheritanceType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public InheritanceTypeDto instantiateDto(InheritanceType poso)  {
		/* Simply return the first enum! */
		InheritanceTypeDto dto = InheritanceTypeDto.HERE;
		return dto;
	}

	public InheritanceTypeDto createDto(InheritanceType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case HERE:
				return InheritanceTypeDto.HERE;
			case INHERITED:
				return InheritanceTypeDto.INHERITED;
			case BOTH:
				return InheritanceTypeDto.BOTH;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
