package net.datenwerke.rs.base.service.datasources.connectors.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.TextDatasourceConnectorDto;
import net.datenwerke.rs.base.service.datasources.connectors.TextDatasourceConnector;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.TextDatasourceConnector2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TextDatasourceConnector
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TextDatasourceConnector2DtoGenerator implements Poso2DtoGenerator<TextDatasourceConnector,TextDatasourceConnectorDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TextDatasourceConnector2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TextDatasourceConnectorDto instantiateDto(TextDatasourceConnector poso)  {
		TextDatasourceConnectorDto dto = new TextDatasourceConnectorDto();
		return dto;
	}

	public TextDatasourceConnectorDto createDto(TextDatasourceConnector poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TextDatasourceConnectorDto dto = new TextDatasourceConnectorDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.ALL) >= 0){
			/*  set data */
			dto.setData(StringEscapeUtils.escapeXml(StringUtils.left(poso.getData(),8192)));

		}

		return dto;
	}


}
