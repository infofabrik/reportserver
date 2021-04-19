package net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.CompiledHTMLTableReportDto;
import net.datenwerke.rs.base.service.reportengines.table.output.object.CompiledHTMLTableReport;
import net.datenwerke.rs.base.service.reportengines.table.output.object.dtogen.CompiledHTMLTableReport2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CompiledHTMLTableReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledHTMLTableReport2DtoGenerator implements Poso2DtoGenerator<CompiledHTMLTableReport,CompiledHTMLTableReportDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CompiledHTMLTableReport2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CompiledHTMLTableReportDto instantiateDto(CompiledHTMLTableReport poso)  {
		CompiledHTMLTableReportDto dto = new CompiledHTMLTableReportDto();
		return dto;
	}

	public CompiledHTMLTableReportDto createDto(CompiledHTMLTableReport poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CompiledHTMLTableReportDto dto = new CompiledHTMLTableReportDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set report */
			dto.setReport(StringEscapeUtils.escapeXml(StringUtils.left(poso.getReport(),8192)));

		}

		return dto;
	}


}
