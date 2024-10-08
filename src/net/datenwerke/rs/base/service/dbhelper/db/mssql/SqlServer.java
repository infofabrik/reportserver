package net.datenwerke.rs.base.service.dbhelper.db.mssql;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderOffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

/**
 * 
 *
 */
public class SqlServer extends DatabaseHelper {

   public static final String DB_NAME = "SQL Server";
   public static final String DB_DRIVER = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
   public static final String DB_DESCRIPTOR = "DBHelper_SQL_Server";
   public static final String DB_DESCRIPTION = "Microsoft JDBC driver for SQL Server (MSSQL)";

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
      return new MsSQLLimitQuery(nestedQuery, queryBuilder);
   }

   @Override
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new MsSQLOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }

   @Override
   public OrderQuery getNewOrderQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new MsSQLOrderQuery(nestedQuery, queryBuilder, columnNamingService);
   }

   @Override
   public OrderOffsetQuery getNewOrderOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new MsSQLOrderOffsetQuery(nestedQuery, queryBuilder, columnNamingService);
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }

}
