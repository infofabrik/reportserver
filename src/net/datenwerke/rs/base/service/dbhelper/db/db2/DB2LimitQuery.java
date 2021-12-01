package net.datenwerke.rs.base.service.dbhelper.db.db2;

import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class DB2LimitQuery extends LimitQuery {

	public DB2LimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
		super(nestedQuery, queryBuilder);
	}
	
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		if(0 == queryBuilder.getLimit()){
			buf.append("SELECT * FROM (");
			nestedQuery.appendToBuffer(buf);
			buf.append(") limitQry");
			buf.append(" WHERE 1=0 ");
		} else {
			nestedQuery.appendToBuffer(buf);
			buf.append(" FETCH FIRST ");
			buf.append(queryBuilder.getLimit());
			buf.append(" ROWS ONLY");
		}
	}

}
