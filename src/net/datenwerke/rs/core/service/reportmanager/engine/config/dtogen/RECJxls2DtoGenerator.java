package net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportexporter.dto.RECJxlsDto;
import net.datenwerke.rs.core.client.reportexporter.dto.decorator.RECJxlsDtoDec;
import net.datenwerke.rs.core.service.reportmanager.engine.config.RECJxls;
import net.datenwerke.rs.core.service.reportmanager.engine.config.dtogen.RECJxls2DtoGenerator;

/**
 * Poso2DtoGenerator for RECJxls
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RECJxls2DtoGenerator implements Poso2DtoGenerator<RECJxls,RECJxlsDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RECJxls2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RECJxlsDtoDec instantiateDto(RECJxls poso)  {
		RECJxlsDtoDec dto = new RECJxlsDtoDec();
		return dto;
	}

	public RECJxlsDtoDec createDto(RECJxls poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RECJxlsDtoDec dto = new RECJxlsDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set currencyColumnWidth */
			dto.setCurrencyColumnWidth(poso.getCurrencyColumnWidth() );

			/*  set dateColumnWidth */
			dto.setDateColumnWidth(poso.getDateColumnWidth() );

			/*  set jxlsReport */
			dto.setJxlsReport(poso.isJxlsReport() );

			/*  set numberColumnWidth */
			dto.setNumberColumnWidth(poso.getNumberColumnWidth() );

			/*  set textColumnWidth */
			dto.setTextColumnWidth(poso.getTextColumnWidth() );

		}

		return dto;
	}


}
