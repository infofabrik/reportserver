package net.datenwerke.rs.base.client.reportengines.table.dto.decorator;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.gxtdto.client.dtomanager.IdedDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;

/**
 * Dto Decorator for {@link FilterRangeDto}
 *
 */
public class FilterRangeDtoDec extends FilterRangeDto implements IdedDto {


	private static final long serialVersionUID = 1L;

	public FilterRangeDtoDec() {
		super();
	}

	public static List<FilterRangeDto> clone(List<FilterRangeDto> ranges) {
		if(null == ranges)
			return null;
		
		
		List<FilterRangeDto> cloneList = new ArrayList<FilterRangeDto>();
	
		for(FilterRangeDto range : ranges){
			FilterRangeDto clone = new FilterRangeDtoDec();
			
			clone.setRangeFrom(range.getRangeFrom());
			clone.setRangeTo(range.getRangeTo());
			
			cloneList.add(clone);
		}
		
		return cloneList;
	}
}
