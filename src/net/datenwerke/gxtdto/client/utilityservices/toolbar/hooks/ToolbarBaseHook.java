package net.datenwerke.gxtdto.client.utilityservices.toolbar.hooks;

import net.datenwerke.gxtdto.client.utilityservices.toolbar.DwHookableToolbar;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ToolbarBaseHook extends Hook {

   public boolean consumes(DwHookableToolbar toolbar);

   public void attachToLeft(DwHookableToolbar dwToolbar);

   public void attachToRight(DwHookableToolbar dwToolbar);
}
