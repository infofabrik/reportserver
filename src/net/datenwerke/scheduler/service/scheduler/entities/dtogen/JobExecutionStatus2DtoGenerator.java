package net.datenwerke.scheduler.service.scheduler.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.IllegalArgumentException;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.dtogen.JobExecutionStatus2DtoGenerator;

/**
 * Poso2DtoGenerator for JobExecutionStatus
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobExecutionStatus2DtoGenerator implements Poso2DtoGenerator<JobExecutionStatus,JobExecutionStatusDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JobExecutionStatus2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JobExecutionStatusDto instantiateDto(JobExecutionStatus poso)  {
		/* Simply return the first enum! */
		JobExecutionStatusDto dto = JobExecutionStatusDto.INACTIVE;
		return dto;
	}

	public JobExecutionStatusDto createDto(JobExecutionStatus poso, DtoView here, DtoView referenced)  {
		switch(poso){
			case INACTIVE:
				return JobExecutionStatusDto.INACTIVE;
			case WAITING:
				return JobExecutionStatusDto.WAITING;
			case EXECUTING:
				return JobExecutionStatusDto.EXECUTING;
			case EXECUTING_ACTIONS:
				return JobExecutionStatusDto.EXECUTING_ACTIONS;
			case BAD_FAILURE:
				return JobExecutionStatusDto.BAD_FAILURE;
		}
		throw new IllegalArgumentException("unknown enum type for: " + poso);
	}


}
