package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class MySQL extends DatabaseHelper {

	public static final String DB_NAME = "MySQL";
	public static final String DB_DRIVER = "com.mysql.cj.jdbc.Driver";
	public static final String DB_DESCRIPTOR = "DBHelper_MySQL";

	@Override
	public String getDescriptor() {
		return DB_DESCRIPTOR; 
	}

	@Override
	public String getDriver() {
		return DB_DRIVER;
	}
	
	@Override
	public String createDummyQuery() {
		return "SELECT 1+1";
	}

	@Override
	public String getName() {
		return DB_NAME;
	}
	
	@Override
	public String getIdentifierQuoteChar() {
		return "`";
	}

}
