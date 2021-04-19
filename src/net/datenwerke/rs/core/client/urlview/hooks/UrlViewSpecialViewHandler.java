package net.datenwerke.rs.core.client.urlview.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

import com.google.gwt.user.client.ui.Widget;

public interface UrlViewSpecialViewHandler extends Hook {

	public boolean consumes(String url);
	
	public Widget getWidget(String url);
}
