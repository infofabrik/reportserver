package net.datenwerke.rs.condition.service.condition.entity.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;
import net.datenwerke.rs.condition.client.condition.dto.ReportConditionDto;
import net.datenwerke.rs.condition.client.condition.dto.decorator.ReportConditionDtoDec;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.condition.service.condition.entity.dtogen.ReportCondition2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ReportCondition
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ReportCondition2DtoGenerator implements Poso2DtoGenerator<ReportCondition,ReportConditionDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ReportCondition2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ReportConditionDtoDec instantiateDto(ReportCondition poso)  {
		ReportConditionDtoDec dto = new ReportConditionDtoDec();
		return dto;
	}

	public ReportConditionDtoDec createDto(ReportCondition poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ReportConditionDtoDec dto = new ReportConditionDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set description */
			dto.setDescription(StringEscapeUtils.escapeXml(StringUtils.left(poso.getDescription(),8192)));

			/*  set id */
			dto.setId(poso.getId() );

			/*  set key */
			dto.setKey(StringEscapeUtils.escapeXml(StringUtils.left(poso.getKey(),8192)));

			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

		}
		if(here.compareTo(DtoView.LIST) >= 0){
			/*  set report */
			Object tmpDtoTableReportDtogetReport = dtoServiceProvider.get().createDto(poso.getReport(), referenced, referenced);
			dto.setReport((TableReportDto)tmpDtoTableReportDtogetReport);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoTableReportDtogetReport, poso.getReport(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setReport((TableReportDto)refDto);
				}
			});

		}

		return dto;
	}


}
