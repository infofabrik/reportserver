package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxFloatValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxFloatValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxFloatValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxFloatValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MaxFloatValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MaxFloatValidator2DtoGenerator implements Poso2DtoGenerator<MaxFloatValidator,MaxFloatValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MaxFloatValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MaxFloatValidatorDtoDec instantiateDto(MaxFloatValidator poso)  {
		MaxFloatValidatorDtoDec dto = new MaxFloatValidatorDtoDec();
		return dto;
	}

	public MaxFloatValidatorDtoDec createDto(MaxFloatValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MaxFloatValidatorDtoDec dto = new MaxFloatValidatorDtoDec();
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
