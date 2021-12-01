package net.datenwerke.rs.utils.simplequery.byid;

import java.lang.reflect.Method;

import net.datenwerke.rs.utils.simplequery.annotations.QueryById;

public class QueryByIdHandler {

	private final Method method;
	private final QueryById metadata;

	public QueryByIdHandler(Method method) {
		this.method = method;
		this.metadata = method.getAnnotation(QueryById.class);
	}

	public static QueryByIdHandler from(Method method) {
		return method.isAnnotationPresent(QueryById.class) ? new QueryByIdHandler(method) : null;
	}

	public QueryById getMetadata() {
		return metadata;
	}
	
	public Method getMethod(){
		return method;
	}

}
