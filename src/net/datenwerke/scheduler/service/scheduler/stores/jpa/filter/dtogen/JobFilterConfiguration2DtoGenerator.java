package net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen.JobFilterConfiguration2DtoGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for JobFilterConfiguration
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JobFilterConfiguration2DtoGenerator implements Poso2DtoGenerator<JobFilterConfiguration,JobFilterConfigurationDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JobFilterConfiguration2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JobFilterConfigurationDto instantiateDto(JobFilterConfiguration poso)  {
		JobFilterConfigurationDto dto = new JobFilterConfigurationDto();
		return dto;
	}

	public JobFilterConfigurationDto createDto(JobFilterConfiguration poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final JobFilterConfigurationDto dto = new JobFilterConfigurationDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set active */
			dto.setActive(poso.isActive() );

			/*  set executionStatus */
			Object tmpDtoJobExecutionStatusDtogetExecutionStatus = dtoServiceProvider.get().createDto(poso.getExecutionStatus(), referenced, referenced);
			dto.setExecutionStatus((JobExecutionStatusDto)tmpDtoJobExecutionStatusDtogetExecutionStatus);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoJobExecutionStatusDtogetExecutionStatus, poso.getExecutionStatus(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setExecutionStatus((JobExecutionStatusDto)refDto);
				}
			});

			/*  set inActive */
			dto.setInActive(poso.isInActive() );

			/*  set jobId */
			dto.setJobId(StringEscapeUtils.escapeXml(StringUtils.left(poso.getJobId(),8192)));

			/*  set jobTitle */
			dto.setJobTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getJobTitle(),8192)));

			/*  set lastOutcome */
			Object tmpDtoOutcomeDtogetLastOutcome = dtoServiceProvider.get().createDto(poso.getLastOutcome(), referenced, referenced);
			dto.setLastOutcome((OutcomeDto)tmpDtoOutcomeDtogetLastOutcome);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOutcomeDtogetLastOutcome, poso.getLastOutcome(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setLastOutcome((OutcomeDto)refDto);
				}
			});

			/*  set limit */
			dto.setLimit(poso.getLimit() );

			/*  set offset */
			dto.setOffset(poso.getOffset() );

			/*  set order */
			Object tmpDtoOrderDtogetOrder = dtoServiceProvider.get().createDto(poso.getOrder(), referenced, referenced);
			dto.setOrder((OrderDto)tmpDtoOrderDtogetOrder);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoOrderDtogetOrder, poso.getOrder(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setOrder((OrderDto)refDto);
				}
			});

			/*  set sortField */
			dto.setSortField(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSortField(),8192)));

		}

		return dto;
	}


}
