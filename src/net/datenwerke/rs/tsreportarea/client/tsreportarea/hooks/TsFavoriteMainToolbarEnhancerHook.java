package net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;

import com.sencha.gxt.widget.core.client.toolbar.ToolBar;

public interface TsFavoriteMainToolbarEnhancerHook extends Hook {

	boolean enhanceToolbarLeft(ToolBar mainToolbar,
			TsDiskMainComponent tsFavoriteMainComponent);

	boolean enhanceToolbarRight(ToolBar mainToolbar,
			TsDiskMainComponent tsFavoriteMainComponent);

	
}
