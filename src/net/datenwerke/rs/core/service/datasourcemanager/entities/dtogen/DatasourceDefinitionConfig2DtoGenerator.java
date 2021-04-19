package net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.datasourcemanager.dto.DatasourceDefinitionConfigDto;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinitionConfig;
import net.datenwerke.rs.core.service.datasourcemanager.entities.dtogen.DatasourceDefinitionConfig2DtoGenerator;

/**
 * Poso2DtoGenerator for DatasourceDefinitionConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceDefinitionConfig2DtoGenerator implements Poso2DtoGenerator<DatasourceDefinitionConfig,DatasourceDefinitionConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceDefinitionConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceDefinitionConfigDto instantiateDto(DatasourceDefinitionConfig poso)  {
		DatasourceDefinitionConfigDto dto = new DatasourceDefinitionConfigDto();
		return dto;
	}

	public DatasourceDefinitionConfigDto createDto(DatasourceDefinitionConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceDefinitionConfigDto dto = new DatasourceDefinitionConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}

		return dto;
	}


}
