package net.datenwerke.rs.grideditor.client.grideditor.vp;

import java.math.BigDecimal;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;

public class RowValueProviderDecimal extends RowValueProvider<BigDecimal> {
	
	public RowValueProviderDecimal(int index) {
		super(index);
	}

	@Override
	public BigDecimal getValue(GridEditorRecordDto object) {
		GridEditorRecordEntryDto entry = object.getData().get(index);
		if(entry.isEntryNull())
			return null;
		
		return entry.getDecimalValue();
	}

	@Override
	public void setValue(GridEditorRecordDto object, BigDecimal value) {
		GridEditorRecordEntryDto entry = object.getData().get(index);
		if(null == value)
			entry.setEntryNull(true);
		else {
			entry.setEntryNull(false);
			entry.setDecimalValue(value);
		}
	}

}