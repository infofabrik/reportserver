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
import net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode;
import net.datenwerke.scheduler.service.scheduler.helper.dtogen.Dto2VetoJobExecutionModeGenerator;

/**
 * Dto2PosoGenerator for VetoJobExecutionMode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2VetoJobExecutionModeGenerator implements Dto2PosoGenerator<VetoJobExecutionModeDto,VetoJobExecutionMode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2VetoJobExecutionModeGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public VetoJobExecutionMode loadPoso(VetoJobExecutionModeDto dto)  {
		return createPoso(dto);
	}

	public VetoJobExecutionMode instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public VetoJobExecutionMode createPoso(VetoJobExecutionModeDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case SKIP:
				return VetoJobExecutionMode.SKIP;
			case RETRY:
				return VetoJobExecutionMode.RETRY;
			case CUSTOM:
				return VetoJobExecutionMode.CUSTOM;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public VetoJobExecutionMode createUnmanagedPoso(VetoJobExecutionModeDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(VetoJobExecutionModeDto dto, VetoJobExecutionMode poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(VetoJobExecutionModeDto dto, VetoJobExecutionMode poso)  {
		/* no merging for enums */
	}

	public VetoJobExecutionMode loadAndMergePoso(VetoJobExecutionModeDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(VetoJobExecutionModeDto dto, VetoJobExecutionMode poso)  {
	}


	public void postProcessCreateUnmanaged(VetoJobExecutionModeDto dto, VetoJobExecutionMode poso)  {
	}


	public void postProcessLoad(VetoJobExecutionModeDto dto, VetoJobExecutionMode poso)  {
	}


	public void postProcessMerge(VetoJobExecutionModeDto dto, VetoJobExecutionMode poso)  {
	}


	public void postProcessInstantiate(VetoJobExecutionMode poso)  {
	}



}
