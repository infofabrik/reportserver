package net.datenwerke.rs.birt.service.reportengine.entities.dtogen;

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
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;
import net.datenwerke.rs.birt.service.reportengine.entities.dtogen.Dto2BirtReportFileGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for BirtReportFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2BirtReportFileGenerator implements Dto2PosoGenerator<BirtReportFileDto,BirtReportFile> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2BirtReportFileGenerator(
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

	public BirtReportFile loadPoso(BirtReportFileDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		BirtReportFile poso = entityManager.find(BirtReportFile.class, id);
		return poso;
	}

	public BirtReportFile instantiatePoso()  {
		BirtReportFile poso = new BirtReportFile();
		return poso;
	}

	public BirtReportFile createPoso(BirtReportFileDto dto)  throws ExpectedException {
		BirtReportFile poso = new BirtReportFile();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public BirtReportFile createUnmanagedPoso(BirtReportFileDto dto)  throws ExpectedException {
		BirtReportFile poso = new BirtReportFile();

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

	public void mergePoso(BirtReportFileDto dto, final BirtReportFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(BirtReportFileDto dto, final BirtReportFile poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(BirtReportFileDto dto, final BirtReportFile poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(BirtReportFileDto dto, final BirtReportFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(BirtReportFileDto dto, final BirtReportFile poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(BirtReportFileDto dto, final BirtReportFile poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public BirtReportFile loadAndMergePoso(BirtReportFileDto dto)  throws ExpectedException {
		BirtReportFile poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(BirtReportFileDto dto, BirtReportFile poso)  {
	}


	public void postProcessCreateUnmanaged(BirtReportFileDto dto, BirtReportFile poso)  {
	}


	public void postProcessLoad(BirtReportFileDto dto, BirtReportFile poso)  {
	}


	public void postProcessMerge(BirtReportFileDto dto, BirtReportFile poso)  {
	}


	public void postProcessInstantiate(BirtReportFile poso)  {
	}



}
