package net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnFormatDateDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.ColumnFormatDateDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.ColumnFormatDate;
import net.datenwerke.rs.base.service.reportengines.table.entities.format.dtogen.ColumnFormatDate2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for ColumnFormatDate
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class ColumnFormatDate2DtoGenerator implements Poso2DtoGenerator<ColumnFormatDate,ColumnFormatDateDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public ColumnFormatDate2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public ColumnFormatDateDtoDec instantiateDto(ColumnFormatDate poso)  {
		ColumnFormatDateDtoDec dto = new ColumnFormatDateDtoDec();
		return dto;
	}

	public ColumnFormatDateDtoDec createDto(ColumnFormatDate poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final ColumnFormatDateDtoDec dto = new ColumnFormatDateDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set baseFormat */
			dto.setBaseFormat(StringEscapeUtils.escapeXml(StringUtils.left(poso.getBaseFormat(),8192)));

			/*  set errorReplacement */
			dto.setErrorReplacement(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorReplacement(),8192)));

			/*  set replaceErrors */
			dto.setReplaceErrors(poso.isReplaceErrors() );

			/*  set rollOver */
			dto.setRollOver(poso.isRollOver() );

			/*  set targetFormat */
			dto.setTargetFormat(StringEscapeUtils.escapeXml(StringUtils.left(poso.getTargetFormat(),8192)));

		}

		return dto;
	}


}
