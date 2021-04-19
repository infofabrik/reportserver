package net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportServerSideStringPropertyDto;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.ReportServerSideStringProperty;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.dtogen.ReportServerSideStringProperty2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ReportServerSideStringProperty
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportServerSideStringProperty2DtoGenerator implements Poso2DtoGenerator<ReportServerSideStringProperty,ReportServerSideStringPropertyDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ReportServerSideStringProperty2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ReportServerSideStringPropertyDto instantiateDto(ReportServerSideStringProperty poso)  {
		ReportServerSideStringPropertyDto dto = new ReportServerSideStringPropertyDto();
		return dto;
	}

	public ReportServerSideStringPropertyDto createDto(ReportServerSideStringProperty poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReportServerSideStringPropertyDto dto = new ReportServerSideStringPropertyDto();
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
