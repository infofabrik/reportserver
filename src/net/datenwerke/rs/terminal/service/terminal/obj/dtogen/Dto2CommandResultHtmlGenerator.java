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
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHtml;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultHtmlGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResultHtml
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultHtmlGenerator implements Dto2PosoGenerator<CommandResultHtmlDto,CommandResultHtml> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultHtmlGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResultHtml loadPoso(CommandResultHtmlDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResultHtml instantiatePoso()  {
		CommandResultHtml poso = new CommandResultHtml();
		return poso;
	}

	public CommandResultHtml createPoso(CommandResultHtmlDto dto)  throws ExpectedException {
		CommandResultHtml poso = new CommandResultHtml();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResultHtml createUnmanagedPoso(CommandResultHtmlDto dto)  throws ExpectedException {
		CommandResultHtml poso = new CommandResultHtml();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultHtmlDto dto, final CommandResultHtml poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultHtmlDto dto, final CommandResultHtml poso)  throws ExpectedException {
		/*  set html */
		poso.setHtml(dto.getHtml() );

	}

	protected void mergeProxy2Poso(CommandResultHtmlDto dto, final CommandResultHtml poso)  throws ExpectedException {
		/*  set html */
		if(dto.isHtmlModified()){
			poso.setHtml(dto.getHtml() );
		}

	}

	public void mergeUnmanagedPoso(CommandResultHtmlDto dto, final CommandResultHtml poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultHtmlDto dto, final CommandResultHtml poso)  throws ExpectedException {
		/*  set html */
		poso.setHtml(dto.getHtml() );

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultHtmlDto dto, final CommandResultHtml poso)  throws ExpectedException {
		/*  set html */
		if(dto.isHtmlModified()){
			poso.setHtml(dto.getHtml() );
		}

	}

	public CommandResultHtml loadAndMergePoso(CommandResultHtmlDto dto)  throws ExpectedException {
		CommandResultHtml poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultHtmlDto dto, CommandResultHtml poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultHtmlDto dto, CommandResultHtml poso)  {
	}


	public void postProcessLoad(CommandResultHtmlDto dto, CommandResultHtml poso)  {
	}


	public void postProcessMerge(CommandResultHtmlDto dto, CommandResultHtml poso)  {
	}


	public void postProcessInstantiate(CommandResultHtml poso)  {
	}



}
