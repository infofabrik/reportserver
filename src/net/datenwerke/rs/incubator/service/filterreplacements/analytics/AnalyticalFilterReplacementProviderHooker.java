package net.datenwerke.rs.incubator.service.filterreplacements.analytics;

import net.datenwerke.rs.base.service.dbhelper.hooks.FilterReplacementProviderHook;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.utils.juel.SimpleJuel;

import com.google.inject.Inject;

public class AnalyticalFilterReplacementProviderHooker implements FilterReplacementProviderHook{

	private static final String AGG = "analytical";
	private final AnalyticalFunctionWrapperFactory aggWrapperFactory;
	
	@Inject
	public AnalyticalFilterReplacementProviderHooker(
			AnalyticalFunctionWrapperFactory analyticalFunctionWrapperFactory) {
		this.aggWrapperFactory = analyticalFunctionWrapperFactory;
	}

	@Override
	public void enhance(SimpleJuel juel, Column column, QueryBuilder queryBuilder, ManagedQuery query) {
		if(null == query.getDatasource() || null == query.getDatasource().getDatasourceContainerProvider())
			return;
		
		juel.addReplacement(AGG, aggWrapperFactory.create(column, query.getDatasource().getParameters(), query.getDatasource().getDatasourceContainerProvider(), queryBuilder, query));
	}

}