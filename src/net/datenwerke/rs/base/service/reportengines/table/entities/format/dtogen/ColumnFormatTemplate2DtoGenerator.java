package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatTemplateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatTemplateDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatTemplate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatTemplate2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ColumnFormatTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ColumnFormatTemplate2DtoGenerator implements Poso2DtoGenerator<ColumnFormatTemplate,ColumnFormatTemplateDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ColumnFormatTemplate2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ColumnFormatTemplateDtoDec instantiateDto(ColumnFormatTemplate poso)  {
		ColumnFormatTemplateDtoDec dto = new ColumnFormatTemplateDtoDec();
		return dto;
	}

	public ColumnFormatTemplateDtoDec createDto(ColumnFormatTemplate poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ColumnFormatTemplateDtoDec dto = new ColumnFormatTemplateDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set template */
			dto.setTemplate(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTemplate(),8192)));

		}

		return dto;
	}


}
