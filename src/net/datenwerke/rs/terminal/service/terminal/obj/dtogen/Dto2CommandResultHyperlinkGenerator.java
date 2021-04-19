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
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHyperlink;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.Dto2CommandResultHyperlinkGenerator;
import net.datenwerke.rs.utils.entitycloner.annotation.TransientID;
import net.datenwerke.rs.utils.reflection.ReflectionService;

/**
 * Dto2PosoGenerator for CommandResultHyperlink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Dto2CommandResultHyperlinkGenerator implements Dto2PosoGenerator<CommandResultHyperlinkDto,CommandResultHyperlink> {

	private final Provider<DtoService> dtoServiceProvider;

	private final net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor;

	private final ReflectionService reflectionService;
	@Inject
	public Dto2CommandResultHyperlinkGenerator(
		net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoSupervisorDefaultImpl dto2PosoSupervisor,
		Provider<DtoService> dtoServiceProvider,
		ReflectionService reflectionService
	){
		this.dto2PosoSupervisor = dto2PosoSupervisor;
		this.dtoServiceProvider = dtoServiceProvider;
		this.reflectionService = reflectionService;
	}

	public CommandResultHyperlink loadPoso(CommandResultHyperlinkDto dto)  {
		/* Poso is not an entity .. so what should I load? */
		return null;
	}

	public CommandResultHyperlink instantiatePoso()  {
		CommandResultHyperlink poso = new CommandResultHyperlink();
		return poso;
	}

	public CommandResultHyperlink createPoso(CommandResultHyperlinkDto dto)  throws ExpectedException {
		CommandResultHyperlink poso = new CommandResultHyperlink();

		/* merge data */
		mergePoso(dto, poso);
		return poso;
	}

	public CommandResultHyperlink createUnmanagedPoso(CommandResultHyperlinkDto dto)  throws ExpectedException {
		CommandResultHyperlink poso = new CommandResultHyperlink();

		mergePlainDto2UnmanagedPoso(dto,poso);

		return poso;
	}

	public void mergePoso(CommandResultHyperlinkDto dto, final CommandResultHyperlink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2Poso(dto, poso);
		else
			mergePlainDto2Poso(dto, poso);
	}

	protected void mergePlainDto2Poso(CommandResultHyperlinkDto dto, final CommandResultHyperlink poso)  throws ExpectedException {
		/*  set caption */
		poso.setCaption(dto.getCaption() );

		/*  set historyToken */
		poso.setHistoryToken(dto.getHistoryToken() );

	}

	protected void mergeProxy2Poso(CommandResultHyperlinkDto dto, final CommandResultHyperlink poso)  throws ExpectedException {
		/*  set caption */
		if(dto.isCaptionModified()){
			poso.setCaption(dto.getCaption() );
		}

		/*  set historyToken */
		if(dto.isHistoryTokenModified()){
			poso.setHistoryToken(dto.getHistoryToken() );
		}

	}

	public void mergeUnmanagedPoso(CommandResultHyperlinkDto dto, final CommandResultHyperlink poso)  throws ExpectedException {
		if(dto.isDtoProxy())
			mergeProxy2UnmanagedPoso(dto, poso);
		else
			mergePlainDto2UnmanagedPoso(dto, poso);
	}

	protected void mergePlainDto2UnmanagedPoso(CommandResultHyperlinkDto dto, final CommandResultHyperlink poso)  throws ExpectedException {
		/*  set caption */
		poso.setCaption(dto.getCaption() );

		/*  set historyToken */
		poso.setHistoryToken(dto.getHistoryToken() );

	}

	protected void mergeProxy2UnmanagedPoso(CommandResultHyperlinkDto dto, final CommandResultHyperlink poso)  throws ExpectedException {
		/*  set caption */
		if(dto.isCaptionModified()){
			poso.setCaption(dto.getCaption() );
		}

		/*  set historyToken */
		if(dto.isHistoryTokenModified()){
			poso.setHistoryToken(dto.getHistoryToken() );
		}

	}

	public CommandResultHyperlink loadAndMergePoso(CommandResultHyperlinkDto dto)  throws ExpectedException {
		CommandResultHyperlink poso = loadPoso(dto);
		if(null != poso){
			mergePoso(dto, poso);
			return poso;
		}
		return createPoso(dto);
	}

	public void postProcessCreate(CommandResultHyperlinkDto dto, CommandResultHyperlink poso)  {
	}


	public void postProcessCreateUnmanaged(CommandResultHyperlinkDto dto, CommandResultHyperlink poso)  {
	}


	public void postProcessLoad(CommandResultHyperlinkDto dto, CommandResultHyperlink poso)  {
	}


	public void postProcessMerge(CommandResultHyperlinkDto dto, CommandResultHyperlink poso)  {
	}


	public void postProcessInstantiate(CommandResultHyperlink poso)  {
	}



}
