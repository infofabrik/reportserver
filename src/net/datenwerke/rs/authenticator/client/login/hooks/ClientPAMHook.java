package net.datenwerke.rs.authenticator.client.login.hooks;

import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;
import net.datenwerke.rs.authenticator.client.login.pam.ClientPAM;

public class ClientPAMHook extends ObjectHook<Provider<? extends ClientPAM>> {

	public ClientPAMHook(Provider<? extends ClientPAM> object) {
		super(object);
	}
	

}
