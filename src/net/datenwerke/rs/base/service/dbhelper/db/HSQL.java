package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class HSQL extends DatabaseHelper {

	public static final String DB_NAME = "HSQL";
	public static final String DB_DRIVER = "org.hsqldb.jdbcDriver";
	public static final String DB_DESCRIPTOR = "DBHelper_HSQL";

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
	public String createDummyQuery(){
		return "SELECT 1 FROM INFORMATION_SCHEMA.SYSTEM_USERS WHERE 1=0"; //$NON-NLS-1$
	}
	

}
