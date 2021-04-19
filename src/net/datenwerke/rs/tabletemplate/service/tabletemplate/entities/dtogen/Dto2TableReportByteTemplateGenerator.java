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
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportByteTemplateDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportByteTemplate;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.Dto2TableReportByteTemplateGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TableReportByteTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TableReportByteTemplateGenerator implements Dto2PosoGenerator<TableReportByteTemplateDto,TableReportByteTemplate> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TableReportByteTemplateGenerator(
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

	public TableReportByteTemplate loadPoso(TableReportByteTemplateDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TableReportByteTemplate poso = entityManager.find(TableReportByteTemplate.class, id);
		return poso;
	}

	public TableReportByteTemplate instantiatePoso()  {
		TableReportByteTemplate poso = new TableReportByteTemplate();
		return poso;
	}

	public TableReportByteTemplate createPoso(TableReportByteTemplateDto dto)  throws ExpectedException {
		TableReportByteTemplate poso = new TableReportByteTemplate();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TableReportByteTemplate createUnmanagedPoso(TableReportByteTemplateDto dto)  throws ExpectedException {
		TableReportByteTemplate poso = new TableReportByteTemplate();

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

	public void mergePoso(TableReportByteTemplateDto dto, final TableReportByteTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TableReportByteTemplateDto dto, final TableReportByteTemplate poso)  throws ExpectedException {
		/*  set contentType */
		poso.setContentType(dto.getContentType() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set fileExtension */
		poso.setFileExtension(dto.getFileExtension() );

		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set templateType */
		poso.setTemplateType(dto.getTemplateType() );

		/*  set temporaryId */
		poso.setTemporaryId(dto.getTemporaryId() );

	}

	protected void mergeProxy2Poso(TableReportByteTemplateDto dto, final TableReportByteTemplate poso)  throws ExpectedException {
		/*  set contentType */
		if(dto.isContentTypeModified()){
			poso.setContentType(dto.getContentType() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set fileExtension */
		if(dto.isFileExtensionModified()){
			poso.setFileExtension(dto.getFileExtension() );
		}

		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set templateType */
		if(dto.isTemplateTypeModified()){
			poso.setTemplateType(dto.getTemplateType() );
		}

		/*  set temporaryId */
		if(dto.isTemporaryIdModified()){
			poso.setTemporaryId(dto.getTemporaryId() );
		}

	}

	public void mergeUnmanagedPoso(TableReportByteTemplateDto dto, final TableReportByteTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TableReportByteTemplateDto dto, final TableReportByteTemplate poso)  throws ExpectedException {
		/*  set contentType */
		poso.setContentType(dto.getContentType() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set fileExtension */
		poso.setFileExtension(dto.getFileExtension() );

		/*  set key */
		poso.setKey(dto.getKey() );

		/*  set name */
		poso.setName(dto.getName() );

		/*  set templateType */
		poso.setTemplateType(dto.getTemplateType() );

		/*  set temporaryId */
		poso.setTemporaryId(dto.getTemporaryId() );

	}

	protected void mergeProxy2UnmanagedPoso(TableReportByteTemplateDto dto, final TableReportByteTemplate poso)  throws ExpectedException {
		/*  set contentType */
		if(dto.isContentTypeModified()){
			poso.setContentType(dto.getContentType() );
		}

		/*  set description */
		if(dto.isDescriptionModified()){
			poso.setDescription(dto.getDescription() );
		}

		/*  set fileExtension */
		if(dto.isFileExtensionModified()){
			poso.setFileExtension(dto.getFileExtension() );
		}

		/*  set key */
		if(dto.isKeyModified()){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		if(dto.isNameModified()){
			poso.setName(dto.getName() );
		}

		/*  set templateType */
		if(dto.isTemplateTypeModified()){
			poso.setTemplateType(dto.getTemplateType() );
		}

		/*  set temporaryId */
		if(dto.isTemporaryIdModified()){
			poso.setTemporaryId(dto.getTemporaryId() );
		}

	}

	public TableReportByteTemplate loadAndMergePoso(TableReportByteTemplateDto dto)  throws ExpectedException {
		TableReportByteTemplate poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TableReportByteTemplateDto dto, TableReportByteTemplate poso)  {
	}


	public void postProcessCreateUnmanaged(TableReportByteTemplateDto dto, TableReportByteTemplate poso)  {
	}


	public void postProcessLoad(TableReportByteTemplateDto dto, TableReportByteTemplate poso)  {
	}


	public void postProcessMerge(TableReportByteTemplateDto dto, TableReportByteTemplate poso)  {
	}


	public void postProcessInstantiate(TableReportByteTemplate poso)  {
	}



}
