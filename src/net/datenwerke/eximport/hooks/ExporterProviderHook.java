package net.datenwerke.eximport.hooks;

import com.google.inject.Provider;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

/**
 * 
 *
 */
public class ExporterProviderHook extends ObjectHook<Exporter> {

   public ExporterProviderHook(Provider<? extends Exporter> provider) {
      super(provider);
   }

}
