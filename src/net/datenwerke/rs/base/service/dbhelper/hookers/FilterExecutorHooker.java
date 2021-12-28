package net.datenwerke.rs.base.service.dbhelper.hookers;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.dbhelper.hooks.DbFilterExecutorHook;
import net.datenwerke.rs.base.service.dbhelper.hooks.FilterReplacementProviderHook;
import net.datenwerke.rs.base.service.dbhelper.queries.Query;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryReplacementHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.IsNullQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.LikeQueryCondition;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.ColumnReference;
import net.datenwerke.rs.base.service.reportengines.table.entities.FilterRange;
import net.datenwerke.rs.base.service.reportengines.table.entities.NullHandling;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.ColumnFilter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.Filter;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;
import net.datenwerke.rs.utils.juel.SimpleJuel;

public class FilterExecutorHooker implements DbFilterExecutorHook {

   public static final String CHARACTER_WILDCARD = "?"; //$NON-NLS-1$
   public static final String SQL_CHARACTER_WILDCARD = "_"; //$NON-NLS-1$

   public static final String WILDCARD = "*"; //$NON-NLS-1$
   public static final String SQL_WILDCARD = "%"; //$NON-NLS-1$

   private final HookHandlerService hookHandler;
   private final Provider<SimpleJuel> simpleJuelProvider;

   @Inject
   public FilterExecutorHooker(HookHandlerService hookHandler, Provider<SimpleJuel> simpleJuelProvider) {

      /* store objects */
      this.hookHandler = hookHandler;
      this.simpleJuelProvider = simpleJuelProvider;
   }

   @Override
   public QryCondition getQueryCondition(FilterSpec filter, Map<String, ParameterValue> pMap,
         QueryReplacementHelper prefixer, QueryBuilder queryBuilder, ManagedQuery managedQuery, Connection connection) {
      return getFilterCondition(((ColumnFilter) filter).getColumn(), pMap, prefixer, queryBuilder, managedQuery);
   }

   private QryCondition getFilterCondition(Column column, Map<String, ParameterValue> pMap,
         QueryReplacementHelper prefixer, QueryBuilder queryBuilder, ManagedQuery managedQuery) {
      QryCondition innerCondition = null;

      /* filter */
      if (null != column.getFilter()) {
         /* include condition */
         QryCondition includeCondition = getIncludeCondition(pMap, prefixer, column, queryBuilder, managedQuery);
         if (null != includeCondition)
            innerCondition = managedQuery.addQryConditionAND(innerCondition, includeCondition, queryBuilder);

         /* exclude values */
         Collection<QryCondition> excludeValueConditions = getExcludeValueCondition(pMap, prefixer, column,
               queryBuilder, managedQuery);
         for (QryCondition condition : excludeValueConditions) {
            innerCondition = managedQuery.addQryConditionAND(innerCondition, condition, queryBuilder);
         }

         /* exclude ranges */
         Collection<QryCondition> excludeRangeConditions = getExcludeRangeConditions(pMap, prefixer, column,
               queryBuilder, managedQuery);
         for (QryCondition condition : excludeRangeConditions) {
            innerCondition = managedQuery.addQryConditionAND(innerCondition, condition, queryBuilder);
         }
      }

      /* likeFilter */
      if (null != column.getLikeFilter() && column.getLikeFilter().length() > 0) {
         LikeQueryCondition cond = queryBuilder.getNewLikeQueryCondition(column, column.getLikeFilter());
         innerCondition = managedQuery.addQryConditionAND(innerCondition, cond, queryBuilder);
      }

      /* null handling */
      if (null != column.getNullHandling()) {
         IsNullQueryCondition inqc = queryBuilder.getNewIsNullQueryCondition(column);

         if (column.getNullHandling().equals(NullHandling.Exlude)) {
            innerCondition = managedQuery.addQryConditionAND(innerCondition, queryBuilder.getNewNotQueryCondition(inqc),
                  queryBuilder);
         } else {
            innerCondition = managedQuery.addQryConditionOR(innerCondition, inqc, queryBuilder);
         }
      }

      return innerCondition;
   }

