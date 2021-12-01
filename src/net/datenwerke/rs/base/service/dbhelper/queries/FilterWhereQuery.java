package net.datenwerke.rs.base.service.dbhelper.queries;

import java.util.Iterator;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;

public class FilterWhereQuery extends CompositeQuery {

	private QueryBuilder queryBuilder;


	public FilterWhereQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		super(nestedQuery);
		this.queryBuilder = queryBuilder;
		this.columnNamingService = columnNamingService;
	}
	
	
	@Override
	public void appendToBuffer(StringBuffer buf) {
		buf.append("SELECT * FROM (");
		nestedQuery.appendToBuffer(buf);
		buf.append(") filterQry ");
		buf.append(" WHERE ");
		
		Iterator<QryCondition> it = queryBuilder.getConditionsWhere().iterator();
		while(it.hasNext()){
			it.next().appendToBuffer(buf, columnNamingService);
			if(it.hasNext())
				buf.append(" AND ");
		}
	}

}
