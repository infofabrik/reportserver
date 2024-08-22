package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;

public class Redshift extends DatabaseHelper {

   public static final String DB_NAME = "Redshift";
   public static final String DB_DRIVER = "com.amazon.redshift.jdbc42.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_Redshift";
   public static final String DB_DESCRIPTION = "AWS Redshift JDBC driver";

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
      return "";
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }
}
