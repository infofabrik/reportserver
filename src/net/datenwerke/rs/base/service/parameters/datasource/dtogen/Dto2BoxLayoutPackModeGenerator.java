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
import net.datenwerke.rs.base.client.parameters.datasource.dto.BoxLayoutPackModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.BoxLayoutPackMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2BoxLayoutPackModeGenerator;

/**
 * Dto2PosoGenerator for BoxLayoutPackMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BoxLayoutPackModeGenerator implements Dto2PosoGenerator<BoxLayoutPackModeDto,BoxLayoutPackMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2BoxLayoutPackModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BoxLayoutPackMode loadPoso(BoxLayoutPackModeDto dto)  {
		return createPoso(dto);
	}

	public BoxLayoutPackMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public BoxLayoutPackMode createPoso(BoxLayoutPackModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case Columns:
				return BoxLayoutPackMode.Columns;
			case Packages:
				return BoxLayoutPackMode.Packages;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public BoxLayoutPackMode createUnmanagedPoso(BoxLayoutPackModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(BoxLayoutPackModeDto dto, BoxLayoutPackMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(BoxLayoutPackModeDto dto, BoxLayoutPackMode poso)  {
		/* no merging for enums */
	}

	public BoxLayoutPackMode loadAndMergePoso(BoxLayoutPackModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(BoxLayoutPackModeDto dto, BoxLayoutPackMode poso)  {
	}


	public void postProcessCreateUnmanaged(BoxLayoutPackModeDto dto, BoxLayoutPackMode poso)  {
	}


	public void postProcessLoad(BoxLayoutPackModeDto dto, BoxLayoutPackMode poso)  {
	}


	public void postProcessMerge(BoxLayoutPackModeDto dto, BoxLayoutPackMode poso)  {
	}


	public void postProcessInstantiate(BoxLayoutPackMode poso)  {
	}



}
