package net.datenwerke.scheduler.service.scheduler.helper.dtogen;

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
import net.datenwerke.scheduler.client.scheduler.dto.VetoActionExecutionModeDto;
import net.datenwerke.scheduler.service.scheduler.helper.VetoActionExecutionMode;
import net.datenwerke.scheduler.service.scheduler.helper.dtogen.Dto2VetoActionExecutionModeGenerator;

/**
 * Dto2PosoGenerator for VetoActionExecutionMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2VetoActionExecutionModeGenerator implements Dto2PosoGenerator<VetoActionExecutionModeDto,VetoActionExecutionMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2VetoActionExecutionModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public VetoActionExecutionMode loadPoso(VetoActionExecutionModeDto dto)  {
		return createPoso(dto);
	}

	public VetoActionExecutionMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public VetoActionExecutionMode createPoso(VetoActionExecutionModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case SKIP:
				return VetoActionExecutionMode.SKIP;
			case RETRY:
				return VetoActionExecutionMode.RETRY;
			case CUSTOM:
				return VetoActionExecutionMode.CUSTOM;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public VetoActionExecutionMode createUnmanagedPoso(VetoActionExecutionModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(VetoActionExecutionModeDto dto, VetoActionExecutionMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(VetoActionExecutionModeDto dto, VetoActionExecutionMode poso)  {
		/* no merging for enums */
	}

	public VetoActionExecutionMode loadAndMergePoso(VetoActionExecutionModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(VetoActionExecutionModeDto dto, VetoActionExecutionMode poso)  {
	}


	public void postProcessCreateUnmanaged(VetoActionExecutionModeDto dto, VetoActionExecutionMode poso)  {
	}


	public void postProcessLoad(VetoActionExecutionModeDto dto, VetoActionExecutionMode poso)  {
	}


	public void postProcessMerge(VetoActionExecutionModeDto dto, VetoActionExecutionMode poso)  {
	}


	public void postProcessInstantiate(VetoActionExecutionMode poso)  {
	}



}
