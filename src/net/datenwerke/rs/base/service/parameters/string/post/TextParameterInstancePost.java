package net.datenwerke.rs.base.service.parameters.string.post;

import net.datenwerke.dtoservices.dtogenerator.dto2posogenerator.interfaces.Dto2PosoPostProcessor;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoPostProcessor;
import net.datenwerke.rs.base.client.parameters.string.dto.TextParameterInstanceDto;
import net.datenwerke.rs.base.service.parameters.string.TextParameterInstance;
import net.datenwerke.rs.core.service.i18ntools.I18nToolsService;

import com.google.inject.Inject;

public class TextParameterInstancePost implements Dto2PosoPostProcessor<TextParameterInstanceDto, TextParameterInstance>, Poso2DtoPostProcessor<TextParameterInstance, TextParameterInstanceDto> {
	
	private final I18nToolsService i18nTools;
	
	@Inject
	public TextParameterInstancePost(I18nToolsService i18nTools){
		this.i18nTools = i18nTools;
	}

	@Override
	public void posoCreated(TextParameterInstanceDto dto, TextParameterInstance poso) {
		user2System(dto, poso);
	}

	@Override
	public void posoCreatedUnmanaged(TextParameterInstanceDto dto,
			TextParameterInstance poso) {
		user2System(dto, poso);
		
	}

	@Override
	public void posoInstantiated(TextParameterInstance poso) {
		
	}

	@Override
	public void posoLoaded(TextParameterInstanceDto dto, TextParameterInstance poso) {
		user2System(dto, poso);
	}

	@Override
	public void posoMerged(TextParameterInstanceDto dto, TextParameterInstance poso) {
		user2System(dto, poso);
	} 


	@Override
	public void dtoCreated(TextParameterInstance poso, TextParameterInstanceDto dto) {
		system2User(poso, dto);
	}


	@Override
	public void dtoInstantiated(TextParameterInstance poso, TextParameterInstanceDto dto) {
		
	}
	
	private void system2User(TextParameterInstance poso,
			TextParameterInstanceDto dto) {
		if(null != dto.getValue() && null != poso.getDefinition() && null != poso.getDefinition().getReturnType()){
			switch(poso.getDefinition().getReturnType()){
			case Integer:
			case Long:
			case BigDecimal:
			case Float:
			case Double:
				dto.setValue(i18nTools.translateNumberFromSystemToUser(dto.getValue()));
				break;
			}
		}
	}
	
	private void user2System(TextParameterInstanceDto dto,
			TextParameterInstance poso) {
		if(null != poso.getValue() && null != poso.getDefinition() && null != poso.getDefinition().getReturnType()){
			switch(poso.getDefinition().getReturnType()){
			case Integer:
			case Long:
			case BigDecimal:
			case Float:
			case Double:
				poso.setValue(i18nTools.translateNumberFromUserToSystem(poso.getValue()));
				break;
			}
		}
	}


}