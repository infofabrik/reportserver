package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.AddStatusbBarLabelExtensionDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddStatusbBarLabelExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddStatusbBarLabelExtension2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AddStatusbBarLabelExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddStatusbBarLabelExtension2DtoGenerator implements Poso2DtoGenerator<AddStatusbBarLabelExtension,AddStatusbBarLabelExtensionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AddStatusbBarLabelExtension2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AddStatusbBarLabelExtensionDto instantiateDto(AddStatusbBarLabelExtension poso)  {
		AddStatusbBarLabelExtensionDto dto = new AddStatusbBarLabelExtensionDto();
		return dto;
	}

	public AddStatusbBarLabelExtensionDto createDto(AddStatusbBarLabelExtension poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AddStatusbBarLabelExtensionDto dto = new AddStatusbBarLabelExtensionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set clear */
			dto.setClear(poso.isClear() );

			/*  set icon */
			dto.setIcon(StringEscapeUtils.escapeXml(StringUtils.left(poso.getIcon(),8192)));

			/*  set label */
			dto.setLabel(StringEscapeUtils.escapeXml(StringUtils.left(poso.getLabel(),8192)));

			/*  set left */
			dto.setLeft(poso.isLeft() );

		}

		return dto;
	}


}
