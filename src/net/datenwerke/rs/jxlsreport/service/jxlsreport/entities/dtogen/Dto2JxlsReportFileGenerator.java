package net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen;

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
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.Dto2JxlsReportFileGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for JxlsReportFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2JxlsReportFileGenerator implements Dto2PosoGenerator<JxlsReportFileDto,JxlsReportFile> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2JxlsReportFileGenerator(
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

	public JxlsReportFile loadPoso(JxlsReportFileDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		JxlsReportFile poso = entityManager.find(JxlsReportFile.class, id);
		return poso;
	}

	public JxlsReportFile instantiatePoso()  {
		JxlsReportFile poso = new JxlsReportFile();
		return poso;
	}

	public JxlsReportFile createPoso(JxlsReportFileDto dto)  throws ExpectedException {
		JxlsReportFile poso = new JxlsReportFile();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public JxlsReportFile createUnmanagedPoso(JxlsReportFileDto dto)  throws ExpectedException {
		JxlsReportFile poso = new JxlsReportFile();

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

	public void mergePoso(JxlsReportFileDto dto, final JxlsReportFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(JxlsReportFileDto dto, final JxlsReportFile poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(JxlsReportFileDto dto, final JxlsReportFile poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(JxlsReportFileDto dto, final JxlsReportFile poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(JxlsReportFileDto dto, final JxlsReportFile poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(JxlsReportFileDto dto, final JxlsReportFile poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public JxlsReportFile loadAndMergePoso(JxlsReportFileDto dto)  throws ExpectedException {
		JxlsReportFile poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(JxlsReportFileDto dto, JxlsReportFile poso)  {
	}


	public void postProcessCreateUnmanaged(JxlsReportFileDto dto, JxlsReportFile poso)  {
	}


	public void postProcessLoad(JxlsReportFileDto dto, JxlsReportFile poso)  {
	}


	public void postProcessMerge(JxlsReportFileDto dto, JxlsReportFile poso)  {
	}


	public void postProcessInstantiate(JxlsReportFile poso)  {
	}



}
