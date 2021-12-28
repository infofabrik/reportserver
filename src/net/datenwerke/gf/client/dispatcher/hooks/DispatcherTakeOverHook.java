package net.datenwerke.gf.client.dispatcher.hooks;

import net.datenwerke.gf.client.dispatcher.Dispatchable;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface DispatcherTakeOverHook extends Hook {

   boolean isActive();

   Dispatchable getDispatcheable();

}
