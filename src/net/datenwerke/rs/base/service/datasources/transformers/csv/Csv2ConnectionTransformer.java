package net.datenwerke.rs.base.service.datasources.transformers.csv;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import com.google.inject.Inject;

import net.datenwerke.dbpool.DbPoolService;
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

public class Csv2ConnectionTransformer extends Csv2XTransformer<Connection> {

   @Inject
   public Csv2ConnectionTransformer(DbPoolService dbPoolService, DBHelperService dbHelperService,
         TempTableService tempTableService, TableModelDbHelper tableModelDbHelper,
         ResultCacheService resultCacheService) {
      super(dbPoolService, dbHelperService, tempTableService, tableModelDbHelper, resultCacheService);
   }

   @Override
   public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
      DatasourceContainer container = containerProvider.getDatasourceContainer();
      return (null != container && container.getDatasource() instanceof CsvDatasource
            && dst.isAssignableFrom(Connection.class));
   }

   @Override
   protected Connection transformResult(Object r, DatasourceContainerProvider datasourceContainerProvider,
         ParameterSet parameterSet, Class<TableDataSource> targetType) {
      TempTableResult tempTableResult = (TempTableResult) r;

      try {
         String query = tempTableResult.getFinalQuery();

         String queryWrapper = ((CsvDatasourceConfig) datasourceContainerProvider.getDatasourceContainer()
               .getDatasourceConfig()).getQueryWrapper();
         if (null == queryWrapper || "".equals(queryWrapper.trim()))
            queryWrapper = query;

         parameterSet.addVariable("query", query);
         for (String alias : tempTableResult.getTableHelper().getTableAliases())
            parameterSet.addVariable(alias, tempTableResult.getTableHelper().getTableName(alias));

         Connection connection = (Connection) dbPoolService.getConnection(tempTableResult.getPoolConfig()).get();

         return connection;
      } catch (InterruptedException | ExecutionException | SQLException e) {
         throw new RuntimeException(e);
      }
   }
}