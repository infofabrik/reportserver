package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.dtogen.FilterRange2DtoGenerator;

/**
 * Poso2DtoGenerator for FilterRange
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class FilterRange2DtoGenerator implements Poso2DtoGenerator<FilterRange,FilterRangeDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public FilterRange2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FilterRangeDtoDec instantiateDto(FilterRange poso)  {
		FilterRangeDtoDec dto = new FilterRangeDtoDec();
		return dto;
	}

	public FilterRangeDtoDec createDto(FilterRange poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FilterRangeDtoDec dto = new FilterRangeDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set rangeFrom */
			dto.setRangeFrom(poso.getRangeFrom() );

			/*  set rangeTo */
			dto.setRangeTo(poso.getRangeTo() );

		}

		return dto;
	}


}
