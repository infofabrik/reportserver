package net.datenwerke.rs.base.service.dbhelper.db.informix;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class Informix extends DatabaseHelper {

   public static final String DB_DESCRIPTOR = "DBHelper_Informix";
   public static final String DB_NAME = "Informix";
   public static final String DB_DRIVER = "com.informix.jdbc.IfxDriver";

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
   public LimitQuery getNewLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
      return new InformixLimitQuery(nestedQuery, queryBuilder);
   }

   @Override
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new InformixOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }

   @Override
   public String getIdentifierQuoteChar() {
      return "";
   }
}
