package net.datenwerke.rs.base.service.dbhelper.hooks;

import java.sql.Connection;
import java.util.Map;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryReplacementHelper;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.entities.filters.FilterSpec;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterValue;

public interface DbFilterExecutorHook extends Hook {

   QryCondition getQueryCondition(FilterSpec filter, Map<String, ParameterValue> pMap, QueryReplacementHelper prefixer,
         QueryBuilder queryBuilder, ManagedQuery managedQuery, Connection connection);

   boolean consumes(FilterSpec filter);

}
