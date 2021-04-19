package net.datenwerke.gf.service.lateinit;

import com.google.inject.AbstractModule;

/**
 * Should be the last module loaded
 *
 */
public class LateInitModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(LateInitStartup.class).asEagerSingleton();
	}

}
