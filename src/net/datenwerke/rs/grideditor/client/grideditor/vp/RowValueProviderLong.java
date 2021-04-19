package net.datenwerke.rs.grideditor.client.grideditor.vp;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;
import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordEntryDto;

public class RowValueProviderLong extends RowValueProvider<Long> {
	
	public RowValueProviderLong(int index) {
		super(index);
	}

	@Override
	public Long getValue(GridEditorRecordDto object) {
		GridEditorRecordEntryDto entry = object.getData().get(index);
		if(entry.isEntryNull())
			return null;
		return entry.getLongValue();
	}

	@Override
	public void setValue(GridEditorRecordDto object, Long value) {
		GridEditorRecordEntryDto entry = object.getData().get(index);
		if(null == value)
			entry.setEntryNull(true);
		else {
			entry.setEntryNull(false);
			entry.setLongValue(value);
		}
	}

}