package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutPackMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.BoxLayoutPackMode2DtoGenerator;

/**
 * Poso2DtoGenerator for BoxLayoutPackMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BoxLayoutPackMode2DtoGenerator implements Poso2DtoGenerator<BoxLayoutPackMode,BoxLayoutPackModeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public BoxLayoutPackMode2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BoxLayoutPackModeDto instantiateDto(BoxLayoutPackMode poso)  {
		/* Simply return the first enum! */
		BoxLayoutPackModeDto dto = BoxLayoutPackModeDto.Columns;
		return dto;
	}

	public BoxLayoutPackModeDto createDto(BoxLayoutPackMode poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case Columns:
				return BoxLayoutPackModeDto.Columns;
			case Packages:
				return BoxLayoutPackModeDto.Packages;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
