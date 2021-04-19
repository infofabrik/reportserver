package net.datenwerke.rs.condition.client.condition;

import com.google.gwt.inject.client.AbstractGinModule;

public class ConditionUiModule extends AbstractGinModule {

	@Override
	protected void configure() {
		bind(ConditionUiStartup.class).asEagerSingleton();
	}

}
