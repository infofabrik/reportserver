package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

/**
 * 
 *
 */
public class MonetDB extends DatabaseHelper {

   public static final String DB_NAME = "MonetDB";
   public static final String DB_DRIVER = "nl.cwi.monetdb.jdbc.MonetDriver";
   public static final String DB_DESCRIPTOR = "DBHelper_MonetDB";
   public static final String DB_DESCRIPTION = "MonetDB JDBC driver";

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
      return "\"";
   }

   @Override
   public OrderQuery getNewOrderQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      OrderQuery orderQuery = super.getNewOrderQuery(nestedQuery, queryBuilder, columnNamingService);
      orderQuery.setNestQuery(false);
      return orderQuery;
   }

   @Override
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      OffsetQuery offsetQuery = super.getNewOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
      offsetQuery.setNestQuery(false);
      return offsetQuery;
   }

   @Override
   public LimitQuery getNewLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
      LimitQuery limitQuery = super.getNewLimitQuery(nestedQuery, queryBuilder);
      limitQuery.setNestQuery(false);
      return limitQuery;
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }
}
