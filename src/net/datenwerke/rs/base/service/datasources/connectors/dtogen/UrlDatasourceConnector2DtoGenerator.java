package net.datenwerke.rs.base.service.datasources.connectors.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.UrlDatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.connectors.UrlDatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.UrlDatasourceConnector2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for UrlDatasourceConnector
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class UrlDatasourceConnector2DtoGenerator implements Poso2DtoGenerator<UrlDatasourceConnector,UrlDatasourceConnectorDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public UrlDatasourceConnector2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public UrlDatasourceConnectorDto instantiateDto(UrlDatasourceConnector poso)  {
		UrlDatasourceConnectorDto dto = new UrlDatasourceConnectorDto();
		return dto;
	}

	public UrlDatasourceConnectorDto createDto(UrlDatasourceConnector poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final UrlDatasourceConnectorDto dto = new UrlDatasourceConnectorDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.ALL) >= 0){
			/*  set url */
			dto.setUrl(StringEscapeUtils.escapeXml(StringUtils.left(poso.getUrl(),8192)));

		}

		return dto;
	}


}
