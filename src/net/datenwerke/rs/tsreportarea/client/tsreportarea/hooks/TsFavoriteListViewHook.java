package net.datenwerke.rs.tsreportarea.client.tsreportarea.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.TsDiskMainComponent;
import net.datenwerke.rs.tsreportarea.client.tsreportarea.ui.listviews.TsDiskListView;

import com.google.gwt.resources.client.ImageResource;

public interface TsFavoriteListViewHook extends Hook {

	ImageResource getViewIcon();

	TsDiskListView getListView(TsDiskMainComponent tsFavoriteMainComponent);

	String getViewId();

}
