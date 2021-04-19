package net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceConfigDto;
import net.datenwerke.rs.birt.client.datasources.dto.BirtReportDatasourceTargetTypeDto;
import net.datenwerke.rs.birt.client.reportengines.dto.BirtReportDto;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.dtogen.BirtReportDatasourceConfig2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for BirtReportDatasourceConfig
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class BirtReportDatasourceConfig2DtoGenerator implements Poso2DtoGenerator<BirtReportDatasourceConfig,BirtReportDatasourceConfigDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public BirtReportDatasourceConfig2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public BirtReportDatasourceConfigDto instantiateDto(BirtReportDatasourceConfig poso)  {
		BirtReportDatasourceConfigDto dto = new BirtReportDatasourceConfigDto();
		return dto;
	}

	public BirtReportDatasourceConfigDto createDto(BirtReportDatasourceConfig poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final BirtReportDatasourceConfigDto dto = new BirtReportDatasourceConfigDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set queryWrapper */
			dto.setQueryWrapper(poso.getQueryWrapper() );

			/*  set report */
			Object tmpDtoBirtReportDtogetReport = dtoServiceProvider.get().createDto(poso.getReport(), referenced, referenced);
			dto.setReport((BirtReportDto)tmpDtoBirtReportDtogetReport);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoBirtReportDtogetReport, poso.getReport(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setReport((BirtReportDto)refDto);
				}
			});

			/*  set target */
			dto.setTarget(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTarget(),8192)));

			/*  set targetType */
			Object tmpDtoBirtReportDatasourceTargetTypeDtogetTargetType = dtoServiceProvider.get().createDto(poso.getTargetType(), referenced, referenced);
			dto.setTargetType((BirtReportDatasourceTargetTypeDto)tmpDtoBirtReportDatasourceTargetTypeDtogetTargetType);
			/* ask for a dto with higher view if generated */
			((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoBirtReportDatasourceTargetTypeDtogetTargetType, poso.getTargetType(), new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
				public void callback(Object refDto){
					if(null != refDto)
						dto.setTargetType((BirtReportDatasourceTargetTypeDto)refDto);
				}
			});

		}

		return dto;
	}


}
