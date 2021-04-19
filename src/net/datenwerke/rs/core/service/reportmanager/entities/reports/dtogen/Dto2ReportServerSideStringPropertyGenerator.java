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
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportServerSideStringProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.Dto2ReportServerSideStringPropertyGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ReportServerSideStringProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ReportServerSideStringPropertyGenerator implements Dto2PosoGenerator<ReportServerSideStringPropertyDto,ReportServerSideStringProperty> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ReportServerSideStringPropertyGenerator(
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

	public ReportServerSideStringProperty loadPoso(ReportServerSideStringPropertyDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ReportServerSideStringProperty poso = entityManager.find(ReportServerSideStringProperty.class, id);
		return poso;
	}

	public ReportServerSideStringProperty instantiatePoso()  {
		ReportServerSideStringProperty poso = new ReportServerSideStringProperty();
		return poso;
	}

	public ReportServerSideStringProperty createPoso(ReportServerSideStringPropertyDto dto)  throws ExpectedException {
		ReportServerSideStringProperty poso = new ReportServerSideStringProperty();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ReportServerSideStringProperty createUnmanagedPoso(ReportServerSideStringPropertyDto dto)  throws ExpectedException {
		ReportServerSideStringProperty poso = new ReportServerSideStringProperty();

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

	public void mergePoso(ReportServerSideStringPropertyDto dto, final ReportServerSideStringProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ReportServerSideStringPropertyDto dto, final ReportServerSideStringProperty poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set strValue */
		poso.setStrValue(dto.getStrValue() );

	}

	protected void mergeProxy2Poso(ReportServerSideStringPropertyDto dto, final ReportServerSideStringProperty poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set strValue */
		if(dto.isStrValueModified()){
			poso.setStrValue(dto.getStrValue() );
		}

	}

	public void mergeUnmanagedPoso(ReportServerSideStringPropertyDto dto, final ReportServerSideStringProperty poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ReportServerSideStringPropertyDto dto, final ReportServerSideStringProperty poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

		/*  set strValue */
		poso.setStrValue(dto.getStrValue() );

	}

	protected void mergeProxy2UnmanagedPoso(ReportServerSideStringPropertyDto dto, final ReportServerSideStringProperty poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set strValue */
		if(dto.isStrValueModified()){
			poso.setStrValue(dto.getStrValue() );
		}

	}

	public ReportServerSideStringProperty loadAndMergePoso(ReportServerSideStringPropertyDto dto)  throws ExpectedException {
		ReportServerSideStringProperty poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ReportServerSideStringPropertyDto dto, ReportServerSideStringProperty poso)  {
	}


	public void postProcessCreateUnmanaged(ReportServerSideStringPropertyDto dto, ReportServerSideStringProperty poso)  {
	}


	public void postProcessLoad(ReportServerSideStringPropertyDto dto, ReportServerSideStringProperty poso)  {
	}


	public void postProcessMerge(ReportServerSideStringPropertyDto dto, ReportServerSideStringProperty poso)  {
	}


	public void postProcessInstantiate(ReportServerSideStringProperty poso)  {
	}



}
