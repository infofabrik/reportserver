package net.datenwerke.scheduler.service.scheduler.entities.dtogen;

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
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.dtogen.Dto2JobExecutionStatusGenerator;

/**
 * Dto2PosoGenerator for JobExecutionStatus
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2JobExecutionStatusGenerator implements Dto2PosoGenerator<JobExecutionStatusDto,JobExecutionStatus> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	@Inject
	public Dto2JobExecutionStatusGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JobExecutionStatus loadPoso(JobExecutionStatusDto dto)  {
		return createPoso(dto);
	}

	public JobExecutionStatus instantiatePoso()  {
		throw new IllegalStateException("Cannot instantiate enum!");
	}

	public JobExecutionStatus createPoso(JobExecutionStatusDto dto)  {
		if (null == dto)
			return null;
		switch(dto){
			case INACTIVE:
				return JobExecutionStatus.INACTIVE;
			case WAITING:
				return JobExecutionStatus.WAITING;
			case EXECUTING:
				return JobExecutionStatus.EXECUTING;
			case EXECUTING_ACTIONS:
				return JobExecutionStatus.EXECUTING_ACTIONS;
			case BAD_FAILURE:
				return JobExecutionStatus.BAD_FAILURE;
		}
		throw new IllegalArgumentException("unknown enum type for: " + dto);
	}

	public JobExecutionStatus createUnmanagedPoso(JobExecutionStatusDto dto)  {
		return createPoso(dto);
	}

	public void mergePoso(JobExecutionStatusDto dto, JobExecutionStatus poso)  {
		/* no merging for enums */
	}

	public void mergeUnmanagedPoso(JobExecutionStatusDto dto, JobExecutionStatus poso)  {
		/* no merging for enums */
	}

	public JobExecutionStatus loadAndMergePoso(JobExecutionStatusDto dto)  {
		return createPoso(dto);
	}

	public void postProcessCreate(JobExecutionStatusDto dto, JobExecutionStatus poso)  {
	}


	public void postProcessCreateUnmanaged(JobExecutionStatusDto dto, JobExecutionStatus poso)  {
	}


	public void postProcessLoad(JobExecutionStatusDto dto, JobExecutionStatus poso)  {
	}


	public void postProcessMerge(JobExecutionStatusDto dto, JobExecutionStatus poso)  {
	}


	public void postProcessInstantiate(JobExecutionStatus poso)  {
	}



}
