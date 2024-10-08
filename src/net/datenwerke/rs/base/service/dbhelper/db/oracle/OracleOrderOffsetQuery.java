package net.datenwerke.rs.base.service.dbhelper.db.oracle;

import net.datenwerke.rs.base.service.dbhelper.queries.OrderOffsetQuery;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder.OrderDefinition;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;

public class OracleOrderOffsetQuery extends OrderOffsetQuery {

   public OracleOrderOffsetQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService columnNamingService) {
      super(nestedQuery, queryBuilder, columnNamingService);
      this.columnNamingService = columnNamingService;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {

      buf.append("SELECT ");

      if (queryBuilder.getColumns().size() == 0) {
         buf.append("oraOrdOffQry2.*"); //$NON-NLS-1$
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

      buf.append(" FROM (SELECT oraOrdOffQry1.*, (ROW_NUMBER() OVER(ORDER BY ");

      int i = 1;
      for (OrderDefinition def : queryBuilder.getOrderDefinitions()) {
         /* if distinct && hidden -> ignore */
         if (queryBuilder.ignoreHiddenColumns() && null != def.getColumn().isHidden() && def.getColumn().isHidden())
            continue;

         if (i > 1)
            buf.append(", "); //$NON-NLS-1$

         buf.append(columnNamingService.getColumnName(def.getColumn())).append(' ') // $NON-NLS-1$
               .append(def.getOrder().equals(Order.ASC) ? "ASC" : "DESC"); //$NON-NLS-1$ //$NON-NLS-2$

         i++;
      }

      buf.append(")) oraRowNum FROM (");

      nestedQuery.appendToBuffer(buf);
      buf.append(") oraOrdOffQry1 ) oraOrdOffQry2 WHERE oraOrdOffQry2.oraRowNum BETWEEN ")
            .append(queryBuilder.getOffset() + 1).append(" AND ") //$NON-NLS-1$
            .append(queryBuilder.getLimit() + queryBuilder.getOffset());

   }

}
