package net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.JobExecutionStatusDto;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.JobFilterConfigurationDto;
import net.datenwerke.scheduler.client.scheduler.dto.filter.OrderDto;
import net.datenwerke.scheduler.service.scheduler.entities.JobExecutionStatus;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.JobFilterConfiguration;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.Order;
import net.datenwerke.scheduler.service.scheduler.stores.jpa.filter.dtogen.Dto2JobFilterConfigurationGenerator;

/**
 * Dto2PosoGenerator for JobFilterConfiguration
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2JobFilterConfigurationGenerator implements Dto2PosoGenerator<JobFilterConfigurationDto,JobFilterConfiguration> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2JobFilterConfigurationGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public JobFilterConfiguration loadPoso(JobFilterConfigurationDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public JobFilterConfiguration instantiatePoso()  {
		JobFilterConfiguration poso = new JobFilterConfiguration();
		return poso;
	}

	public JobFilterConfiguration createPoso(JobFilterConfigurationDto dto)  throws ExpectedException {
		JobFilterConfiguration poso = new JobFilterConfiguration();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public JobFilterConfiguration createUnmanagedPoso(JobFilterConfigurationDto dto)  throws ExpectedException {
		JobFilterConfiguration poso = new JobFilterConfiguration();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(JobFilterConfigurationDto dto, final JobFilterConfiguration poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(JobFilterConfigurationDto dto, final JobFilterConfiguration poso)  throws ExpectedException {
		/*  set active */
		try{
			poso.setActive(dto.isActive() );
		} catch(NullPointerException e){
		}

		/*  set executionStatus */
		JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
		poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));

		/*  set inActive */
		try{
			poso.setInActive(dto.isInActive() );
		} catch(NullPointerException e){
		}

		/*  set jobId */
		poso.setJobId(dto.getJobId() );

		/*  set jobTitle */
		poso.setJobTitle(dto.getJobTitle() );

		/*  set lastOutcome */
		OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
		poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));

		/*  set limit */
		try{
			poso.setLimit(dto.getLimit() );
		} catch(NullPointerException e){
		}

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set order */
		OrderDto tmpDto_order = dto.getOrder();
		poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));

		/*  set sortField */
		poso.setSortField(dto.getSortField() );

	}

	protected void mergeProxy2Poso(JobFilterConfigurationDto dto, final JobFilterConfiguration poso)  throws ExpectedException {
		/*  set active */
		if(dto.isActiveModified()){
			try{
				poso.setActive(dto.isActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set executionStatus */
		if(dto.isExecutionStatusModified()){
			JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
			poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));
		}

		/*  set inActive */
		if(dto.isInActiveModified()){
			try{
				poso.setInActive(dto.isInActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set jobId */
		if(dto.isJobIdModified()){
			poso.setJobId(dto.getJobId() );
		}

		/*  set jobTitle */
		if(dto.isJobTitleModified()){
			poso.setJobTitle(dto.getJobTitle() );
		}

		/*  set lastOutcome */
		if(dto.isLastOutcomeModified()){
			OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
			poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));
		}

		/*  set limit */
		if(dto.isLimitModified()){
			try{
				poso.setLimit(dto.getLimit() );
			} catch(NullPointerException e){
			}
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set order */
		if(dto.isOrderModified()){
			OrderDto tmpDto_order = dto.getOrder();
			poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));
		}

		/*  set sortField */
		if(dto.isSortFieldModified()){
			poso.setSortField(dto.getSortField() );
		}

	}

	public void mergeUnmanagedPoso(JobFilterConfigurationDto dto, final JobFilterConfiguration poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(JobFilterConfigurationDto dto, final JobFilterConfiguration poso)  throws ExpectedException {
		/*  set active */
		try{
			poso.setActive(dto.isActive() );
		} catch(NullPointerException e){
		}

		/*  set executionStatus */
		JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
		poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));

		/*  set inActive */
		try{
			poso.setInActive(dto.isInActive() );
		} catch(NullPointerException e){
		}

		/*  set jobId */
		poso.setJobId(dto.getJobId() );

		/*  set jobTitle */
		poso.setJobTitle(dto.getJobTitle() );

		/*  set lastOutcome */
		OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
		poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));

		/*  set limit */
		try{
			poso.setLimit(dto.getLimit() );
		} catch(NullPointerException e){
		}

		/*  set offset */
		try{
			poso.setOffset(dto.getOffset() );
		} catch(NullPointerException e){
		}

		/*  set order */
		OrderDto tmpDto_order = dto.getOrder();
		poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));

		/*  set sortField */
		poso.setSortField(dto.getSortField() );

	}

	protected void mergeProxy2UnmanagedPoso(JobFilterConfigurationDto dto, final JobFilterConfiguration poso)  throws ExpectedException {
		/*  set active */
		if(dto.isActiveModified()){
			try{
				poso.setActive(dto.isActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set executionStatus */
		if(dto.isExecutionStatusModified()){
			JobExecutionStatusDto tmpDto_executionStatus = dto.getExecutionStatus();
			poso.setExecutionStatus((JobExecutionStatus)dtoServiceProvider.get().createPoso(tmpDto_executionStatus));
		}

		/*  set inActive */
		if(dto.isInActiveModified()){
			try{
				poso.setInActive(dto.isInActive() );
			} catch(NullPointerException e){
			}
		}

		/*  set jobId */
		if(dto.isJobIdModified()){
			poso.setJobId(dto.getJobId() );
		}

		/*  set jobTitle */
		if(dto.isJobTitleModified()){
			poso.setJobTitle(dto.getJobTitle() );
		}

		/*  set lastOutcome */
		if(dto.isLastOutcomeModified()){
			OutcomeDto tmpDto_lastOutcome = dto.getLastOutcome();
			poso.setLastOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_lastOutcome));
		}

		/*  set limit */
		if(dto.isLimitModified()){
			try{
				poso.setLimit(dto.getLimit() );
			} catch(NullPointerException e){
			}
		}

		/*  set offset */
		if(dto.isOffsetModified()){
			try{
				poso.setOffset(dto.getOffset() );
			} catch(NullPointerException e){
			}
		}

		/*  set order */
		if(dto.isOrderModified()){
			OrderDto tmpDto_order = dto.getOrder();
			poso.setOrder((Order)dtoServiceProvider.get().createPoso(tmpDto_order));
		}

		/*  set sortField */
		if(dto.isSortFieldModified()){
			poso.setSortField(dto.getSortField() );
		}

	}

	public JobFilterConfiguration loadAndMergePoso(JobFilterConfigurationDto dto)  throws ExpectedException {
		JobFilterConfiguration poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(JobFilterConfigurationDto dto, JobFilterConfiguration poso)  {
	}


	public void postProcessCreateUnmanaged(JobFilterConfigurationDto dto, JobFilterConfiguration poso)  {
	}


	public void postProcessLoad(JobFilterConfigurationDto dto, JobFilterConfiguration poso)  {
	}


	public void postProcessMerge(JobFilterConfigurationDto dto, JobFilterConfiguration poso)  {
	}


	public void postProcessInstantiate(JobFilterConfiguration poso)  {
	}



}
