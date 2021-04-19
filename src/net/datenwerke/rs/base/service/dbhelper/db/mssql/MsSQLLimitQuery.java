package net.datenwerke.rs.base.service.dbhelper.db.mssql;

import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class MsSQLLimitQuery extends LimitQuery {

	public MsSQLLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
		super(nestedQuery, queryBuilder);
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		buf.append("SELECT TOP ");
		buf.append(queryBuilder.getLimit());
		buf.append(" * FROM (");
		nestedQuery.appendToBuffer(buf);
		buf.append(") limitQry");
	}


}
