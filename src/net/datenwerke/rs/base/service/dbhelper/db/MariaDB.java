package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class MariaDB extends DatabaseHelper {

   public static final String DB_NAME = "MariaDB";
   public static final String DB_DRIVER = "org.mariadb.jdbc.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_MariaDB";
   public static final String DB_DESCRIPTION = "MariaDB JDBC driver";

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
   public String getIdentifierQuoteChar() {
      return "`";
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }

}
