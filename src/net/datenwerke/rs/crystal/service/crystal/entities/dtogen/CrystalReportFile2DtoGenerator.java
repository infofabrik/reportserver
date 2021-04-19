package net.datenwerke.rs.crystal.service.crystal.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.crystal.client.crystal.dto.CrystalReportFileDto;
import net.datenwerke.rs.crystal.service.crystal.entities.CrystalReportFile;
import net.datenwerke.rs.crystal.service.crystal.entities.dtogen.CrystalReportFile2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for CrystalReportFile
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class CrystalReportFile2DtoGenerator implements Poso2DtoGenerator<CrystalReportFile,CrystalReportFileDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public CrystalReportFile2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public CrystalReportFileDto instantiateDto(CrystalReportFile poso)  {
		CrystalReportFileDto dto = new CrystalReportFileDto();
		return dto;
	}

	public CrystalReportFileDto createDto(CrystalReportFile poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final CrystalReportFileDto dto = new CrystalReportFileDto();
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
