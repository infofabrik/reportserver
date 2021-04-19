package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionDto;
import net.datenwerke.rs.scripting.client.scripting.dto.DisplayConditionTypeDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.DisplayCondition;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.DisplayCondition2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DisplayCondition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DisplayCondition2DtoGenerator implements Poso2DtoGenerator<DisplayCondition,DisplayConditionDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DisplayCondition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DisplayConditionDto instantiateDto(DisplayCondition poso)  {
		DisplayConditionDto dto = new DisplayConditionDto();
		return dto;
	}

	public DisplayConditionDto createDto(DisplayCondition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DisplayConditionDto dto = new DisplayConditionDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set propertyName */
			dto.setPropertyName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getPropertyName(),8192)));

			/*  set type */
			Object tmpDtoDisplayConditionTypeDtogetType = dtoServiceProvider.get().createDto(poso.getType(), referenced, referenced);
			dto.setType((DisplayConditionTypeDto)tmpDtoDisplayConditionTypeDtogetType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDisplayConditionTypeDtogetType, poso.getType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setType((DisplayConditionTypeDto)refDto);
				}
			});

			/*  set value */
			dto.setValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getValue(),8192)));

		}

		return dto;
	}


}
