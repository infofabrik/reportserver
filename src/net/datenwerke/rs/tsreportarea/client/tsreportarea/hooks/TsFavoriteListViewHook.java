package net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks;

import com.google.gwt.resources.client.ImageResource;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews.TsDiskListView;

public interface TsFavoriteListViewHook extends Hook {

	ImageResource getViewIcon();

	TsDiskListView getListView(TsDiskMainComponent tsFavoriteMainComponent);

	String getViewId();

}
