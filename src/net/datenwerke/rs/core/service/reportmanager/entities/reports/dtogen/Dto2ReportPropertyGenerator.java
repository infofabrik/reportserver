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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportPropertyDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportPropertyGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ReportProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ReportPropertyGenerator implements Dto2PosoGenerator<ReportPropertyDto,ReportProperty> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ReportPropertyGenerator(
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

	public ReportProperty loadPoso(ReportPropertyDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ReportProperty poso = entityManager.find(ReportProperty.class, id);
		return poso;
	}

	public ReportProperty instantiatePoso()  {
		ReportProperty poso = new ReportProperty();
		return poso;
	}

	public ReportProperty createPoso(ReportPropertyDto dto)  throws ExpectedException {
		ReportProperty poso = new ReportProperty();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ReportProperty createUnmanagedPoso(ReportPropertyDto dto)  throws ExpectedException {
		ReportProperty poso = new ReportProperty();

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

	public void mergePoso(ReportPropertyDto dto, final ReportProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ReportPropertyDto dto, final ReportProperty poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(ReportPropertyDto dto, final ReportProperty poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(ReportPropertyDto dto, final ReportProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ReportPropertyDto dto, final ReportProperty poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(ReportPropertyDto dto, final ReportProperty poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public ReportProperty loadAndMergePoso(ReportPropertyDto dto)  throws ExpectedException {
		ReportProperty poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ReportPropertyDto dto, ReportProperty poso)  {
	}


	public void postProcessCreateUnmanaged(ReportPropertyDto dto, ReportProperty poso)  {
	}


	public void postProcessLoad(ReportPropertyDto dto, ReportProperty poso)  {
	}


	public void postProcessMerge(ReportPropertyDto dto, ReportProperty poso)  {
	}


	public void postProcessInstantiate(ReportProperty poso)  {
	}



}
