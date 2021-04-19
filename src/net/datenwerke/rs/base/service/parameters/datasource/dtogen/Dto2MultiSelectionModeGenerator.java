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
import net.datenwerke.rs.base.client.parameters.datasource.dto.MultiSelectionModeDto;
import net.datenwerke.rs.base.service.parameters.datasource.MultiSelectionMode;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.Dto2MultiSelectionModeGenerator;

/**
 * Dto2PosoGenerator for MultiSelectionMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2MultiSelectionModeGenerator implements Dto2PosoGenerator<MultiSelectionModeDto,MultiSelectionMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2MultiSelectionModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MultiSelectionMode loadPoso(MultiSelectionModeDto dto)  {
		return createPoso(dto);
	}

	public MultiSelectionMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public MultiSelectionMode createPoso(MultiSelectionModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case Popup:
				return MultiSelectionMode.Popup;
			case Checkbox:
				return MultiSelectionMode.Checkbox;
			case Listbox:
				return MultiSelectionMode.Listbox;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public MultiSelectionMode createUnmanagedPoso(MultiSelectionModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(MultiSelectionModeDto dto, MultiSelectionMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(MultiSelectionModeDto dto, MultiSelectionMode poso)  {
		/* no merging for enums */
	}

	public MultiSelectionMode loadAndMergePoso(MultiSelectionModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(MultiSelectionModeDto dto, MultiSelectionMode poso)  {
	}


	public void postProcessCreateUnmanaged(MultiSelectionModeDto dto, MultiSelectionMode poso)  {
	}


	public void postProcessLoad(MultiSelectionModeDto dto, MultiSelectionMode poso)  {
	}


	public void postProcessMerge(MultiSelectionModeDto dto, MultiSelectionMode poso)  {
	}


	public void postProcessInstantiate(MultiSelectionMode poso)  {
	}



}
