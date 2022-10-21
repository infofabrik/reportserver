package net.datenwerke.rs.base.service.reportengines.table.hookers;

import java.sql.Connection;
import java.util.Map;

import net.datenwerke.rs.base.service.dbhelper.hooks.DbFilterExecutorHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryReplacementHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.BinaryColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;

public class BinaryColumnFilterExecutorHooker implements DbFilterExecutorHook {

   @Override
   public QryCondition getQueryCondition(FilterSpec filter, Map<String, ParameterValue> pMap,
         QueryReplacementHelper prefixer, QueryBuilder queryBuilder, ManagedQuery managedQuery, Connection connection) {

      Column a = ((BinaryColumnFilter) filter).getColumnA();
      Column b = ((BinaryColumnFilter) filter).getColumnB();

      switch (((BinaryColumnFilter) filter).getOperator()) {
      case EQUALS:
         return queryBuilder.getNewEqualQueryCondition(a, b);
      case NOT_EQUALS:
         return queryBuilder.getNewNotQueryCondition(queryBuilder.getNewEqualQueryCondition(a, b));
      case LESS:
         return queryBuilder.getNewLessQueryCondition(a, b);
      case LESS_OR_EQUALS:
         return queryBuilder.getNewLessEqualQueryCondition(a, b);
      case GREATER:
         return queryBuilder.getNewGreaterQueryCondition(a, b);
      case GREATER_OR_EQUALS:
         return queryBuilder.getNewGreaterEqualQueryCondition(a, b);
      }
      return null;
   }

   @Override
   public boolean consumes(FilterSpec filter) {
      return filter instanceof BinaryColumnFilter;
   }

}
