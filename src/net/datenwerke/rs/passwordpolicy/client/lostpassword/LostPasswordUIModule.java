package net.datenwerke.rs.passwordpolicy.client.lostpassword;

import com.google.gwt.inject.client.AbstractGinModule;

import net.datenwerke.rs.passwordpolicy.client.LostPasswordUIStartup;

public class LostPasswordUIModule extends AbstractGinModule{

	@Override
	protected void configure() {

		bind(LostPasswordUIStartup.class).asEagerSingleton();
	}
}
