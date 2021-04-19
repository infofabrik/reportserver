package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.MultiSelectionMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.MultiSelectionMode2DtoGenerator;

/**
 * Poso2DtoGenerator for MultiSelectionMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MultiSelectionMode2DtoGenerator implements Poso2DtoGenerator<MultiSelectionMode,MultiSelectionModeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MultiSelectionMode2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MultiSelectionModeDto instantiateDto(MultiSelectionMode poso)  {
		/* Simply return the first enum! */
		MultiSelectionModeDto dto = MultiSelectionModeDto.Popup;
		return dto;
	}

	public MultiSelectionModeDto createDto(MultiSelectionMode poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case Popup:
				return MultiSelectionModeDto.Popup;
			case Checkbox:
				return MultiSelectionModeDto.Checkbox;
			case Listbox:
				return MultiSelectionModeDto.Listbox;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
