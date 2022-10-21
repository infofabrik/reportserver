package net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportStringTemplateDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportStringTemplate;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.TableReportStringTemplate2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TableReportStringTemplate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TableReportStringTemplate2DtoGenerator implements Poso2DtoGenerator<TableReportStringTemplate,TableReportStringTemplateDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TableReportStringTemplate2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TableReportStringTemplateDto instantiateDto(TableReportStringTemplate poso)  {
		TableReportStringTemplateDto dto = new TableReportStringTemplateDto();
		return dto;
	}

	public TableReportStringTemplateDto createDto(TableReportStringTemplate poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TableReportStringTemplateDto dto = new TableReportStringTemplateDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set temporaryId */
			dto.setTemporaryId(poso.getTemporaryId() );

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set contentType */
			dto.setContentType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getContentType(),8192)));

			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set fileExtension */
			dto.setFileExtension(StringEscapeUtils.escapeXml(StringUtils.left(poso.getFileExtension(),8192)));

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set templateType */
			dto.setTemplateType(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTemplateType(),8192)));

		}

		return dto;
	}


}
