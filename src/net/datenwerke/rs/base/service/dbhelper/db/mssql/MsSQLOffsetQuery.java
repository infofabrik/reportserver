package net.datenwerke.rs.base.service.dbhelper.db.mssql;

import net.datenwerke.rs.base.service.dbhelper.queries.OffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class MsSQLOffsetQuery extends OffsetQuery {

   public MsSQLOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder, ColumnNamingService columnNamingService) {
      super(nestedQuery, queryBuilder, columnNamingService);
      this.columnNamingService = columnNamingService;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {

      buf.append("SELECT ");

      if (queryBuilder.getColumns().size() == 0) {
         buf.append("mssqlOffQry2.*"); //$NON-NLS-1$
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

      buf.append(" FROM (SELECT mssqlOffQry1.*, (ROW_NUMBER() OVER(ORDER BY (SELECT '1' as a))) rowNum FROM (");

      nestedQuery.appendToBuffer(buf);
      buf.append(") mssqlOffQry1 ) mssqlOffQry2 WHERE mssqlOffQry2.rowNum BETWEEN ").append(queryBuilder.getOffset())
            .append(" AND ") //$NON-NLS-1$
            .append(queryBuilder.getLimit() + queryBuilder.getOffset());

   }

}
