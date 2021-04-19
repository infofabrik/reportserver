package net.datenwerke.scheduler.service.scheduler.entities.history.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobHistoryDto;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobHistory;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2JobHistoryGenerator;

/**
 * Dto2PosoGenerator for JobHistory
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2JobHistoryGenerator implements Dto2PosoGenerator<JobHistoryDto,JobHistory> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2JobHistoryGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		Provider<EntityManager> entityManagerProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.entityManagerProvider = entityManagerProvider;
		this.reflectionService = reflectionService;
	}

	public JobHistory loadPoso(JobHistoryDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		JobHistory poso = entityManager.find(JobHistory.class, id);
		return poso;
	}

	public JobHistory instantiatePoso()  {
		JobHistory poso = new JobHistory();
		return poso;
	}

	public JobHistory createPoso(JobHistoryDto dto)  throws ExpectedException {
		JobHistory poso = new JobHistory();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public JobHistory createUnmanagedPoso(JobHistoryDto dto)  throws ExpectedException {
		JobHistory poso = new JobHistory();

		/* store old id */
		if(null != dto.getId()){
			Field transientIdField = reflectionService.getFieldByAnnotation(poso, TransientID.class);
			if(null != transientIdField){
				transientIdField.setAccessible(true);
				try{
					transientIdField.set(poso, dto.getId());
				} catch(Exception e){
				}
			}
		}

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(JobHistoryDto dto, final JobHistory poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(JobHistoryDto dto, final JobHistory poso)  throws ExpectedException {
		/*  set executionLogEntries */
		final List<ExecutionLogEntry> col_executionLogEntries = new ArrayList<ExecutionLogEntry>();
		/* load new data from dto */
		Collection<ExecutionLogEntryDto> tmpCol_executionLogEntries = dto.getExecutionLogEntries();

		/* load current data from poso */
		if(null != poso.getExecutionLogEntries())
			col_executionLogEntries.addAll(poso.getExecutionLogEntries());

		/* remove non existing data */
		List<ExecutionLogEntry> remDto_executionLogEntries = new ArrayList<ExecutionLogEntry>();
		for(ExecutionLogEntry ref : col_executionLogEntries){
			boolean found = false;
			for(ExecutionLogEntryDto refDto : tmpCol_executionLogEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_executionLogEntries.add(ref);
		}
		for(ExecutionLogEntry ref : remDto_executionLogEntries)
			col_executionLogEntries.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_executionLogEntries, "executionLogEntries");

		/* merge or add data */
		List<ExecutionLogEntry> new_col_executionLogEntries = new ArrayList<ExecutionLogEntry>();
		for(ExecutionLogEntryDto refDto : tmpCol_executionLogEntries){
			boolean found = false;
			for(ExecutionLogEntry ref : col_executionLogEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_executionLogEntries.add((ExecutionLogEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_executionLogEntries.add((ExecutionLogEntry) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(executionLogEntries) ");
		}
		poso.setExecutionLogEntries(new_col_executionLogEntries);

	}

	protected void mergeProxy2Poso(JobHistoryDto dto, final JobHistory poso)  throws ExpectedException {
		/*  set executionLogEntries */
		if(dto.isExecutionLogEntriesModified()){
			final List<ExecutionLogEntry> col_executionLogEntries = new ArrayList<ExecutionLogEntry>();
			/* load new data from dto */
			Collection<ExecutionLogEntryDto> tmpCol_executionLogEntries = null;
			tmpCol_executionLogEntries = dto.getExecutionLogEntries();

			/* load current data from poso */
			if(null != poso.getExecutionLogEntries())
				col_executionLogEntries.addAll(poso.getExecutionLogEntries());

			/* remove non existing data */
			List<ExecutionLogEntry> remDto_executionLogEntries = new ArrayList<ExecutionLogEntry>();
			for(ExecutionLogEntry ref : col_executionLogEntries){
				boolean found = false;
				for(ExecutionLogEntryDto refDto : tmpCol_executionLogEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_executionLogEntries.add(ref);
			}
			for(ExecutionLogEntry ref : remDto_executionLogEntries)
				col_executionLogEntries.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_executionLogEntries, "executionLogEntries");

			/* merge or add data */
			List<ExecutionLogEntry> new_col_executionLogEntries = new ArrayList<ExecutionLogEntry>();
			for(ExecutionLogEntryDto refDto : tmpCol_executionLogEntries){
				boolean found = false;
				for(ExecutionLogEntry ref : col_executionLogEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_executionLogEntries.add((ExecutionLogEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_executionLogEntries.add((ExecutionLogEntry) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(executionLogEntries) ");
			}
			poso.setExecutionLogEntries(new_col_executionLogEntries);
		}

	}

	public void mergeUnmanagedPoso(JobHistoryDto dto, final JobHistory poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(JobHistoryDto dto, final JobHistory poso)  throws ExpectedException {
		/*  set executionLogEntries */
		final List<ExecutionLogEntry> col_executionLogEntries = new ArrayList<ExecutionLogEntry>();
		/* load new data from dto */
		Collection<ExecutionLogEntryDto> tmpCol_executionLogEntries = dto.getExecutionLogEntries();

		/* merge or add data */
		for(ExecutionLogEntryDto refDto : tmpCol_executionLogEntries){
			col_executionLogEntries.add((ExecutionLogEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setExecutionLogEntries(col_executionLogEntries);

	}

	protected void mergeProxy2UnmanagedPoso(JobHistoryDto dto, final JobHistory poso)  throws ExpectedException {
		/*  set executionLogEntries */
		if(dto.isExecutionLogEntriesModified()){
			final List<ExecutionLogEntry> col_executionLogEntries = new ArrayList<ExecutionLogEntry>();
			/* load new data from dto */
			Collection<ExecutionLogEntryDto> tmpCol_executionLogEntries = null;
			tmpCol_executionLogEntries = dto.getExecutionLogEntries();

			/* merge or add data */
			for(ExecutionLogEntryDto refDto : tmpCol_executionLogEntries){
				col_executionLogEntries.add((ExecutionLogEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setExecutionLogEntries(col_executionLogEntries);
		}

	}

	public JobHistory loadAndMergePoso(JobHistoryDto dto)  throws ExpectedException {
		JobHistory poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(JobHistoryDto dto, JobHistory poso)  {
	}


	public void postProcessCreateUnmanaged(JobHistoryDto dto, JobHistory poso)  {
	}


	public void postProcessLoad(JobHistoryDto dto, JobHistory poso)  {
	}


	public void postProcessMerge(JobHistoryDto dto, JobHistory poso)  {
	}


	public void postProcessInstantiate(JobHistory poso)  {
	}



}
