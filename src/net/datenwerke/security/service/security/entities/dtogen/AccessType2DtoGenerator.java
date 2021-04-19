package net.datenwerke.security.service.security.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.AccessTypeDto;
import net.datenwerke.security.service.security.entities.AccessType;
import net.datenwerke.security.service.security.entities.dtogen.AccessType2DtoGenerator;

/**
 * Poso2DtoGenerator for AccessType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AccessType2DtoGenerator implements Poso2DtoGenerator<AccessType,AccessTypeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AccessType2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AccessTypeDto instantiateDto(AccessType poso)  {
		/* Simply return the first enum! */
		AccessTypeDto dto = AccessTypeDto.ALLOW;
		return dto;
	}

	public AccessTypeDto createDto(AccessType poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case ALLOW:
				return AccessTypeDto.ALLOW;
			case DENY:
				return AccessTypeDto.DENY;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
