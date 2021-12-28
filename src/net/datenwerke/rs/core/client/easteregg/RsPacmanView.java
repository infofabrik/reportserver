package net.datenwerke.rs.core.client.easteregg;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.gxtdto.client.baseex.widget.DwContentPanel;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewSpecialViewHandler;

public class RsPacmanView implements UrlViewSpecialViewHandler {

	@Override
	public boolean consumes(String url) {
		return "rs:pacman://".equals(url);
	}

	@Override
	public Widget getWidget(String url) {
		DwContentPanel panel = new DwContentPanel();
		panel.setHeaderVisible(false);
		
		panel.setUrl("resources/ee/pm/index.html");
		
		return panel;
	}

}
