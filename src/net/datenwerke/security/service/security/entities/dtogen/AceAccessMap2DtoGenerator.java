package net.datenwerke.security.service.security.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.utils.misc.StringEscapeUtils;
import net.datenwerke.security.client.security.dto.AceAccessMapDto;
import net.datenwerke.security.client.security.dto.decorator.AceAccessMapDtoDec;
import net.datenwerke.security.service.security.entities.AceAccessMap;
import net.datenwerke.security.service.security.entities.dtogen.AceAccessMap2DtoGenerator;
import org.apache.commons.lang3.StringUtils;

/**
 * Poso2DtoGenerator for AceAccessMap
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class AceAccessMap2DtoGenerator implements Poso2DtoGenerator<AceAccessMap,AceAccessMapDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public AceAccessMap2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public AceAccessMapDtoDec instantiateDto(AceAccessMap poso)  {
		AceAccessMapDtoDec dto = new AceAccessMapDtoDec();
		return dto;
	}

	public AceAccessMapDtoDec createDto(AceAccessMap poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final AceAccessMapDtoDec dto = new AceAccessMapDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set access */
			dto.setAccess(poso.getAccess() );

			/*  set securee */
			dto.setSecuree(StringEscapeUtils.escapeXml(StringUtils.left(poso.getSecuree(),8192)));

		}

		return dto;
	}


}
