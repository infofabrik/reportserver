package net.datenwerke.rs.base.service.dbhelper.db.informix;

import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class InformixOffsetQuery extends OffsetQuery {

   public InformixOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
      super(nestedQuery, queryBuilder, columnNamingService);
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      buf.append("SELECT SKIP ");
      buf.append(queryBuilder.getOffset());
      buf.append(" FIRST ");
      buf.append(queryBuilder.getLimit());
      buf.append(" * FROM (");
      nestedQuery.appendToBuffer(buf);
      buf.append(") limitQry");
   }

}
