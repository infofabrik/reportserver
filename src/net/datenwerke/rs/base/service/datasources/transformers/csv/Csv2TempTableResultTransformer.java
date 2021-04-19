package net.datenwerke.rs.base.service.datasources.transformers.csv;

import com.google.inject.Inject;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableModelDbHelper;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.internaldb.TempTableResult;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.resultcache.ResultCacheService;

public class Csv2TempTableResultTransformer extends Csv2XTransformer<TempTableResult> {

	@Inject
	public Csv2TempTableResultTransformer(
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
		return (null != container && container.getDatasource() instanceof CsvDatasource && dst.isAssignableFrom(TempTableResult.class));
	}

	@Override
	protected TempTableResult transformResult(Object r, DatasourceContainerProvider datasourceContainerProvider, ParameterSet parameterSet, Class<TableDataSource> targetType) {
		TempTableResult tempTableResult = (TempTableResult) r;
		return tempTableResult;
	}
}