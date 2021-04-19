package net.datenwerke.security.service.eventlogger;

import net.datenwerke.security.service.eventlogger.annotations.FireEvent;
import net.datenwerke.security.service.eventlogger.annotations.FireForceRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireMergeEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FirePersistEntityEvents;
import net.datenwerke.security.service.eventlogger.annotations.FireRemoveEntityEvents;
import net.datenwerke.security.service.eventlogger.aop.FireEventInterceptor;
import net.datenwerke.security.service.eventlogger.aop.ForceRemoveEntityEventInterceptor;
import net.datenwerke.security.service.eventlogger.aop.MergeEntityEventInterceptor;
import net.datenwerke.security.service.eventlogger.aop.PersistEntityEventInterceptor;
import net.datenwerke.security.service.eventlogger.aop.RemoveEntityEventInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class DwEventLoggerModule extends AbstractModule {

	@Override
	protected void configure() {
		PersistEntityEventInterceptor persistInterceptor = new PersistEntityEventInterceptor();
		requestInjection(persistInterceptor);
		bindInterceptor(Matchers.any(),Matchers.annotatedWith(FirePersistEntityEvents.class), persistInterceptor);

		MergeEntityEventInterceptor mergeInterceptor = new MergeEntityEventInterceptor();
		requestInjection(mergeInterceptor);
		bindInterceptor(Matchers.any(),Matchers.annotatedWith(FireMergeEntityEvents.class), mergeInterceptor);

		RemoveEntityEventInterceptor removeInterceptor = new RemoveEntityEventInterceptor();
		requestInjection(removeInterceptor);
		bindInterceptor(Matchers.any(),Matchers.annotatedWith(FireRemoveEntityEvents.class), removeInterceptor);

		ForceRemoveEntityEventInterceptor forceRemoveInterceptor = new ForceRemoveEntityEventInterceptor();
		requestInjection(forceRemoveInterceptor);
		bindInterceptor(Matchers.any(),Matchers.annotatedWith(FireForceRemoveEntityEvents.class), forceRemoveInterceptor);
		
		FireEventInterceptor fireEventInterceptor = new FireEventInterceptor();
		requestInjection(fireEventInterceptor);
		bindInterceptor(Matchers.any(),Matchers.annotatedWith(FireEvent.class), fireEventInterceptor);

	}

}
