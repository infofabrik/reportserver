package net.datenwerke.rs.base.service.dbhelper.db;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

/**
 * 
 *
 */
public class Exasol extends DatabaseHelper {

   public static final String DB_NAME = "Exasol";
   public static final String DB_DRIVER = "com.exasol.jdbc.EXADriver";
   public static final String DB_DESCRIPTOR = "DBHelper_Exasol";
   public static final String DB_DESCRIPTION = "Exasol JDBC driver";

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
   public OffsetQuery getNewOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      return new OffsetQuery(nestedQuery, queryBuilder, columnNamingService) {
         @Override
         public void appendToBuffer(StringBuffer buf) {
            if (nestQuery)
               buf.append("SELECT * FROM (");

            nestedQuery.appendToBuffer(buf);

            if (nestQuery)
               buf.append(") limitQry");

            buf.append(" ORDER BY 1 ");
            buf.append(" LIMIT ");
            buf.append(queryBuilder.getLimit());
            buf.append(" OFFSET ");
            buf.append(queryBuilder.getOffset());
         }
      };
   }
   
   @Override
   public String getDescription() {
      return DB_DESCRIPTION;
   }

}
