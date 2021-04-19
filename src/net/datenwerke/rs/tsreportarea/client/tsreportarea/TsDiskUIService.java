package net.datenwerke.rs.tsreportarea.client.tsreportarea;

import java.util.List;

import com.google.gwt.resources.client.ImageResource;
import com.sencha.gxt.data.shared.IconProvider;
import com.sencha.gxt.widget.core.client.grid.ColumnConfig;

import net.datenwerke.rs.tsreportarea.client.tsreportarea.dto.AbstractTsDiskNodeDto;
import net.datenwerke.treedb.client.treedb.dto.AbstractNodeDto;

/**
 * 
 *
 */
public interface TsDiskUIService {

	abstract <A extends AbstractTsDiskNodeDto> List<ColumnConfig<A, ?>> createGridColumnConfig(Class<A> type);

	abstract <A extends AbstractTsDiskNodeDto> List<ColumnConfig<A, ?>> createGridColumnConfig(Class<A> type, boolean idColumn);

	abstract ImageResource getIconFor(AbstractTsDiskNodeDto node, boolean largeIcons);

	IconProvider<AbstractNodeDto> getIconProvider();
	
}
