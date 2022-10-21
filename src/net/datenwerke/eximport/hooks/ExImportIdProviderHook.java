package net.datenwerke.eximport.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface ExImportIdProviderHook extends Hook {

   public String provideIdFor(Object object);
}
