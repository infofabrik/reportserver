package net.datenwerke.rs.tabletemplate.service.tabletemplate.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.config.RECTableTemplate;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.config.dtogen.Dto2RECTableTemplateGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for RECTableTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2RECTableTemplateGenerator implements Dto2PosoGenerator<RECTableTemplateDto,RECTableTemplate> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2RECTableTemplateGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public RECTableTemplate loadPoso(RECTableTemplateDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public RECTableTemplate instantiatePoso()  {
		RECTableTemplate poso = new RECTableTemplate();
		return poso;
	}

	public RECTableTemplate createPoso(RECTableTemplateDto dto)  throws ExpectedException {
		RECTableTemplate poso = new RECTableTemplate();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public RECTableTemplate createUnmanagedPoso(RECTableTemplateDto dto)  throws ExpectedException {
		RECTableTemplate poso = new RECTableTemplate();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(RECTableTemplateDto dto, final RECTableTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(RECTableTemplateDto dto, final RECTableTemplate poso)  throws ExpectedException {
		/*  set templateId */
		poso.setTemplateId(dto.getTemplateId() );

		/*  set templateKey */
		poso.setTemplateKey(dto.getTemplateKey() );

		/*  set temporaryId */
		poso.setTemporaryId(dto.getTemporaryId() );

	}

	protected void mergeProxy2Poso(RECTableTemplateDto dto, final RECTableTemplate poso)  throws ExpectedException {
		/*  set templateId */
		if(dto.isTemplateIdModified()){
			poso.setTemplateId(dto.getTemplateId() );
		}

		/*  set templateKey */
		if(dto.isTemplateKeyModified()){
			poso.setTemplateKey(dto.getTemplateKey() );
		}

		/*  set temporaryId */
		if(dto.isTemporaryIdModified()){
			poso.setTemporaryId(dto.getTemporaryId() );
		}

	}

	public void mergeUnmanagedPoso(RECTableTemplateDto dto, final RECTableTemplate poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(RECTableTemplateDto dto, final RECTableTemplate poso)  throws ExpectedException {
		/*  set templateId */
		poso.setTemplateId(dto.getTemplateId() );

		/*  set templateKey */
		poso.setTemplateKey(dto.getTemplateKey() );

		/*  set temporaryId */
		poso.setTemporaryId(dto.getTemporaryId() );

	}

	protected void mergeProxy2UnmanagedPoso(RECTableTemplateDto dto, final RECTableTemplate poso)  throws ExpectedException {
		/*  set templateId */
		if(dto.isTemplateIdModified()){
			poso.setTemplateId(dto.getTemplateId() );
		}

		/*  set templateKey */
		if(dto.isTemplateKeyModified()){
			poso.setTemplateKey(dto.getTemplateKey() );
		}

		/*  set temporaryId */
		if(dto.isTemporaryIdModified()){
			poso.setTemporaryId(dto.getTemporaryId() );
		}

	}

	public RECTableTemplate loadAndMergePoso(RECTableTemplateDto dto)  throws ExpectedException {
		RECTableTemplate poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(RECTableTemplateDto dto, RECTableTemplate poso)  {
	}


	public void postProcessCreateUnmanaged(RECTableTemplateDto dto, RECTableTemplate poso)  {
	}


	public void postProcessLoad(RECTableTemplateDto dto, RECTableTemplate poso)  {
	}


	public void postProcessMerge(RECTableTemplateDto dto, RECTableTemplate poso)  {
	}


	public void postProcessInstantiate(RECTableTemplate poso)  {
	}



}
