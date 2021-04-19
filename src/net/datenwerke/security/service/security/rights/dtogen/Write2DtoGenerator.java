package net.datenwerke.security.service.security.rights.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.WriteDto;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.security.rights.dtogen.Write2DtoGenerator;

/**
 * Poso2DtoGenerator for Write
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Write2DtoGenerator implements Poso2DtoGenerator<Write,WriteDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Write2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public WriteDto instantiateDto(Write poso)  {
		WriteDto dto = new WriteDto();
		return dto;
	}

	public WriteDto createDto(Write poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final WriteDto dto = new WriteDto();
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
