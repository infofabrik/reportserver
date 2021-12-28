package net.datenwerke.rs.base.service.dbhelper.queries;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class OffsetQuery extends CompositeQuery {

   protected QueryBuilder queryBuilder;
   protected boolean nestQuery = true;

   public OffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
      super(nestedQuery);
      this.queryBuilder = queryBuilder;
      this.columnNamingService = columnNamingService;
   }

   public void setNestQuery(boolean nestQuery) {
      this.nestQuery = nestQuery;
   }

   public boolean isNestQuery() {
      return nestQuery;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      if (nestQuery)
         buf.append("SELECT * FROM (");

      nestedQuery.appendToBuffer(buf);

      if (nestQuery)
         buf.append(") limitQry");

      buf.append(" LIMIT ");
      buf.append(queryBuilder.getLimit());
      buf.append(" OFFSET ");
      buf.append(queryBuilder.getOffset());
   }
}
