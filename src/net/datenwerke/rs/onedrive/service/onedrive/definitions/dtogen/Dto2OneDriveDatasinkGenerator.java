package net.datenwerke.rs.onedrive.service.onedrive.definitions.dtogen;

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
import net.datenwerke.rs.onedrive.client.onedrive.dto.OneDriveDatasinkDto;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.OneDriveDatasink;
import net.datenwerke.rs.onedrive.service.onedrive.definitions.dtogen.Dto2OneDriveDatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for OneDriveDatasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2OneDriveDatasinkGenerator implements Dto2PosoGenerator<OneDriveDatasinkDto,OneDriveDatasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2OneDriveDatasinkGenerator(
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

	public OneDriveDatasink loadPoso(OneDriveDatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		OneDriveDatasink poso = entityManager.find(OneDriveDatasink.class, id);
		return poso;
	}

	public OneDriveDatasink instantiatePoso()  {
		OneDriveDatasink poso = new OneDriveDatasink();
		return poso;
	}

	public OneDriveDatasink createPoso(OneDriveDatasinkDto dto)  throws ExpectedException {
		OneDriveDatasink poso = new OneDriveDatasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public OneDriveDatasink createUnmanagedPoso(OneDriveDatasinkDto dto)  throws ExpectedException {
		OneDriveDatasink poso = new OneDriveDatasink();

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

	public void mergePoso(OneDriveDatasinkDto dto, final OneDriveDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(OneDriveDatasinkDto dto, final OneDriveDatasink poso)  throws ExpectedException {
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

		/*  set name */
		poso.setName(dto.getName() );

		/*  set refreshToken */
		poso.setRefreshToken(dto.getRefreshToken() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

		/*  set tenantId */
		poso.setTenantId(dto.getTenantId() );

	}

	protected void mergeProxy2Poso(OneDriveDatasinkDto dto, final OneDriveDatasink poso)  throws ExpectedException {
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

		/*  set tenantId */
		if(dto.isTenantIdModified()){
			poso.setTenantId(dto.getTenantId() );
		}

	}

	public void mergeUnmanagedPoso(OneDriveDatasinkDto dto, final OneDriveDatasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(OneDriveDatasinkDto dto, final OneDriveDatasink poso)  throws ExpectedException {
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

		/*  set name */
		poso.setName(dto.getName() );

		/*  set refreshToken */
		poso.setRefreshToken(dto.getRefreshToken() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

		/*  set tenantId */
		poso.setTenantId(dto.getTenantId() );

	}

	protected void mergeProxy2UnmanagedPoso(OneDriveDatasinkDto dto, final OneDriveDatasink poso)  throws ExpectedException {
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

		/*  set tenantId */
		if(dto.isTenantIdModified()){
			poso.setTenantId(dto.getTenantId() );
		}

	}

	public OneDriveDatasink loadAndMergePoso(OneDriveDatasinkDto dto)  throws ExpectedException {
		OneDriveDatasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(OneDriveDatasinkDto dto, OneDriveDatasink poso)  {
	}


	public void postProcessCreateUnmanaged(OneDriveDatasinkDto dto, OneDriveDatasink poso)  {
	}


	public void postProcessLoad(OneDriveDatasinkDto dto, OneDriveDatasink poso)  {
	}


	public void postProcessMerge(OneDriveDatasinkDto dto, OneDriveDatasink poso)  {
	}


	public void postProcessInstantiate(OneDriveDatasink poso)  {
	}



}
