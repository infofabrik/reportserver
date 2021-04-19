package net.datenwerke.rs.passwordpolicy.client.lostpassword;

import net.datenwerke.rs.passwordpolicy.client.LostPasswordUIStartup;

import com.google.gwt.inject.client.AbstractGinModule;

public class LostPasswordUIModule extends AbstractGinModule{

	@Override
	protected void configure() {

		bind(LostPasswordUIStartup.class).asEagerSingleton();
	}
}
