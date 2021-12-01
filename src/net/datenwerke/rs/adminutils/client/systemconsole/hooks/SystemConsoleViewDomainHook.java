package net.datenwerke.rs.adminutils.client.systemconsole.hooks;

import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.user.client.ui.Widget;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

/**
 * Defines a hook for attaching system console views.
 */
public interface SystemConsoleViewDomainHook extends Hook {

	/**
	 * The text used in the navigation panel.
	 */
	public String getNavigationText();
	
	/**
	 * The icon used in the navigation panel
	 */
	public ImageResource getNavigationIcon();
	
	public Widget getMainWidget();
}
