package net.datenwerke.rs.base.service.dbhelper.queries;

import java.util.Iterator;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;

public class InnerFilterWhereQuery extends CompositeQuery {

   private QueryBuilder queryBuilder;

   public InnerFilterWhereQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService uniqueNameService) {
      super(nestedQuery);
      this.queryBuilder = queryBuilder;
      this.columnNamingService = uniqueNameService;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      if (queryBuilder.getAdditionalColumns().isEmpty()) {
         nestedQuery.appendToBuffer(buf);
         buf.append(" WHERE ");
      } else {
         buf.append("SELECT * FROM (");
         nestedQuery.appendToBuffer(buf);
         buf.append(") innerFilterQry ");
         buf.append(" WHERE ");
      }

      Iterator<QryCondition> it = queryBuilder.getInnerConditions().iterator();
      while (it.hasNext()) {
         it.next().appendToBuffer(buf, columnNamingService);
         if (it.hasNext())
            buf.append(" AND ");
      }
   }

}
