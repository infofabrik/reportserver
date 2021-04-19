package net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen;

import com.google.inject.Inject;
import com.google.inject.Provider;
import java.util.ArrayList;
import java.util.List;
import net.datenwerke.dtoservices.dtogenerator.annotations.GeneratedType;
import net.datenwerke.dtoservices.dtogenerator.poso2dtogenerator.interfaces.Poso2DtoGenerator;
import net.datenwerke.gxtdto.client.dtomanager.DtoView;
import net.datenwerke.gxtdto.server.dtomanager.DtoMainService;
import net.datenwerke.gxtdto.server.dtomanager.DtoService;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterDtoDec;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.dtogen.Filter2DtoGenerator;

/**
 * Poso2DtoGenerator for Filter
 *
 * This file was automatically created by DtoAnnotationProcessor, version 0.1
 */
@GeneratedType("net.datenwerke.dtoservices.dtogenerator.DtoAnnotationProcessor")
public class Filter2DtoGenerator implements Poso2DtoGenerator<Filter,FilterDtoDec> {

	private final Provider<DtoService> dtoServiceProvider;

	@Inject
	public Filter2DtoGenerator(
		Provider<DtoService> dtoServiceProvider	){
		this.dtoServiceProvider = dtoServiceProvider;
	}

	public FilterDtoDec instantiateDto(Filter poso)  {
		FilterDtoDec dto = new FilterDtoDec();
		return dto;
	}

	public FilterDtoDec createDto(Filter poso, DtoView here, DtoView referenced)  {
		/* create dto and set view */
		final FilterDtoDec dto = new FilterDtoDec();
		dto.setDtoView(here);

		if(here.compareTo(DtoView.MINIMAL) >= 0){
			/*  set id */
			dto.setId(poso.getId() );

		}
		if(here.compareTo(DtoView.NORMAL) >= 0){
			/*  set caseSensitive */
			dto.setCaseSensitive(poso.isCaseSensitive() );

			/*  set excludeRanges */
			final List<FilterRangeDto> col_excludeRanges = new ArrayList<FilterRangeDto>();
			if( null != poso.getExcludeRanges()){
				for(FilterRange refPoso : poso.getExcludeRanges()){
					final Object tmpDtoFilterRangeDtogetExcludeRanges = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_excludeRanges.add((FilterRangeDto) tmpDtoFilterRangeDtogetExcludeRanges);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFilterRangeDtogetExcludeRanges, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (excludeRanges)");
							int tmp_index = col_excludeRanges.indexOf(tmpDtoFilterRangeDtogetExcludeRanges);
							col_excludeRanges.set(tmp_index,(FilterRangeDto) dto);
						}
					});
				}
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
			final List<FilterRangeDto> col_includeRanges = new ArrayList<FilterRangeDto>();
			if( null != poso.getIncludeRanges()){
				for(FilterRange refPoso : poso.getIncludeRanges()){
					final Object tmpDtoFilterRangeDtogetIncludeRanges = dtoServiceProvider.get().createDto(refPoso, here, referenced);
					col_includeRanges.add((FilterRangeDto) tmpDtoFilterRangeDtogetIncludeRanges);
					/* ask for dto with higher view if generated */
					((DtoMainService)dtoServiceProvider.get()).getCreationHelper().onDtoCreation(tmpDtoFilterRangeDtogetIncludeRanges, refPoso, new net.datenwerke.gxtdto.server.dtomanager.CallbackOnDtoCreation(){
						public void callback(Object dto){
							if(null == dto)
								throw new IllegalArgumentException("expected to get dto object (includeRanges)");
							int tmp_index = col_includeRanges.indexOf(tmpDtoFilterRangeDtogetIncludeRanges);
							col_includeRanges.set(tmp_index,(FilterRangeDto) dto);
						}
					});
				}
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
