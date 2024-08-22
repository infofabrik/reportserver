package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class SQLite extends DatabaseHelper {

   public static final String DB_NAME = "SQLite";
   public static final String DB_DRIVER = "org.sqlite.JDBC";
   public static final String DB_DESCRIPTOR = "DBHelper_SQLite";
   public static final String DB_DESCRIPTION = "SQLite JDBC driver";

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
   public boolean canChangeReadOnlyFlagAfterConnectionCreation() {
      return false;
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }
}
