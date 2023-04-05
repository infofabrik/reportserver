package net.datenwerke.rs.base.service.dbhelper.db.derby;

import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;

public class DerbyOffsetQuery extends OffsetQuery {

   public DerbyOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
      super(nestedQuery, queryBuilder, columnNamingService);
      this.columnNamingService = columnNamingService;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      if (nestQuery)
         buf.append("SELECT * FROM (");

      nestedQuery.appendToBuffer(buf);

      if (nestQuery)
         buf.append(") limitQry");

      buf.append(" OFFSET ");
      buf.append(queryBuilder.getOffset());
      buf.append(" ROWS ");
      buf.append(" FETCH NEXT ");
      buf.append(queryBuilder.getLimit());
      buf.append(" ROWS ONLY ");

   }

}
