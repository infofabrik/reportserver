package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLongValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLongValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLongValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxLongValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MaxLongValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MaxLongValidator2DtoGenerator implements Poso2DtoGenerator<MaxLongValidator,MaxLongValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MaxLongValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MaxLongValidatorDtoDec instantiateDto(MaxLongValidator poso)  {
		MaxLongValidatorDtoDec dto = new MaxLongValidatorDtoDec();
		return dto;
	}

	public MaxLongValidatorDtoDec createDto(MaxLongValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MaxLongValidatorDtoDec dto = new MaxLongValidatorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set errorMsg */
			dto.setErrorMsg(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorMsg(),8192)));

			/*  set number */
			dto.setNumber(poso.getNumber() );

		}

		return dto;
	}


}
