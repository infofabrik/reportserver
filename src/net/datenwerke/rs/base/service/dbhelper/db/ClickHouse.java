package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

public class ClickHouse extends DatabaseHelper{
	
	   public static final String DB_NAME = "ClickHouse";
	   public static final String DB_DRIVER = "com.clickhouse.jdbc.ClickHouseDriver";
	   public static final String DB_DESCRIPTOR = "DBHelper_ChickHouse";
	   public static final String DB_DESCRIPTION = "ClickHouse JDBC driver";

	@Override
	public String getDriver() {
		// TODO Auto-generated method stub
		return DB_DRIVER;
	}

	@Override
	public String getDescriptor() {
		// TODO Auto-generated method stub
		return DB_DESCRIPTOR;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return DB_NAME;
	}
	
	@Override
	public String getDescription() {
	   return DB_DESCRIPTION;
	}

}
