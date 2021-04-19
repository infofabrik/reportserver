package net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkContainerDto;
import net.datenwerke.rs.core.client.datasinkmanager.dto.DatasinkDefinitionDto;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkContainer;
import net.datenwerke.rs.core.service.datasinkmanager.entities.dtogen.DatasinkContainer2DtoGenerator;

/**
 * Poso2DtoGenerator for DatasinkContainer
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasinkContainer2DtoGenerator implements Poso2DtoGenerator<DatasinkContainer,DatasinkContainerDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasinkContainer2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasinkContainerDto instantiateDto(DatasinkContainer poso)  {
		DatasinkContainerDto dto = new DatasinkContainerDto();
		return dto;
	}

	public DatasinkContainerDto createDto(DatasinkContainer poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasinkContainerDto dto = new DatasinkContainerDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set datasink */
			Object tmpDtoDatasinkDefinitionDtogetDatasink = dtoServiceProvider.get().createDto(poso.getDatasink(), referenced, referenced);
			dto.setDatasink((DatasinkDefinitionDto)tmpDtoDatasinkDefinitionDtogetDatasink);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoDatasinkDefinitionDtogetDatasink, poso.getDatasink(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setDatasink((DatasinkDefinitionDto)refDto);
				}
			});

		}

		return dto;
	}


}
