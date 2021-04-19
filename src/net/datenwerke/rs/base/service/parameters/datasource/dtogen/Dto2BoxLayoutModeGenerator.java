package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

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
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2BoxLayoutModeGenerator;

/**
 * Dto2PosoGenerator for BoxLayoutMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BoxLayoutModeGenerator implements Dto2PosoGenerator<BoxLayoutModeDto,BoxLayoutMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2BoxLayoutModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BoxLayoutMode loadPoso(BoxLayoutModeDto dto)  {
		return createPoso(dto);
	}

	public BoxLayoutMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public BoxLayoutMode createPoso(BoxLayoutModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case TopDownLeftRight:
				return BoxLayoutMode.TopDownLeftRight;
			case LeftRightTopDown:
				return BoxLayoutMode.LeftRightTopDown;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public BoxLayoutMode createUnmanagedPoso(BoxLayoutModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(BoxLayoutModeDto dto, BoxLayoutMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(BoxLayoutModeDto dto, BoxLayoutMode poso)  {
		/* no merging for enums */
	}

	public BoxLayoutMode loadAndMergePoso(BoxLayoutModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(BoxLayoutModeDto dto, BoxLayoutMode poso)  {
	}


	public void postProcessCreateUnmanaged(BoxLayoutModeDto dto, BoxLayoutMode poso)  {
	}


	public void postProcessLoad(BoxLayoutModeDto dto, BoxLayoutMode poso)  {
	}


	public void postProcessMerge(BoxLayoutModeDto dto, BoxLayoutMode poso)  {
	}


	public void postProcessInstantiate(BoxLayoutMode poso)  {
	}



}
