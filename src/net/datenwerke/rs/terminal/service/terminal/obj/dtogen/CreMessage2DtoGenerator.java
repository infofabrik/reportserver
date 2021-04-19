package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CreMessageDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CreMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CreMessage2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CreMessage
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CreMessage2DtoGenerator implements Poso2DtoGenerator<CreMessage,CreMessageDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CreMessage2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CreMessageDto instantiateDto(CreMessage poso)  {
		CreMessageDto dto = new CreMessageDto();
		return dto;
	}

	public CreMessageDto createDto(CreMessage poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CreMessageDto dto = new CreMessageDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set html */
			dto.setHtml(poso.getHtml() );

			/*  set text */
			dto.setText(StringEscapeUtils.escapeXml(StringUtils.left(poso.getText(),8192)));

			/*  set title */
			dto.setTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTitle(),8192)));

			/*  set width */
			dto.setWidth(poso.getWidth() );

			/*  set windowTitle */
			dto.setWindowTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getWindowTitle(),8192)));

		}

		return dto;
	}


}
