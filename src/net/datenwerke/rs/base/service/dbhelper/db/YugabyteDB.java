package net.datenwerke.rs.base.service.dbhelper.db;

/**
 * 
 *
 */
public class YugabyteDB extends PostgreSQL {

   public static final String DB_NAME = "YugabyteDB";
   public static final String DB_DRIVER = "com.yugabyte.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_YugabyteDB";
   public static final String DB_DESCRIPTION = "Yugabyte JDBC driver";

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
   public String getDescription() {
      return DB_DESCRIPTION;
   }

}
