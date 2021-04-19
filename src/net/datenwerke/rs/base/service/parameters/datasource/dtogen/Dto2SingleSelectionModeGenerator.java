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
import net.datenwerke.rs.base.client.parameters.datasource.dto.SingleSelectionModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.SingleSelectionMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2SingleSelectionModeGenerator;

/**
 * Dto2PosoGenerator for SingleSelectionMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2SingleSelectionModeGenerator implements Dto2PosoGenerator<SingleSelectionModeDto,SingleSelectionMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2SingleSelectionModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public SingleSelectionMode loadPoso(SingleSelectionModeDto dto)  {
		return createPoso(dto);
	}

	public SingleSelectionMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public SingleSelectionMode createPoso(SingleSelectionModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case Dropdown:
				return SingleSelectionMode.Dropdown;
			case Popup:
				return SingleSelectionMode.Popup;
			case Radio:
				return SingleSelectionMode.Radio;
			case Listbox:
				return SingleSelectionMode.Listbox;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public SingleSelectionMode createUnmanagedPoso(SingleSelectionModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(SingleSelectionModeDto dto, SingleSelectionMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(SingleSelectionModeDto dto, SingleSelectionMode poso)  {
		/* no merging for enums */
	}

	public SingleSelectionMode loadAndMergePoso(SingleSelectionModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(SingleSelectionModeDto dto, SingleSelectionMode poso)  {
	}


	public void postProcessCreateUnmanaged(SingleSelectionModeDto dto, SingleSelectionMode poso)  {
	}


	public void postProcessLoad(SingleSelectionModeDto dto, SingleSelectionMode poso)  {
	}


	public void postProcessMerge(SingleSelectionModeDto dto, SingleSelectionMode poso)  {
	}


	public void postProcessInstantiate(SingleSelectionMode poso)  {
	}



}
