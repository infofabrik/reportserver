package net.datenwerke.rs.crystal.service.crystal.entities.dtogen;

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
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile;
import net.datenwerke.rs.crystal.service.crystal.entities.dtogen.Dto2CrystalReportFileGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CrystalReportFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CrystalReportFileGenerator implements Dto2PosoGenerator<CrystalReportFileDto,CrystalReportFile> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CrystalReportFileGenerator(
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

	public CrystalReportFile loadPoso(CrystalReportFileDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		CrystalReportFile poso = entityManager.find(CrystalReportFile.class, id);
		return poso;
	}

	public CrystalReportFile instantiatePoso()  {
		CrystalReportFile poso = new CrystalReportFile();
		return poso;
	}

	public CrystalReportFile createPoso(CrystalReportFileDto dto)  throws ExpectedException {
		CrystalReportFile poso = new CrystalReportFile();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CrystalReportFile createUnmanagedPoso(CrystalReportFileDto dto)  throws ExpectedException {
		CrystalReportFile poso = new CrystalReportFile();

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

	public void mergePoso(CrystalReportFileDto dto, final CrystalReportFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CrystalReportFileDto dto, final CrystalReportFile poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(CrystalReportFileDto dto, final CrystalReportFile poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(CrystalReportFileDto dto, final CrystalReportFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CrystalReportFileDto dto, final CrystalReportFile poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(CrystalReportFileDto dto, final CrystalReportFile poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public CrystalReportFile loadAndMergePoso(CrystalReportFileDto dto)  throws ExpectedException {
		CrystalReportFile poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CrystalReportFileDto dto, CrystalReportFile poso)  {
	}


	public void postProcessCreateUnmanaged(CrystalReportFileDto dto, CrystalReportFile poso)  {
	}


	public void postProcessLoad(CrystalReportFileDto dto, CrystalReportFile poso)  {
	}


	public void postProcessMerge(CrystalReportFileDto dto, CrystalReportFile poso)  {
	}


	public void postProcessInstantiate(CrystalReportFile poso)  {
	}



}
