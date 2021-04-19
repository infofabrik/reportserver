package net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.uservariables.client.uservariables.locale.UserVariablesMessages;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableDefinition;
import net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.ListUserVariableDefinition2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ListUserVariableDefinition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ListUserVariableDefinition2DtoGenerator implements Poso2DtoGenerator<ListUserVariableDefinition,ListUserVariableDefinitionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ListUserVariableDefinition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ListUserVariableDefinitionDto instantiateDto(ListUserVariableDefinition poso)  {
		ListUserVariableDefinitionDto dto = new ListUserVariableDefinitionDto();
		return dto;
	}

	public ListUserVariableDefinitionDto createDto(ListUserVariableDefinition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ListUserVariableDefinitionDto dto = new ListUserVariableDefinitionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}

		return dto;
	}


}
