package net.datenwerke.rs.base.service.dbhelper.queries;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;

public abstract class CompositeQuery implements Query {
	
	protected Query nestedQuery;
	protected ColumnNamingService columnNamingService;
	
	public CompositeQuery(Query nestedQuery) {
		this.nestedQuery = nestedQuery;
	}

	public Query getNestedQuery() {
		return nestedQuery;
	}
	
	public void setNestedQuery(Query nestedQuery) {
		this.nestedQuery = nestedQuery;
	}
	
	
}
