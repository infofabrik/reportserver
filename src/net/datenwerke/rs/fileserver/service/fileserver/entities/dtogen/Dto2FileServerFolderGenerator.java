package net.datenwerke.rs.fileserver.service.fileserver.entities.dtogen;

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
import net.datenwerke.rs.fileserver.client.fileserver.dto.FileServerFolderDto;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.entities.dtogen.Dto2FileServerFolderGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for FileServerFolder
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2FileServerFolderGenerator implements Dto2PosoGenerator<FileServerFolderDto,FileServerFolder> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2FileServerFolderGenerator(
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

	public FileServerFolder loadPoso(FileServerFolderDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		FileServerFolder poso = entityManager.find(FileServerFolder.class, id);
		return poso;
	}

	public FileServerFolder instantiatePoso()  {
		FileServerFolder poso = new FileServerFolder();
		return poso;
	}

	public FileServerFolder createPoso(FileServerFolderDto dto)  throws ExpectedException {
		FileServerFolder poso = new FileServerFolder();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public FileServerFolder createUnmanagedPoso(FileServerFolderDto dto)  throws ExpectedException {
		FileServerFolder poso = new FileServerFolder();

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

	public void mergePoso(FileServerFolderDto dto, final FileServerFolder poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(FileServerFolderDto dto, final FileServerFolder poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set publiclyAccessible */
		try{
			poso.setPubliclyAccessible(dto.isPubliclyAccessible() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2Poso(FileServerFolderDto dto, final FileServerFolder poso)  throws ExpectedException {
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

		/*  set publiclyAccessible */
		if(dto.isPubliclyAccessibleModified()){
			try{
				poso.setPubliclyAccessible(dto.isPubliclyAccessible() );
			} catch(NullPointerException e){
			}
		}

	}

	public void mergeUnmanagedPoso(FileServerFolderDto dto, final FileServerFolder poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(FileServerFolderDto dto, final FileServerFolder poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set publiclyAccessible */
		try{
			poso.setPubliclyAccessible(dto.isPubliclyAccessible() );
		} catch(NullPointerException e){
		}

	}

	protected void mergeProxy2UnmanagedPoso(FileServerFolderDto dto, final FileServerFolder poso)  throws ExpectedException {
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

		/*  set publiclyAccessible */
		if(dto.isPubliclyAccessibleModified()){
			try{
				poso.setPubliclyAccessible(dto.isPubliclyAccessible() );
			} catch(NullPointerException e){
			}
		}

	}

	public FileServerFolder loadAndMergePoso(FileServerFolderDto dto)  throws ExpectedException {
		FileServerFolder poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(FileServerFolderDto dto, FileServerFolder poso)  {
	}


	public void postProcessCreateUnmanaged(FileServerFolderDto dto, FileServerFolder poso)  {
	}


	public void postProcessLoad(FileServerFolderDto dto, FileServerFolder poso)  {
	}


	public void postProcessMerge(FileServerFolderDto dto, FileServerFolder poso)  {
	}


	public void postProcessInstantiate(FileServerFolder poso)  {
	}



}
