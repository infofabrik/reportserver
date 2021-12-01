package net.datenwerke.rs.base.service.dbhelper.db.db2;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

/**
 * 
 *
 */
public class DB2 extends DatabaseHelper {

	public static final String DB_DESCRIPTOR = "DBHelper_DB2";
	public static final String DB_NAME = "DB2";
	public static final String DB_DRIVER = "com.ibm.db2.jcc.DB2Driver";

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
		return "select * from sysibm.sysdummy1";
	}
	
	@Override
	public LimitQuery getNewLimitQuery(Query nestedQuery, QueryBuilder queryBuilder){
		return new DB2LimitQuery(nestedQuery, queryBuilder);
	}

	@Override
	public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
		return new DB2OffsetQuery(nestedQuery, queryBuilder, columnNamingService);
	}
	
}
