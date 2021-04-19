package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

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
import net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CreJavaScript;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CreJavaScriptGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CreJavaScript
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CreJavaScriptGenerator implements Dto2PosoGenerator<CreJavaScriptDto,CreJavaScript> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CreJavaScriptGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CreJavaScript loadPoso(CreJavaScriptDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CreJavaScript instantiatePoso()  {
		CreJavaScript poso = new CreJavaScript();
		return poso;
	}

	public CreJavaScript createPoso(CreJavaScriptDto dto)  throws ExpectedException {
		CreJavaScript poso = new CreJavaScript();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CreJavaScript createUnmanagedPoso(CreJavaScriptDto dto)  throws ExpectedException {
		CreJavaScript poso = new CreJavaScript();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CreJavaScriptDto dto, final CreJavaScript poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CreJavaScriptDto dto, final CreJavaScript poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

	}

	protected void mergeProxy2Poso(CreJavaScriptDto dto, final CreJavaScript poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

	}

	public void mergeUnmanagedPoso(CreJavaScriptDto dto, final CreJavaScript poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CreJavaScriptDto dto, final CreJavaScript poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

	}

	protected void mergeProxy2UnmanagedPoso(CreJavaScriptDto dto, final CreJavaScript poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

	}

	public CreJavaScript loadAndMergePoso(CreJavaScriptDto dto)  throws ExpectedException {
		CreJavaScript poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CreJavaScriptDto dto, CreJavaScript poso)  {
	}


	public void postProcessCreateUnmanaged(CreJavaScriptDto dto, CreJavaScript poso)  {
	}


	public void postProcessLoad(CreJavaScriptDto dto, CreJavaScript poso)  {
	}


	public void postProcessMerge(CreJavaScriptDto dto, CreJavaScript poso)  {
	}


	public void postProcessInstantiate(CreJavaScript poso)  {
	}



}
