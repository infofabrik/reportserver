package net.datenwerke.rs.base.service.dbhelper.db;

/**
 * 
 *
 */
public class CockroachDB extends PostgreSQL {

   public static final String DB_NAME = "CockroachDB";
   public static final String DB_DRIVER = "org.postgresql.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_CockroachDB";

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
