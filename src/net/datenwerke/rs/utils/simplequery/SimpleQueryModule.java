package net.datenwerke.rs.utils.simplequery;

import net.datenwerke.rs.utils.simplequery.annotations.QueryByAttribute;
import net.datenwerke.rs.utils.simplequery.annotations.QueryById;
import net.datenwerke.rs.utils.simplequery.annotations.SimpleQuery;
import net.datenwerke.rs.utils.simplequery.byatt.QueryByAttInterceptor;
import net.datenwerke.rs.utils.simplequery.byid.QueryByIdInterceptor;
import net.datenwerke.rs.utils.simplequery.simple.SimpleQueryInterceptor;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class SimpleQueryModule extends AbstractModule {

	@Override
	protected void configure() {
		/* simple */
		SimpleQueryInterceptor interceptor = new SimpleQueryInterceptor();
		requestInjection(interceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(SimpleQuery.class), interceptor);
		
		QueryByIdInterceptor idQInterceptor = new QueryByIdInterceptor();
		requestInjection(idQInterceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(QueryById.class), idQInterceptor);
		
		QueryByAttInterceptor attQInterceptor = new QueryByAttInterceptor();
		requestInjection(attQInterceptor);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(QueryByAttribute.class), attQInterceptor);

	}

}
