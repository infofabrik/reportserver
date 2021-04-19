package net.datenwerke.rs.birt.service.reportengine.output.object.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.reportengines.dto.CompiledPNGBirtReportDto;
import net.datenwerke.rs.birt.service.reportengine.output.object.CompiledPNGBirtReport;
import net.datenwerke.rs.birt.service.reportengine.output.object.dtogen.CompiledPNGBirtReport2DtoGenerator;

/**
 * Poso2DtoGenerator for CompiledPNGBirtReport
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CompiledPNGBirtReport2DtoGenerator implements Poso2DtoGenerator<CompiledPNGBirtReport,CompiledPNGBirtReportDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CompiledPNGBirtReport2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CompiledPNGBirtReportDto instantiateDto(CompiledPNGBirtReport poso)  {
		CompiledPNGBirtReportDto dto = new CompiledPNGBirtReportDto();
		return dto;
	}

	public CompiledPNGBirtReportDto createDto(CompiledPNGBirtReport poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CompiledPNGBirtReportDto dto = new CompiledPNGBirtReportDto();
		dto.setDtoView(here);


		return dto;
	}


}
