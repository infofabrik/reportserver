package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.AddMenuSeparatorEntryExtensionDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddMenuSeparatorEntryExtension;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddMenuSeparatorEntryExtension2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AddMenuSeparatorEntryExtension
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddMenuSeparatorEntryExtension2DtoGenerator implements Poso2DtoGenerator<AddMenuSeparatorEntryExtension,AddMenuSeparatorEntryExtensionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AddMenuSeparatorEntryExtension2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AddMenuSeparatorEntryExtensionDto instantiateDto(AddMenuSeparatorEntryExtension poso)  {
		AddMenuSeparatorEntryExtensionDto dto = new AddMenuSeparatorEntryExtensionDto();
		return dto;
	}

	public AddMenuSeparatorEntryExtensionDto createDto(AddMenuSeparatorEntryExtension poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AddMenuSeparatorEntryExtensionDto dto = new AddMenuSeparatorEntryExtensionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set menuName */
			dto.setMenuName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getMenuName(),8192)));

		}

		return dto;
	}


}
