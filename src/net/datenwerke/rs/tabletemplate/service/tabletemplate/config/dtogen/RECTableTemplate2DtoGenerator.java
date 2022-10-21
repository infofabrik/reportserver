package net.datenwerke.rs.tabletemplate.service.tabletemplate.config.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.RECTableTemplateDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.config.RECTableTemplate;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.config.dtogen.RECTableTemplate2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for RECTableTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class RECTableTemplate2DtoGenerator implements Poso2DtoGenerator<RECTableTemplate,RECTableTemplateDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public RECTableTemplate2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public RECTableTemplateDto instantiateDto(RECTableTemplate poso)  {
		RECTableTemplateDto dto = new RECTableTemplateDto();
		return dto;
	}

	public RECTableTemplateDto createDto(RECTableTemplate poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final RECTableTemplateDto dto = new RECTableTemplateDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set templateId */
			dto.setTemplateId(poso.getTemplateId() );

			/*  set templateKey */
			dto.setTemplateKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTemplateKey(),8192)));

			/*  set temporaryId */
			dto.setTemporaryId(poso.getTemporaryId() );

		}

		return dto;
	}


}
