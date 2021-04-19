package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

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
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.Dto2ColumnFormatTemplateGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ColumnFormatTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ColumnFormatTemplateGenerator implements Dto2PosoGenerator<ColumnFormatTemplateDto,ColumnFormatTemplate> {

	private final Provider<DtoService> dtoServiceProvider;

	private final Provider<EntityManager> entityManagerProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ColumnFormatTemplateGenerator(
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

	public ColumnFormatTemplate loadPoso(ColumnFormatTemplateDto dto)  {
		if(null == dto)
			return null;

		/* get id */
		Object id = dto.getId();
		if(null == id)
			return null;

		/* load poso from db */
		EntityManager entityManager = entityManagerProvider.get();
		ColumnFormatTemplate poso = entityManager.find(ColumnFormatTemplate.class, id);
		return poso;
	}

	public ColumnFormatTemplate instantiatePoso()  {
		ColumnFormatTemplate poso = new ColumnFormatTemplate();
		return poso;
	}

	public ColumnFormatTemplate createPoso(ColumnFormatTemplateDto dto)  throws ExpectedException {
		ColumnFormatTemplate poso = new ColumnFormatTemplate();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ColumnFormatTemplate createUnmanagedPoso(ColumnFormatTemplateDto dto)  throws ExpectedException {
		ColumnFormatTemplate poso = new ColumnFormatTemplate();

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

	public void mergePoso(ColumnFormatTemplateDto dto, final ColumnFormatTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ColumnFormatTemplateDto dto, final ColumnFormatTemplate poso)  throws ExpectedException {
		/*  set template */
		poso.setTemplate(dto.getTemplate() );

	}

	protected void mergeProxy2Poso(ColumnFormatTemplateDto dto, final ColumnFormatTemplate poso)  throws ExpectedException {
		/*  set template */
		if(dto.isTemplateModified()){
			poso.setTemplate(dto.getTemplate() );
		}

	}

	public void mergeUnmanagedPoso(ColumnFormatTemplateDto dto, final ColumnFormatTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ColumnFormatTemplateDto dto, final ColumnFormatTemplate poso)  throws ExpectedException {
		/*  set template */
		poso.setTemplate(dto.getTemplate() );

	}

	protected void mergeProxy2UnmanagedPoso(ColumnFormatTemplateDto dto, final ColumnFormatTemplate poso)  throws ExpectedException {
		/*  set template */
		if(dto.isTemplateModified()){
			poso.setTemplate(dto.getTemplate() );
		}

	}

	public ColumnFormatTemplate loadAndMergePoso(ColumnFormatTemplateDto dto)  throws ExpectedException {
		ColumnFormatTemplate poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ColumnFormatTemplateDto dto, ColumnFormatTemplate poso)  {
	}


	public void postProcessCreateUnmanaged(ColumnFormatTemplateDto dto, ColumnFormatTemplate poso)  {
	}


	public void postProcessLoad(ColumnFormatTemplateDto dto, ColumnFormatTemplate poso)  {
	}


	public void postProcessMerge(ColumnFormatTemplateDto dto, ColumnFormatTemplate poso)  {
	}


	public void postProcessInstantiate(ColumnFormatTemplate poso)  {
	}



}
