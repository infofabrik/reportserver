package net.datenwerke.rs.base.service.dbhelper.db.derby;

import net.datenwerke.rs.base.service.dbhelper.queries.OrderOffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.OrderQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class DerbyOrderOffsetQuery extends OrderOffsetQuery {

   public DerbyOrderOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      super(nestedQuery, queryBuilder, columnNamingService);
      this.columnNamingService = columnNamingService;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      /* order by */
      OrderQuery orderQuery = new OrderQuery(nestedQuery, queryBuilder, columnNamingService);
      orderQuery.appendToBuffer(buf);
      
      /* limit */
      if (0 == queryBuilder.getLimit()) {
         buf.append(" WHERE 0 = 1 ");
      } else {
         buf.append(" OFFSET ");
         buf.append(queryBuilder.getOffset());
         buf.append(" ROWS ");
         buf.append(" FETCH NEXT ");
         buf.append(queryBuilder.getLimit());
         buf.append(" ROWS ONLY ");
      }

   }
}
