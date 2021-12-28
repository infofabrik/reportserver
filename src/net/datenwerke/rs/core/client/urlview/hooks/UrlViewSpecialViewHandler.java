package net.datenwerke.rs.core.client.urlview.hooks;

import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface UrlViewSpecialViewHandler extends Hook {

	public boolean consumes(String url);
	
	public Widget getWidget(String url);
}
