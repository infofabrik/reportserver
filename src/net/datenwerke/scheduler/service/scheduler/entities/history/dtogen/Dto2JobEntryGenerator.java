package net.datenwerke.scheduler.service.scheduler.entities.history.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.EntityManager;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.HistoryEntryPropertyDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.history.HistoryEntryProperty;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2JobEntryGenerator;

/**
 * Dto2PosoGenerator for JobEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2JobEntryGenerator implements Dto2PosoGenerator<JobEntryDto,JobEntry> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2JobEntryGenerator(
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

	public JobEntry loadPoso(JobEntryDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		JobEntry poso = entityManager.find(JobEntry.class, id);
		return poso;
	}

	public JobEntry instantiatePoso()  {
		JobEntry poso = new JobEntry();
		return poso;
	}

	public JobEntry createPoso(JobEntryDto dto)  throws ExpectedException {
		JobEntry poso = new JobEntry();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public JobEntry createUnmanagedPoso(JobEntryDto dto)  throws ExpectedException {
		JobEntry poso = new JobEntry();

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

	public void mergePoso(JobEntryDto dto, final JobEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(JobEntryDto dto, final JobEntry poso)  throws ExpectedException {
		/*  set errorDescription */
		poso.setErrorDescription(dto.getErrorDescription() );

		/*  set historyProperties */
		final Set<HistoryEntryProperty> col_historyProperties = new HashSet<HistoryEntryProperty>();
		/* load new data from dto */
		Collection<HistoryEntryPropertyDto> tmpCol_historyProperties = dto.getHistoryProperties();

		/* load current data from poso */
		if(null != poso.getHistoryProperties())
			col_historyProperties.addAll(poso.getHistoryProperties());

		/* remove non existing data */
		Set<HistoryEntryProperty> remDto_historyProperties = new HashSet<HistoryEntryProperty>();
		for(HistoryEntryProperty ref : col_historyProperties){
			boolean found = false;
			for(HistoryEntryPropertyDto refDto : tmpCol_historyProperties){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_historyProperties.add(ref);
		}
		for(HistoryEntryProperty ref : remDto_historyProperties)
			col_historyProperties.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_historyProperties, "historyProperties");

		/* merge or add data */
		Set<HistoryEntryProperty> new_col_historyProperties = new HashSet<HistoryEntryProperty>();
		for(HistoryEntryPropertyDto refDto : tmpCol_historyProperties){
			boolean found = false;
			for(HistoryEntryProperty ref : col_historyProperties){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_historyProperties.add((HistoryEntryProperty) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_historyProperties.add((HistoryEntryProperty) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(historyProperties) ");
		}
		poso.setHistoryProperties(new_col_historyProperties);

		/*  set outcome */
		OutcomeDto tmpDto_outcome = dto.getOutcome();
		poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));

	}

	protected void mergeProxy2Poso(JobEntryDto dto, final JobEntry poso)  throws ExpectedException {
		/*  set errorDescription */
		if(dto.isErrorDescriptionModified()){
			poso.setErrorDescription(dto.getErrorDescription() );
		}

		/*  set historyProperties */
		if(dto.isHistoryPropertiesModified()){
			final Set<HistoryEntryProperty> col_historyProperties = new HashSet<HistoryEntryProperty>();
			/* load new data from dto */
			Collection<HistoryEntryPropertyDto> tmpCol_historyProperties = null;
			tmpCol_historyProperties = dto.getHistoryProperties();

			/* load current data from poso */
			if(null != poso.getHistoryProperties())
				col_historyProperties.addAll(poso.getHistoryProperties());

			/* remove non existing data */
			Set<HistoryEntryProperty> remDto_historyProperties = new HashSet<HistoryEntryProperty>();
			for(HistoryEntryProperty ref : col_historyProperties){
				boolean found = false;
				for(HistoryEntryPropertyDto refDto : tmpCol_historyProperties){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_historyProperties.add(ref);
			}
			for(HistoryEntryProperty ref : remDto_historyProperties)
				col_historyProperties.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_historyProperties, "historyProperties");

			/* merge or add data */
			Set<HistoryEntryProperty> new_col_historyProperties = new HashSet<HistoryEntryProperty>();
			for(HistoryEntryPropertyDto refDto : tmpCol_historyProperties){
				boolean found = false;
				for(HistoryEntryProperty ref : col_historyProperties){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_historyProperties.add((HistoryEntryProperty) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_historyProperties.add((HistoryEntryProperty) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(historyProperties) ");
			}
			poso.setHistoryProperties(new_col_historyProperties);
		}

		/*  set outcome */
		if(dto.isOutcomeModified()){
			OutcomeDto tmpDto_outcome = dto.getOutcome();
			poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));
		}

	}

	public void mergeUnmanagedPoso(JobEntryDto dto, final JobEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(JobEntryDto dto, final JobEntry poso)  throws ExpectedException {
		/*  set errorDescription */
		poso.setErrorDescription(dto.getErrorDescription() );

		/*  set historyProperties */
		final Set<HistoryEntryProperty> col_historyProperties = new HashSet<HistoryEntryProperty>();
		/* load new data from dto */
		Collection<HistoryEntryPropertyDto> tmpCol_historyProperties = dto.getHistoryProperties();

		/* merge or add data */
		for(HistoryEntryPropertyDto refDto : tmpCol_historyProperties){
			col_historyProperties.add((HistoryEntryProperty) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setHistoryProperties(col_historyProperties);

		/*  set outcome */
		OutcomeDto tmpDto_outcome = dto.getOutcome();
		poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));

	}

	protected void mergeProxy2UnmanagedPoso(JobEntryDto dto, final JobEntry poso)  throws ExpectedException {
		/*  set errorDescription */
		if(dto.isErrorDescriptionModified()){
			poso.setErrorDescription(dto.getErrorDescription() );
		}

		/*  set historyProperties */
		if(dto.isHistoryPropertiesModified()){
			final Set<HistoryEntryProperty> col_historyProperties = new HashSet<HistoryEntryProperty>();
			/* load new data from dto */
			Collection<HistoryEntryPropertyDto> tmpCol_historyProperties = null;
			tmpCol_historyProperties = dto.getHistoryProperties();

			/* merge or add data */
			for(HistoryEntryPropertyDto refDto : tmpCol_historyProperties){
				col_historyProperties.add((HistoryEntryProperty) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setHistoryProperties(col_historyProperties);
		}

		/*  set outcome */
		if(dto.isOutcomeModified()){
			OutcomeDto tmpDto_outcome = dto.getOutcome();
			poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));
		}

	}

	public JobEntry loadAndMergePoso(JobEntryDto dto)  throws ExpectedException {
		JobEntry poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(JobEntryDto dto, JobEntry poso)  {
	}


	public void postProcessCreateUnmanaged(JobEntryDto dto, JobEntry poso)  {
	}


	public void postProcessLoad(JobEntryDto dto, JobEntry poso)  {
	}


	public void postProcessMerge(JobEntryDto dto, JobEntry poso)  {
	}


	public void postProcessInstantiate(JobEntry poso)  {
	}



}