   private Query nestedAggregatedInnerQuery(Column column, AggregateFunction aggregateFunction, Object data,
         Map<String, ParameterValue> pMap, QueryReplacementHelper prefixer, ManagedQuery managedQuery) {
      QueryBuilder nested = managedQuery.getDbHelper().createNestedQuery(managedQuery.getInnerQuery(), pMap, prefixer,
            managedQuery.getPlainColumnNames());

      Column aggregateColumn = createShallowCopy(column);
      aggregateColumn.setName(column.getName());
      aggregateColumn.setType(column.getType());
      aggregateColumn.setAggregateFunction(aggregateFunction);

      /*
       * select same column twice, as adding the filter to the aggregated column would
       * result in a HAVING filter
       */
      Column filterColumn = createShallowCopy(column);
      filterColumn.setName(column.getName());
      filterColumn.setType(column.getType());
      filterColumn.setHidden(true);
      filterColumn.setGroupedBy(false);

      nested.column(aggregateColumn);
      nested.column(filterColumn);

      nested.where(nested.getNewLikeQueryCondition(filterColumn, data));

      Query nestedQuery = nested.getQuery();
      return nestedQuery;
   }

   private Column createShallowCopy(Column column) {
      if (column instanceof ColumnReference) {
         try {
            Column col = column.getClass().newInstance();
            ((ColumnReference) col).setReference(((ColumnReference) column).getReference());
            return col;
         } catch (Exception e) {
            throw new IllegalArgumentException(e);
         }
      } else
         return new Column();
   }

   private List<QryCondition> getExcludeValueCondition(Map<String, ParameterValue> pMap,
         QueryReplacementHelper prefixer, Column column, QueryBuilder queryBuilder, ManagedQuery managedQuery) {
      List<QryCondition> excludeConditions = new ArrayList<QryCondition>();

      /* get filter */
      Filter filter = column.getFilter();

      List<String> baseExcludes = filter.getExcludeValues();
      List<?> resolvedExcludes = resolveFilterReplacements(baseExcludes, column, queryBuilder, managedQuery);
      List<String> strExcludeValues = getStringValues(resolvedExcludes);

      List<String> nonWildcards = getValuesWithoutWildcards(strExcludeValues);
      List<String> wildcards = getValuesWithWildcards(strExcludeValues);

      /* no wildcards */
      if (!nonWildcards.isEmpty()) {
         excludeConditions
               .add(queryBuilder.getNewNotQueryCondition(queryBuilder.getNewInQueryCondition(column, nonWildcards)));

      }

      /* wildcards */
      for (String wildcard : wildcards) {
         excludeConditions
               .add(queryBuilder.getNewNotQueryCondition(queryBuilder.getNewLikeQueryCondition(column, wildcard)));
      }

      /* include subqueries */

      for (QryCondition cond : getQryConditionValues(resolvedExcludes))
         excludeConditions.add(queryBuilder.getNewNotQueryCondition(cond));

      return excludeConditions;
   }

   private String resolveFilterReplacements(String filter, Column column, QueryBuilder queryBuilder,
         ManagedQuery managedQuery) {
      if (null == filter)
         return null;

      SimpleJuel juel = configureJuel(column, queryBuilder, managedQuery);

      return juel.parse(filter);
   }

   private List<?> resolveFilterReplacements(List<String> filters, Column column, QueryBuilder queryBuilder,
         ManagedQuery managedQuery) {
      if (null == filters || filters.isEmpty())
         return filters;

      SimpleJuel juel = configureJuel(column, queryBuilder, managedQuery);

      List<Object> replaced = new ArrayList<Object>();

      for (String filter : filters) {
         Object object = juel.parseAsObject(filter);
         if (null == object)
            continue;
         if (object instanceof Collection)
            for (Object o : (Collection) object)
               if (o instanceof QryCondition)
                  replaced.add(o);
               else
                  replaced.add(o.toString());
         else if (object instanceof QryCondition)
            replaced.add(object);
         else
            replaced.add(object.toString());

      }

      return replaced;
   }

   private SimpleJuel configureJuel(Column col, QueryBuilder queryBuilder, ManagedQuery managedQuery) {
      SimpleJuel juel = simpleJuelProvider.get();

      for (FilterReplacementProviderHook repProvider : hookHandler.getHookers(FilterReplacementProviderHook.class))
         repProvider.enhance(juel, col, queryBuilder, managedQuery);

      return juel;
   }

