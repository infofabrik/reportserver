package net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportStringPropertyDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportStringProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportStringProperty2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ReportStringProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportStringProperty2DtoGenerator implements Poso2DtoGenerator<ReportStringProperty,ReportStringPropertyDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ReportStringProperty2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ReportStringPropertyDto instantiateDto(ReportStringProperty poso)  {
		ReportStringPropertyDto dto = new ReportStringPropertyDto();
		return dto;
	}

	public ReportStringPropertyDto createDto(ReportStringProperty poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReportStringPropertyDto dto = new ReportStringPropertyDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set strValue */
			dto.setStrValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getStrValue(),8192)));

		}

		return dto;
	}


}
