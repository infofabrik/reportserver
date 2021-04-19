package net.datenwerke.rs.base.client.reportengines.table.helpers.filter;

import java.util.List;

import net.datenwerke.gxtdto.client.model.StringBaseModel;
import net.datenwerke.rs.base.client.reportengines.table.columnfilter.locale.FilterMessages;
import net.datenwerke.rs.base.client.reportengines.table.dto.ColumnDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.FilterDto;
import net.datenwerke.rs.base.client.reportengines.table.dto.TableReportDto;

/**
 * 
 *
 */
public class ExcludeValuesFilterAspect extends AbstractSingleFilterAspect{
	
	public ExcludeValuesFilterAspect(TableReportDto report, ColumnDto column, String executeToken) {
		super(FilterMessages.INSTANCE.excludeTitle(), report, column, FilterType.Exclude, executeToken); 
	}	
	
	@Override
	protected List<String> getValues(FilterDto filter) {
		return filter.getExcludeValues();
	}
	
	@Override
	protected SelectionPanel<StringBaseModel> createSelectionPanel(ColumnDto column) {
		return new SingleSelectionPanel(this, column);
	}

	@Override
	protected void setValues(FilterDto filter, List<String> newValues) {
		filter.setExcludeValues(newValues);
	}
}
