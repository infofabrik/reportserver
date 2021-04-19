package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.PressKeyResultModifierDto;
import net.datenwerke.rs.terminal.service.terminal.obj.PressKeyResultModifier;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.PressKeyResultModifier2DtoGenerator;

/**
 * Poso2DtoGenerator for PressKeyResultModifier
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class PressKeyResultModifier2DtoGenerator implements Poso2DtoGenerator<PressKeyResultModifier,PressKeyResultModifierDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public PressKeyResultModifier2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public PressKeyResultModifierDto instantiateDto(PressKeyResultModifier poso)  {
		PressKeyResultModifierDto dto = new PressKeyResultModifierDto();
		return dto;
	}

	public PressKeyResultModifierDto createDto(PressKeyResultModifier poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final PressKeyResultModifierDto dto = new PressKeyResultModifierDto();
		dto.setDtoView(here);


		return dto;
	}


}
