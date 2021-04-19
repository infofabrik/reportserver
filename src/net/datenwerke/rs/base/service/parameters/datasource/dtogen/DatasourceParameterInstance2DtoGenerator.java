package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterInstanceDto;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterInstance;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.DatasourceParameterInstance2DtoGenerator;
import net.datenwerke.rs.core.client.parameters.dto.ParameterDefinitionDto;

/**
 * Poso2DtoGenerator for DatasourceParameterInstance
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceParameterInstance2DtoGenerator implements Poso2DtoGenerator<DatasourceParameterInstance,DatasourceParameterInstanceDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceParameterInstance2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceParameterInstanceDto instantiateDto(DatasourceParameterInstance poso)  {
		DatasourceParameterInstanceDto dto = new DatasourceParameterInstanceDto();
		return dto;
	}

	public DatasourceParameterInstanceDto createDto(DatasourceParameterInstance poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceParameterInstanceDto dto = new DatasourceParameterInstanceDto();
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
			/*  set multiValue */
			final List<DatasourceParameterDataDto> col_multiValue = new ArrayList<DatasourceParameterDataDto>();
			if( null != poso.getMultiValue()){
				for(DatasourceParameterData refPoso : poso.getMultiValue()){
					final Object tmpDtoDatasourceParameterDataDtogetMultiValue = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_multiValue.add((DatasourceParameterDataDto) tmpDtoDatasourceParameterDataDtogetMultiValue);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceParameterDataDtogetMultiValue, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (multiValue)");
							int tmp_index = col_multiValue.indexOf(tmpDtoDatasourceParameterDataDtogetMultiValue);
							col_multiValue.set(tmp_index,(DatasourceParameterDataDto) dto);
						}
					});
				}
				dto.setMultiValue(col_multiValue);
			}

			/*  set singleValue */
			Object tmpDtoDatasourceParameterDataDtogetSingleValue = dtoServiceProvider.get().createDto(poso.getSingleValue(), here, referenced);
			dto.setSingleValue((DatasourceParameterDataDto)tmpDtoDatasourceParameterDataDtogetSingleValue);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceParameterDataDtogetSingleValue, poso.getSingleValue(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setSingleValue((DatasourceParameterDataDto)refDto);
				}
			});

			/*  set stillDefault */
			dto.setStillDefault(poso.isStillDefault() );

		}

		return dto;
	}


}
