package net.datenwerke.rs.incubator.service.filterreplacements.analytics;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.ArrayList;
import java.util.List;

import net.datenwerke.rs.base.service.dbhelper.querybuilder.ManagedQuery;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.QueryBuilder;
import net.datenwerke.rs.base.service.dbhelper.querybuilder.queryconditions.QryCondition;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier;
import net.datenwerke.rs.base.service.reportengines.table.SimpleDataSupplier.DataConsumer;
import net.datenwerke.rs.base.service.reportengines.table.entities.Column;
import net.datenwerke.rs.base.service.reportengines.table.entities.Order;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.ReportExecutorRuntimeException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

public class AnalyticalFunctionWrapper {

	private final EntityClonerService entityClonerService;
	private final SimpleDataSupplier datasupplier;
	private final DatasourceContainerProvider datasourceContainerProvider;
	private final Column column;
	private final ParameterSet parameterSet;
	private final ManagedQuery query;
	private final QueryBuilder queryBuilder;

	@Inject
	public AnalyticalFunctionWrapper(
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
	
	public QryCondition top(double i){
		return executeBT(i, Order.DESC);
	}
	
	public QryCondition bottom(double i){
		return executeBT(i, Order.ASC);
	}
	
	private QryCondition executeBT(final double i, final Order order){
		return AccessController.doPrivileged(new PrivilegedAction<QryCondition>() {
			@Override
			public QryCondition run() {
				return _executeBT(i, order);
			}
		});
	}
	
	private QryCondition _executeBT(double i, Order order){
		Column clone = entityClonerService.cloneEntity(column);
		clone.setAggregateFunction(null);
		clone.setFilter(null);
		clone.setOrder(order);
		
		if(i >= 1){
			int n = (int)Math.round(i);
			
			try{
				RSTableModel model = datasupplier.getColumnValuesPaged(datasourceContainerProvider, parameterSet, clone, n-1, 1, false);
				
				if(model.getRowCount() != 1)
					throw new ReportExecutorRuntimeException("expected one max value");
				
				Object value = model.getData().get(0).getAt(0);
				
				return queryBuilder.getNewGreaterEqualQueryCondition(column, value);
			} catch (ReportExecutorException e) {
				throw new ReportExecutorRuntimeException(e);
			}
		} else if (i > 0){
			try{
				int count = datasupplier.getColumnValuesCount(datasourceContainerProvider, parameterSet, clone, false);
				return executeBT(Math.max(count * i,1), order);
			} catch (ReportExecutorException e) {
				throw new ReportExecutorRuntimeException(e);
			}
		} else 
			throw new ReportExecutorRuntimeException("The analyitcal.max/min expression needs to be bigger than 0");
	}
	
	public List<QryCondition> topGrouped(int i, String groupedBy){
		return executeBTin(i, Order.DESC, groupedBy);
	}
	
	public List<QryCondition> bottomGrouped(int i, String groupedBy){
		return executeBTin(i, Order.ASC, groupedBy);
	}
	
	private List<QryCondition> executeBTin(final int n, final Order order, final String groupedBy){
		return AccessController.doPrivileged(new PrivilegedAction<List<QryCondition>>() {
			@Override
			public List<QryCondition> run() {
				return _executeBTin(n, order, groupedBy);
			}
		});
	}
	
	private List<QryCondition> _executeBTin(final int n, Order order, String groupedBy){
		final List<QryCondition> conditions = new ArrayList<QryCondition>();
		
		/* create list of columns */
		final List<Column> columnList = new ArrayList<Column>();
		
		/* get potential group by columns */
		if(null != groupedBy){
			for(String colName : groupedBy.split(",")){
				colName = colName.trim();
				Column groupByCol = query.getColumnByName(colName);
				
				Column groupByClone = entityClonerService.cloneEntity(groupByCol);
				groupByClone.setAggregateFunction(null);
				groupByClone.setFilter(null);
				groupByClone.setOrder(order);
				
				/* add to list */
				columnList.add(groupByClone);
			}
		}
		
		/* get in column */
		Column colClone = entityClonerService.cloneEntity(column);
		colClone.setAggregateFunction(null);
		colClone.setFilter(null);
		colClone.setOrder(order);
		
		columnList.add(colClone);
		
		if(n >= 1){
			try{
				DataConsumer consumer = null;
				final int nrOfGroupRows = columnList.size()-1;
				
				if(nrOfGroupRows > 0){
					consumer = new DataConsumer() {
						private Object[] lastGroupColValues = new Object[nrOfGroupRows];
						private int cnt = n;
						private Object value = null;
						
						@Override
						public void consumeRow(Object[] row) {
							boolean groupBreak = false;

							/* loop over group fields */
							for(int i = 0; i < nrOfGroupRows; i++){
								if((null == lastGroupColValues[i] && null != row[i]) ||
								   (null == row[i] && null != lastGroupColValues[i]) ||
								   (null != lastGroupColValues[i] && null != row[i] && ! lastGroupColValues[i].equals(row[i]))){
									groupBreak = true;
									break;
								}
							}
							
							if(cnt > 0 && ! groupBreak){
								cnt--;
								if(null != row[columnList.size()-1])
									value = row[columnList.size()-1];
							}
							
							/* break ? */
							if(groupBreak){
								/* add condition */
								if(null != value){
									conditions.add(
										queryBuilder.getNewAndQueryCondition(
											queryBuilder.getNewGreaterEqualQueryCondition(column, value),
											buildEqualGroupCondition(columnList, lastGroupColValues, nrOfGroupRows)
										)
									);
								}
								
								/* reset */
								cnt = n - 1;
								value = row[columnList.size()-1];
							}
							
							/* set group fields */
							for(int i = 0; i < nrOfGroupRows; i++)
								lastGroupColValues[i] = row[i];
						}

						@Override
						public void allConsumed() {
							if(null != value){
								conditions.add(
									queryBuilder.getNewAndQueryCondition(
										queryBuilder.getNewGreaterEqualQueryCondition(column, value),
										buildEqualGroupCondition(columnList, lastGroupColValues, nrOfGroupRows)
									)
								);
							}
						}
					};
				} else {
					throw new ReportExecutorRuntimeException("Expected at least one group row");
				}
				
				/* loop over result */
				datasupplier.getColumnValuesPaged(datasourceContainerProvider, parameterSet, columnList, null, null, null, false, consumer);
			} catch (ReportExecutorException e) {
				throw new ReportExecutorRuntimeException(e);
			}
		} else 
			throw new ReportExecutorRuntimeException("The analyitcal.top/bottom expression needs to be bigger than 1");
		
		return conditions;
	}

	protected QryCondition buildEqualGroupCondition(List<Column> columnList, Object[] lastGroupColValues, int nrOfGroupRows) {
		if(nrOfGroupRows == 1)
			return queryBuilder.getNewEqualQueryCondition(query.getColumnByName(columnList.get(0).getName()), lastGroupColValues[0]);
		
		return queryBuilder.getNewAndQueryCondition(
			queryBuilder.getNewEqualQueryCondition(query.getColumnByName(columnList.get(nrOfGroupRows-1).getName()), lastGroupColValues[nrOfGroupRows-1]),
			buildEqualGroupCondition(columnList, lastGroupColValues, nrOfGroupRows -1)
		);
	}
}