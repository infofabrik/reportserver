package net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.CompiledPNGJasperReportDto;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.CompiledPNGJasperReport;
import net.datenwerke.rs.base.service.reportengines.jasper.output.object.dtogen.CompiledPNGJasperReport2DtoGenerator;

/**
 * Poso2DtoGenerator for CompiledPNGJasperReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledPNGJasperReport2DtoGenerator implements Poso2DtoGenerator<CompiledPNGJasperReport,CompiledPNGJasperReportDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CompiledPNGJasperReport2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CompiledPNGJasperReportDto instantiateDto(CompiledPNGJasperReport poso)  {
		CompiledPNGJasperReportDto dto = new CompiledPNGJasperReportDto();
		return dto;
	}

	public CompiledPNGJasperReportDto createDto(CompiledPNGJasperReport poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CompiledPNGJasperReportDto dto = new CompiledPNGJasperReportDto();
		dto.setDtoView(here);


		return dto;
	}


}
