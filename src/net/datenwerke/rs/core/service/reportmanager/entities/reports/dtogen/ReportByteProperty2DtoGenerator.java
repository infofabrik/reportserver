package net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportBytePropertyDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportByteProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportByteProperty2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ReportByteProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportByteProperty2DtoGenerator implements Poso2DtoGenerator<ReportByteProperty,ReportBytePropertyDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ReportByteProperty2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ReportBytePropertyDto instantiateDto(ReportByteProperty poso)  {
		ReportBytePropertyDto dto = new ReportBytePropertyDto();
		return dto;
	}

	public ReportBytePropertyDto createDto(ReportByteProperty poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReportBytePropertyDto dto = new ReportBytePropertyDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}

		return dto;
	}


}
