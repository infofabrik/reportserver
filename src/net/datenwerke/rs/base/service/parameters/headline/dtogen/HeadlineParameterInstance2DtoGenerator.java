package net.datenwerke.rs.base.service.parameters.headline.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.headline.dto.HeadlineParameterInstanceDto;
import net.datenwerke.rs.base.service.parameters.headline.HeadlineParameterInstance;
import net.datenwerke.rs.base.service.parameters.headline.dtogen.HeadlineParameterInstance2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;

/**
 * Poso2DtoGenerator for HeadlineParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class HeadlineParameterInstance2DtoGenerator implements Poso2DtoGenerator<HeadlineParameterInstance,HeadlineParameterInstanceDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public HeadlineParameterInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public HeadlineParameterInstanceDto instantiateDto(HeadlineParameterInstance poso)  {
		HeadlineParameterInstanceDto dto = new HeadlineParameterInstanceDto();
		return dto;
	}

	public HeadlineParameterInstanceDto createDto(HeadlineParameterInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final HeadlineParameterInstanceDto dto = new HeadlineParameterInstanceDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set definition */
			Object tmpDtoParameterDefinitionDtogetDefinition = dtoServiceProvider.get().createDto(poso.getDefinition(), referenced, referenced);
			dto.setDefinition((ParameterDefinitionDto)tmpDtoParameterDefinitionDtogetDefinition);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoParameterDefinitionDtogetDefinition, poso.getDefinition(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDefinition((ParameterDefinitionDto)refDto);
				}
			});

			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set stillDefault */
			dto.setStillDefault(poso.isStillDefault() );

		}

		return dto;
	}


}
