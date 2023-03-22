package net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.dtogen;

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
import net.datenwerke.rs.localfsdatasink.client.localfsdatasink.dto.LocalFileSystemDatasinkDto;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.LocalFileSystemDatasink;
import net.datenwerke.rs.localfsdatasink.service.localfsdatasink.definitions.dtogen.Dto2LocalFileSystemDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for LocalFileSystemDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2LocalFileSystemDatasinkGenerator implements Dto2PosoGenerator<LocalFileSystemDatasinkDto,LocalFileSystemDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2LocalFileSystemDatasinkGenerator(
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

	public LocalFileSystemDatasink loadPoso(LocalFileSystemDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		LocalFileSystemDatasink poso = entityManager.find(LocalFileSystemDatasink.class, id);
		return poso;
	}

	public LocalFileSystemDatasink instantiatePoso()  {
		LocalFileSystemDatasink poso = new LocalFileSystemDatasink();
		return poso;
	}

	public LocalFileSystemDatasink createPoso(LocalFileSystemDatasinkDto dto)  throws ExpectedException {
		LocalFileSystemDatasink poso = new LocalFileSystemDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public LocalFileSystemDatasink createUnmanagedPoso(LocalFileSystemDatasinkDto dto)  throws ExpectedException {
		LocalFileSystemDatasink poso = new LocalFileSystemDatasink();

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

	public void mergePoso(LocalFileSystemDatasinkDto dto, final LocalFileSystemDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(LocalFileSystemDatasinkDto dto, final LocalFileSystemDatasink poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set folder */
		poso.setFolder(dto.getFolder() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set path */
		poso.setPath(dto.getPath() );

	}

	protected void mergeProxy2Poso(LocalFileSystemDatasinkDto dto, final LocalFileSystemDatasink poso)  throws ExpectedException {
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

		/*  set folder */
		if(dto.isFolderModified()){
			poso.setFolder(dto.getFolder() );
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

		/*  set path */
		if(dto.isPathModified()){
			poso.setPath(dto.getPath() );
		}

	}

	public void mergeUnmanagedPoso(LocalFileSystemDatasinkDto dto, final LocalFileSystemDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(LocalFileSystemDatasinkDto dto, final LocalFileSystemDatasink poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set folder */
		poso.setFolder(dto.getFolder() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set path */
		poso.setPath(dto.getPath() );

	}

	protected void mergeProxy2UnmanagedPoso(LocalFileSystemDatasinkDto dto, final LocalFileSystemDatasink poso)  throws ExpectedException {
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

		/*  set folder */
		if(dto.isFolderModified()){
			poso.setFolder(dto.getFolder() );
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

		/*  set path */
		if(dto.isPathModified()){
			poso.setPath(dto.getPath() );
		}

	}

	public LocalFileSystemDatasink loadAndMergePoso(LocalFileSystemDatasinkDto dto)  throws ExpectedException {
		LocalFileSystemDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(LocalFileSystemDatasinkDto dto, LocalFileSystemDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(LocalFileSystemDatasinkDto dto, LocalFileSystemDatasink poso)  {
	}


	public void postProcessLoad(LocalFileSystemDatasinkDto dto, LocalFileSystemDatasink poso)  {
	}


	public void postProcessMerge(LocalFileSystemDatasinkDto dto, LocalFileSystemDatasink poso)  {
	}


	public void postProcessInstantiate(LocalFileSystemDatasink poso)  {
	}


	public boolean validateKeyProperty(LocalFileSystemDatasinkDto dto, LocalFileSystemDatasink poso)  throws ExpectedException {
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
