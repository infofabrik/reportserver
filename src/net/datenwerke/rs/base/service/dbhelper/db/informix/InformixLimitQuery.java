package net.datenwerke.rs.base.service.dbhelper.db.informix;

import net.datenwerke.rs.base.service.dbhelper.queries.LimitQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class InformixLimitQuery extends LimitQuery {

   public InformixLimitQuery(Query nestedQuery, QueryBuilder queryBuilder) {
      super(nestedQuery, queryBuilder);
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      if (0 == queryBuilder.getLimit()) {
         buf.append("SELECT * FROM (");
         nestedQuery.appendToBuffer(buf);
         buf.append(") limitQry");
         buf.append(" WHERE 1=0 ");
      } else {
         buf.append("SELECT FIRST ");
         buf.append(queryBuilder.getLimit());
         buf.append(" * FROM (");
         nestedQuery.appendToBuffer(buf);
         buf.append(") limitQry");
      }
   }
}
