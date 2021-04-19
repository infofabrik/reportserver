package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultAnchorDto;
import net.datenwerke.rs.terminal.client.terminal.dto.decorator.CommandResultAnchorDtoDec;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResultAnchor;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CommandResultAnchor2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CommandResultAnchor
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CommandResultAnchor2DtoGenerator implements Poso2DtoGenerator<CommandResultAnchor,CommandResultAnchorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CommandResultAnchor2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CommandResultAnchorDtoDec instantiateDto(CommandResultAnchor poso)  {
		CommandResultAnchorDtoDec dto = new CommandResultAnchorDtoDec();
		return dto;
	}

	public CommandResultAnchorDtoDec createDto(CommandResultAnchor poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CommandResultAnchorDtoDec dto = new CommandResultAnchorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set target */
			dto.setTarget(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTarget(),8192)));

			/*  set text */
			dto.setText(StringEscapeUtils.escapeXml(StringUtils.left(poso.getText(),8192)));

			/*  set url */
			dto.setUrl(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUrl(),8192)));

		}

		return dto;
	}


}
