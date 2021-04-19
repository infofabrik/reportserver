package net.datenwerke.rs.globalconstants.service.globalconstants.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.globalconstants.client.globalconstants.dto.GlobalConstantDto;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.GlobalConstant;
import net.datenwerke.rs.globalconstants.service.globalconstants.entities.dtogen.GlobalConstant2DtoGenerator;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for GlobalConstant
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class GlobalConstant2DtoGenerator implements Poso2DtoGenerator<GlobalConstant,GlobalConstantDto> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public GlobalConstant2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public GlobalConstantDto instantiateDto(GlobalConstant poso)  {
		GlobalConstantDto dto = new GlobalConstantDto();
		return dto;
	}

	public GlobalConstantDto createDto(GlobalConstant poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final GlobalConstantDto dto = new GlobalConstantDto();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set name */
			dto.setName(StringEscapeUtils.escapeXml(StringUtils.left(poso.getName(),8192)));

			/*  set value */
			dto.setValue(StringEscapeUtils.escapeXml(StringUtils.left(poso.getValue(),8192)));

		}

		return dto;
	}


}
