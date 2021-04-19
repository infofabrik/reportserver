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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportByteProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportBytePropertyGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ReportByteProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ReportBytePropertyGenerator implements Dto2PosoGenerator<ReportBytePropertyDto,ReportByteProperty> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ReportBytePropertyGenerator(
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

	public ReportByteProperty loadPoso(ReportBytePropertyDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ReportByteProperty poso = entityManager.find(ReportByteProperty.class, id);
		return poso;
	}

	public ReportByteProperty instantiatePoso()  {
		ReportByteProperty poso = new ReportByteProperty();
		return poso;
	}

	public ReportByteProperty createPoso(ReportBytePropertyDto dto)  throws ExpectedException {
		ReportByteProperty poso = new ReportByteProperty();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ReportByteProperty createUnmanagedPoso(ReportBytePropertyDto dto)  throws ExpectedException {
		ReportByteProperty poso = new ReportByteProperty();

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

	public void mergePoso(ReportBytePropertyDto dto, final ReportByteProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ReportBytePropertyDto dto, final ReportByteProperty poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(ReportBytePropertyDto dto, final ReportByteProperty poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(ReportBytePropertyDto dto, final ReportByteProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ReportBytePropertyDto dto, final ReportByteProperty poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(ReportBytePropertyDto dto, final ReportByteProperty poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public ReportByteProperty loadAndMergePoso(ReportBytePropertyDto dto)  throws ExpectedException {
		ReportByteProperty poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ReportBytePropertyDto dto, ReportByteProperty poso)  {
	}


	public void postProcessCreateUnmanaged(ReportBytePropertyDto dto, ReportByteProperty poso)  {
	}


	public void postProcessLoad(ReportBytePropertyDto dto, ReportByteProperty poso)  {
	}


	public void postProcessMerge(ReportBytePropertyDto dto, ReportByteProperty poso)  {
	}


	public void postProcessInstantiate(ReportByteProperty poso)  {
	}



}
