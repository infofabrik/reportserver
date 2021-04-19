package net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen;

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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportMetadataDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportMetadata;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportMetadataGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ReportMetadata
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ReportMetadataGenerator implements Dto2PosoGenerator<ReportMetadataDto,ReportMetadata> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ReportMetadataGenerator(
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

	public ReportMetadata loadPoso(ReportMetadataDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ReportMetadata poso = entityManager.find(ReportMetadata.class, id);
		return poso;
	}

	public ReportMetadata instantiatePoso()  {
		ReportMetadata poso = new ReportMetadata();
		return poso;
	}

	public ReportMetadata createPoso(ReportMetadataDto dto)  throws ExpectedException {
		ReportMetadata poso = new ReportMetadata();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ReportMetadata createUnmanagedPoso(ReportMetadataDto dto)  throws ExpectedException {
		ReportMetadata poso = new ReportMetadata();

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

	public void mergePoso(ReportMetadataDto dto, final ReportMetadata poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ReportMetadataDto dto, final ReportMetadata poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2Poso(ReportMetadataDto dto, final ReportMetadata poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public void mergeUnmanagedPoso(ReportMetadataDto dto, final ReportMetadata poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ReportMetadataDto dto, final ReportMetadata poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set value */
		poso.setValue(dto.getValue() );

	}

	protected void mergeProxy2UnmanagedPoso(ReportMetadataDto dto, final ReportMetadata poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set value */
		if(dto.isValueModified()){
			poso.setValue(dto.getValue() );
		}

	}

	public ReportMetadata loadAndMergePoso(ReportMetadataDto dto)  throws ExpectedException {
		ReportMetadata poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ReportMetadataDto dto, ReportMetadata poso)  {
	}


	public void postProcessCreateUnmanaged(ReportMetadataDto dto, ReportMetadata poso)  {
	}


	public void postProcessLoad(ReportMetadataDto dto, ReportMetadata poso)  {
	}


	public void postProcessMerge(ReportMetadataDto dto, ReportMetadata poso)  {
	}


	public void postProcessInstantiate(ReportMetadata poso)  {
	}



}
