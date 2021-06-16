package net.datenwerke.hookhandler.shared.hookhandler.interfaces;

import com.google.inject.Provider;

/**
 * 
 *
 * @param <O>
 */
public abstract class ObjectHook<O> implements Hook {

   private O object;
   private Provider<? extends O> provider;

   public ObjectHook(O object) {
      this.object = object;
   }

   public ObjectHook(Provider<? extends O> provider) {
      this.provider = provider;
   }

   public O getObject() {
      if (null != provider)
         return provider.get();
      return object;
   }

}
