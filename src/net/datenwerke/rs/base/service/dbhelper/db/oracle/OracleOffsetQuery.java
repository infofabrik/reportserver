package net.datenwerke.rs.base.service.dbhelper.db.oracle;

import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class OracleOffsetQuery extends OffsetQuery {

   public OracleOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
      super(nestedQuery, queryBuilder, columnNamingService);
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      buf.append("SELECT ");

      if (queryBuilder.getColumns().size() == 0) {
         buf.append('*'); // $NON-NLS-1$
      } else {
         int i = 1;
         for (Column col : queryBuilder.getColumns()) {
            /* if distinct && hidden -> ignore */
            if (queryBuilder.ignoreHiddenColumns() && null != col.isHidden() && col.isHidden())
               continue;

            if (i > 1)
               buf.append(", "); //$NON-NLS-1$

            /* column name */
            buf.append(columnNamingService.getColumnName(col));

            i++;
         }
      }

      buf.append(" FROM ( SELECT oraOffQry1.*, ROWNUM rownumber FROM (");
      nestedQuery.appendToBuffer(buf);
      buf.append(") oraOffQry1 ) oraOffQry2 WHERE oraOffQry2.rownumber BETWEEN ");
      buf.append(queryBuilder.getOffset()).append(" AND ") //$NON-NLS-1$
            .append(queryBuilder.getLimit() + queryBuilder.getOffset()).append(" AND ROWNUM <= ")
            .append(queryBuilder.getLimit());

   }
}
