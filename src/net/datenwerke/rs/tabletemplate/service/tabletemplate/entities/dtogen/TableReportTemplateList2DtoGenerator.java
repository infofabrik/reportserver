package net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.dto.TableReportTemplateListDto;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.TableReportTemplateList;
import net.datenwerke.rs.tabletemplate.service.tabletemplate.entities.dtogen.TableReportTemplateList2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for TableReportTemplateList
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class TableReportTemplateList2DtoGenerator implements Poso2DtoGenerator<TableReportTemplateList,TableReportTemplateListDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public TableReportTemplateList2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public TableReportTemplateListDto instantiateDto(TableReportTemplateList poso)  {
		TableReportTemplateListDto dto = new TableReportTemplateListDto();
		return dto;
	}

	public TableReportTemplateListDto createDto(TableReportTemplateList poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final TableReportTemplateListDto dto = new TableReportTemplateListDto();
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
