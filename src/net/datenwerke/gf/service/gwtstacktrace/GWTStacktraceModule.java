package net.datenwerke.gf.service.gwtstacktrace;

import net.datenwerke.gf.base.server.gwtstacktrace.annotations.GWTInitClientException;
import net.datenwerke.rs.utils.guice.GuiceMatchers;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

/**
 * 
 *
 */
public class GWTStacktraceModule extends AbstractModule {

	@Override
	protected void configure() {
		CatchStacktraceInterceptor interceptor = new CatchStacktraceInterceptor();
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(GWTInitClientException.class), interceptor);
		bindInterceptor(Matchers.annotatedWith(GWTInitClientException.class), Matchers.not(Matchers.annotatedWith(GWTInitClientException.class)).and(GuiceMatchers.publicMethod()), interceptor);
	}

}
