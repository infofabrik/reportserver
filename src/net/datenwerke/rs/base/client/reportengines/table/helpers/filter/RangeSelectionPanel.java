package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.List;

import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.decorator.FilterRangeDtoDec;

/**
 * 
 *
 */
public class RangeSelectionPanel extends SelectionPanel<FilterRangeDto> {

	public RangeSelectionPanel(AbstractFilterAspect<FilterRangeDto> filterAspect,
			ColumnDto column) {
		super(filterAspect, column);
	}
	
	@Override
	protected GridView<FilterRangeDto> initializeGridView() {
		return new TwoColumnGridView(store, column, this);
	}

	@Override
	protected TextView<FilterRangeDto> initializeTextView() {
		return new TwoColumnTextView(store, column, this, tabPanel);
	}
	
	@Override
	public void insertElement(StringBaseModel value){
		List<FilterRangeDto> storedModels = store.getAll();
		FilterRangeDto last = null;
		if(storedModels.size() > 0)
			last = (FilterRangeDto) storedModels.get(storedModels.size() - 1);
		if(null != last && null == last.getRangeTo()){
			last.setRangeTo(filterService.getStringValue(value.getValue(), column.getType()));
			store.update(last);
		}else{
			FilterRangeDto range = new FilterRangeDtoDec();
			range.setRangeFrom(filterService.getStringValue(value.getValue(), column.getType()));
			store.add(range);
		}
	}

}
