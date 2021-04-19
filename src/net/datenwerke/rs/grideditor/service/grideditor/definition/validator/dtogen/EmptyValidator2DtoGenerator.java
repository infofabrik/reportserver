package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.EmptyValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.EmptyValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.EmptyValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.EmptyValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for EmptyValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class EmptyValidator2DtoGenerator implements Poso2DtoGenerator<EmptyValidator,EmptyValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public EmptyValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public EmptyValidatorDtoDec instantiateDto(EmptyValidator poso)  {
		EmptyValidatorDtoDec dto = new EmptyValidatorDtoDec();
		return dto;
	}

	public EmptyValidatorDtoDec createDto(EmptyValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final EmptyValidatorDtoDec dto = new EmptyValidatorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set errorMsg */
			dto.setErrorMsg(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorMsg(),8192)));

		}

		return dto;
	}


}
