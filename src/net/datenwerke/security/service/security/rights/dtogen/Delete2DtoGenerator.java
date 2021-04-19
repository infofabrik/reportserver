package net.datenwerke.security.service.security.rights.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.security.client.security.dto.DeleteDto;
import net.datenwerke.security.service.security.rights.Delete;
import net.datenwerke.security.service.security.rights.dtogen.Delete2DtoGenerator;

/**
 * Poso2DtoGenerator for Delete
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Delete2DtoGenerator implements Poso2DtoGenerator<Delete,DeleteDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Delete2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DeleteDto instantiateDto(Delete poso)  {
		DeleteDto dto = new DeleteDto();
		return dto;
	}

	public DeleteDto createDto(Delete poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DeleteDto dto = new DeleteDto();
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
