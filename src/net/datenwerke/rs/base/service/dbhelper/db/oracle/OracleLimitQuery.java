package net.datenwerke.rs.base.service.dbhelper.db.oracle;

import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class OracleLimitQuery extends LimitQuery {

	public OracleLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
		super(nestedQuery, queryBuilder);
	}
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		if(0 == queryBuilder.getLimit()){
			buf.append("SELECT * FROM (");
			nestedQuery.appendToBuffer(buf);
			buf.append(") limitQry");
			buf.append(" WHERE ROWNUM < 0 ");
		} else {
			buf.append("SELECT * FROM (");
			nestedQuery.appendToBuffer(buf);
			buf.append(") limitQry");
			buf.append(" WHERE ROWNUM <= ");
			buf.append(queryBuilder.getLimit());
		}
	}

}
