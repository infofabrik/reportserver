package net.datenwerke.rs.authenticator.cr.service;

import com.google.inject.AbstractModule;

public class ChallengeResponseModule extends AbstractModule{

	@Override
	protected void configure() {

		bind(ChallengeResponseService.class).to(ChallengeResponseServiceImpl.class);
		bind(ChallengeResponseStartup.class).asEagerSingleton();
	}

}
