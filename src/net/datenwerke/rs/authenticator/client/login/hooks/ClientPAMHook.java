package net.datenwerke.rs.authenticator.client.login.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM;

import com.google.inject.Provider;

public class ClientPAMHook extends ObjectHook<Provider<? extends ClientPAM>> {

	public ClientPAMHook(Provider<? extends ClientPAM> object) {
		super(object);
	}
	

}
