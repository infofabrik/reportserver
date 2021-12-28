package net.datenwerke.rs.base.service.reportengines.table.entities.dtogen;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;

/**
 * Poso2DtoGenerator for Filter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Filter2DtoGenerator implements Poso2DtoGenerator<Filter,FilterDto> {

	private final DtoService dtoService;

	@Inject
	public Filter2DtoGenerator(
		DtoService dtoService	){
		this.dtoService = dtoService;
	}

	public FilterDto instantiateDto(Filter poso)  {
		FilterDto dto = new FilterDtoDec();
		return dto;
	}

	public FilterDto createDto(Filter poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		FilterDto dto = new FilterDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set caseSensitive */
			dto.setCaseSensitive(poso.isCaseSensitive() );

			/*  set excludeRanges */
			List<FilterRangeDto> col_excludeRanges = new ArrayList<FilterRangeDto>();
			if( null != poso.getExcludeRanges()){
				for(FilterRange refPoso : poso.getExcludeRanges())
					col_excludeRanges.add((FilterRangeDto) dtoService.createDto(refPoso, here, referenced));
				dto.setExcludeRanges(col_excludeRanges);
			}

			/*  set excludeValues */
			List<String> col_excludeValues = new ArrayList<String>();
			if( null != poso.getExcludeValues()){
				for(String obj : poso.getExcludeValues())
					col_excludeValues.add((String) obj);
				dto.setExcludeValues(col_excludeValues);
			}

			/*  set includeRanges */
			List<FilterRangeDto> col_includeRanges = new ArrayList<FilterRangeDto>();
			if( null != poso.getIncludeRanges()){
				for(FilterRange refPoso : poso.getIncludeRanges())
					col_includeRanges.add((FilterRangeDto) dtoService.createDto(refPoso, here, referenced));
				dto.setIncludeRanges(col_includeRanges);
			}

			/*  set includeValues */
			List<String> col_includeValues = new ArrayList<String>();
			if( null != poso.getIncludeValues()){
				for(String obj : poso.getIncludeValues())
					col_includeValues.add((String) obj);
				dto.setIncludeValues(col_includeValues);
			}

		}

		return dto;
	}


}
