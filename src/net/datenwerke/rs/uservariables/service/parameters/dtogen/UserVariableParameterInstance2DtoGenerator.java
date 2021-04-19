package net.datenwerke.rs.uservariables.service.parameters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.UserVariableParameterInstanceDto;
import net.datenwerke.rs.uservariables.client.parameters.dto.decorator.UserVariableParameterInstanceDtoDec;
import net.datenwerke.rs.uservariables.service.parameters.UserVariableParameterInstance;
import net.datenwerke.rs.uservariables.service.parameters.dtogen.UserVariableParameterInstance2DtoGenerator;

/**
 * Poso2DtoGenerator for UserVariableParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class UserVariableParameterInstance2DtoGenerator implements Poso2DtoGenerator<UserVariableParameterInstance,UserVariableParameterInstanceDtoDec> {

	private final net.datenwerke.rs.uservariables.service.parameters.postprocessor.UserVariableInstance2DtoPostProcessor postProcessor_1;
	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public UserVariableParameterInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider,
		net.datenwerke.rs.uservariables.service.parameters.postprocessor.UserVariableInstance2DtoPostProcessor postProcessor_1	){
		this.dtoServiceProvider = dtoServiceProvider;
		this.postProcessor_1 = postProcessor_1;
	}

	public UserVariableParameterInstanceDtoDec instantiateDto(UserVariableParameterInstance poso)  {
		UserVariableParameterInstanceDtoDec dto = new UserVariableParameterInstanceDtoDec();

		/* post processing */
		this.postProcessor_1.dtoInstantiated(poso, dto);
		return dto;
	}

	public UserVariableParameterInstanceDtoDec createDto(UserVariableParameterInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final UserVariableParameterInstanceDtoDec dto = new UserVariableParameterInstanceDtoDec();
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

		/* post processing */
		this.postProcessor_1.dtoCreated(poso, dto);

		return dto;
	}


}
