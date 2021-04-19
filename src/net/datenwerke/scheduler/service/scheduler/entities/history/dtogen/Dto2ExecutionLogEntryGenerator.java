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
import net.datenwerke.scheduler.client.scheduler.dto.OutcomeDto;
import net.datenwerke.scheduler.client.scheduler.dto.VetoJobExecutionModeDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.ExecutionLogEntryDto;
import net.datenwerke.scheduler.client.scheduler.dto.history.JobEntryDto;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.ExecutionLogEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.JobEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2ExecutionLogEntryGenerator;
import net.datenwerke.scheduler.service.scheduler.helper.VetoJobExecutionMode;

/**
 * Dto2PosoGenerator for ExecutionLogEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ExecutionLogEntryGenerator implements Dto2PosoGenerator<ExecutionLogEntryDto,ExecutionLogEntry> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ExecutionLogEntryGenerator(
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

	public ExecutionLogEntry loadPoso(ExecutionLogEntryDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ExecutionLogEntry poso = entityManager.find(ExecutionLogEntry.class, id);
		return poso;
	}

	public ExecutionLogEntry instantiatePoso()  {
		ExecutionLogEntry poso = new ExecutionLogEntry();
		return poso;
	}

	public ExecutionLogEntry createPoso(ExecutionLogEntryDto dto)  throws ExpectedException {
		ExecutionLogEntry poso = new ExecutionLogEntry();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ExecutionLogEntry createUnmanagedPoso(ExecutionLogEntryDto dto)  throws ExpectedException {
		ExecutionLogEntry poso = new ExecutionLogEntry();

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

	public void mergePoso(ExecutionLogEntryDto dto, final ExecutionLogEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ExecutionLogEntryDto dto, final ExecutionLogEntry poso)  throws ExpectedException {
		/*  set actionEntries */
		final List<ActionEntry> col_actionEntries = new ArrayList<ActionEntry>();
		/* load new data from dto */
		Collection<ActionEntryDto> tmpCol_actionEntries = dto.getActionEntries();

		/* load current data from poso */
		if(null != poso.getActionEntries())
			col_actionEntries.addAll(poso.getActionEntries());

		/* remove non existing data */
		List<ActionEntry> remDto_actionEntries = new ArrayList<ActionEntry>();
		for(ActionEntry ref : col_actionEntries){
			boolean found = false;
			for(ActionEntryDto refDto : tmpCol_actionEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_actionEntries.add(ref);
		}
		for(ActionEntry ref : remDto_actionEntries)
			col_actionEntries.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_actionEntries, "actionEntries");

		/* merge or add data */
		List<ActionEntry> new_col_actionEntries = new ArrayList<ActionEntry>();
		for(ActionEntryDto refDto : tmpCol_actionEntries){
			boolean found = false;
			for(ActionEntry ref : col_actionEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_actionEntries.add((ActionEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_actionEntries.add((ActionEntry) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(actionEntries) ");
		}
		poso.setActionEntries(new_col_actionEntries);

		/*  set badErrorDescription */
		poso.setBadErrorDescription(dto.getBadErrorDescription() );

		/*  set end */
		poso.setEnd(dto.getEnd() );

		/*  set jobEntry */
		JobEntryDto tmpDto_jobEntry = dto.getJobEntry();
		if(null != tmpDto_jobEntry && null != tmpDto_jobEntry.getId()){
			if(null != poso.getJobEntry() && null != poso.getJobEntry().getId() && poso.getJobEntry().getId().equals(tmpDto_jobEntry.getId()))
				poso.setJobEntry((JobEntry)dtoServiceProvider.get().loadAndMergePoso(tmpDto_jobEntry));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (jobEntry)");
		} else if(null != poso.getJobEntry()){
			JobEntry newPropertyValue = (JobEntry)dtoServiceProvider.get().createPoso(tmpDto_jobEntry);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getJobEntry(), newPropertyValue, "jobEntry");
			poso.setJobEntry(newPropertyValue);
		} else {
			poso.setJobEntry((JobEntry)dtoServiceProvider.get().createPoso(tmpDto_jobEntry));
		}

		/*  set outcome */
		OutcomeDto tmpDto_outcome = dto.getOutcome();
		poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));

		/*  set scheduledStart */
		poso.setScheduledStart(dto.getScheduledStart() );

		/*  set start */
		poso.setStart(dto.getStart() );

		/*  set vetoExplanation */
		poso.setVetoExplanation(dto.getVetoExplanation() );

		/*  set vetoMode */
		VetoJobExecutionModeDto tmpDto_vetoMode = dto.getVetoMode();
		poso.setVetoMode((VetoJobExecutionMode)dtoServiceProvider.get().createPoso(tmpDto_vetoMode));

	}

	protected void mergeProxy2Poso(ExecutionLogEntryDto dto, final ExecutionLogEntry poso)  throws ExpectedException {
		/*  set actionEntries */
		if(dto.isActionEntriesModified()){
			final List<ActionEntry> col_actionEntries = new ArrayList<ActionEntry>();
			/* load new data from dto */
			Collection<ActionEntryDto> tmpCol_actionEntries = null;
			tmpCol_actionEntries = dto.getActionEntries();

			/* load current data from poso */
			if(null != poso.getActionEntries())
				col_actionEntries.addAll(poso.getActionEntries());

			/* remove non existing data */
			List<ActionEntry> remDto_actionEntries = new ArrayList<ActionEntry>();
			for(ActionEntry ref : col_actionEntries){
				boolean found = false;
				for(ActionEntryDto refDto : tmpCol_actionEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_actionEntries.add(ref);
			}
			for(ActionEntry ref : remDto_actionEntries)
				col_actionEntries.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_actionEntries, "actionEntries");

			/* merge or add data */
			List<ActionEntry> new_col_actionEntries = new ArrayList<ActionEntry>();
			for(ActionEntryDto refDto : tmpCol_actionEntries){
				boolean found = false;
				for(ActionEntry ref : col_actionEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_actionEntries.add((ActionEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_actionEntries.add((ActionEntry) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(actionEntries) ");
			}
			poso.setActionEntries(new_col_actionEntries);
		}

		/*  set badErrorDescription */
		if(dto.isBadErrorDescriptionModified()){
			poso.setBadErrorDescription(dto.getBadErrorDescription() );
		}

		/*  set end */
		if(dto.isEndModified()){
			poso.setEnd(dto.getEnd() );
		}

		/*  set jobEntry */
		if(dto.isJobEntryModified()){
			JobEntryDto tmpDto_jobEntry = dto.getJobEntry();
			if(null != tmpDto_jobEntry && null != tmpDto_jobEntry.getId()){
				if(null != poso.getJobEntry() && null != poso.getJobEntry().getId() && poso.getJobEntry().getId().equals(tmpDto_jobEntry.getId()))
					poso.setJobEntry((JobEntry)dtoServiceProvider.get().loadAndMergePoso(tmpDto_jobEntry));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (jobEntry)");
			} else if(null != poso.getJobEntry()){
				JobEntry newPropertyValue = (JobEntry)dtoServiceProvider.get().createPoso(tmpDto_jobEntry);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getJobEntry(), newPropertyValue, "jobEntry");
				poso.setJobEntry(newPropertyValue);
			} else {
				poso.setJobEntry((JobEntry)dtoServiceProvider.get().createPoso(tmpDto_jobEntry));
			}
		}

		/*  set outcome */
		if(dto.isOutcomeModified()){
			OutcomeDto tmpDto_outcome = dto.getOutcome();
			poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));
		}

		/*  set scheduledStart */
		if(dto.isScheduledStartModified()){
			poso.setScheduledStart(dto.getScheduledStart() );
		}

		/*  set start */
		if(dto.isStartModified()){
			poso.setStart(dto.getStart() );
		}

		/*  set vetoExplanation */
		if(dto.isVetoExplanationModified()){
			poso.setVetoExplanation(dto.getVetoExplanation() );
		}

		/*  set vetoMode */
		if(dto.isVetoModeModified()){
			VetoJobExecutionModeDto tmpDto_vetoMode = dto.getVetoMode();
			poso.setVetoMode((VetoJobExecutionMode)dtoServiceProvider.get().createPoso(tmpDto_vetoMode));
		}

	}

	public void mergeUnmanagedPoso(ExecutionLogEntryDto dto, final ExecutionLogEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ExecutionLogEntryDto dto, final ExecutionLogEntry poso)  throws ExpectedException {
		/*  set actionEntries */
		final List<ActionEntry> col_actionEntries = new ArrayList<ActionEntry>();
		/* load new data from dto */
		Collection<ActionEntryDto> tmpCol_actionEntries = dto.getActionEntries();

		/* merge or add data */
		for(ActionEntryDto refDto : tmpCol_actionEntries){
			col_actionEntries.add((ActionEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setActionEntries(col_actionEntries);

		/*  set badErrorDescription */
		poso.setBadErrorDescription(dto.getBadErrorDescription() );

		/*  set end */
		poso.setEnd(dto.getEnd() );

		/*  set jobEntry */
		JobEntryDto tmpDto_jobEntry = dto.getJobEntry();
		poso.setJobEntry((JobEntry)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_jobEntry));

		/*  set outcome */
		OutcomeDto tmpDto_outcome = dto.getOutcome();
		poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));

		/*  set scheduledStart */
		poso.setScheduledStart(dto.getScheduledStart() );

		/*  set start */
		poso.setStart(dto.getStart() );

		/*  set vetoExplanation */
		poso.setVetoExplanation(dto.getVetoExplanation() );

		/*  set vetoMode */
		VetoJobExecutionModeDto tmpDto_vetoMode = dto.getVetoMode();
		poso.setVetoMode((VetoJobExecutionMode)dtoServiceProvider.get().createPoso(tmpDto_vetoMode));

	}

	protected void mergeProxy2UnmanagedPoso(ExecutionLogEntryDto dto, final ExecutionLogEntry poso)  throws ExpectedException {
		/*  set actionEntries */
		if(dto.isActionEntriesModified()){
			final List<ActionEntry> col_actionEntries = new ArrayList<ActionEntry>();
			/* load new data from dto */
			Collection<ActionEntryDto> tmpCol_actionEntries = null;
			tmpCol_actionEntries = dto.getActionEntries();

			/* merge or add data */
			for(ActionEntryDto refDto : tmpCol_actionEntries){
				col_actionEntries.add((ActionEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setActionEntries(col_actionEntries);
		}

		/*  set badErrorDescription */
		if(dto.isBadErrorDescriptionModified()){
			poso.setBadErrorDescription(dto.getBadErrorDescription() );
		}

		/*  set end */
		if(dto.isEndModified()){
			poso.setEnd(dto.getEnd() );
		}

		/*  set jobEntry */
		if(dto.isJobEntryModified()){
			JobEntryDto tmpDto_jobEntry = dto.getJobEntry();
			poso.setJobEntry((JobEntry)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_jobEntry));
		}

		/*  set outcome */
		if(dto.isOutcomeModified()){
			OutcomeDto tmpDto_outcome = dto.getOutcome();
			poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));
		}

		/*  set scheduledStart */
		if(dto.isScheduledStartModified()){
			poso.setScheduledStart(dto.getScheduledStart() );
		}

		/*  set start */
		if(dto.isStartModified()){
			poso.setStart(dto.getStart() );
		}

		/*  set vetoExplanation */
		if(dto.isVetoExplanationModified()){
			poso.setVetoExplanation(dto.getVetoExplanation() );
		}

		/*  set vetoMode */
		if(dto.isVetoModeModified()){
			VetoJobExecutionModeDto tmpDto_vetoMode = dto.getVetoMode();
			poso.setVetoMode((VetoJobExecutionMode)dtoServiceProvider.get().createPoso(tmpDto_vetoMode));
		}

	}

	public ExecutionLogEntry loadAndMergePoso(ExecutionLogEntryDto dto)  throws ExpectedException {
		ExecutionLogEntry poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ExecutionLogEntryDto dto, ExecutionLogEntry poso)  {
	}


	public void postProcessCreateUnmanaged(ExecutionLogEntryDto dto, ExecutionLogEntry poso)  {
	}


	public void postProcessLoad(ExecutionLogEntryDto dto, ExecutionLogEntry poso)  {
	}


	public void postProcessMerge(ExecutionLogEntryDto dto, ExecutionLogEntry poso)  {
	}


	public void postProcessInstantiate(ExecutionLogEntry poso)  {
	}



}
