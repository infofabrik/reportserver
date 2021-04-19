package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinIntegerValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinIntegerValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinIntegerValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinIntegerValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MinIntegerValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MinIntegerValidator2DtoGenerator implements Poso2DtoGenerator<MinIntegerValidator,MinIntegerValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MinIntegerValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MinIntegerValidatorDtoDec instantiateDto(MinIntegerValidator poso)  {
		MinIntegerValidatorDtoDec dto = new MinIntegerValidatorDtoDec();
		return dto;
	}

	public MinIntegerValidatorDtoDec createDto(MinIntegerValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MinIntegerValidatorDtoDec dto = new MinIntegerValidatorDtoDec();
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
