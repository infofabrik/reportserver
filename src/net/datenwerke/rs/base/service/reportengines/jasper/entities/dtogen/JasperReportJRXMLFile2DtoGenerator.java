package net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.jasper.dto.JasperReportJRXMLFileDto;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.JasperReportJRXMLFile;
import net.datenwerke.rs.base.service.reportengines.jasper.entities.dtogen.JasperReportJRXMLFile2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for JasperReportJRXMLFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class JasperReportJRXMLFile2DtoGenerator implements Poso2DtoGenerator<JasperReportJRXMLFile,JasperReportJRXMLFileDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public JasperReportJRXMLFile2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public JasperReportJRXMLFileDto instantiateDto(JasperReportJRXMLFile poso)  {
		JasperReportJRXMLFileDto dto = new JasperReportJRXMLFileDto();
		return dto;
	}

	public JasperReportJRXMLFileDto createDto(JasperReportJRXMLFile poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final JasperReportJRXMLFileDto dto = new JasperReportJRXMLFileDto();
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
