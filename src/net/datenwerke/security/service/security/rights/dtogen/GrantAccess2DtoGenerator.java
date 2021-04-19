package net.datenwerke.security.service.security.rights.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.GrantAccessDto;
import net.datenwerke.security.service.security.rights.GrantAccess;
import net.datenwerke.security.service.security.rights.dtogen.GrantAccess2DtoGenerator;

/**
 * Poso2DtoGenerator for GrantAccess
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GrantAccess2DtoGenerator implements Poso2DtoGenerator<GrantAccess,GrantAccessDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public GrantAccess2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public GrantAccessDto instantiateDto(GrantAccess poso)  {
		GrantAccessDto dto = new GrantAccessDto();
		return dto;
	}

	public GrantAccessDto createDto(GrantAccess poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final GrantAccessDto dto = new GrantAccessDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set Abbreviation */
			dto.setAbbreviation(poso.getAbbreviation() );

			/*  set BitField */
			dto.setBitField(poso.getBitField() );

			/*  set Description */
			dto.setDescription(poso.getDescription() );

		}

		return dto;
	}


}
