package net.datenwerke.rs.authenticator.cr.client;

import com.google.gwt.inject.client.AbstractGinModule;

public class ChallengeResponseUIModule extends AbstractGinModule{

	@Override
	protected void configure() {

		bind(ChallengeResponseUIStartup.class).asEagerSingleton();
	}

}
