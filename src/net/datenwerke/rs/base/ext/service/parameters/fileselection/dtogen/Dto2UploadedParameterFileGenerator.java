package net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
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
import net.datenwerke.rs.base.ext.client.parameters.fileselection.dto.UploadedParameterFileDto;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.UploadedParameterFile;
import net.datenwerke.rs.base.ext.service.parameters.fileselection.dtogen.Dto2UploadedParameterFileGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for UploadedParameterFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2UploadedParameterFileGenerator implements Dto2PosoGenerator<UploadedParameterFileDto,UploadedParameterFile> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2UploadedParameterFileGenerator(
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

	public UploadedParameterFile loadPoso(UploadedParameterFileDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		UploadedParameterFile poso = entityManager.find(UploadedParameterFile.class, id);
		return poso;
	}

	public UploadedParameterFile instantiatePoso()  {
		UploadedParameterFile poso = new UploadedParameterFile();
		return poso;
	}

	public UploadedParameterFile createPoso(UploadedParameterFileDto dto)  throws ExpectedException {
		UploadedParameterFile poso = new UploadedParameterFile();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public UploadedParameterFile createUnmanagedPoso(UploadedParameterFileDto dto)  throws ExpectedException {
		UploadedParameterFile poso = new UploadedParameterFile();

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

	public void mergePoso(UploadedParameterFileDto dto, final UploadedParameterFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(UploadedParameterFileDto dto, final UploadedParameterFile poso)  throws ExpectedException {
	}

	protected void mergeProxy2Poso(UploadedParameterFileDto dto, final UploadedParameterFile poso)  throws ExpectedException {
	}

	public void mergeUnmanagedPoso(UploadedParameterFileDto dto, final UploadedParameterFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(UploadedParameterFileDto dto, final UploadedParameterFile poso)  throws ExpectedException {
	}

	protected void mergeProxy2UnmanagedPoso(UploadedParameterFileDto dto, final UploadedParameterFile poso)  throws ExpectedException {
	}

	public UploadedParameterFile loadAndMergePoso(UploadedParameterFileDto dto)  throws ExpectedException {
		UploadedParameterFile poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(UploadedParameterFileDto dto, UploadedParameterFile poso)  {
	}


	public void postProcessCreateUnmanaged(UploadedParameterFileDto dto, UploadedParameterFile poso)  {
	}


	public void postProcessLoad(UploadedParameterFileDto dto, UploadedParameterFile poso)  {
	}


	public void postProcessMerge(UploadedParameterFileDto dto, UploadedParameterFile poso)  {
	}


	public void postProcessInstantiate(UploadedParameterFile poso)  {
	}



}
