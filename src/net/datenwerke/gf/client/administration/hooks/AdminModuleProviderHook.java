package net.datenwerke.gf.client.administration.hooks;

import com.google.inject.Provider;

import net.datenwerke.gf.client.administration.interfaces.AdminModule;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

/**
 * 
 *
 */
public class AdminModuleProviderHook extends ObjectHook<AdminModule> {

   public AdminModuleProviderHook(AdminModule object) {
      super(object);
   }

   public AdminModuleProviderHook(Provider<? extends AdminModule> provider) {
      super(provider);
   }

   public AdminModule getModule() {
      return getObject();
   }
}
