package net.datenwerke.gf.client.homepage.hooks;

import net.datenwerke.gf.client.homepage.modules.ClientMainModule;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

import com.google.inject.Provider;

/**
 * 
 *
 */
public class ClientMainModuleProviderHook extends ObjectHook<ClientMainModule>{


	public ClientMainModuleProviderHook(ClientMainModule object) {
		super(object);
	}

	public ClientMainModuleProviderHook(Provider<? extends ClientMainModule> provider) {
		super(provider);
	}
	

}
