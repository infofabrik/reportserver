package net.datenwerke.rs.saiku.service.saiku.reportengine.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.saiku.client.saiku.dto.RECSaikuChartDto;
import net.datenwerke.rs.saiku.client.saiku.dto.decorator.RECSaikuChartDtoDec;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.RECSaikuChart;
import net.datenwerke.rs.saiku.service.saiku.reportengine.config.dtogen.RECSaikuChart2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for RECSaikuChart
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RECSaikuChart2DtoGenerator implements Poso2DtoGenerator<RECSaikuChart,RECSaikuChartDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RECSaikuChart2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RECSaikuChartDtoDec instantiateDto(RECSaikuChart poso)  {
		RECSaikuChartDtoDec dto = new RECSaikuChartDtoDec();
		return dto;
	}

	public RECSaikuChartDtoDec createDto(RECSaikuChart poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RECSaikuChartDtoDec dto = new RECSaikuChartDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set type */
			dto.setType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getType(),8192)));

		}

		return dto;
	}


}
