package net.datenwerke.rs.base.service.datasources.transformers.csv;

import com.google.inject.Inject;
import com.google.inject.Singleton;

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
import net.datenwerke.rs.saikupivot.service.SaikuDatasourceQueryTransformerMarker;

/**
 * Transforms DataSourceDefitions into QRDataSources
 *
 */
@Singleton
public class Csv2QueryTransformer extends Csv2XTransformer<SaikuDatasourceQueryTransformerMarker> {

   @Inject
   public Csv2QueryTransformer(DbPoolService dbPoolService, DBHelperService dbHelperService,
         TempTableService tempTableService, TableModelDbHelper tableModelDbHelper,
         ResultCacheService resultCacheService) {
      super(dbPoolService, dbHelperService, tempTableService, tableModelDbHelper, resultCacheService);
   }

   @Override
   public boolean consumes(DatasourceContainerProvider containerProvider, Class<?> dst) {
      DatasourceContainer container = containerProvider.getDatasourceContainer();
      return (null != container && container.getDatasource() instanceof CsvDatasource
            && dst.isAssignableFrom(SaikuDatasourceQueryTransformerMarker.class));
   }

   @Override
   protected SaikuDatasourceQueryTransformerMarker transformResult(Object r,
         DatasourceContainerProvider datasourceContainerProvider, ParameterSet parameterSet,
         Class<TableDataSource> targetType) {
      TempTableResult tempTableResult = (TempTableResult) r;

      String query = tempTableResult.getFinalQuery();

      String queryWrapper = ((CsvDatasourceConfig) datasourceContainerProvider.getDatasourceContainer()
            .getDatasourceConfig()).getQueryWrapper();
      if (null == queryWrapper || "".equals(queryWrapper.trim()))
         queryWrapper = query;

      parameterSet.addVariable("query", query);
      for (String alias : tempTableResult.getTableHelper().getTableAliases())
         parameterSet.addVariable(alias, tempTableResult.getTableHelper().getTableName(alias));

      return new SaikuDatasourceQueryTransformerMarker(queryWrapper);
   }
}
