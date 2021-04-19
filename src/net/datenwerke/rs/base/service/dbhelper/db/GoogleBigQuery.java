package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

public class GoogleBigQuery extends DatabaseHelper {

    public static final String DB_NAME = "Google BigQuery";
    public static final String DB_DRIVER = "com.simba.googlebigquery.jdbc42.Driver";
    public static final String DB_DESCRIPTOR = "DBHelper_GoogleBigQuery";

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
        return "select 1";
    }

    @Override
    public String getIdentifierQuoteChar() {
        return "";
    }
}
