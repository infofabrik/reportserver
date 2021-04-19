package net.datenwerke.rs.scripting.service.scripting.extensions.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.scripting.client.scripting.dto.AddReportExportFormatProviderDto;
import net.datenwerke.rs.scripting.service.scripting.extensions.AddReportExportFormatProvider;
import net.datenwerke.rs.scripting.service.scripting.extensions.dtogen.AddReportExportFormatProvider2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AddReportExportFormatProvider
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AddReportExportFormatProvider2DtoGenerator implements Poso2DtoGenerator<AddReportExportFormatProvider,AddReportExportFormatProviderDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AddReportExportFormatProvider2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AddReportExportFormatProviderDto instantiateDto(AddReportExportFormatProvider poso)  {
		AddReportExportFormatProviderDto dto = new AddReportExportFormatProviderDto();
		return dto;
	}

	public AddReportExportFormatProviderDto createDto(AddReportExportFormatProvider poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AddReportExportFormatProviderDto dto = new AddReportExportFormatProviderDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set icon */
			dto.setIcon(StringEscapeUtils.escapeXml(StringUtils.left(poso.getIcon(),8192)));

			/*  set outputFormat */
			dto.setOutputFormat(StringEscapeUtils.escapeXml(StringUtils.left(poso.getOutputFormat(),8192)));

			/*  set reportType */
			dto.setReportType(poso.getReportType() );

			/*  set skipDownload */
			dto.setSkipDownload(poso.isSkipDownload() );

			/*  set title */
			dto.setTitle(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTitle(),8192)));

		}

		return dto;
	}


}
