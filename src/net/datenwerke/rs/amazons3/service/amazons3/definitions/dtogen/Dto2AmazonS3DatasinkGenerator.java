package net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen;

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
import net.datenwerke.rs.amazons3.client.amazons3.dto.AmazonS3DatasinkDto;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.AmazonS3Datasink;
import net.datenwerke.rs.amazons3.service.amazons3.definitions.dtogen.Dto2AmazonS3DatasinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for AmazonS3Datasink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2AmazonS3DatasinkGenerator implements Dto2PosoGenerator<AmazonS3DatasinkDto,AmazonS3Datasink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2AmazonS3DatasinkGenerator(
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

	public AmazonS3Datasink loadPoso(AmazonS3DatasinkDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		AmazonS3Datasink poso = entityManager.find(AmazonS3Datasink.class, id);
		return poso;
	}

	public AmazonS3Datasink instantiatePoso()  {
		AmazonS3Datasink poso = new AmazonS3Datasink();
		return poso;
	}

	public AmazonS3Datasink createPoso(AmazonS3DatasinkDto dto)  throws ExpectedException {
		AmazonS3Datasink poso = new AmazonS3Datasink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public AmazonS3Datasink createUnmanagedPoso(AmazonS3DatasinkDto dto)  throws ExpectedException {
		AmazonS3Datasink poso = new AmazonS3Datasink();

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

	public void mergePoso(AmazonS3DatasinkDto dto, final AmazonS3Datasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(AmazonS3DatasinkDto dto, final AmazonS3Datasink poso)  throws ExpectedException {
		/*  set appKey */
		poso.setAppKey(dto.getAppKey() );

		/*  set bucketName */
		poso.setBucketName(dto.getBucketName() );

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

		/*  set regionName */
		poso.setRegionName(dto.getRegionName() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

	}

	protected void mergeProxy2Poso(AmazonS3DatasinkDto dto, final AmazonS3Datasink poso)  throws ExpectedException {
		/*  set appKey */
		if(dto.isAppKeyModified()){
			poso.setAppKey(dto.getAppKey() );
		}

		/*  set bucketName */
		if(dto.isBucketNameModified()){
			poso.setBucketName(dto.getBucketName() );
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

		/*  set regionName */
		if(dto.isRegionNameModified()){
			poso.setRegionName(dto.getRegionName() );
		}

		/*  set secretKey */
		if(dto.isSecretKeyModified()){
			poso.setSecretKey(dto.getSecretKey() );
		}

	}

	public void mergeUnmanagedPoso(AmazonS3DatasinkDto dto, final AmazonS3Datasink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(AmazonS3DatasinkDto dto, final AmazonS3Datasink poso)  throws ExpectedException {
		/*  set appKey */
		poso.setAppKey(dto.getAppKey() );

		/*  set bucketName */
		poso.setBucketName(dto.getBucketName() );

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

		/*  set regionName */
		poso.setRegionName(dto.getRegionName() );

		/*  set secretKey */
		poso.setSecretKey(dto.getSecretKey() );

	}

	protected void mergeProxy2UnmanagedPoso(AmazonS3DatasinkDto dto, final AmazonS3Datasink poso)  throws ExpectedException {
		/*  set appKey */
		if(dto.isAppKeyModified()){
			poso.setAppKey(dto.getAppKey() );
		}

		/*  set bucketName */
		if(dto.isBucketNameModified()){
			poso.setBucketName(dto.getBucketName() );
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

		/*  set regionName */
		if(dto.isRegionNameModified()){
			poso.setRegionName(dto.getRegionName() );
		}

		/*  set secretKey */
		if(dto.isSecretKeyModified()){
			poso.setSecretKey(dto.getSecretKey() );
		}

	}

	public AmazonS3Datasink loadAndMergePoso(AmazonS3DatasinkDto dto)  throws ExpectedException {
		AmazonS3Datasink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(AmazonS3DatasinkDto dto, AmazonS3Datasink poso)  {
	}


	public void postProcessCreateUnmanaged(AmazonS3DatasinkDto dto, AmazonS3Datasink poso)  {
	}


	public void postProcessLoad(AmazonS3DatasinkDto dto, AmazonS3Datasink poso)  {
	}


	public void postProcessMerge(AmazonS3DatasinkDto dto, AmazonS3Datasink poso)  {
	}


	public void postProcessInstantiate(AmazonS3Datasink poso)  {
	}



}
