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
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ValidationFailedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportStringTemplate;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.Dto2TableReportStringTemplateGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for TableReportStringTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2TableReportStringTemplateGenerator implements Dto2PosoGenerator<TableReportStringTemplateDto,TableReportStringTemplate> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2TableReportStringTemplateGenerator(
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

	public TableReportStringTemplate loadPoso(TableReportStringTemplateDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		TableReportStringTemplate poso = entityManager.find(TableReportStringTemplate.class, id);
		return poso;
	}

	public TableReportStringTemplate instantiatePoso()  {
		TableReportStringTemplate poso = new TableReportStringTemplate();
		return poso;
	}

	public TableReportStringTemplate createPoso(TableReportStringTemplateDto dto)  throws ExpectedException {
		TableReportStringTemplate poso = new TableReportStringTemplate();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public TableReportStringTemplate createUnmanagedPoso(TableReportStringTemplateDto dto)  throws ExpectedException {
		TableReportStringTemplate poso = new TableReportStringTemplate();

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

	public void mergePoso(TableReportStringTemplateDto dto, final TableReportStringTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(TableReportStringTemplateDto dto, final TableReportStringTemplate poso)  throws ExpectedException {
		/*  set contentType */
		poso.setContentType(dto.getContentType() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set fileExtension */
		poso.setFileExtension(dto.getFileExtension() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set templateType */
		poso.setTemplateType(dto.getTemplateType() );

		/*  set temporaryId */
		poso.setTemporaryId(dto.getTemporaryId() );

	}

	protected void mergeProxy2Poso(TableReportStringTemplateDto dto, final TableReportStringTemplate poso)  throws ExpectedException {
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
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
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

	public void mergeUnmanagedPoso(TableReportStringTemplateDto dto, final TableReportStringTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(TableReportStringTemplateDto dto, final TableReportStringTemplate poso)  throws ExpectedException {
		/*  set contentType */
		poso.setContentType(dto.getContentType() );

		/*  set description */
		poso.setDescription(dto.getDescription() );

		/*  set fileExtension */
		poso.setFileExtension(dto.getFileExtension() );

		/*  set key */
		if(validateKeyProperty(dto, poso)){
			poso.setKey(dto.getKey() );
		}

		/*  set name */
		poso.setName(dto.getName() );

		/*  set templateType */
		poso.setTemplateType(dto.getTemplateType() );

		/*  set temporaryId */
		poso.setTemporaryId(dto.getTemporaryId() );

	}

	protected void mergeProxy2UnmanagedPoso(TableReportStringTemplateDto dto, final TableReportStringTemplate poso)  throws ExpectedException {
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
			if(validateKeyProperty(dto, poso)){
				poso.setKey(dto.getKey() );
			}
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

	public TableReportStringTemplate loadAndMergePoso(TableReportStringTemplateDto dto)  throws ExpectedException {
		TableReportStringTemplate poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(TableReportStringTemplateDto dto, TableReportStringTemplate poso)  {
	}


	public void postProcessCreateUnmanaged(TableReportStringTemplateDto dto, TableReportStringTemplate poso)  {
	}


	public void postProcessLoad(TableReportStringTemplateDto dto, TableReportStringTemplate poso)  {
	}


	public void postProcessMerge(TableReportStringTemplateDto dto, TableReportStringTemplate poso)  {
	}


	public void postProcessInstantiate(TableReportStringTemplate poso)  {
	}


	public boolean validateKeyProperty(TableReportStringTemplateDto dto, TableReportStringTemplate poso)  throws ExpectedException {
		Object propertyValue = dto.getKey();

		/* allow null */
		if(null == propertyValue)
			return true;

		/* make sure property is string */
		if(! java.lang.String.class.isAssignableFrom(propertyValue.getClass()))
			throw new ValidationFailedException("String validation failed for key", "expected a String");

		if(! ((String)propertyValue).matches("^[a-zA-Z0-9_\\-]+$"))
			throw new ValidationFailedException("String validation failed for key", " Regex test failed.");

		/* all went well */
		return true;
	}


}
