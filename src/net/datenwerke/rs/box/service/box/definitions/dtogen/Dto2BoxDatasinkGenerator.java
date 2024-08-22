package net.datenwerke.rs.box.service.box.definitions.dtogen;

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
import net.datenwerke.rs.box.client.box.dto.BoxDatasinkDto;
import net.datenwerke.rs.box.service.box.definitions.BoxDatasink;
import net.datenwerke.rs.box.service.box.definitions.dtogen.Dto2BoxDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for BoxDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BoxDatasinkGenerator implements Dto2PosoGenerator<BoxDatasinkDto,BoxDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2BoxDatasinkGenerator(
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

	public BoxDatasink loadPoso(BoxDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		BoxDatasink poso = entityManager.find(BoxDatasink.class, id);
		return poso;
	}

	public BoxDatasink instantiatePoso()  {
		BoxDatasink poso = new BoxDatasink();
		return poso;
	}

	public BoxDatasink createPoso(BoxDatasinkDto dto)  throws ExpectedException {
		BoxDatasink poso = new BoxDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public BoxDatasink createUnmanagedPoso(BoxDatasinkDto dto)  throws ExpectedException {
		BoxDatasink poso = new BoxDatasink();

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

	public void mergePoso(BoxDatasinkDto dto, final BoxDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(BoxDatasinkDto dto, final BoxDatasink poso)  throws ExpectedException {
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

	protected void mergeProxy2Poso(BoxDatasinkDto dto, final BoxDatasink poso)  throws ExpectedException {
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

	public void mergeUnmanagedPoso(BoxDatasinkDto dto, final BoxDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(BoxDatasinkDto dto, final BoxDatasink poso)  throws ExpectedException {
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

	protected void mergeProxy2UnmanagedPoso(BoxDatasinkDto dto, final BoxDatasink poso)  throws ExpectedException {
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

	public BoxDatasink loadAndMergePoso(BoxDatasinkDto dto)  throws ExpectedException {
		BoxDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(BoxDatasinkDto dto, BoxDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(BoxDatasinkDto dto, BoxDatasink poso)  {
	}


	public void postProcessLoad(BoxDatasinkDto dto, BoxDatasink poso)  {
	}


	public void postProcessMerge(BoxDatasinkDto dto, BoxDatasink poso)  {
	}


	public void postProcessInstantiate(BoxDatasink poso)  {
	}


	public boolean validateKeyProperty(BoxDatasinkDto dto, BoxDatasink poso)  throws ExpectedException {
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
