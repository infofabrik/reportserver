package net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen;

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
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceFolderDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceFolder;
import net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.Dto2DatasourceFolderGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for DatasourceFolder
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2DatasourceFolderGenerator implements Dto2PosoGenerator<DatasourceFolderDto,DatasourceFolder> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2DatasourceFolderGenerator(
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

	public DatasourceFolder loadPoso(DatasourceFolderDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		DatasourceFolder poso = entityManager.find(DatasourceFolder.class, id);
		return poso;
	}

	public DatasourceFolder instantiatePoso()  {
		DatasourceFolder poso = new DatasourceFolder();
		return poso;
	}

	public DatasourceFolder createPoso(DatasourceFolderDto dto)  throws ExpectedException {
		DatasourceFolder poso = new DatasourceFolder();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public DatasourceFolder createUnmanagedPoso(DatasourceFolderDto dto)  throws ExpectedException {
		DatasourceFolder poso = new DatasourceFolder();

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

	public void mergePoso(DatasourceFolderDto dto, final DatasourceFolder poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(DatasourceFolderDto dto, final DatasourceFolder poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(DatasourceFolderDto dto, final DatasourceFolder poso)  throws ExpectedException {
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

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(DatasourceFolderDto dto, final DatasourceFolder poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(DatasourceFolderDto dto, final DatasourceFolder poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(DatasourceFolderDto dto, final DatasourceFolder poso)  throws ExpectedException {
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

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public DatasourceFolder loadAndMergePoso(DatasourceFolderDto dto)  throws ExpectedException {
		DatasourceFolder poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(DatasourceFolderDto dto, DatasourceFolder poso)  {
	}


	public void postProcessCreateUnmanaged(DatasourceFolderDto dto, DatasourceFolder poso)  {
	}


	public void postProcessLoad(DatasourceFolderDto dto, DatasourceFolder poso)  {
	}


	public void postProcessMerge(DatasourceFolderDto dto, DatasourceFolder poso)  {
	}


	public void postProcessInstantiate(DatasourceFolder poso)  {
	}



}
