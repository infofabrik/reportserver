package net.datenwerke.eximport.hooks;

import net.datenwerke.eximport.ex.Exporter;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

import com.google.inject.Provider;

/**
 * 
 *
 */
public class ExporterProviderHook extends ObjectHook<Exporter> {

	public ExporterProviderHook(Provider<? extends Exporter> provider) {
		super(provider);
	}

}
