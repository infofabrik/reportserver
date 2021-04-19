package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CreOverlayDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CreOverlay;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CreOverlay2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CreOverlay
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CreOverlay2DtoGenerator implements Poso2DtoGenerator<CreOverlay,CreOverlayDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CreOverlay2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CreOverlayDto instantiateDto(CreOverlay poso)  {
		CreOverlayDto dto = new CreOverlayDto();
		return dto;
	}

	public CreOverlayDto createDto(CreOverlay poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CreOverlayDto dto = new CreOverlayDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set cssProperties */
			dto.setCssProperties(poso.getCssProperties() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set remove */
			dto.setRemove(poso.isRemove() );

			/*  set text */
			dto.setText(StringEscapeUtils.escapeXml(StringUtils.left(poso.getText(),8192)));

		}

		return dto;
	}


}
