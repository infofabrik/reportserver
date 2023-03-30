package net.datenwerke.rs.base.service.dbhelper.db;

/**
 * 
 *
 */
public class CrateDB extends PostgreSQL {

   public static final String DB_NAME = "CrateDB";
   public static final String DB_DRIVER = "io.crate.client.jdbc.CrateDriver";
   public static final String DB_DESCRIPTOR = "DBHelper_CrateDB";
   public static final String DB_DESCRIPTION = "CrateDB JDBC driver";

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
