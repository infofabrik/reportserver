package net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen;

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
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.teamspace.client.teamspace.dto.TeamSpaceDto;
import net.datenwerke.rs.teamspace.service.teamspace.entities.TeamSpace;
import net.datenwerke.rs.teamspace.service.teamspace.entities.dtogen.Dto2TeamSpaceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TeamSpace
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TeamSpaceGenerator implements Dto2PosoGenerator<TeamSpaceDto,TeamSpace> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TeamSpaceGenerator(
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

	public TeamSpace loadPoso(TeamSpaceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TeamSpace poso = entityManager.find(TeamSpace.class, id);
		return poso;
	}

	public TeamSpace instantiatePoso()  {
		TeamSpace poso = new TeamSpace();
		return poso;
	}

	public TeamSpace createPoso(TeamSpaceDto dto)  throws ExpectedException {
		TeamSpace poso = new TeamSpace();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TeamSpace createUnmanagedPoso(TeamSpaceDto dto)  throws ExpectedException {
		TeamSpace poso = new TeamSpace();

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

	public void mergePoso(TeamSpaceDto dto, final TeamSpace poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TeamSpaceDto dto, final TeamSpace poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set name */
		if(validateNameProperty(dto, poso)){
			poso.setName(dto.getName() );
		}

	}

	protected void mergeProxy2Poso(TeamSpaceDto dto, final TeamSpace poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set name */
		if(dto.isNameModified()){
			if(validateNameProperty(dto, poso)){
				poso.setName(dto.getName() );
			}
		}

	}

	public void mergeUnmanagedPoso(TeamSpaceDto dto, final TeamSpace poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TeamSpaceDto dto, final TeamSpace poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set name */
		if(validateNameProperty(dto, poso)){
			poso.setName(dto.getName() );
		}

	}

	protected void mergeProxy2UnmanagedPoso(TeamSpaceDto dto, final TeamSpace poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set name */
		if(dto.isNameModified()){
			if(validateNameProperty(dto, poso)){
				poso.setName(dto.getName() );
			}
		}

	}

	public TeamSpace loadAndMergePoso(TeamSpaceDto dto)  throws ExpectedException {
		TeamSpace poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TeamSpaceDto dto, TeamSpace poso)  {
	}


	public void postProcessCreateUnmanaged(TeamSpaceDto dto, TeamSpace poso)  {
	}


	public void postProcessLoad(TeamSpaceDto dto, TeamSpace poso)  {
	}


	public void postProcessMerge(TeamSpaceDto dto, TeamSpace poso)  {
	}


	public void postProcessInstantiate(TeamSpace poso)  {
	}


	public boolean validateNameProperty(TeamSpaceDto dto, TeamSpace poso)  throws ExpectedException {
		Object propertyValue = dto.getName();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for name", "expected a String");

		if(((String)propertyValue).length() > 255) 
			throw new ValidationFailedException("String validation failed for name", "passed String is too long. Expected maximum length is: 255");

		/* all went well */
		return true;
	}


}
