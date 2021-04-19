package net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen;

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
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplateList;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.Dto2TableReportTemplateListGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TableReportTemplateList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TableReportTemplateListGenerator implements Dto2PosoGenerator<TableReportTemplateListDto,TableReportTemplateList> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TableReportTemplateListGenerator(
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

	public TableReportTemplateList loadPoso(TableReportTemplateListDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TableReportTemplateList poso = entityManager.find(TableReportTemplateList.class, id);
		return poso;
	}

	public TableReportTemplateList instantiatePoso()  {
		TableReportTemplateList poso = new TableReportTemplateList();
		return poso;
	}

	public TableReportTemplateList createPoso(TableReportTemplateListDto dto)  throws ExpectedException {
		TableReportTemplateList poso = new TableReportTemplateList();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TableReportTemplateList createUnmanagedPoso(TableReportTemplateListDto dto)  throws ExpectedException {
		TableReportTemplateList poso = new TableReportTemplateList();

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

	public void mergePoso(TableReportTemplateListDto dto, final TableReportTemplateList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TableReportTemplateListDto dto, final TableReportTemplateList poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2Poso(TableReportTemplateListDto dto, final TableReportTemplateList poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public void mergeUnmanagedPoso(TableReportTemplateListDto dto, final TableReportTemplateList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TableReportTemplateListDto dto, final TableReportTemplateList poso)  throws ExpectedException {
		/*  set name */
		poso.setName(dto.getName() );

	}

	protected void mergeProxy2UnmanagedPoso(TableReportTemplateListDto dto, final TableReportTemplateList poso)  throws ExpectedException {
		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

	}

	public TableReportTemplateList loadAndMergePoso(TableReportTemplateListDto dto)  throws ExpectedException {
		TableReportTemplateList poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TableReportTemplateListDto dto, TableReportTemplateList poso)  {
	}


	public void postProcessCreateUnmanaged(TableReportTemplateListDto dto, TableReportTemplateList poso)  {
	}


	public void postProcessLoad(TableReportTemplateListDto dto, TableReportTemplateList poso)  {
	}


	public void postProcessMerge(TableReportTemplateListDto dto, TableReportTemplateList poso)  {
	}


	public void postProcessInstantiate(TableReportTemplateList poso)  {
	}



}