   private QryCondition getIncludeCondition(Map<String, ParameterValue> pMap, QueryReplacementHelper prefixer,
         Column column, QueryBuilder queryBuilder, ManagedQuery managedQuery) {
      /* get filter */
      Filter filter = column.getFilter();

      /* don't care if we have neither condition */
      if ((null == filter.getIncludeValues() || filter.getIncludeValues().size() == 0)
            && (null == filter.getIncludeRanges() || filter.getIncludeRanges().size() == 0)) {
         return null;
      }

      /* create include condition */
      List<QryCondition> includeConditions = new ArrayList<QryCondition>();

      /* include values */
      boolean haveIncludeValues = false;
      if (null != filter.getIncludeValues() && filter.getIncludeValues().size() > 0)
         haveIncludeValues = true;

      if (haveIncludeValues) {
         List<String> baseIncludes = filter.getIncludeValues();
         List<?> resolvedIncludes = resolveFilterReplacements(baseIncludes, column, queryBuilder, managedQuery);
         List<String> strIncludeValues = getStringValues(resolvedIncludes);

         /* include non wildcards */
         List<String> nonWildcards = getValuesWithoutWildcards(strIncludeValues);

         if (!nonWildcards.isEmpty())
            includeConditions.add(queryBuilder.getNewInQueryCondition(column, nonWildcards));

         /* include wildcards */
         List<String> wildcards = getValuesWithWildcards(strIncludeValues);

         if (!wildcards.isEmpty()) {

            Iterator<String> wildcardIterator = wildcards.iterator();
            while (wildcardIterator.hasNext()) {
               includeConditions.add(queryBuilder.getNewLikeQueryCondition(column, wildcardIterator.next()));
            }
         }

         /* include subqueries */
         List<QryCondition> directConditions = getQryConditionValues(resolvedIncludes);
         includeConditions.addAll(directConditions);
      }

      /* include ranges */
      Iterator<FilterRange> filterRangeIterator = filter.getIncludeRanges().iterator();
      while (filterRangeIterator.hasNext()) {
         FilterRange filterRange = filterRangeIterator.next();
         addIncludeRange(includeConditions, column, filterRange, queryBuilder, prefixer, pMap, managedQuery);
      }

      if (includeConditions.size() == 0)
         return null;

      if (includeConditions.size() == 1)
         return includeConditions.get(0);

      Iterator<QryCondition> it = includeConditions.iterator();
      QryCondition res = it.next();

      while (it.hasNext()) {
         res = queryBuilder.getNewOrQueryCondition(it.next(), res);
      }

      return res;
   }

   private void addIncludeRange(List<QryCondition> includeConditions, Column column, FilterRange filterRange,
         QueryBuilder queryBuilder, QueryReplacementHelper prefixer, Map<String, ParameterValue> pMap,
         ManagedQuery managedQuery) {
      String from = resolveFilterReplacements(filterRange.getRangeFrom(), column, queryBuilder, managedQuery);
      from = getValueWithConvertedWildcard(from);

      String to = resolveFilterReplacements(filterRange.getRangeTo(), column, queryBuilder, managedQuery);
      to = getValueWithConvertedWildcard(to);

      if (null == from && null == to)
         return;

      QryCondition fromIncludeCondition = null;
      QryCondition toIncludeCondition = null;

      /* from */
      if (null != from) {
         if (!isValueWithSqlWildcard(from))
            fromIncludeCondition = queryBuilder.getNewGreaterEqualQueryCondition(column, from);
         else
            fromIncludeCondition = queryBuilder.getNewGreaterEqualQueryCondition(column,
                  nestedAggregatedInnerQuery(column, AggregateFunction.MIN, from, pMap, prefixer, managedQuery));
      }

      /* to */
      if (null != to) {
         if (!isValueWithSqlWildcard(to))
            toIncludeCondition = queryBuilder.getNewLessEqualQueryCondition(column, to);
         else
            toIncludeCondition = queryBuilder.getNewLessEqualQueryCondition(column,
                  nestedAggregatedInnerQuery(column, AggregateFunction.MAX, to, pMap, prefixer, managedQuery));
      }

      if (null != fromIncludeCondition && null != toIncludeCondition)
         includeConditions.add(queryBuilder.getNewAndQueryCondition(fromIncludeCondition, toIncludeCondition));
      else if (null != fromIncludeCondition)
         includeConditions.add(fromIncludeCondition);
      else if (null != toIncludeCondition)
         includeConditions.add(toIncludeCondition);

   }

