package net.datenwerke.rs.saiku.service.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.saiku.client.datasource.dto.MondrianDatasourceConfigDto;
import net.datenwerke.rs.saiku.service.datasource.MondrianDatasourceConfig;
import net.datenwerke.rs.saiku.service.datasource.dtogen.MondrianDatasourceConfig2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MondrianDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MondrianDatasourceConfig2DtoGenerator implements Poso2DtoGenerator<MondrianDatasourceConfig,MondrianDatasourceConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MondrianDatasourceConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MondrianDatasourceConfigDto instantiateDto(MondrianDatasourceConfig poso)  {
		MondrianDatasourceConfigDto dto = new MondrianDatasourceConfigDto();
		return dto;
	}

	public MondrianDatasourceConfigDto createDto(MondrianDatasourceConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MondrianDatasourceConfigDto dto = new MondrianDatasourceConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set cubeName */
			dto.setCubeName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getCubeName(),8192)));

		}

		return dto;
	}


}
