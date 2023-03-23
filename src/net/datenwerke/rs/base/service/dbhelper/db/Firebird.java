package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class Firebird extends DatabaseHelper {

   public static final String DB_NAME = "Firebird";
   public static final String DB_DRIVER = "org.firebirdsql.jdbc.FBDriver";
   public static final String DB_DESCRIPTOR = "DBHelper_Firebird";
   public static final String DB_DESCRIPTION = "Firebird Jaybird driver";
   
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
      return new LimitQuery(nestedQuery, queryBuilder) {
         @Override
         public void appendToBuffer(StringBuffer buf) {
            buf.append("SELECT FIRST ");
            buf.append(queryBuilder.getLimit());
            buf.append(" * FROM (");
            nestedQuery.appendToBuffer(buf);
            buf.append(") limitQry");
         }
      };
   }

   @Override
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new OffsetQuery(nestedQuery, queryBuilder, columnNamingService) {
         @Override
         public void appendToBuffer(StringBuffer buf) {
            buf.append("SELECT FIRST ");
            buf.append(queryBuilder.getLimit());
            buf.append(" SKIP ");
            buf.append(queryBuilder.getOffset());
            buf.append(" * FROM (");
            nestedQuery.appendToBuffer(buf);
            buf.append(") limitQry");
         }
      };
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }
}
