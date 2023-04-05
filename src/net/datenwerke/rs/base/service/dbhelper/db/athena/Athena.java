package net.datenwerke.rs.base.service.dbhelper.db.athena;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderOffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class Athena extends DatabaseHelper {

   public static final String DB_NAME = "Athena";
   public static final String DB_DRIVER = "com.simba.athena.jdbc.Driver";
   public static final String DB_DESCRIPTOR = "DBHelper_Athena";
   public static final String DB_DESCRIPTION = "Simba AWS Athena driver";

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
   
   @Override
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new AthenaOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }
   
   @Override
   public OrderOffsetQuery getNewOrderOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new AthenaOrderOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }
}
