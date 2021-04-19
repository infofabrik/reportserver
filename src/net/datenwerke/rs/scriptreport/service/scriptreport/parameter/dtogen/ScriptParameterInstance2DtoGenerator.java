package net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.dto.ScriptParameterInstanceDto;
import net.datenwerke.rs.scriptreport.service.scriptreport.parameter.ScriptParameterInstance;
import net.datenwerke.rs.scriptreport.service.scriptreport.parameter.dtogen.ScriptParameterInstance2DtoGenerator;

/**
 * Poso2DtoGenerator for ScriptParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ScriptParameterInstance2DtoGenerator implements Poso2DtoGenerator<ScriptParameterInstance,ScriptParameterInstanceDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ScriptParameterInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ScriptParameterInstanceDto instantiateDto(ScriptParameterInstance poso)  {
		ScriptParameterInstanceDto dto = new ScriptParameterInstanceDto();
		return dto;
	}

	public ScriptParameterInstanceDto createDto(ScriptParameterInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ScriptParameterInstanceDto dto = new ScriptParameterInstanceDto();
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

			/*  set value */
			dto.setValue(poso.getValue() );

		}

		return dto;
	}


}
