package net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen;

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
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.Dto2StringUserVariableDefinitionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for StringUserVariableDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2StringUserVariableDefinitionGenerator implements Dto2PosoGenerator<StringUserVariableDefinitionDto,StringUserVariableDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2StringUserVariableDefinitionGenerator(
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

	public StringUserVariableDefinition loadPoso(StringUserVariableDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		StringUserVariableDefinition poso = entityManager.find(StringUserVariableDefinition.class, id);
		return poso;
	}

	public StringUserVariableDefinition instantiatePoso()  {
		StringUserVariableDefinition poso = new StringUserVariableDefinition();
		return poso;
	}

	public StringUserVariableDefinition createPoso(StringUserVariableDefinitionDto dto)  throws ExpectedException {
		StringUserVariableDefinition poso = new StringUserVariableDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public StringUserVariableDefinition createUnmanagedPoso(StringUserVariableDefinitionDto dto)  throws ExpectedException {
		StringUserVariableDefinition poso = new StringUserVariableDefinition();

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

	public void mergePoso(StringUserVariableDefinitionDto dto, final StringUserVariableDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(StringUserVariableDefinitionDto dto, final StringUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set height */
		poso.setHeight(dto.getHeight() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set width */
		poso.setWidth(dto.getWidth() );

	}

	protected void mergeProxy2Poso(StringUserVariableDefinitionDto dto, final StringUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set height */
		if(dto.isHeightModified()){
			poso.setHeight(dto.getHeight() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set width */
		if(dto.isWidthModified()){
			poso.setWidth(dto.getWidth() );
		}

	}

	public void mergeUnmanagedPoso(StringUserVariableDefinitionDto dto, final StringUserVariableDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(StringUserVariableDefinitionDto dto, final StringUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set height */
		poso.setHeight(dto.getHeight() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set width */
		poso.setWidth(dto.getWidth() );

	}

	protected void mergeProxy2UnmanagedPoso(StringUserVariableDefinitionDto dto, final StringUserVariableDefinition poso)  throws ExpectedException {
		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set height */
		if(dto.isHeightModified()){
			poso.setHeight(dto.getHeight() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set width */
		if(dto.isWidthModified()){
			poso.setWidth(dto.getWidth() );
		}

	}

	public StringUserVariableDefinition loadAndMergePoso(StringUserVariableDefinitionDto dto)  throws ExpectedException {
		StringUserVariableDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(StringUserVariableDefinitionDto dto, StringUserVariableDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(StringUserVariableDefinitionDto dto, StringUserVariableDefinition poso)  {
	}


	public void postProcessLoad(StringUserVariableDefinitionDto dto, StringUserVariableDefinition poso)  {
	}


	public void postProcessMerge(StringUserVariableDefinitionDto dto, StringUserVariableDefinition poso)  {
	}


	public void postProcessInstantiate(StringUserVariableDefinition poso)  {
	}



}
