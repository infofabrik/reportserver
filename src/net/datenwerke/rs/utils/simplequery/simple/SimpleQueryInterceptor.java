package net.datenwerke.rs.utils.simplequery.simple;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class SimpleQueryInterceptor implements MethodInterceptor {

	@Inject
	private Provider<SimpleQueryProcessor> processorProvider;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		SimpleQueryHandler handler = SimpleQueryHandler.from(invocation.getMethod());
		if(null == handler)
			return invocation.proceed();

		return processorProvider.get().process(handler, invocation);
	}
	
}
