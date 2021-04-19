package net.datenwerke.rs.utils.simplequery.byid;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.google.inject.Inject;
import com.google.inject.Provider;

/**
 * 
 *
 */
public class QueryByIdInterceptor implements MethodInterceptor {

	@Inject
	private Provider<QueryByIdProcessor> processorProvider;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		QueryByIdHandler handler = QueryByIdHandler.from(invocation.getMethod());
		if(null == handler)
			return invocation.proceed();

		return processorProvider.get().process(handler, invocation);
	}
	
}
