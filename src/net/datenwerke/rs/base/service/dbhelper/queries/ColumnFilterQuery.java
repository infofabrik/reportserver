package net.datenwerke.rs.base.service.dbhelper.queries;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class ColumnFilterQuery extends CompositeQuery {

   private final QueryBuilder queryBuilder;
   private ColumnNamingService uniqueColumnNamingService;

   public ColumnFilterQuery(Query nestedQuery, QueryBuilder queryBuilder,
         ColumnNamingService uniqueColumnNamingService) {
      super(nestedQuery);
      this.queryBuilder = queryBuilder;
      this.uniqueColumnNamingService = uniqueColumnNamingService;
   }

   @Override
   public void appendToBuffer(StringBuffer buf) {
      if (null == queryBuilder.getColumns() || queryBuilder.getColumns().size() == 0) {
         nestedQuery.appendToBuffer(buf);
         return;
      }

      DatabaseHelper dbHelper = queryBuilder.getDbHelper();

      buf.append("SELECT ");
      if (queryBuilder.isDistinct())
         buf.append("DISTINCT ");

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
            buf.append(queryBuilder.isDistinct()
                  ? dbHelper.prepareColumnForDistinctQuery(uniqueColumnNamingService.getColumnName(col), col)
                  : uniqueColumnNamingService.getColumnName(col));

            i++;
         }
      }

      buf.append(" FROM ( ");
      nestedQuery.appendToBuffer(buf);
      buf.append(") aliasQry");
   }

   protected String quoteAlias(String alias) {
      return queryBuilder.getDbHelper().quoteAlias(alias);
   }
}
