package net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen;

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
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.Dto2ListUserVariableDefinitionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ListUserVariableDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ListUserVariableDefinitionGenerator implements Dto2PosoGenerator<ListUserVariableDefinitionDto,ListUserVariableDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ListUserVariableDefinitionGenerator(
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

	public ListUserVariableDefinition loadPoso(ListUserVariableDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ListUserVariableDefinition poso = entityManager.find(ListUserVariableDefinition.class, id);
		return poso;
	}

	public ListUserVariableDefinition instantiatePoso()  {
		ListUserVariableDefinition poso = new ListUserVariableDefinition();
		return poso;
	}

	public ListUserVariableDefinition createPoso(ListUserVariableDefinitionDto dto)  throws ExpectedException {
		ListUserVariableDefinition poso = new ListUserVariableDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ListUserVariableDefinition createUnmanagedPoso(ListUserVariableDefinitionDto dto)  throws ExpectedException {
		ListUserVariableDefinition poso = new ListUserVariableDefinition();

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

	public void mergePoso(ListUserVariableDefinitionDto dto, final ListUserVariableDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ListUserVariableDefinitionDto dto, final ListUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(ListUserVariableDefinitionDto dto, final ListUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(ListUserVariableDefinitionDto dto, final ListUserVariableDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ListUserVariableDefinitionDto dto, final ListUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(ListUserVariableDefinitionDto dto, final ListUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public ListUserVariableDefinition loadAndMergePoso(ListUserVariableDefinitionDto dto)  throws ExpectedException {
		ListUserVariableDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ListUserVariableDefinitionDto dto, ListUserVariableDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(ListUserVariableDefinitionDto dto, ListUserVariableDefinition poso)  {
	}


	public void postProcessLoad(ListUserVariableDefinitionDto dto, ListUserVariableDefinition poso)  {
	}


	public void postProcessMerge(ListUserVariableDefinitionDto dto, ListUserVariableDefinition poso)  {
	}


	public void postProcessInstantiate(ListUserVariableDefinition poso)  {
	}



}
