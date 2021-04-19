package net.datenwerke.rs.base.service.datasources.connectors.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.datasources.dto.DatasourceConnectorConfigDto;
import net.datenwerke.rs.base.service.datasources.connectors.DatasourceConnectorConfig;
import net.datenwerke.rs.base.service.datasources.connectors.dtogen.DatasourceConnectorConfig2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DatasourceConnectorConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceConnectorConfig2DtoGenerator implements Poso2DtoGenerator<DatasourceConnectorConfig,DatasourceConnectorConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceConnectorConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceConnectorConfigDto instantiateDto(DatasourceConnectorConfig poso)  {
		DatasourceConnectorConfigDto dto = new DatasourceConnectorConfigDto();
		return dto;
	}

	public DatasourceConnectorConfigDto createDto(DatasourceConnectorConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceConnectorConfigDto dto = new DatasourceConnectorConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set value */
			dto.setValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getValue(),8192)));

		}

		return dto;
	}


}
