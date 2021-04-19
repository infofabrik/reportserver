package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.FixedLengthValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.FixedLengthValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.FixedLengthValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.FixedLengthValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for FixedLengthValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FixedLengthValidator2DtoGenerator implements Poso2DtoGenerator<FixedLengthValidator,FixedLengthValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FixedLengthValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FixedLengthValidatorDtoDec instantiateDto(FixedLengthValidator poso)  {
		FixedLengthValidatorDtoDec dto = new FixedLengthValidatorDtoDec();
		return dto;
	}

	public FixedLengthValidatorDtoDec createDto(FixedLengthValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FixedLengthValidatorDtoDec dto = new FixedLengthValidatorDtoDec();
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
