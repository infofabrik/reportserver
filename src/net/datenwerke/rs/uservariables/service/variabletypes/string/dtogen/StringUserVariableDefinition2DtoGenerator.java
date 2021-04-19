package net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.client.variabletypes.string.dto.StringUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.service.variabletypes.string.StringUserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.string.dtogen.StringUserVariableDefinition2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for StringUserVariableDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class StringUserVariableDefinition2DtoGenerator implements Poso2DtoGenerator<StringUserVariableDefinition,StringUserVariableDefinitionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public StringUserVariableDefinition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public StringUserVariableDefinitionDto instantiateDto(StringUserVariableDefinition poso)  {
		StringUserVariableDefinitionDto dto = new StringUserVariableDefinitionDto();
		return dto;
	}

	public StringUserVariableDefinitionDto createDto(StringUserVariableDefinition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final StringUserVariableDefinitionDto dto = new StringUserVariableDefinitionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set height */
			dto.setHeight(poso.getHeight() );

			/*  set width */
			dto.setWidth(poso.getWidth() );

		}

		return dto;
	}


}
