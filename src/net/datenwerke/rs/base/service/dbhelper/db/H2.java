package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class H2 extends DatabaseHelper {

	public static final String DB_NAME = "H2";
	public static final String DB_DRIVER = "org.h2.Driver";
	public static final String DB_DESCRIPTOR = "DBHelper_H2";

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

	
}
