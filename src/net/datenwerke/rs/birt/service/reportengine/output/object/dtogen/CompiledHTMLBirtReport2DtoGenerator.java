package net.datenwerke.rs.birt.service.reportengine.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledHTMLBirtReportDto;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledHTMLBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.CompiledHTMLBirtReport2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CompiledHTMLBirtReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledHTMLBirtReport2DtoGenerator implements Poso2DtoGenerator<CompiledHTMLBirtReport,CompiledHTMLBirtReportDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CompiledHTMLBirtReport2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CompiledHTMLBirtReportDto instantiateDto(CompiledHTMLBirtReport poso)  {
		CompiledHTMLBirtReportDto dto = new CompiledHTMLBirtReportDto();
		return dto;
	}

	public CompiledHTMLBirtReportDto createDto(CompiledHTMLBirtReport poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CompiledHTMLBirtReportDto dto = new CompiledHTMLBirtReportDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set report */
			dto.setReport(StringEscapeUtils.escapeXml(StringUtils.left(poso.getReport(),8192)));

		}

		return dto;
	}


}
