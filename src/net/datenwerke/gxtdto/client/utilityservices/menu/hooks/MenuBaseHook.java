package net.datenwerke.gxtdto.client.utilityservices.menu.hooks;

import net.datenwerke.gxtdto.client.utilityservices.menu.DwHookableMenu;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface MenuBaseHook extends Hook {

	boolean consumes(DwHookableMenu dwMenu);

	boolean attachTo(DwHookableMenu dwMenu, boolean addSeparator);

}
