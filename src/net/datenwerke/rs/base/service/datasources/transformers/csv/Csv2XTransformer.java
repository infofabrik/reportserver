package net.datenwerke.rs.base.service.datasources.transformers.csv;

import java.io.IOError;
import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.inject.Inject;

import liquibase.exception.DatabaseException;
import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasource;
import net.datenwerke.rs.base.service.datasources.definitions.CsvDatasourceConfig;
import net.datenwerke.rs.base.service.datasources.helpers.CsvCellProcessorGuesser;
import net.datenwerke.rs.base.service.datasources.helpers.CsvToTableModelHelper;
import net.datenwerke.rs.base.service.datasources.table.impl.TableDataSource;
import net.datenwerke.rs.base.service.datasources.transformers.DataSourceDefinitionTransformer;
import net.datenwerke.rs.base.service.dbhelper.DBHelperService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.utils.TableModelDbHelper;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainer;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceContainerProvider;
import net.datenwerke.rs.core.service.internaldb.TempTableHelper;
import net.datenwerke.rs.core.service.internaldb.TempTableResult;
import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.core.service.reportmanager.exceptions.DatabaseConnectionException;
import net.datenwerke.rs.core.service.reportmanager.exceptions.UnsupportedDriverException;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.resultcache.CacheableResult;
import net.datenwerke.rs.resultcache.ResultCacheKeyDatasource;
import net.datenwerke.rs.resultcache.ResultCacheService;

public abstract class Csv2XTransformer<X> implements DataSourceDefinitionTransformer<X> {

   protected static final String TABLE_ALIAS = "_RS_TMP_TABLENAME";

   protected DBHelperService dbHelperService;
   protected DbPoolService dbPoolService;
   protected TempTableService tempTableService;
   protected TableModelDbHelper tableModelDbHelper;
   protected ResultCacheService resultCacheService;
   protected LoadingCache<ResultCacheKeyDatasource, CacheableResult> cacheAccessor;

   @Inject
   public Csv2XTransformer(DbPoolService dbPoolService, DBHelperService dbHelperService,
         TempTableService tempTableService, TableModelDbHelper tableModelDbHelper,
         ResultCacheService resultCacheService) {
      this.dbPoolService = dbPoolService;
      this.dbHelperService = dbHelperService;
      this.tempTableService = tempTableService;
      this.tableModelDbHelper = tableModelDbHelper;
      this.resultCacheService = resultCacheService;

      initCache();
   }

   protected void initCache() {
      cacheAccessor = CacheBuilder.newBuilder().maximumSize(0)
            .build(new CacheLoader<ResultCacheKeyDatasource, CacheableResult>() {

               @Override
               public CacheableResult load(ResultCacheKeyDatasource key) throws Exception {
                  CacheableResult cachedResult = resultCacheService.getCachedResult(key);

                  if (null != cachedResult)
                     return cachedResult;

                  TempTableResult tempTableResult = createTempTable((CsvDatasource) key.getDatasource(),
                        (CsvDatasourceConfig) key.getConfig());

                  if (null == tempTableResult.getTimeout())
                     tempTableResult.setTimeout(((CsvDatasource) key.getDatasource()).getDatabaseCache() * 60 * 1000L);

                  resultCacheService.addToResultCache(key, tempTableResult);

                  return tempTableResult;
               }

            });
   }

   @Override
   public X transform(DatasourceContainerProvider containerProvider, Class<?> dst, ParameterSet parameters)
         throws UnsupportedDriverException, DatabaseConnectionException {
      /* get correct datasource definition and config */
      DatasourceContainer container = containerProvider.getDatasourceContainer();
      CsvDatasource ds = (CsvDatasource) container.getDatasource();
      CsvDatasourceConfig dsConfig = (CsvDatasourceConfig) container.getDatasourceConfig();

      /* check cache or create object */
      ResultCacheKeyDatasource key = new ResultCacheKeyDatasource(ds, dsConfig);
      try {
         CacheableResult result = cacheAccessor.get(key);

         return transformResult(result, containerProvider, parameters, TableDataSource.class);
      } catch (ExecutionException e) {
         throw new IllegalStateException(e);
      }
   }

   private TempTableResult createTempTable(CsvDatasource ds, CsvDatasourceConfig dsConfig) {
      ConnectionPoolConfig cpc = tempTableService.getConnectionConfig();
      String helperId = String.valueOf(ds.getId()) + "_" + String.valueOf(dsConfig.getId()) + "_"
            + String.valueOf(dsConfig.getOldTransientId());
      TempTableHelper tempTableHelper = tempTableService.getHelper(helperId);
      try {
         String tableName = tempTableHelper.getTableName(cpc, TABLE_ALIAS);

         try {
            CsvToTableModelHelper csvToTableModelHelper = new CsvToTableModelHelper();
            csvToTableModelHelper.setPreferences(ds.getQuote().charAt(0), ds.getSeparator().charAt(0), "\n");
            CsvCellProcessorGuesser guessDatatypes = csvToTableModelHelper.guessDatatypes(ds.getDataStream(dsConfig),
                  100);
            RSTableModel rsTableModel = csvToTableModelHelper.processCSV(ds.getDataStream(dsConfig), guessDatatypes);

            tableModelDbHelper.writeRsTableModel(rsTableModel, cpc, tableName);
            String query = "select * from ${" + TABLE_ALIAS + "}";

            return new TempTableResult(tempTableHelper, cpc, query);

         } catch (IOError | IOException | DatabaseException | SQLException | InterruptedException
               | ExecutionException e) {
            throw new RuntimeException(e);
         }
      } finally {
         tempTableHelper.writeOperationCompleted();
      }
   }

   protected abstract X transformResult(Object r, DatasourceContainerProvider datasourceContainerProvider,
         ParameterSet parameterSet, Class<TableDataSource> targetType);
}
