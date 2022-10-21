package net.datenwerke.hookhandler.shared.hookhandler;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public class HookConfiguration {

   private final Class<? extends Hook> hook;

   private boolean isSingleton = false;

   public HookConfiguration(Class<? extends Hook> hook) {
      this.hook = hook;
   }

   public boolean isSingleton() {
      return isSingleton;
   }

   public void setSingleton(boolean isSingleton) {
      this.isSingleton = isSingleton;
   }

   public Class<? extends Hook> getHook() {
      return hook;
   }

}
