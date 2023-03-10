package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

/**
 * 
 *
 */
public class SapHana extends DatabaseHelper {

   public static final String DB_NAME = "SAP HANA";
   public static final String DB_DRIVER = "com.sap.db.jdbc.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_SAPHANADB";

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
