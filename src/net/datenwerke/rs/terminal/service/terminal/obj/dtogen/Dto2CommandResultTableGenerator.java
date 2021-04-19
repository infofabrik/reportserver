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
import net.datenwerke.rs.base.client.reportengines.table.dto.RSTableModelDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultTableDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultTable;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultTableGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResultTable
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultTableGenerator implements Dto2PosoGenerator<CommandResultTableDto,CommandResultTable> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultTableGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResultTable loadPoso(CommandResultTableDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResultTable instantiatePoso()  {
		CommandResultTable poso = new CommandResultTable();
		return poso;
	}

	public CommandResultTable createPoso(CommandResultTableDto dto)  throws ExpectedException {
		CommandResultTable poso = new CommandResultTable();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResultTable createUnmanagedPoso(CommandResultTableDto dto)  throws ExpectedException {
		CommandResultTable poso = new CommandResultTable();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultTableDto dto, final CommandResultTable poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultTableDto dto, final CommandResultTable poso)  throws ExpectedException {
		/*  set table */
		RSTableModelDto tmpDto_table = dto.getTable();
		poso.setTable((RSTableModel)dtoServiceProvider.get().createPoso(tmpDto_table));

	}

	protected void mergeProxy2Poso(CommandResultTableDto dto, final CommandResultTable poso)  throws ExpectedException {
		/*  set table */
		if(dto.isTableModified()){
			RSTableModelDto tmpDto_table = dto.getTable();
			poso.setTable((RSTableModel)dtoServiceProvider.get().createPoso(tmpDto_table));
		}

	}

	public void mergeUnmanagedPoso(CommandResultTableDto dto, final CommandResultTable poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultTableDto dto, final CommandResultTable poso)  throws ExpectedException {
		/*  set table */
		RSTableModelDto tmpDto_table = dto.getTable();
		poso.setTable((RSTableModel)dtoServiceProvider.get().createPoso(tmpDto_table));

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultTableDto dto, final CommandResultTable poso)  throws ExpectedException {
		/*  set table */
		if(dto.isTableModified()){
			RSTableModelDto tmpDto_table = dto.getTable();
			poso.setTable((RSTableModel)dtoServiceProvider.get().createPoso(tmpDto_table));
		}

	}

	public CommandResultTable loadAndMergePoso(CommandResultTableDto dto)  throws ExpectedException {
		CommandResultTable poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultTableDto dto, CommandResultTable poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultTableDto dto, CommandResultTable poso)  {
	}


	public void postProcessLoad(CommandResultTableDto dto, CommandResultTable poso)  {
	}


	public void postProcessMerge(CommandResultTableDto dto, CommandResultTable poso)  {
	}


	public void postProcessInstantiate(CommandResultTable poso)  {
	}



}
