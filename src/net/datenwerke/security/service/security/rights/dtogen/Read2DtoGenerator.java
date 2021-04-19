package net.datenwerke.security.service.security.rights.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.ReadDto;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.dtogen.Read2DtoGenerator;

/**
 * Poso2DtoGenerator for Read
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Read2DtoGenerator implements Poso2DtoGenerator<Read,ReadDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Read2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ReadDto instantiateDto(Read poso)  {
		ReadDto dto = new ReadDto();
		return dto;
	}

	public ReadDto createDto(Read poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReadDto dto = new ReadDto();
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
