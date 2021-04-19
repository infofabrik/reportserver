package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultHyperlinkDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultHyperlinkDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultHyperlink;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultHyperlink2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CommandResultHyperlink
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CommandResultHyperlink2DtoGenerator implements Poso2DtoGenerator<CommandResultHyperlink,CommandResultHyperlinkDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CommandResultHyperlink2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CommandResultHyperlinkDtoDec instantiateDto(CommandResultHyperlink poso)  {
		CommandResultHyperlinkDtoDec dto = new CommandResultHyperlinkDtoDec();
		return dto;
	}

	public CommandResultHyperlinkDtoDec createDto(CommandResultHyperlink poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CommandResultHyperlinkDtoDec dto = new CommandResultHyperlinkDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set caption */
			dto.setCaption(StringEscapeUtils.escapeXml(StringUtils.left(poso.getCaption(),8192)));

			/*  set historyToken */
			dto.setHistoryToken(StringEscapeUtils.escapeXml(StringUtils.left(poso.getHistoryToken(),8192)));

		}

		return dto;
	}


}
