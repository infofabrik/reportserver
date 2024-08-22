package net.datenwerke.rs.adminutils.service.logs.terminal.commands.dtogen;

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
import net.datenwerke.rs.adminutils.client.logs.dto.ViewLogFileCommandResultExtensionDto;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.ViewLogFileCommandResultExtension;
import net.datenwerke.rs.adminutils.service.logs.terminal.commands.dtogen.Dto2ViewLogFileCommandResultExtensionGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for ViewLogFileCommandResultExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2ViewLogFileCommandResultExtensionGenerator implements Dto2PosoGenerator<ViewLogFileCommandResultExtensionDto,ViewLogFileCommandResultExtension> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2ViewLogFileCommandResultExtensionGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public ViewLogFileCommandResultExtension loadPoso(ViewLogFileCommandResultExtensionDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public ViewLogFileCommandResultExtension instantiatePoso()  {
		ViewLogFileCommandResultExtension poso = new ViewLogFileCommandResultExtension();
		return poso;
	}

	public ViewLogFileCommandResultExtension createPoso(ViewLogFileCommandResultExtensionDto dto)  throws ExpectedException {
		ViewLogFileCommandResultExtension poso = new ViewLogFileCommandResultExtension();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public ViewLogFileCommandResultExtension createUnmanagedPoso(ViewLogFileCommandResultExtensionDto dto)  throws ExpectedException {
		ViewLogFileCommandResultExtension poso = new ViewLogFileCommandResultExtension();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(ViewLogFileCommandResultExtensionDto dto, final ViewLogFileCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(ViewLogFileCommandResultExtensionDto dto, final ViewLogFileCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

		/*  set filename */
		poso.setFilename(dto.getFilename() );

	}

	protected void mergeProxy2Poso(ViewLogFileCommandResultExtensionDto dto, final ViewLogFileCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

		/*  set filename */
		if(dto.isFilenameModified()){
			poso.setFilename(dto.getFilename() );
		}

	}

	public void mergeUnmanagedPoso(ViewLogFileCommandResultExtensionDto dto, final ViewLogFileCommandResultExtension poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(ViewLogFileCommandResultExtensionDto dto, final ViewLogFileCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		poso.setData(dto.getData() );

		/*  set filename */
		poso.setFilename(dto.getFilename() );

	}

	protected void mergeProxy2UnmanagedPoso(ViewLogFileCommandResultExtensionDto dto, final ViewLogFileCommandResultExtension poso)  throws ExpectedException {
		/*  set data */
		if(dto.isDataModified()){
			poso.setData(dto.getData() );
		}

		/*  set filename */
		if(dto.isFilenameModified()){
			poso.setFilename(dto.getFilename() );
		}

	}

	public ViewLogFileCommandResultExtension loadAndMergePoso(ViewLogFileCommandResultExtensionDto dto)  throws ExpectedException {
		ViewLogFileCommandResultExtension poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(ViewLogFileCommandResultExtensionDto dto, ViewLogFileCommandResultExtension poso)  {
	}


	public void postProcessCreateUnmanaged(ViewLogFileCommandResultExtensionDto dto, ViewLogFileCommandResultExtension poso)  {
	}


	public void postProcessLoad(ViewLogFileCommandResultExtensionDto dto, ViewLogFileCommandResultExtension poso)  {
	}


	public void postProcessMerge(ViewLogFileCommandResultExtensionDto dto, ViewLogFileCommandResultExtension poso)  {
	}


	public void postProcessInstantiate(ViewLogFileCommandResultExtension poso)  {
	}



}
