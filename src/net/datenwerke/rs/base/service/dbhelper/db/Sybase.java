package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class Sybase extends DatabaseHelper {

	public static final String DB_NAME = "Sybase";
	public static final String DB_DRIVER = "com.sybase.jdbc4.jdbc.SybDriver";
	public static final String DB_DESCRIPTOR = "DBHelper_Sybase";

	@Override
	public String getDescriptor() {
		return DB_DESCRIPTOR;
	}

	@Override
	public String getDriver() {
		return DB_DRIVER;
	}

	@Override
	public String getName() {
		return DB_NAME;
	}

	@Override
	public String createDummyQuery() {
		return "select 1 dummy from dummy";
	}

	@Override
	public LimitQuery getNewLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
		return new LimitQuery(nestedQuery, queryBuilder){
			@Override
			public void appendToBuffer(StringBuffer buf) {
				buf.append("SELECT TOP ");
				buf.append(queryBuilder.getLimit() < 1 ? 1 : queryBuilder.getLimit());
				buf.append(" * FROM (");
				nestedQuery.appendToBuffer(buf);
				buf.append(") limitQry");
			}
		};
	}

	@Override
	public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		return new OffsetQuery(nestedQuery, queryBuilder, columnNamingService){
			@Override
			public void appendToBuffer(StringBuffer buf) {
				buf.append("SELECT TOP ");
				buf.append(queryBuilder.getLimit() < 1 ? 1 : queryBuilder.getLimit());
				buf.append(" START AT ");
				buf.append(queryBuilder.getOffset() + 1);
				buf.append(" * FROM (");
				nestedQuery.appendToBuffer(buf);
				buf.append(") limitQry");
			}
		};
	}
}
