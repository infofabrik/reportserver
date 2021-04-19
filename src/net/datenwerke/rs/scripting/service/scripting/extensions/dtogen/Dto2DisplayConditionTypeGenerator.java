package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

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
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayConditionType;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.Dto2DisplayConditionTypeGenerator;

/**
 * Dto2PosoGenerator for DisplayConditionType
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DisplayConditionTypeGenerator implements Dto2PosoGenerator<DisplayConditionTypeDto,DisplayConditionType> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2DisplayConditionTypeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DisplayConditionType loadPoso(DisplayConditionTypeDto dto)  {
		return createPoso(dto);
	}

	public DisplayConditionType instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public DisplayConditionType createPoso(DisplayConditionTypeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case EQUALS:
				return DisplayConditionType.EQUALS;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public DisplayConditionType createUnmanagedPoso(DisplayConditionTypeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(DisplayConditionTypeDto dto, DisplayConditionType poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(DisplayConditionTypeDto dto, DisplayConditionType poso)  {
		/* no merging for enums */
	}

	public DisplayConditionType loadAndMergePoso(DisplayConditionTypeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(DisplayConditionTypeDto dto, DisplayConditionType poso)  {
	}


	public void postProcessCreateUnmanaged(DisplayConditionTypeDto dto, DisplayConditionType poso)  {
	}


	public void postProcessLoad(DisplayConditionTypeDto dto, DisplayConditionType poso)  {
	}


	public void postProcessMerge(DisplayConditionTypeDto dto, DisplayConditionType poso)  {
	}


	public void postProcessInstantiate(DisplayConditionType poso)  {
	}



}
