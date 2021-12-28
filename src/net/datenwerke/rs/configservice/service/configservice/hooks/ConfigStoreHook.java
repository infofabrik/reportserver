package net.datenwerke.rs.configservice.service.configservice.hooks;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.configservice.service.configservice.AbstractConfigStore;

public class ConfigStoreHook extends ObjectHook<AbstractConfigStore> {

   public ConfigStoreHook(AbstractConfigStore object) {
      super(object);
   }

   public ConfigStoreHook(Provider<? extends AbstractConfigStore> provider) {
      super(provider);
   }

}
