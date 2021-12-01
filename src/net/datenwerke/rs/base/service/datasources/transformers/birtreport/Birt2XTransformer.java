package net.datenwerke.rs.base.service.datasources.transformers.birtreport;

import java.io.IOError;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;
import com.google.inject.Provider;

import liquibase.exception.DatabaseException;
import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableModelDbHelper;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceConfig;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceDefinition;
import net.datenwerke.rs.birt.service.datasources.birtreport.entities.BirtReportDatasourceTargetType;
import net.datenwerke.rs.birt.service.datasources.helper.BirtToRSTableHelper;
import net.datenwerke.rs.birt.service.reportengine.entities.BirtReport;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.internaldb.TempTableHelper;
import net.datenwerke.rs.core.service.internaldb.TempTableResult;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.resultcache.CacheableResult;
import net.datenwerke.rs.resultcache.ExtendedResultCacheKeyDatasource;
import net.datenwerke.rs.resultcache.ResultCacheService;

public abstract class Birt2XTransformer<X> implements DataSourceDefinitionTransformer<X> {

	protected static final String TABLE_ALIAS = "_RS_TMP_TABLENAME";
	
	protected final Provider<BirtToRSTableHelper> birtToTableProvider;

	protected DBHelperService dbHelperService;
	protected DbPoolService dbPoolService;
	protected TempTableService tempTableService;
	protected TableModelDbHelper tableModelDbHelper;
	protected ResultCacheService resultCacheService;
	
	protected LoadingCache<ExtendedResultCacheKeyDatasource, CacheableResult> cacheAccessor;

	@Inject
	public Birt2XTransformer(
		Provider<BirtToRSTableHelper> birtToTableProvider,
		DbPoolService dbPoolService,
		DBHelperService dbHelperService, 
		TempTableService tempTableService, 
		TableModelDbHelper tableModelDbHelper, 
		ResultCacheService resultCacheService) {
		
		this.birtToTableProvider = birtToTableProvider;
		this.dbPoolService = dbPoolService;
		this.dbHelperService = dbHelperService;
		this.tempTableService = tempTableService;
		this.tableModelDbHelper = tableModelDbHelper;
		this.resultCacheService = resultCacheService;
		
		initCache();
	}

	
	protected void initCache() {
		cacheAccessor = CacheBuilder.newBuilder()
			.maximumSize(0)
			.build(new CacheLoader<ExtendedResultCacheKeyDatasource, CacheableResult>(){

				@Override
				public CacheableResult load(ExtendedResultCacheKeyDatasource key) throws Exception {
					CacheableResult cachedResult = resultCacheService.getCachedResult(key);
					
					if(null != cachedResult)
						return cachedResult;
					
					TempTableResult tempTableResult = createTempTable(key.getContainerProvider(), key.getParameters());

					if(null == tempTableResult.getTimeout())
						tempTableResult.setTimeout(((BirtReportDatasourceDefinition)key.getDatasource()).getDatabaseCache() * 60 * 1000L);

					resultCacheService.addToResultCache(key, tempTableResult);

					return tempTableResult;
				}
				
			});
	}


	@Override
	public X transform(DatasourceContainerProvider containerProvider, Class<?> dst, ParameterSet parameters) throws UnsupportedDriverException, DatabaseConnectionException {
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		BirtReportDatasourceConfig dsc = (BirtReportDatasourceConfig) container.getDatasourceConfig();

		/* check cache or create object */
		ExtendedResultCacheKeyDatasource key = new ExtendedResultCacheKeyDatasource(container.getDatasource(), dsc, parameters, containerProvider, dst );
		try {
			CacheableResult result = cacheAccessor.get(key);
			
			return transformResult(result, containerProvider, parameters, TableDataSource.class);
		} catch (ExecutionException e) {
			throw new IllegalStateException(e);
		} 
		
	}
	
	protected TempTableResult createTempTable(DatasourceContainerProvider containerProvider, ParameterSet parameters){
		DatasourceContainer container = containerProvider.getDatasourceContainer();
		DatasourceDefinition ds = container.getDatasource();
		BirtReportDatasourceConfig dsc = (BirtReportDatasourceConfig) container.getDatasourceConfig();
		
		ConnectionPoolConfig cpc = tempTableService.getConnectionConfig();
		String helperId = String.valueOf(ds.getId())  + "_" + String.valueOf(dsc.getId()) + "_" + String.valueOf(dsc.getOldTransientId());

		TempTableHelper tempTableHelper = tempTableService.getHelper(helperId);
		try{
			String tableName = tempTableHelper.getTableName(cpc, TABLE_ALIAS);
			
			BirtReport report = dsc.getReport();
			String target = dsc.getTarget();
			BirtReportDatasourceTargetType targetType = dsc.getTargetType();
			
			if(null == report)
				throw new DatabaseConnectionException("Unable to load data from BirtReport. Report was null.");
			
			try{
				RSTableModel model = birtToTableProvider.get().asTableModel(containerProvider, report, parameters, target, targetType);
				
				tableModelDbHelper.writeRsTableModel(model, cpc, tableName);
				
				String query = "select * from ${" + TABLE_ALIAS + "}";
				
				return new TempTableResult(tempTableHelper, cpc, query);
				
			}catch(IOError | DatabaseException | SQLException | InterruptedException | ExecutionException e){
				throw new RuntimeException(e);
			}
		} finally {
			tempTableHelper.writeOperationCompleted();
		}
	}

	protected abstract X transformResult(Object r, DatasourceContainerProvider datasourceContainerProvider, ParameterSet parameterSet, Class<TableDataSource> targetType);
}
