package net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.grideditor.client.grideditor.dto.MinDateValidatorDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.decorator.MinDateValidatorDtoDec;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.MinDateValidator;
import net.datenwerke.rs.grideditor.service.grideditor.definition.validator.dtogen.MinDateValidator2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for MinDateValidator
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class MinDateValidator2DtoGenerator implements Poso2DtoGenerator<MinDateValidator,MinDateValidatorDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public MinDateValidator2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public MinDateValidatorDtoDec instantiateDto(MinDateValidator poso)  {
		MinDateValidatorDtoDec dto = new MinDateValidatorDtoDec();
		return dto;
	}

	public MinDateValidatorDtoDec createDto(MinDateValidator poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final MinDateValidatorDtoDec dto = new MinDateValidatorDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set errorMsg */
			dto.setErrorMsg(StringEscapeUtils.escapeXml(StringUtils.left(poso.getErrorMsg(),8192)));

			/*  set minDate */
			dto.setMinDate(poso.getMinDate() );

		}

		return dto;
	}


}
