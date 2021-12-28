package net.datenwerke.async;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.async.annotations.WaitBeforeForcedShutdown;
import net.datenwerke.async.helpers.TransactionalRunnableFactory;

public class DwAsyncModule extends AbstractModule {

	@Override
	protected void configure() {
		bind(DwAsyncService.class).to(DwAsyncServiceImpl.class).asEagerSingleton();
		
		bind(Long.class).annotatedWith(WaitBeforeForcedShutdown.class).toInstance(1000l);

		/* factories */
		install(new FactoryModuleBuilder().build(TransactionalRunnableFactory.class));
	}

}
