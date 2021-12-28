package net.datenwerke.rs.incubator.service.filterreplacements.analytics;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface AnalyticalFunctionWrapperFactory {

   public AnalyticalFunctionWrapper create(Column column, ParameterSet parameters,
         DatasourceContainerProvider datasourceContainerProvider, QueryBuilder queryBuilder, ManagedQuery query);
}