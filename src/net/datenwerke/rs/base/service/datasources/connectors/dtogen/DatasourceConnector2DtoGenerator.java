package net.datenwerke.rs.base.service.datasources.connectors.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorDto;
import net.datenwerke.rs.base.client.datasources.dto.decorator.DatasourceConnectorDtoDec;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.DatasourceConnector2DtoGenerator;

/**
 * Poso2DtoGenerator for DatasourceConnector
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceConnector2DtoGenerator implements Poso2DtoGenerator<DatasourceConnector,DatasourceConnectorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceConnector2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceConnectorDtoDec instantiateDto(DatasourceConnector poso)  {
		DatasourceConnectorDtoDec dto = new DatasourceConnectorDtoDec();
		return dto;
	}

	public DatasourceConnectorDtoDec createDto(DatasourceConnector poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceConnectorDtoDec dto = new DatasourceConnectorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}

		return dto;
	}


}
