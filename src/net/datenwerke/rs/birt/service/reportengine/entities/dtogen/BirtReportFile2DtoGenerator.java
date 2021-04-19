package net.datenwerke.rs.birt.service.reportengine.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportFileDto;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReportFile;
import net.datenwerke.rs.birt.service.reportengine.entities.dtogen.BirtReportFile2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for BirtReportFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BirtReportFile2DtoGenerator implements Poso2DtoGenerator<BirtReportFile,BirtReportFileDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public BirtReportFile2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BirtReportFileDto instantiateDto(BirtReportFile poso)  {
		BirtReportFileDto dto = new BirtReportFileDto();
		return dto;
	}

	public BirtReportFileDto createDto(BirtReportFile poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final BirtReportFileDto dto = new BirtReportFileDto();
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
