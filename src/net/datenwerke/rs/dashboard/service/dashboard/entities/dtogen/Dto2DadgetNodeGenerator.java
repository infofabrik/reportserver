package net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
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
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetDto;
import net.datenwerke.rs.dashboard.client.dashboard.dto.DadgetNodeDto;
import net.datenwerke.rs.dashboard.service.dashboard.entities.Dadget;
import net.datenwerke.rs.dashboard.service.dashboard.entities.DadgetNode;
import net.datenwerke.rs.dashboard.service.dashboard.entities.dtogen.Dto2DadgetNodeGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DadgetNode
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DadgetNodeGenerator implements Dto2PosoGenerator<DadgetNodeDto,DadgetNode> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DadgetNodeGenerator(
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

	public DadgetNode loadPoso(DadgetNodeDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DadgetNode poso = entityManager.find(DadgetNode.class, id);
		return poso;
	}

	public DadgetNode instantiatePoso()  {
		DadgetNode poso = new DadgetNode();
		return poso;
	}

	public DadgetNode createPoso(DadgetNodeDto dto)  throws ExpectedException {
		DadgetNode poso = new DadgetNode();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DadgetNode createUnmanagedPoso(DadgetNodeDto dto)  throws ExpectedException {
		DadgetNode poso = new DadgetNode();

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

	public void mergePoso(DadgetNodeDto dto, final DadgetNode poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DadgetNodeDto dto, final DadgetNode poso)  throws ExpectedException {
		/*  set dadget */
		DadgetDto tmpDto_dadget = dto.getDadget();
		if(null != tmpDto_dadget && null != tmpDto_dadget.getId()){
			if(null != poso.getDadget() && null != poso.getDadget().getId() && poso.getDadget().getId().equals(tmpDto_dadget.getId()))
				poso.setDadget((Dadget)dtoServiceProvider.get().loadAndMergePoso(tmpDto_dadget));
			else
				throw new IllegalArgumentException("enclosed dto should not have non matching id (dadget)");
		} else if(null != poso.getDadget()){
			Dadget newPropertyValue = (Dadget)dtoServiceProvider.get().createPoso(tmpDto_dadget);
			dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDadget(), newPropertyValue, "dadget");
			poso.setDadget(newPropertyValue);
		} else {
			poso.setDadget((Dadget)dtoServiceProvider.get().createPoso(tmpDto_dadget));
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(DadgetNodeDto dto, final DadgetNode poso)  throws ExpectedException {
		/*  set dadget */
		if(dto.isDadgetModified()){
			DadgetDto tmpDto_dadget = dto.getDadget();
			if(null != tmpDto_dadget && null != tmpDto_dadget.getId()){
				if(null != poso.getDadget() && null != poso.getDadget().getId() && poso.getDadget().getId().equals(tmpDto_dadget.getId()))
					poso.setDadget((Dadget)dtoServiceProvider.get().loadAndMergePoso(tmpDto_dadget));
				else
					throw new IllegalArgumentException("enclosed dto should not have non matching id (dadget)");
			} else if(null != poso.getDadget()){
				Dadget newPropertyValue = (Dadget)dtoServiceProvider.get().createPoso(tmpDto_dadget);
				dto2PosoSupervisor.enclosedObjectRemoved(dto, poso, poso.getDadget(), newPropertyValue, "dadget");
				poso.setDadget(newPropertyValue);
			} else {
				poso.setDadget((Dadget)dtoServiceProvider.get().createPoso(tmpDto_dadget));
			}
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(DadgetNodeDto dto, final DadgetNode poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DadgetNodeDto dto, final DadgetNode poso)  throws ExpectedException {
		/*  set dadget */
		DadgetDto tmpDto_dadget = dto.getDadget();
		poso.setDadget((Dadget)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dadget));

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(DadgetNodeDto dto, final DadgetNode poso)  throws ExpectedException {
		/*  set dadget */
		if(dto.isDadgetModified()){
			DadgetDto tmpDto_dadget = dto.getDadget();
			poso.setDadget((Dadget)dtoServiceProvider.get().createUnmanagedPoso(tmpDto_dadget));
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set flags */
		if(dto.isFlagsModified()){
			try{
				poso.setFlags(dto.getFlags() );
			} catch(NullPointerException e){
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public DadgetNode loadAndMergePoso(DadgetNodeDto dto)  throws ExpectedException {
		DadgetNode poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DadgetNodeDto dto, DadgetNode poso)  {
	}


	public void postProcessCreateUnmanaged(DadgetNodeDto dto, DadgetNode poso)  {
	}


	public void postProcessLoad(DadgetNodeDto dto, DadgetNode poso)  {
	}


	public void postProcessMerge(DadgetNodeDto dto, DadgetNode poso)  {
	}


	public void postProcessInstantiate(DadgetNode poso)  {
	}



}
