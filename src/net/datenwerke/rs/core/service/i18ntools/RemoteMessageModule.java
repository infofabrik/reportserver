package net.datenwerke.rs.core.service.i18ntools;

import com.google.inject.AbstractModule;

import net.datenwerke.gf.service.localization.RemoteMessageService;

public class RemoteMessageModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(RemoteMessageService.class).to(RemoteMessageServiceImpl.class);
	}

}
