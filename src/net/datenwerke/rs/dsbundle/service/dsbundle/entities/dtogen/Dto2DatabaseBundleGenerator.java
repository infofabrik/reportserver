package net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.IllegalArgumentException;
import java.lang.NullPointerException;
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
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleDto;
import net.datenwerke.rs.dsbundle.client.dsbundle.dto.DatabaseBundleEntryDto;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundle;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.DatabaseBundleEntry;
import net.datenwerke.rs.dsbundle.service.dsbundle.entities.dtogen.Dto2DatabaseBundleGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatabaseBundle
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatabaseBundleGenerator implements Dto2PosoGenerator<DatabaseBundleDto,DatabaseBundle> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatabaseBundleGenerator(
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

	public DatabaseBundle loadPoso(DatabaseBundleDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatabaseBundle poso = entityManager.find(DatabaseBundle.class, id);
		return poso;
	}

	public DatabaseBundle instantiatePoso()  {
		DatabaseBundle poso = new DatabaseBundle();
		return poso;
	}

	public DatabaseBundle createPoso(DatabaseBundleDto dto)  throws ExpectedException {
		DatabaseBundle poso = new DatabaseBundle();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatabaseBundle createUnmanagedPoso(DatabaseBundleDto dto)  throws ExpectedException {
		DatabaseBundle poso = new DatabaseBundle();

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

	public void mergePoso(DatabaseBundleDto dto, final DatabaseBundle poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatabaseBundleDto dto, final DatabaseBundle poso)  throws ExpectedException {
		/*  set bundleEntries */
		final Set<DatabaseBundleEntry> col_bundleEntries = new HashSet<DatabaseBundleEntry>();
		/* load new data from dto */
		Collection<DatabaseBundleEntryDto> tmpCol_bundleEntries = dto.getBundleEntries();

		/* load current data from poso */
		if(null != poso.getBundleEntries())
			col_bundleEntries.addAll(poso.getBundleEntries());

		/* remove non existing data */
		Set<DatabaseBundleEntry> remDto_bundleEntries = new HashSet<DatabaseBundleEntry>();
		for(DatabaseBundleEntry ref : col_bundleEntries){
			boolean found = false;
			for(DatabaseBundleEntryDto refDto : tmpCol_bundleEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					found = true;
					break;
				}
			}
			if(! found)
				remDto_bundleEntries.add(ref);
		}
		for(DatabaseBundleEntry ref : remDto_bundleEntries)
			col_bundleEntries.remove(ref);
		dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_bundleEntries, "bundleEntries");

		/* merge or add data */
		Set<DatabaseBundleEntry> new_col_bundleEntries = new HashSet<DatabaseBundleEntry>();
		for(DatabaseBundleEntryDto refDto : tmpCol_bundleEntries){
			boolean found = false;
			for(DatabaseBundleEntry ref : col_bundleEntries){
				if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
					new_col_bundleEntries.add((DatabaseBundleEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
					found = true;
					break;
				}
			}
			if(! found && null != refDto && null == refDto.getId() )
				new_col_bundleEntries.add((DatabaseBundleEntry) dtoServiceProvider.get().createPoso(refDto));
			else if(! found && null != refDto && null != refDto.getId() )
				throw new IllegalArgumentException("dto should not have an id. property(bundleEntries) ");
		}
		poso.setBundleEntries(new_col_bundleEntries);

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

		/*  set keySource */
		poso.setKeySource(dto.getKeySource() );

		/*  set keySourceParamName */
		poso.setKeySourceParamName(dto.getKeySourceParamName() );

		/*  set mappingSource */
		poso.setMappingSource(dto.getMappingSource() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2Poso(DatabaseBundleDto dto, final DatabaseBundle poso)  throws ExpectedException {
		/*  set bundleEntries */
		if(dto.isBundleEntriesModified()){
			final Set<DatabaseBundleEntry> col_bundleEntries = new HashSet<DatabaseBundleEntry>();
			/* load new data from dto */
			Collection<DatabaseBundleEntryDto> tmpCol_bundleEntries = null;
			tmpCol_bundleEntries = dto.getBundleEntries();

			/* load current data from poso */
			if(null != poso.getBundleEntries())
				col_bundleEntries.addAll(poso.getBundleEntries());

			/* remove non existing data */
			Set<DatabaseBundleEntry> remDto_bundleEntries = new HashSet<DatabaseBundleEntry>();
			for(DatabaseBundleEntry ref : col_bundleEntries){
				boolean found = false;
				for(DatabaseBundleEntryDto refDto : tmpCol_bundleEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						found = true;
						break;
					}
				}
				if(! found)
					remDto_bundleEntries.add(ref);
			}
			for(DatabaseBundleEntry ref : remDto_bundleEntries)
				col_bundleEntries.remove(ref);
			dto2PosoSupervisor.enclosedObjectsRemovedFromCollection(dto, poso, remDto_bundleEntries, "bundleEntries");

			/* merge or add data */
			Set<DatabaseBundleEntry> new_col_bundleEntries = new HashSet<DatabaseBundleEntry>();
			for(DatabaseBundleEntryDto refDto : tmpCol_bundleEntries){
				boolean found = false;
				for(DatabaseBundleEntry ref : col_bundleEntries){
					if(null != refDto && null != refDto.getId() && refDto.getId().equals(ref.getId())){
						new_col_bundleEntries.add((DatabaseBundleEntry) dtoServiceProvider.get().loadAndMergePoso(refDto));
						found = true;
						break;
					}
				}
				if(! found && null != refDto && null == refDto.getId() )
					new_col_bundleEntries.add((DatabaseBundleEntry) dtoServiceProvider.get().createPoso(refDto));
				else if(! found && null != refDto && null != refDto.getId() )
					throw new IllegalArgumentException("dto should not have an id. property(bundleEntries) ");
			}
			poso.setBundleEntries(new_col_bundleEntries);
		}

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

		/*  set keySource */
		if(dto.isKeySourceModified()){
			poso.setKeySource(dto.getKeySource() );
		}

		/*  set keySourceParamName */
		if(dto.isKeySourceParamNameModified()){
			poso.setKeySourceParamName(dto.getKeySourceParamName() );
		}

		/*  set mappingSource */
		if(dto.isMappingSourceModified()){
			poso.setMappingSource(dto.getMappingSource() );
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

	public void mergeUnmanagedPoso(DatabaseBundleDto dto, final DatabaseBundle poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatabaseBundleDto dto, final DatabaseBundle poso)  throws ExpectedException {
		/*  set bundleEntries */
		final Set<DatabaseBundleEntry> col_bundleEntries = new HashSet<DatabaseBundleEntry>();
		/* load new data from dto */
		Collection<DatabaseBundleEntryDto> tmpCol_bundleEntries = dto.getBundleEntries();

		/* merge or add data */
		for(DatabaseBundleEntryDto refDto : tmpCol_bundleEntries){
			col_bundleEntries.add((DatabaseBundleEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
		}
		poso.setBundleEntries(col_bundleEntries);

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

		/*  set keySource */
		poso.setKeySource(dto.getKeySource() );

		/*  set keySourceParamName */
		poso.setKeySourceParamName(dto.getKeySourceParamName() );

		/*  set mappingSource */
		poso.setMappingSource(dto.getMappingSource() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set password */
		poso.setPassword(dto.getPassword() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

		/*  set username */
		poso.setUsername(dto.getUsername() );

	}

	protected void mergeProxy2UnmanagedPoso(DatabaseBundleDto dto, final DatabaseBundle poso)  throws ExpectedException {
		/*  set bundleEntries */
		if(dto.isBundleEntriesModified()){
			final Set<DatabaseBundleEntry> col_bundleEntries = new HashSet<DatabaseBundleEntry>();
			/* load new data from dto */
			Collection<DatabaseBundleEntryDto> tmpCol_bundleEntries = null;
			tmpCol_bundleEntries = dto.getBundleEntries();

			/* merge or add data */
			for(DatabaseBundleEntryDto refDto : tmpCol_bundleEntries){
				col_bundleEntries.add((DatabaseBundleEntry) dtoServiceProvider.get().createUnmanagedPoso(refDto));
			}
			poso.setBundleEntries(col_bundleEntries);
		}

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

		/*  set keySource */
		if(dto.isKeySourceModified()){
			poso.setKeySource(dto.getKeySource() );
		}

		/*  set keySourceParamName */
		if(dto.isKeySourceParamNameModified()){
			poso.setKeySourceParamName(dto.getKeySourceParamName() );
		}

		/*  set mappingSource */
		if(dto.isMappingSourceModified()){
			poso.setMappingSource(dto.getMappingSource() );
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

	public DatabaseBundle loadAndMergePoso(DatabaseBundleDto dto)  throws ExpectedException {
		DatabaseBundle poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatabaseBundleDto dto, DatabaseBundle poso)  {
	}


	public void postProcessCreateUnmanaged(DatabaseBundleDto dto, DatabaseBundle poso)  {
	}


	public void postProcessLoad(DatabaseBundleDto dto, DatabaseBundle poso)  {
	}


	public void postProcessMerge(DatabaseBundleDto dto, DatabaseBundle poso)  {
	}


	public void postProcessInstantiate(DatabaseBundle poso)  {
	}


	public boolean validateKeyProperty(DatabaseBundleDto dto, DatabaseBundle poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]+$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
