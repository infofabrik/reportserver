package net.datenwerke.rs.incubator.service.filterreplacements.agg;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;

public interface AggWrapperFactory {

	public AggregateWrapper create(Column column, ParameterSet parameters,
			DatasourceContainerProvider datasourceContainerProvider, QueryBuilder queryBuilder, ManagedQuery query);
}