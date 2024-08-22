package net.datenwerke.rs.dropbox.service.dropbox.definitions.dtogen;

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
import net.datenwerke.rs.dropbox.client.dropbox.dto.DropboxDatasinkDto;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.DropboxDatasink;
import net.datenwerke.rs.dropbox.service.dropbox.definitions.dtogen.Dto2DropboxDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DropboxDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DropboxDatasinkGenerator implements Dto2PosoGenerator<DropboxDatasinkDto,DropboxDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DropboxDatasinkGenerator(
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

	public DropboxDatasink loadPoso(DropboxDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DropboxDatasink poso = entityManager.find(DropboxDatasink.class, id);
		return poso;
	}

	public DropboxDatasink instantiatePoso()  {
		DropboxDatasink poso = new DropboxDatasink();
		return poso;
	}

	public DropboxDatasink createPoso(DropboxDatasinkDto dto)  throws ExpectedException {
		DropboxDatasink poso = new DropboxDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DropboxDatasink createUnmanagedPoso(DropboxDatasinkDto dto)  throws ExpectedException {
		DropboxDatasink poso = new DropboxDatasink();

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

	public void mergePoso(DropboxDatasinkDto dto, final DropboxDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DropboxDatasinkDto dto, final DropboxDatasink poso)  throws ExpectedException {
		/*  set appKey */
		poso.setAppKey(dto.getAppKey() );

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

		/*  set refreshToken */
		poso.setRefreshToken(dto.getRefreshToken() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

	}

	protected void mergeProxy2Poso(DropboxDatasinkDto dto, final DropboxDatasink poso)  throws ExpectedException {
		/*  set appKey */
		if(dto.isAppKeyModified()){
			poso.setAppKey(dto.getAppKey() );
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

		/*  set refreshToken */
		if(dto.isRefreshTokenModified()){
			poso.setRefreshToken(dto.getRefreshToken() );
		}

		/*  set secretKey */
		if(dto.isSecretKeyModified()){
			poso.setSecretKey(dto.getSecretKey() );
		}

	}

	public void mergeUnmanagedPoso(DropboxDatasinkDto dto, final DropboxDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DropboxDatasinkDto dto, final DropboxDatasink poso)  throws ExpectedException {
		/*  set appKey */
		poso.setAppKey(dto.getAppKey() );

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

		/*  set refreshToken */
		poso.setRefreshToken(dto.getRefreshToken() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

	}

	protected void mergeProxy2UnmanagedPoso(DropboxDatasinkDto dto, final DropboxDatasink poso)  throws ExpectedException {
		/*  set appKey */
		if(dto.isAppKeyModified()){
			poso.setAppKey(dto.getAppKey() );
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

		/*  set refreshToken */
		if(dto.isRefreshTokenModified()){
			poso.setRefreshToken(dto.getRefreshToken() );
		}

		/*  set secretKey */
		if(dto.isSecretKeyModified()){
			poso.setSecretKey(dto.getSecretKey() );
		}

	}

	public DropboxDatasink loadAndMergePoso(DropboxDatasinkDto dto)  throws ExpectedException {
		DropboxDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DropboxDatasinkDto dto, DropboxDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(DropboxDatasinkDto dto, DropboxDatasink poso)  {
	}


	public void postProcessLoad(DropboxDatasinkDto dto, DropboxDatasink poso)  {
	}


	public void postProcessMerge(DropboxDatasinkDto dto, DropboxDatasink poso)  {
	}


	public void postProcessInstantiate(DropboxDatasink poso)  {
	}


	public boolean validateKeyProperty(DropboxDatasinkDto dto, DropboxDatasink poso)  throws ExpectedException {
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
