package net.datenwerke.eximport.hooks;

import com.google.inject.Provider;

import net.datenwerke.eximport.im.Importer;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

/**
 * 
 *
 */
public class ImporterProviderHook extends ObjectHook<Importer> {

   public ImporterProviderHook(Importer object) {
      super(object);
   }

   public ImporterProviderHook(Provider<? extends Importer> provider) {
      super(provider);
   }

}
