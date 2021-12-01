package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews;

import java.util.List;

import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

import com.google.gwt.user.client.ui.Widget;

public interface TsDiskListView{
	
	public abstract Widget getComponent();

	public abstract void select(AbstractTsDiskNodeDto model);
	
	public abstract List<AbstractTsDiskNodeDto> getSelected();

}
