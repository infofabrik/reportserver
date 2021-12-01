package net.datenwerke.rs.base.service.datasources.transformers.csv;

import javax.sql.DataSource;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.predefined.ReadOnlyConnectionConfig;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableModelDbHelper;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.internaldb.TempTableResult;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.resultcache.ResultCacheService;


/**
 * Transforms DataSourceDefitions into QRDataSources
 *
 */
@Singleton
public class Csv2JdcbDatasourceTransformer extends Csv2XTransformer<DataSource> {

	@Inject
	public Csv2JdcbDatasourceTransformer(
			DbPoolService dbPoolService,
			DBHelperService dbHelperService, 
			TempTableService tempTableService, 
			TableModelDbHelper tableModelDbHelper, 
			ResultCacheService resultCacheService
			){
		super(dbPoolService, dbHelperService, tempTableService, tableModelDbHelper, resultCacheService);
	}

	@Override
	public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		return (null != container && container.getDatasource() instanceof CsvDatasource && dst.isAssignableFrom(DataSource.class));
	}

	@Override
	protected DataSource transformResult(Object r, DatasourceContainerProvider datasourceContainerProvider, ParameterSet parameterSet, Class<TableDataSource> targetType) {
		TempTableResult tempTableResult = (TempTableResult) r;

		String query = tempTableResult.getFinalQuery();
		
		String queryWrapper = ((CsvDatasourceConfig)datasourceContainerProvider.getDatasourceContainer().getDatasourceConfig()).getQueryWrapper();
		if(null == queryWrapper || "".equals(queryWrapper.trim()))
			queryWrapper = query;
		
		parameterSet.addVariable("query", query);
		for(String alias : tempTableResult.getTableHelper().getTableAliases())
			parameterSet.addVariable(alias, tempTableResult.getTableHelper().getTableName(alias));
		
		DataSource conn = dbPoolService.getDataSource(tempTableResult.getPoolConfig(), new ReadOnlyConnectionConfig());
		return conn;
	}
}
