package net.datenwerke.rs.base.service.dbhelper.queries;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class OrderLimitQuery extends OrderQuery{

	protected QueryBuilder queryBuilder;
	protected boolean nestQuery = true;

	public OrderLimitQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		super(nestedQuery, queryBuilder, columnNamingService);
		this.queryBuilder = queryBuilder;
		this.columnNamingService = columnNamingService;
	}
	
	public void setNestQuery(boolean nestQuery) {
		this.nestQuery = nestQuery;
	}
	
	public boolean isNestQuery() {
		return nestQuery;
	}

	@Override
	public void appendToBuffer(StringBuffer buf) {
		/* order by */
		super.appendToBuffer(buf);
		
		/* limit */
		if(0 == queryBuilder.getLimit()){
			buf.append(" WHERE 0 = 1 ");
		} else {
			buf.append(" LIMIT ");
			buf.append(queryBuilder.getLimit());
		}
	}
	
}

