package net.datenwerke.rs.base.service.parameters.string.post;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterDefinitionDto;
import net.datenwerke.rs.base.service.parameters.string.TextParameterDefinition;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;

import com.google.inject.Inject;

public class TextParameterDefinitionPost implements Dto2PosoPostProcessor<TextParameterDefinitionDto, TextParameterDefinition>, Poso2DtoPostProcessor<TextParameterDefinition, TextParameterDefinitionDto> {

	private final I18nToolsService i18nTools;

	@Inject
	public TextParameterDefinitionPost(I18nToolsService i18nTools) {
		this.i18nTools = i18nTools;
	}

	@Override
	public void dtoCreated(TextParameterDefinition poso, TextParameterDefinitionDto dto) {
		system2User(dto, poso);
	}

	@Override
	public void dtoInstantiated(TextParameterDefinition poso,
			TextParameterDefinitionDto dto) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void posoCreated(TextParameterDefinitionDto dto,
			TextParameterDefinition poso) {
		user2System(dto, poso);
	}

	@Override
	public void posoCreatedUnmanaged(TextParameterDefinitionDto dto,
			TextParameterDefinition poso) {
		user2System(dto, poso);
	}

	@Override
	public void posoInstantiated(TextParameterDefinition poso) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void posoLoaded(TextParameterDefinitionDto dto,
			TextParameterDefinition poso) {
		user2System(dto, poso);
	}

	@Override
	public void posoMerged(TextParameterDefinitionDto dto,
			TextParameterDefinition poso) {
		user2System(dto, poso);
	}

	private void user2System(TextParameterDefinitionDto dto,
			TextParameterDefinition poso) {
		if(null != poso.getDefaultValue() &&  null != poso.getReturnType()){
			switch(poso.getReturnType()){
			case Integer:
			case Long:
			case BigDecimal:
			case Float:
			case Double:
				String number = i18nTools.translateNumberFromUserToSystem(poso.getDefaultValue());
				i18nTools.validateSystemNumber(number);
				poso.setDefaultValue(number);
				break;
			}
		}
	}
	
	private void system2User(TextParameterDefinitionDto dto,
			TextParameterDefinition poso) {
		if(null != dto.getDefaultValue() && null != poso.getReturnType()){
			switch(poso.getReturnType()){
			case Integer:
			case Long:
			case BigDecimal:
			case Float:
			case Double:
				dto.setDefaultValue(i18nTools.translateNumberFromSystemToUser(dto.getDefaultValue()));
				break;
			}
		}
	}

}
