package net.datenwerke.rs.configservice.service.configservice.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.configservice.service.configservice.AbstractConfigStore;

import com.google.inject.Provider;

public class ConfigStoreHook extends ObjectHook<AbstractConfigStore> {

   public ConfigStoreHook(AbstractConfigStore object) {
      super(object);
   }

   public ConfigStoreHook(Provider<? extends AbstractConfigStore> provider) {
      super(provider);
   }

}
