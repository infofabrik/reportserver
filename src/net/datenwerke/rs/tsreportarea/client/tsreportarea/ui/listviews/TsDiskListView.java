package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews;

import java.util.List;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

public interface TsDiskListView {

   public abstract Widget getComponent();

   public abstract void select(AbstractTsDiskNodeDto model);

   public abstract List<AbstractTsDiskNodeDto> getSelected();

}
