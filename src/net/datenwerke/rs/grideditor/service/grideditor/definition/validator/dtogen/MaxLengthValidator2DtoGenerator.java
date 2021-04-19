package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MaxLengthValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MaxLengthValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MaxLengthValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MaxLengthValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MaxLengthValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MaxLengthValidator2DtoGenerator implements Poso2DtoGenerator<MaxLengthValidator,MaxLengthValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MaxLengthValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MaxLengthValidatorDtoDec instantiateDto(MaxLengthValidator poso)  {
		MaxLengthValidatorDtoDec dto = new MaxLengthValidatorDtoDec();
		return dto;
	}

	public MaxLengthValidatorDtoDec createDto(MaxLengthValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MaxLengthValidatorDtoDec dto = new MaxLengthValidatorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set errorMsg */
			dto.setErrorMsg(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorMsg(),8192)));

			/*  set length */
			dto.setLength(poso.getLength() );

		}

		return dto;
	}


}
