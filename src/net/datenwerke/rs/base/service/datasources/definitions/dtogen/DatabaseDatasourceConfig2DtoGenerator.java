package net.datenwerke.rs.base.service.datasources.definitions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.DatabaseDatasourceConfigDto;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.definitions.dtogen.DatabaseDatasourceConfig2DtoGenerator;

/**
 * Poso2DtoGenerator for DatabaseDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatabaseDatasourceConfig2DtoGenerator implements Poso2DtoGenerator<DatabaseDatasourceConfig,DatabaseDatasourceConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatabaseDatasourceConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatabaseDatasourceConfigDto instantiateDto(DatabaseDatasourceConfig poso)  {
		DatabaseDatasourceConfigDto dto = new DatabaseDatasourceConfigDto();
		return dto;
	}

	public DatabaseDatasourceConfigDto createDto(DatabaseDatasourceConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatabaseDatasourceConfigDto dto = new DatabaseDatasourceConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set query */
			dto.setQuery(poso.getQuery() );

		}

		return dto;
	}


}
