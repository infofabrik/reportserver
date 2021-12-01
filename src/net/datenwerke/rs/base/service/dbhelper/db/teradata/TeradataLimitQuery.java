package net.datenwerke.rs.base.service.dbhelper.db.teradata;

import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class TeradataLimitQuery extends LimitQuery {

	public TeradataLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
		super(nestedQuery, queryBuilder);
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		buf.append("SELECT TOP ");
		buf.append(0 == queryBuilder.getLimit() ? 1: queryBuilder.getLimit());
		buf.append(" * FROM (");
		nestedQuery.appendToBuffer(buf);
		buf.append(") limitQry");
	}


}
