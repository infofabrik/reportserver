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
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultLine;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultLineGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResultLine
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultLineGenerator implements Dto2PosoGenerator<CommandResultLineDto,CommandResultLine> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultLineGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResultLine loadPoso(CommandResultLineDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResultLine instantiatePoso()  {
		CommandResultLine poso = new CommandResultLine();
		return poso;
	}

	public CommandResultLine createPoso(CommandResultLineDto dto)  throws ExpectedException {
		CommandResultLine poso = new CommandResultLine();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResultLine createUnmanagedPoso(CommandResultLineDto dto)  throws ExpectedException {
		CommandResultLine poso = new CommandResultLine();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultLineDto dto, final CommandResultLine poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultLineDto dto, final CommandResultLine poso)  throws ExpectedException {
		/*  set line */
		poso.setLine(dto.getLine() );

	}

	protected void mergeProxy2Poso(CommandResultLineDto dto, final CommandResultLine poso)  throws ExpectedException {
		/*  set line */
		if(dto.isLineModified()){
			poso.setLine(dto.getLine() );
		}

	}

	public void mergeUnmanagedPoso(CommandResultLineDto dto, final CommandResultLine poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultLineDto dto, final CommandResultLine poso)  throws ExpectedException {
		/*  set line */
		poso.setLine(dto.getLine() );

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultLineDto dto, final CommandResultLine poso)  throws ExpectedException {
		/*  set line */
		if(dto.isLineModified()){
			poso.setLine(dto.getLine() );
		}

	}

	public CommandResultLine loadAndMergePoso(CommandResultLineDto dto)  throws ExpectedException {
		CommandResultLine poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultLineDto dto, CommandResultLine poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultLineDto dto, CommandResultLine poso)  {
	}


	public void postProcessLoad(CommandResultLineDto dto, CommandResultLine poso)  {
	}


	public void postProcessMerge(CommandResultLineDto dto, CommandResultLine poso)  {
	}


	public void postProcessInstantiate(CommandResultLine poso)  {
	}



}
