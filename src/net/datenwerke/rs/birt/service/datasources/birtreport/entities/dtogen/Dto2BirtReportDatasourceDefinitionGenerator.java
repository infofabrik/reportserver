package net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen;

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
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceDefinitionDto;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.Dto2BirtReportDatasourceDefinitionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for BirtReportDatasourceDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BirtReportDatasourceDefinitionGenerator implements Dto2PosoGenerator<BirtReportDatasourceDefinitionDto,BirtReportDatasourceDefinition> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2BirtReportDatasourceDefinitionGenerator(
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

	public BirtReportDatasourceDefinition loadPoso(BirtReportDatasourceDefinitionDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		BirtReportDatasourceDefinition poso = entityManager.find(BirtReportDatasourceDefinition.class, id);
		return poso;
	}

	public BirtReportDatasourceDefinition instantiatePoso()  {
		BirtReportDatasourceDefinition poso = new BirtReportDatasourceDefinition();
		return poso;
	}

	public BirtReportDatasourceDefinition createPoso(BirtReportDatasourceDefinitionDto dto)  throws ExpectedException {
		BirtReportDatasourceDefinition poso = new BirtReportDatasourceDefinition();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public BirtReportDatasourceDefinition createUnmanagedPoso(BirtReportDatasourceDefinitionDto dto)  throws ExpectedException {
		BirtReportDatasourceDefinition poso = new BirtReportDatasourceDefinition();

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

	public void mergePoso(BirtReportDatasourceDefinitionDto dto, final BirtReportDatasourceDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(BirtReportDatasourceDefinitionDto dto, final BirtReportDatasourceDefinition poso)  throws ExpectedException {
		/*  set databaseCache */
		try{
			poso.setDatabaseCache(dto.getDatabaseCache() );
		} catch(NullPointerException e){
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(BirtReportDatasourceDefinitionDto dto, final BirtReportDatasourceDefinition poso)  throws ExpectedException {
		/*  set databaseCache */
		if(dto.isDatabaseCacheModified()){
			try{
				poso.setDatabaseCache(dto.getDatabaseCache() );
			} catch(NullPointerException e){
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

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(BirtReportDatasourceDefinitionDto dto, final BirtReportDatasourceDefinition poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(BirtReportDatasourceDefinitionDto dto, final BirtReportDatasourceDefinition poso)  throws ExpectedException {
		/*  set databaseCache */
		try{
			poso.setDatabaseCache(dto.getDatabaseCache() );
		} catch(NullPointerException e){
		}

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(BirtReportDatasourceDefinitionDto dto, final BirtReportDatasourceDefinition poso)  throws ExpectedException {
		/*  set databaseCache */
		if(dto.isDatabaseCacheModified()){
			try{
				poso.setDatabaseCache(dto.getDatabaseCache() );
			} catch(NullPointerException e){
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

		/*  set key */
		if(dto.isKeyModified()){
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public BirtReportDatasourceDefinition loadAndMergePoso(BirtReportDatasourceDefinitionDto dto)  throws ExpectedException {
		BirtReportDatasourceDefinition poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(BirtReportDatasourceDefinitionDto dto, BirtReportDatasourceDefinition poso)  {
	}


	public void postProcessCreateUnmanaged(BirtReportDatasourceDefinitionDto dto, BirtReportDatasourceDefinition poso)  {
	}


	public void postProcessLoad(BirtReportDatasourceDefinitionDto dto, BirtReportDatasourceDefinition poso)  {
	}


	public void postProcessMerge(BirtReportDatasourceDefinitionDto dto, BirtReportDatasourceDefinition poso)  {
	}


	public void postProcessInstantiate(BirtReportDatasourceDefinition poso)  {
	}


	public boolean validateKeyProperty(BirtReportDatasourceDefinitionDto dto, BirtReportDatasourceDefinition poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]*$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
