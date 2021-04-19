package net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledHTMLJasperReportDto;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledHTMLJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.CompiledHTMLJasperReport2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CompiledHTMLJasperReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledHTMLJasperReport2DtoGenerator implements Poso2DtoGenerator<CompiledHTMLJasperReport,CompiledHTMLJasperReportDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CompiledHTMLJasperReport2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CompiledHTMLJasperReportDto instantiateDto(CompiledHTMLJasperReport poso)  {
		CompiledHTMLJasperReportDto dto = new CompiledHTMLJasperReportDto();
		return dto;
	}

	public CompiledHTMLJasperReportDto createDto(CompiledHTMLJasperReport poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CompiledHTMLJasperReportDto dto = new CompiledHTMLJasperReportDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set report */
			dto.setReport(StringEscapeUtils.escapeXml(StringUtils.left(poso.getReport(),8192)));

		}

		return dto;
	}


}
