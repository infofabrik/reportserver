package net.datenwerke.rs.grideditor.client.grideditor.vp;

import net.datenwerke.rs.grideditor.client.grideditor.dto.GridEditorRecordDto;

import com.sencha.gxt.core.client.ValueProvider;

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
