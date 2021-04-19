package net.datenwerke.gf.client.homepage.hooks;

import com.sencha.gxt.widget.core.client.container.HBoxLayoutContainer;

import net.datenwerke.gf.client.homepage.ui.DwMainViewportTopBarElement;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

/**
 * 
 *
 */
public interface HomepageHeaderContentHook extends Hook {

	public DwMainViewportTopBarElement homepageHeaderContentHook_addTopRight(HBoxLayoutContainer container);
}
