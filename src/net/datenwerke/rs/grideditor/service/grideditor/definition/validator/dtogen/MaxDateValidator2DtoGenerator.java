package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxDateValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxDateValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxDateValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxDateValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MaxDateValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MaxDateValidator2DtoGenerator implements Poso2DtoGenerator<MaxDateValidator,MaxDateValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MaxDateValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MaxDateValidatorDtoDec instantiateDto(MaxDateValidator poso)  {
		MaxDateValidatorDtoDec dto = new MaxDateValidatorDtoDec();
		return dto;
	}

	public MaxDateValidatorDtoDec createDto(MaxDateValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MaxDateValidatorDtoDec dto = new MaxDateValidatorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set errorMsg */
			dto.setErrorMsg(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorMsg(),8192)));

			/*  set maxDate */
			dto.setMaxDate(poso.getMaxDate() );

		}

		return dto;
	}


}