   private Collection<QryCondition> getExcludeRangeConditions(Map<String, ParameterValue> pMap,
         QueryReplacementHelper prefixer, Column column, QueryBuilder queryBuilder, ManagedQuery managedQuery) {
      /* prepare list */
      List<QryCondition> conditions = new ArrayList<QryCondition>();

      /* get filter */
      Filter filter = column.getFilter();

      /* make sure we have any ranges */
      if (null == filter.getExcludeRanges())
         return conditions;

      /* add conditions for */
      for (FilterRange filterRange : filter.getExcludeRanges()) {
         String from = resolveFilterReplacements(filterRange.getRangeFrom(), column, queryBuilder, managedQuery);
         from = getValueWithConvertedWildcard(from);

         String to = resolveFilterReplacements(filterRange.getRangeTo(), column, queryBuilder, managedQuery);
         to = getValueWithConvertedWildcard(to);

         if (null == from && null == to)
            continue;

         QryCondition qryFrom = null;
         QryCondition qryTo = null;

         if (null != filterRange.getRangeFrom()) {
            if (isValueWithSqlWildcard(from)) {
               qryFrom = queryBuilder.getNewGreaterEqualQueryCondition(column,
                     nestedAggregatedInnerQuery(column, AggregateFunction.MIN, from, pMap, prefixer, managedQuery));
            } else {
               qryFrom = queryBuilder.getNewGreaterEqualQueryCondition(column, from);
            }

            qryFrom = queryBuilder.getNewNotQueryCondition(qryFrom);
         }

         if (null != filterRange.getRangeTo()) {

            if (isValueWithSqlWildcard(to)) {
               qryTo = queryBuilder.getNewLessEqualQueryCondition(column,
                     nestedAggregatedInnerQuery(column, AggregateFunction.MAX, to, pMap, prefixer, managedQuery));
            } else {
               qryTo = queryBuilder.getNewLessEqualQueryCondition(column, to);
            }

            qryTo = queryBuilder.getNewNotQueryCondition(qryTo);
         }

         if (null != qryFrom && null != qryTo)
            conditions.add(queryBuilder.getNewOrQueryCondition(qryFrom, qryTo));
         else if (null != qryFrom)
            conditions.add(qryFrom);
         else if (null != qryTo)
            conditions.add(qryTo);
      }

      return conditions;
   }

   public List<String> getValuesWithWildcards(Collection<String> values) {
      List<String> list = new ArrayList<String>();

      for (String value : values)
         if (value.contains(WILDCARD) || value.contains(CHARACTER_WILDCARD))
            list.add(value.replace(WILDCARD, SQL_WILDCARD).replace(CHARACTER_WILDCARD, SQL_CHARACTER_WILDCARD));

      return list;
   }

   public List<String> getValuesWithoutWildcards(Collection<String> values) {
      List<String> list = new ArrayList<String>();

      for (String value : values)
         if (!value.contains(WILDCARD) && !value.contains(CHARACTER_WILDCARD))
            list.add(value);

      return list;
   }

   public List<String> getStringValues(List<?> list) {
      List<String> values = new ArrayList<String>();

      for (Object value : list)
         if (value instanceof String)
            values.add((String) value);

      return values;
   }

   public List<QryCondition> getQryConditionValues(List<?> list) {
      List<QryCondition> values = new ArrayList<QryCondition>();

      for (Object value : list)
         if (value instanceof QryCondition)
            values.add((QryCondition) value);

      return values;
   }

   public boolean isValueWithSqlWildcard(String value) {
      if (null == value)
         return false;
      return value.contains(SQL_WILDCARD) || value.contains(SQL_CHARACTER_WILDCARD);
   }

   public String getValueWithConvertedWildcard(String value) {
      if (null == value)
         return null;
      return value.replace(WILDCARD, SQL_WILDCARD).replace(CHARACTER_WILDCARD, SQL_CHARACTER_WILDCARD);
   }

   @Override
   public boolean consumes(FilterSpec filter) {
      return filter instanceof ColumnFilter;
   }

}
