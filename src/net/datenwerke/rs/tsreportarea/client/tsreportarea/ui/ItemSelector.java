package net.datenwerke.rs.tsreportarea.client.tsreportarea.ui;

import java.util.List;

import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;

public interface ItemSelector {

   public List<AbstractTsDiskNodeDto> getSelectedItems();

   boolean isInFolder();

}
