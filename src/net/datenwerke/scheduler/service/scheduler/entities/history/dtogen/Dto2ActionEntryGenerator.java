package net.datenwerke.scheduler.service.scheduler.entities.history.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
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
import net.datenwerke.scheduler.client.scheduler.dto.history.ActionEntryDto;
import net.datenwerke.scheduler.service.scheduler.entities.Outcome;
import net.datenwerke.scheduler.service.scheduler.entities.history.ActionEntry;
import net.datenwerke.scheduler.service.scheduler.entities.history.dtogen.Dto2ActionEntryGenerator;

/**
 * Dto2PosoGenerator for ActionEntry
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ActionEntryGenerator implements Dto2PosoGenerator<ActionEntryDto,ActionEntry> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ActionEntryGenerator(
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

	public ActionEntry loadPoso(ActionEntryDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ActionEntry poso = entityManager.find(ActionEntry.class, id);
		return poso;
	}

	public ActionEntry instantiatePoso()  {
		ActionEntry poso = new ActionEntry();
		return poso;
	}

	public ActionEntry createPoso(ActionEntryDto dto)  throws ExpectedException {
		ActionEntry poso = new ActionEntry();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ActionEntry createUnmanagedPoso(ActionEntryDto dto)  throws ExpectedException {
		ActionEntry poso = new ActionEntry();

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

	public void mergePoso(ActionEntryDto dto, final ActionEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ActionEntryDto dto, final ActionEntry poso)  throws ExpectedException {
		/*  set actionName */
		poso.setActionName(dto.getActionName() );

		/*  set errorDescription */
		poso.setErrorDescription(dto.getErrorDescription() );

		/*  set outcome */
		OutcomeDto tmpDto_outcome = dto.getOutcome();
		poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));

	}

	protected void mergeProxy2Poso(ActionEntryDto dto, final ActionEntry poso)  throws ExpectedException {
		/*  set actionName */
		if(dto.isActionNameModified()){
			poso.setActionName(dto.getActionName() );
		}

		/*  set errorDescription */
		if(dto.isErrorDescriptionModified()){
			poso.setErrorDescription(dto.getErrorDescription() );
		}

		/*  set outcome */
		if(dto.isOutcomeModified()){
			OutcomeDto tmpDto_outcome = dto.getOutcome();
			poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));
		}

	}

	public void mergeUnmanagedPoso(ActionEntryDto dto, final ActionEntry poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ActionEntryDto dto, final ActionEntry poso)  throws ExpectedException {
		/*  set actionName */
		poso.setActionName(dto.getActionName() );

		/*  set errorDescription */
		poso.setErrorDescription(dto.getErrorDescription() );

		/*  set outcome */
		OutcomeDto tmpDto_outcome = dto.getOutcome();
		poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));

	}

	protected void mergeProxy2UnmanagedPoso(ActionEntryDto dto, final ActionEntry poso)  throws ExpectedException {
		/*  set actionName */
		if(dto.isActionNameModified()){
			poso.setActionName(dto.getActionName() );
		}

		/*  set errorDescription */
		if(dto.isErrorDescriptionModified()){
			poso.setErrorDescription(dto.getErrorDescription() );
		}

		/*  set outcome */
		if(dto.isOutcomeModified()){
			OutcomeDto tmpDto_outcome = dto.getOutcome();
			poso.setOutcome((Outcome)dtoServiceProvider.get().createPoso(tmpDto_outcome));
		}

	}

	public ActionEntry loadAndMergePoso(ActionEntryDto dto)  throws ExpectedException {
		ActionEntry poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ActionEntryDto dto, ActionEntry poso)  {
	}


	public void postProcessCreateUnmanaged(ActionEntryDto dto, ActionEntry poso)  {
	}


	public void postProcessLoad(ActionEntryDto dto, ActionEntry poso)  {
	}


	public void postProcessMerge(ActionEntryDto dto, ActionEntry poso)  {
	}


	public void postProcessInstantiate(ActionEntry poso)  {
	}



}
