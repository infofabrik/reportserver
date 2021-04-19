package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultLineDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultLineDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultLine;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultLine2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CommandResultLine
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CommandResultLine2DtoGenerator implements Poso2DtoGenerator<CommandResultLine,CommandResultLineDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CommandResultLine2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CommandResultLineDtoDec instantiateDto(CommandResultLine poso)  {
		CommandResultLineDtoDec dto = new CommandResultLineDtoDec();
		return dto;
	}

	public CommandResultLineDtoDec createDto(CommandResultLine poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CommandResultLineDtoDec dto = new CommandResultLineDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set line */
			dto.setLine(StringEscapeUtils.escapeXml(StringUtils.left(poso.getLine(),8192)));

		}

		return dto;
	}


}
