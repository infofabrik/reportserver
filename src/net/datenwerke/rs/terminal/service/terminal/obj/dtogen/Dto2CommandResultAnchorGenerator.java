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
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultAnchor;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultAnchorGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResultAnchor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultAnchorGenerator implements Dto2PosoGenerator<CommandResultAnchorDto,CommandResultAnchor> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultAnchorGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResultAnchor loadPoso(CommandResultAnchorDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResultAnchor instantiatePoso()  {
		CommandResultAnchor poso = new CommandResultAnchor();
		return poso;
	}

	public CommandResultAnchor createPoso(CommandResultAnchorDto dto)  throws ExpectedException {
		CommandResultAnchor poso = new CommandResultAnchor();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResultAnchor createUnmanagedPoso(CommandResultAnchorDto dto)  throws ExpectedException {
		CommandResultAnchor poso = new CommandResultAnchor();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultAnchorDto dto, final CommandResultAnchor poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultAnchorDto dto, final CommandResultAnchor poso)  throws ExpectedException {
		/*  set target */
		poso.setTarget(dto.getTarget() );

		/*  set text */
		poso.setText(dto.getText() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

	}

	protected void mergeProxy2Poso(CommandResultAnchorDto dto, final CommandResultAnchor poso)  throws ExpectedException {
		/*  set target */
		if(dto.isTargetModified()){
			poso.setTarget(dto.getTarget() );
		}

		/*  set text */
		if(dto.isTextModified()){
			poso.setText(dto.getText() );
		}

		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

	}

	public void mergeUnmanagedPoso(CommandResultAnchorDto dto, final CommandResultAnchor poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultAnchorDto dto, final CommandResultAnchor poso)  throws ExpectedException {
		/*  set target */
		poso.setTarget(dto.getTarget() );

		/*  set text */
		poso.setText(dto.getText() );

		/*  set url */
		poso.setUrl(dto.getUrl() );

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultAnchorDto dto, final CommandResultAnchor poso)  throws ExpectedException {
		/*  set target */
		if(dto.isTargetModified()){
			poso.setTarget(dto.getTarget() );
		}

		/*  set text */
		if(dto.isTextModified()){
			poso.setText(dto.getText() );
		}

		/*  set url */
		if(dto.isUrlModified()){
			poso.setUrl(dto.getUrl() );
		}

	}

	public CommandResultAnchor loadAndMergePoso(CommandResultAnchorDto dto)  throws ExpectedException {
		CommandResultAnchor poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultAnchorDto dto, CommandResultAnchor poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultAnchorDto dto, CommandResultAnchor poso)  {
	}


	public void postProcessLoad(CommandResultAnchorDto dto, CommandResultAnchor poso)  {
	}


	public void postProcessMerge(CommandResultAnchorDto dto, CommandResultAnchor poso)  {
	}


	public void postProcessInstantiate(CommandResultAnchor poso)  {
	}



}
