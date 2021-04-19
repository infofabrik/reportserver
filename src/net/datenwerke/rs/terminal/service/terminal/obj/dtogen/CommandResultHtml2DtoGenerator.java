package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHtmlDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHtmlDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHtml;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultHtml2DtoGenerator;

/**
 * Poso2DtoGenerator for CommandResultHtml
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CommandResultHtml2DtoGenerator implements Poso2DtoGenerator<CommandResultHtml,CommandResultHtmlDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CommandResultHtml2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CommandResultHtmlDtoDec instantiateDto(CommandResultHtml poso)  {
		CommandResultHtmlDtoDec dto = new CommandResultHtmlDtoDec();
		return dto;
	}

	public CommandResultHtmlDtoDec createDto(CommandResultHtml poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CommandResultHtmlDtoDec dto = new CommandResultHtmlDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set html */
			dto.setHtml(poso.getHtml() );

		}

		return dto;
	}


}
