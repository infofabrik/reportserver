package net.datenwerke.rs.terminal.service.terminal.obj.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.terminal.client.terminal.dto.CreJavaScriptDto;
import net.datenwerke.rs.terminal.service.terminal.obj.CreJavaScript;
import net.datenwerke.rs.terminal.service.terminal.obj.dtogen.CreJavaScript2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CreJavaScript
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CreJavaScript2DtoGenerator implements Poso2DtoGenerator<CreJavaScript,CreJavaScriptDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CreJavaScript2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CreJavaScriptDto instantiateDto(CreJavaScript poso)  {
		CreJavaScriptDto dto = new CreJavaScriptDto();
		return dto;
	}

	public CreJavaScriptDto createDto(CreJavaScript poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CreJavaScriptDto dto = new CreJavaScriptDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set data */
			dto.setData(StringEscapeUtils.escapeXml(poso.getData() ));

		}

		return dto;
	}


}
