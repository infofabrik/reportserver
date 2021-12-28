package net.datenwerke.gf.client.homepage.hooks;

import com.google.inject.Provider;

import net.datenwerke.gf.client.homepage.modules.ClientMainModule;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

/**
 * 
 *
 */
public class ClientMainModuleProviderHook extends ObjectHook<ClientMainModule> {

   public ClientMainModuleProviderHook(ClientMainModule object) {
      super(object);
   }

   public ClientMainModuleProviderHook(Provider<? extends ClientMainModule> provider) {
      super(provider);
   }

}
