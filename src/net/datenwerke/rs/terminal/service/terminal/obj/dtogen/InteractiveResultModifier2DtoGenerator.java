package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.InteractiveResultModifierDto;
import net.datenwerke.rs.terminal.service.terminal.obj.InteractiveResultModifier;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.InteractiveResultModifier2DtoGenerator;

/**
 * Poso2DtoGenerator for InteractiveResultModifier
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class InteractiveResultModifier2DtoGenerator implements Poso2DtoGenerator<InteractiveResultModifier,InteractiveResultModifierDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public InteractiveResultModifier2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public InteractiveResultModifierDto instantiateDto(InteractiveResultModifier poso)  {
		InteractiveResultModifierDto dto = new InteractiveResultModifierDto();
		return dto;
	}

	public InteractiveResultModifierDto createDto(InteractiveResultModifier poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final InteractiveResultModifierDto dto = new InteractiveResultModifierDto();
		dto.setDtoView(here);


		return dto;
	}


}
