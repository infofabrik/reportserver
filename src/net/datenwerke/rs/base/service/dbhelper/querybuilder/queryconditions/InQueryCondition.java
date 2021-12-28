package net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions;

import java.sql.Types;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import net.datenwerke.rs.base.service.dbhelper.DatabaseHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ColumnNamingService;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;

public class InQueryCondition implements QryCondition {

   private Column column;
   private QueryBuilder queryBuilder;
   private Collection<?> dataCollection;

   public InQueryCondition(Column column, Collection<?> dataCollection, QueryBuilder queryBuilder) {
      this.column = column;
      this.dataCollection = dataCollection;
      this.queryBuilder = queryBuilder;
   }

   @Override
   public void appendToBuffer(StringBuffer buf, ColumnNamingService columnNamingService) {
      Collection<?> preprocessedCollection = preprocessCollection(dataCollection);

      boolean caseSensitive = null != column.getFilter() ? column.getFilter().isCaseSensitive() : true;

      DatabaseHelper dbHelper = queryBuilder.getDbHelper();

      buf.append(' '); // $NON-NLS-1$

      DatabaseHelper helper = queryBuilder.getDbHelper();
      Integer type = column.getType();
      if (null != column.getAggregateFunction()) {
         switch (column.getAggregateFunction()) {
         case COUNT:
         case COUNT_DISTINCT:
            type = Types.BIGINT;
         }
      }
      List<String> reps = queryBuilder.nextReplacement(preprocessedCollection, type);
      buf.append(
            helper.conditionIn(dbHelper.prepareColumnForComparison(columnNamingService.getColumnName(column), column),
                  reps, caseSensitive));
   }

   private Collection<?> preprocessCollection(Collection<?> col) {
      boolean caseSensitive = null != column.getFilter() ? column.getFilter().isCaseSensitive() : true;

      if (caseSensitive)
         return col;

      /* to lower everything */
      Collection<Object> preprocessed = new ArrayList<Object>();

      for (Object o : col) {
         if (o instanceof String)
            o = ((String) o).toLowerCase();

         preprocessed.add(o);
      }

      return preprocessed;
   }

   @Override
   public List<Column> getContainedColumns() {
      LinkedList<Column> res = new LinkedList<Column>();
      res.add(column);
      return res;
   }

}
