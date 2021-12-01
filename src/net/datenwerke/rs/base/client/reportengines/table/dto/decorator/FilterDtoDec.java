package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import java.util.ArrayList;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;

/**
 * Dto Decorator for {@link FilterDto}
 *
 */
public class FilterDtoDec extends FilterDto implements IdedDto {


	private static final long serialVersionUID = 1L;

	public FilterDtoDec() {
		super();
		setCaseSensitive(true);
	}

	public static FilterDto clone(FilterDto filter) {
		if(null == filter)
			return null;
		
		FilterDto clone = new FilterDtoDec();
		
		clone.setExcludeRanges(FilterRangeDtoDec.clone(filter.getExcludeRanges()));
		clone.setExcludeValues(new ArrayList<String>(filter.getExcludeValues()));
		clone.setIncludeRanges(FilterRangeDtoDec.clone(filter.getIncludeRanges()));
		clone.setIncludeValues(new ArrayList<String>(filter.getIncludeValues()));
		clone.setCaseSensitive(Boolean.TRUE.equals(filter.isCaseSensitive()));
		
		return clone;
	}
}
