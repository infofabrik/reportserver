package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import java.lang.IllegalStateException;
import java.lang.RuntimeException;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.DisplayModeDto;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2DisplayModeGenerator;

/**
 * Dto2PosoGenerator for DisplayMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DisplayModeGenerator implements Dto2PosoGenerator<DisplayModeDto,DisplayMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2DisplayModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DisplayMode loadPoso(DisplayModeDto dto)  {
		return createPoso(dto);
	}

	public DisplayMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public DisplayMode createPoso(DisplayModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case NORMAL:
				return DisplayMode.NORMAL;
			case WINDOW:
				return DisplayMode.WINDOW;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public DisplayMode createUnmanagedPoso(DisplayModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(DisplayModeDto dto, DisplayMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(DisplayModeDto dto, DisplayMode poso)  {
		/* no merging for enums */
	}

	public DisplayMode loadAndMergePoso(DisplayModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(DisplayModeDto dto, DisplayMode poso)  {
	}


	public void postProcessCreateUnmanaged(DisplayModeDto dto, DisplayMode poso)  {
	}


	public void postProcessLoad(DisplayModeDto dto, DisplayMode poso)  {
	}


	public void postProcessMerge(DisplayModeDto dto, DisplayMode poso)  {
	}


	public void postProcessInstantiate(DisplayMode poso)  {
	}



}
