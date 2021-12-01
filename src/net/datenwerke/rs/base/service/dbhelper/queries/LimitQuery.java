package net.datenwerke.rs.base.service.dbhelper.queries;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class LimitQuery extends CompositeQuery{

	protected QueryBuilder queryBuilder;
	protected boolean nestQuery = true;

	public LimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
		super(nestedQuery);
		this.queryBuilder = queryBuilder;
	}
	
	public void setNestQuery(boolean nestQuery) {
		this.nestQuery = nestQuery;
	}
	
	public boolean isNestQuery() {
		return nestQuery;
	}

	@Override
	public void appendToBuffer(StringBuffer buf) {
		if(0 == queryBuilder.getLimit()){
			buf.append("SELECT * FROM (");
			nestedQuery.appendToBuffer(buf);
			buf.append(") limitQry");
			buf.append(" WHERE 0 = 1 ");
		} else {
			if(nestQuery)
				buf.append("SELECT * FROM (");
			
			nestedQuery.appendToBuffer(buf);
			
			if(nestQuery)
				buf.append(") limitQry");
			
			buf.append(" LIMIT ");
			buf.append(queryBuilder.getLimit());
		}
	}
}
