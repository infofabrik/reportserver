package net.datenwerke.rs.grideditor.client.grideditor.vp;

import com.sencha.gxt.core.client.ValueProvider;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;

public abstract class RowValueProvider<T> implements  ValueProvider<GridEditorRecordDto, T> {

	protected int index;

	public RowValueProvider(int index) {
		this.index = index;
		
	}
	
	@Override
	public String getPath() {
		return "" + index;
	}

}
