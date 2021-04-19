package net.datenwerke.rs.utils.simplequery.byatt;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class QueryByAttInterceptor implements MethodInterceptor {

	@Inject
	private Provider<QueryByAttProcessor> processorProvider;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		QueryByAttHandler handler = QueryByAttHandler.from(invocation.getMethod());
		if(null == handler)
			return invocation.proceed();

		return processorProvider.get().process(handler, invocation);
	}
	
}
