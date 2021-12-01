package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterRangeDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

import com.sencha.gxt.data.shared.ListStore;

/**
 * 
 *
 */
public abstract class AbstractRangeFilterAspect extends AbstractFilterAspect<FilterRangeDto>{

	public AbstractRangeFilterAspect(String titleString, TableReportDto report, ColumnDto column, FilterType type, String executeToken) {
		super(titleString, report, column, type, executeToken);
	}

	@Override
	public void storeConfiguration() {
		ListStore<FilterRangeDto> store = this.selectionPanel.getStore();

		List<FilterRangeDto> newValues = new ArrayList<FilterRangeDto>();
		for(FilterRangeDto m : store.getAll())
			newValues.add(m);
		
		setValues(column.getFilter(), newValues);
	}
	
	abstract protected void setValues(FilterDto filter, List<FilterRangeDto> newValues);

	abstract protected List<FilterRangeDto> getValues(FilterDto filter);

	@Override
	public void loadConfiguration(FilterDto filter) {
		/* stop updates */
		selectionPanel.setFireUpdates(false);

		ListStore<FilterRangeDto> store = this.selectionPanel.getStore();
		store.addAll(getValues(filter));
		
		/* resume updates */
		selectionPanel.setFireUpdates(true);
	}

}
