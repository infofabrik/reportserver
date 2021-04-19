package net.datenwerke.rs.base.service.parameters.datasource.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.parameters.datasource.dto.DatasourceParameterDataDto;
import net.datenwerke.rs.base.client.parameters.datasource.dto.decorator.DatasourceParameterDataDtoDec;
import net.datenwerke.rs.base.service.parameters.datasource.DatasourceParameterData;
import net.datenwerke.rs.base.service.parameters.datasource.dtogen.DatasourceParameterData2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for DatasourceParameterData
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class DatasourceParameterData2DtoGenerator implements Poso2DtoGenerator<DatasourceParameterData,DatasourceParameterDataDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public DatasourceParameterData2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public DatasourceParameterDataDtoDec instantiateDto(DatasourceParameterData poso)  {
		DatasourceParameterDataDtoDec dto = new DatasourceParameterDataDtoDec();
		return dto;
	}

	public DatasourceParameterDataDtoDec createDto(DatasourceParameterData poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final DatasourceParameterDataDtoDec dto = new DatasourceParameterDataDtoDec();
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
