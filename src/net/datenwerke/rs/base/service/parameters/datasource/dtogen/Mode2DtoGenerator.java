package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.ModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.Mode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Mode2DtoGenerator;

/**
 * Poso2DtoGenerator for Mode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Mode2DtoGenerator implements Poso2DtoGenerator<Mode,ModeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Mode2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ModeDto instantiateDto(Mode poso)  {
		/* Simply return the first enum! */
		ModeDto dto = ModeDto.Single;
		return dto;
	}

	public ModeDto createDto(Mode poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case Single:
				return ModeDto.Single;
			case Multi:
				return ModeDto.Multi;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
