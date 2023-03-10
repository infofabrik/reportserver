package net.datenwerke.rs.base.service.dbhelper.db;

/**
 * 
 *
 */
public class Incorta extends PostgreSQL {

   public static final String DB_NAME = "Incorta";
   public static final String DB_DRIVER = "org.postgresql.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_Incorta";

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
