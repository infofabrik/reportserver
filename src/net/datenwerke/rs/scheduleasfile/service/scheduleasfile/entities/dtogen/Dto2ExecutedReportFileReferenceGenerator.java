package net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.dtogen;

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
import net.datenwerke.rs.scheduleasfile.client.scheduleasfile.dto.ExecutedReportFileReferenceDto;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.ExecutedReportFileReference;
import net.datenwerke.rs.scheduleasfile.service.scheduleasfile.entities.dtogen.Dto2ExecutedReportFileReferenceGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ExecutedReportFileReference
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ExecutedReportFileReferenceGenerator implements Dto2PosoGenerator<ExecutedReportFileReferenceDto,ExecutedReportFileReference> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ExecutedReportFileReferenceGenerator(
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

	public ExecutedReportFileReference loadPoso(ExecutedReportFileReferenceDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ExecutedReportFileReference poso = entityManager.find(ExecutedReportFileReference.class, id);
		return poso;
	}

	public ExecutedReportFileReference instantiatePoso()  {
		ExecutedReportFileReference poso = new ExecutedReportFileReference();
		return poso;
	}

	public ExecutedReportFileReference createPoso(ExecutedReportFileReferenceDto dto)  throws ExpectedException {
		ExecutedReportFileReference poso = new ExecutedReportFileReference();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ExecutedReportFileReference createUnmanagedPoso(ExecutedReportFileReferenceDto dto)  throws ExpectedException {
		ExecutedReportFileReference poso = new ExecutedReportFileReference();

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

	public void mergePoso(ExecutedReportFileReferenceDto dto, final ExecutedReportFileReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ExecutedReportFileReferenceDto dto, final ExecutedReportFileReference poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set outputFormat */
		poso.setOutputFormat(dto.getOutputFormat() );

	}

	protected void mergeProxy2Poso(ExecutedReportFileReferenceDto dto, final ExecutedReportFileReference poso)  throws ExpectedException {
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

		/*  set outputFormat */
		if(dto.isOutputFormatModified()){
			poso.setOutputFormat(dto.getOutputFormat() );
		}

	}

	public void mergeUnmanagedPoso(ExecutedReportFileReferenceDto dto, final ExecutedReportFileReference poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ExecutedReportFileReferenceDto dto, final ExecutedReportFileReference poso)  throws ExpectedException {
		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set flags */
		try{
			poso.setFlags(dto.getFlags() );
		} catch(NullPointerException e){
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set outputFormat */
		poso.setOutputFormat(dto.getOutputFormat() );

	}

	protected void mergeProxy2UnmanagedPoso(ExecutedReportFileReferenceDto dto, final ExecutedReportFileReference poso)  throws ExpectedException {
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

		/*  set outputFormat */
		if(dto.isOutputFormatModified()){
			poso.setOutputFormat(dto.getOutputFormat() );
		}

	}

	public ExecutedReportFileReference loadAndMergePoso(ExecutedReportFileReferenceDto dto)  throws ExpectedException {
		ExecutedReportFileReference poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ExecutedReportFileReferenceDto dto, ExecutedReportFileReference poso)  {
	}


	public void postProcessCreateUnmanaged(ExecutedReportFileReferenceDto dto, ExecutedReportFileReference poso)  {
	}


	public void postProcessLoad(ExecutedReportFileReferenceDto dto, ExecutedReportFileReference poso)  {
	}


	public void postProcessMerge(ExecutedReportFileReferenceDto dto, ExecutedReportFileReference poso)  {
	}


	public void postProcessInstantiate(ExecutedReportFileReference poso)  {
	}



}
