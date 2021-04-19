package net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.HashSet;
import java.util.Set;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.uservariables.client.uservariables.dto.UserVariableDefinitionDto;
import net.datenwerke.rs.uservariables.client.variabletypes.list.dto.ListUserVariableInstanceDto;
import net.datenwerke.rs.uservariables.service.variabletypes.list.ListUserVariableInstance;
import net.datenwerke.rs.uservariables.service.variabletypes.list.dtogen.ListUserVariableInstance2DtoGenerator;
import net.datenwerke.security.client.usermanager.dto.AbstractUserManagerNodeDto;

/**
 * Poso2DtoGenerator for ListUserVariableInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ListUserVariableInstance2DtoGenerator implements Poso2DtoGenerator<ListUserVariableInstance,ListUserVariableInstanceDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ListUserVariableInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ListUserVariableInstanceDto instantiateDto(ListUserVariableInstance poso)  {
		ListUserVariableInstanceDto dto = new ListUserVariableInstanceDto();
		return dto;
	}

	public ListUserVariableInstanceDto createDto(ListUserVariableInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ListUserVariableInstanceDto dto = new ListUserVariableInstanceDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set definition */
			Object tmpDtoUserVariableDefinitionDtogetDefinition = dtoServiceProvider.get().createDto(poso.getDefinition(), referenced, referenced);
			dto.setDefinition((UserVariableDefinitionDto)tmpDtoUserVariableDefinitionDtogetDefinition);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoUserVariableDefinitionDtogetDefinition, poso.getDefinition(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDefinition((UserVariableDefinitionDto)refDto);
				}
			});

			/*  set folk */
			Object tmpDtoAbstractUserManagerNodeDtogetFolk = dtoServiceProvider.get().createDto(poso.getFolk(), referenced, referenced);
			dto.setFolk((AbstractUserManagerNodeDto)tmpDtoAbstractUserManagerNodeDtogetFolk);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoAbstractUserManagerNodeDtogetFolk, poso.getFolk(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setFolk((AbstractUserManagerNodeDto)refDto);
				}
			});

			/*  set value */
			Set<String> col_value = new HashSet<String>();
			if( null != poso.getValue()){
				for(String obj : poso.getValue())
					col_value.add((String) obj);
				dto.setValue(col_value);
			}

		}

		return dto;
	}


}
