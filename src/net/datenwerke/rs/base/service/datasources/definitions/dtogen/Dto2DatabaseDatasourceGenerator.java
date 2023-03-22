package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

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
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.Dto2DatabaseDatasourceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatabaseDatasource
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatabaseDatasourceGenerator implements Dto2PosoGenerator<DatabaseDatasourceDto,DatabaseDatasource> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatabaseDatasourceGenerator(
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

	public DatabaseDatasource loadPoso(DatabaseDatasourceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatabaseDatasource poso = entityManager.find(DatabaseDatasource.class, id);
		return poso;
	}

	public DatabaseDatasource instantiatePoso()  {
		DatabaseDatasource poso = new DatabaseDatasource();
		return poso;
	}

	public DatabaseDatasource createPoso(DatabaseDatasourceDto dto)  throws ExpectedException {
		DatabaseDatasource poso = new DatabaseDatasource();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatabaseDatasource createUnmanagedPoso(DatabaseDatasourceDto dto)  throws ExpectedException {
		DatabaseDatasource poso = new DatabaseDatasource();

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

	public void mergePoso(DatabaseDatasourceDto dto, final DatabaseDatasource poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatabaseDatasourceDto dto, final DatabaseDatasource poso)  throws ExpectedException {
		/*  set databaseDescriptor */
		poso.setDatabaseDescriptor(dto.getDatabaseDescriptor() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set jdbcProperties */
		poso.setJdbcProperties(dto.getJdbcProperties() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(DatabaseDatasourceDto dto, final DatabaseDatasource poso)  throws ExpectedException {
		/*  set databaseDescriptor */
		if(dto.isDatabaseDescriptorModified()){
			poso.setDatabaseDescriptor(dto.getDatabaseDescriptor() );
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

		/*  set jdbcProperties */
		if(dto.isJdbcPropertiesModified()){
			poso.setJdbcProperties(dto.getJdbcProperties() );
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

		/*  set password */
		if(dto.isPasswordModified()){
			poso.setPassword(dto.getPassword() );
		}

		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public void mergeUnmanagedPoso(DatabaseDatasourceDto dto, final DatabaseDatasource poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatabaseDatasourceDto dto, final DatabaseDatasource poso)  throws ExpectedException {
		/*  set databaseDescriptor */
		poso.setDatabaseDescriptor(dto.getDatabaseDescriptor() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set jdbcProperties */
		poso.setJdbcProperties(dto.getJdbcProperties() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(DatabaseDatasourceDto dto, final DatabaseDatasource poso)  throws ExpectedException {
		/*  set databaseDescriptor */
		if(dto.isDatabaseDescriptorModified()){
			poso.setDatabaseDescriptor(dto.getDatabaseDescriptor() );
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

		/*  set jdbcProperties */
		if(dto.isJdbcPropertiesModified()){
			poso.setJdbcProperties(dto.getJdbcProperties() );
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

		/*  set password */
		if(dto.isPasswordModified()){
			poso.setPassword(dto.getPassword() );
		}

		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

		/*  set username */
		if(dto.isUsernameModified()){
			poso.setUsername(dto.getUsername() );
		}

	}

	public DatabaseDatasource loadAndMergePoso(DatabaseDatasourceDto dto)  throws ExpectedException {
		DatabaseDatasource poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatabaseDatasourceDto dto, DatabaseDatasource poso)  {
	}


	public void postProcessCreateUnmanaged(DatabaseDatasourceDto dto, DatabaseDatasource poso)  {
	}


	public void postProcessLoad(DatabaseDatasourceDto dto, DatabaseDatasource poso)  {
	}


	public void postProcessMerge(DatabaseDatasourceDto dto, DatabaseDatasource poso)  {
	}


	public void postProcessInstantiate(DatabaseDatasource poso)  {
	}


	public boolean validateKeyProperty(DatabaseDatasourceDto dto, DatabaseDatasource poso)  throws ExpectedException {
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
