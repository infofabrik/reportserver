package net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceContainerDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.DatasourceContainer2DtoGenerator;

/**
 * Poso2DtoGenerator for DatasourceContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceContainer2DtoGenerator implements Poso2DtoGenerator<DatasourceContainer,DatasourceContainerDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceContainer2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceContainerDto instantiateDto(DatasourceContainer poso)  {
		DatasourceContainerDto dto = new DatasourceContainerDto();
		return dto;
	}

	public DatasourceContainerDto createDto(DatasourceContainer poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceContainerDto dto = new DatasourceContainerDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set datasource */
			Object tmpDtoDatasourceDefinitionDtogetDatasource = dtoServiceProvider.get().createDto(poso.getDatasource(), referenced, referenced);
			dto.setDatasource((DatasourceDefinitionDto)tmpDtoDatasourceDefinitionDtogetDatasource);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceDefinitionDtogetDatasource, poso.getDatasource(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatasource((DatasourceDefinitionDto)refDto);
				}
			});

			/*  set datasourceConfig */
			Object tmpDtoDatasourceDefinitionConfigDtogetDatasourceConfig = dtoServiceProvider.get().createDto(poso.getDatasourceConfig(), here, referenced);
			dto.setDatasourceConfig((DatasourceDefinitionConfigDto)tmpDtoDatasourceDefinitionConfigDtogetDatasourceConfig);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasourceDefinitionConfigDtogetDatasourceConfig, poso.getDatasourceConfig(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatasourceConfig((DatasourceDefinitionConfigDto)refDto);
				}
			});

		}

		return dto;
	}


}
