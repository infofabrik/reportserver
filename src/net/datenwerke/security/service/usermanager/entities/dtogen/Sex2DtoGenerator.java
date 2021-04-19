package net.datenwerke.security.service.usermanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.usermanager.dto.SexDto;
import net.datenwerke.security.service.usermanager.entities.Sex;
import net.datenwerke.security.service.usermanager.entities.dtogen.Sex2DtoGenerator;

/**
 * Poso2DtoGenerator for Sex
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Sex2DtoGenerator implements Poso2DtoGenerator<Sex,SexDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Sex2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SexDto instantiateDto(Sex poso)  {
		/* Simply return the first enum! */
		SexDto dto = SexDto.Male;
		return dto;
	}

	public SexDto createDto(Sex poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case Male:
				return SexDto.Male;
			case Female:
				return SexDto.Female;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
