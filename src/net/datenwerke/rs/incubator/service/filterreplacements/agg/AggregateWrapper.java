package net.datenwerke.rs.incubator.service.filterreplacements.agg;

import java.security.AccessController;
import java.security.PrivilegedAction;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.entities.AggregateFunction;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class AggregateWrapper {

	private final EntityClonerService entityClonerService;
	private final SimpleDataSupplier datasupplier;
	private final DatasourceContainerProvider datasourceContainerProvider;
	private final Column column;
	private final ParameterSet parameterSet;
	private final ManagedQuery query;
	private AggregateFunction agg;
	private final QueryBuilder queryBuilder;

	@Inject
	public AggregateWrapper(
		EntityClonerService entityClonerService,
		SimpleDataSupplier datasupplier,
		@Assisted Column column,
		@Assisted ParameterSet parameterSet,
		@Assisted DatasourceContainerProvider datasourceContainerProvider,
		@Assisted ManagedQuery query,
		@Assisted QueryBuilder queryBuilder
		) {
		
		this.entityClonerService = entityClonerService;
		this.datasupplier = datasupplier;
		this.column = column;
		this.parameterSet = parameterSet;
		this.datasourceContainerProvider = datasourceContainerProvider;
		this.query = query;
		this.queryBuilder = queryBuilder;
	}
	
	public Object max(){
		return execute(AggregateFunction.MAX);
	}

	public Object min(){
		return execute(AggregateFunction.MIN);
	}

	public Object avg(){
		return execute(AggregateFunction.AVG);
	}

	public Object count(){
		return execute(AggregateFunction.COUNT);
	}

	public Object countDistinct(){
		return execute(AggregateFunction.COUNT_DISTINCT);
	}

	public Object sum(){
		return execute(AggregateFunction.SUM);
	}

	public Object variance(){
		return execute(AggregateFunction.VARIANCE);
	}
	
	public Object execute(final AggregateFunction agg) {
		return AccessController.doPrivileged(new PrivilegedAction<Object>() {
			@Override
			public Object run() {
				if(null == agg)
					throw new ReportExecutorRuntimeException("aggregate filter needs an aggregate. for example: agg.max()");
				
				try {
					Column clone = entityClonerService.cloneEntity(column);
					clone.setAggregateFunction(agg);
					clone.setFilter(null);
					
					RSTableModel model = datasupplier.getColumnValuesPaged(datasourceContainerProvider, parameterSet, clone, null, null, false);
					if(model.getRowCount() != 1)
						throw new ReportExecutorRuntimeException("expected one max value");
					
					return model.getData().get(0).getAt(0);
				} catch (ReportExecutorException e) {
					throw new ReportExecutorRuntimeException(e);
				}
			}
		});
	}
}