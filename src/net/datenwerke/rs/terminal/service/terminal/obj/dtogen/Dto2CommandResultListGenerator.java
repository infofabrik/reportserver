package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.lang.Exception;
import java.lang.NullPointerException;
import java.lang.RuntimeException;
import java.lang.reflect.Field;
import java.util.Collection;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoGenerator;
import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.validator.DtoPropertyValidator;
import net.datenwerke.gxtdto.client.servercommunication.exceptions.ExpectedException;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultListDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultList;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultListGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResultList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultListGenerator implements Dto2PosoGenerator<CommandResultListDto,CommandResultList> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultListGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResultList loadPoso(CommandResultListDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResultList instantiatePoso()  {
		CommandResultList poso = new CommandResultList();
		return poso;
	}

	public CommandResultList createPoso(CommandResultListDto dto)  throws ExpectedException {
		CommandResultList poso = new CommandResultList();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResultList createUnmanagedPoso(CommandResultListDto dto)  throws ExpectedException {
		CommandResultList poso = new CommandResultList();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultListDto dto, final CommandResultList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultListDto dto, final CommandResultList poso)  throws ExpectedException {
		/*  set denyBreakUp */
		try{
			poso.setDenyBreakUp(dto.isDenyBreakUp() );
		} catch(NullPointerException e){
		}

		/*  set list */
		poso.setList(dto.getList() );

	}

	protected void mergeProxy2Poso(CommandResultListDto dto, final CommandResultList poso)  throws ExpectedException {
		/*  set denyBreakUp */
		if(dto.isDenyBreakUpModified()){
			try{
				poso.setDenyBreakUp(dto.isDenyBreakUp() );
			} catch(NullPointerException e){
			}
		}

		/*  set list */
		if(dto.isListModified()){
			poso.setList(dto.getList() );
		}

	}

	public void mergeUnmanagedPoso(CommandResultListDto dto, final CommandResultList poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultListDto dto, final CommandResultList poso)  throws ExpectedException {
		/*  set denyBreakUp */
		try{
			poso.setDenyBreakUp(dto.isDenyBreakUp() );
		} catch(NullPointerException e){
		}

		/*  set list */
		poso.setList(dto.getList() );

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultListDto dto, final CommandResultList poso)  throws ExpectedException {
		/*  set denyBreakUp */
		if(dto.isDenyBreakUpModified()){
			try{
				poso.setDenyBreakUp(dto.isDenyBreakUp() );
			} catch(NullPointerException e){
			}
		}

		/*  set list */
		if(dto.isListModified()){
			poso.setList(dto.getList() );
		}

	}

	public CommandResultList loadAndMergePoso(CommandResultListDto dto)  throws ExpectedException {
		CommandResultList poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultListDto dto, CommandResultList poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultListDto dto, CommandResultList poso)  {
	}


	public void postProcessLoad(CommandResultListDto dto, CommandResultList poso)  {
	}


	public void postProcessMerge(CommandResultListDto dto, CommandResultList poso)  {
	}


	public void postProcessInstantiate(CommandResultList poso)  {
	}



}
