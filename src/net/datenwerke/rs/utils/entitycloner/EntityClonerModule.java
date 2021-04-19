package net.datenwerke.rs.utils.entitycloner;

import com.google.inject.AbstractModule;

public class EntityClonerModule extends AbstractModule {

	@Override
	protected void configure() {
		/* bind service */
		bind(EntityClonerService.class).to(EntityClonerServiceImpl.class);
	}

}
