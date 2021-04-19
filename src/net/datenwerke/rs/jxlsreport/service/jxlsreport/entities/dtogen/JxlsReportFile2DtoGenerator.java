package net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.jxlsreport.client.jxlsreport.dto.JxlsReportFileDto;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.JxlsReportFile;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.entities.dtogen.JxlsReportFile2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for JxlsReportFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JxlsReportFile2DtoGenerator implements Poso2DtoGenerator<JxlsReportFile,JxlsReportFileDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JxlsReportFile2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JxlsReportFileDto instantiateDto(JxlsReportFile poso)  {
		JxlsReportFileDto dto = new JxlsReportFileDto();
		return dto;
	}

	public JxlsReportFileDto createDto(JxlsReportFile poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final JxlsReportFileDto dto = new JxlsReportFileDto();
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
