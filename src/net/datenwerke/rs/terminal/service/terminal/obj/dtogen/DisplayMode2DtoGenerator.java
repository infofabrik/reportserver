package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.DisplayMode2DtoGenerator;

/**
 * Poso2DtoGenerator for DisplayMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DisplayMode2DtoGenerator implements Poso2DtoGenerator<DisplayMode,DisplayModeDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DisplayMode2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DisplayModeDto instantiateDto(DisplayMode poso)  {
		/* Simply return the first enum! */
		DisplayModeDto dto = DisplayModeDto.NORMAL;
		return dto;
	}

	public DisplayModeDto createDto(DisplayMode poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case NORMAL:
				return DisplayModeDto.NORMAL;
			case WINDOW:
				return DisplayModeDto.WINDOW;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
