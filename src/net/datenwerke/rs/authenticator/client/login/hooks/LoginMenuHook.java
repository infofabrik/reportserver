package net.datenwerke.rs.authenticator.client.login.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.ObjectHook;

import com.google.inject.Provider;
import com.sencha.gxt.widget.core.client.menu.MenuItem;

public class LoginMenuHook extends ObjectHook<MenuItem>{

	public LoginMenuHook(MenuItem object) {
		super(object);
	}

	public LoginMenuHook(Provider<? extends MenuItem> provider) {
		super(provider);
	}
}
