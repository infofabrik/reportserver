package net.datenwerke.rs.core.service.internaldb;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datenwerke.dbpool.DbPoolService;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.datasourcemanager.DatasourceService;

@Singleton
public class TempTableServiceImpl implements TempTableService {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private final ConcurrentHashMap<String, TempTableHelper> helperCache = new ConcurrentHashMap<String, TempTableHelper>();
   private AtomicInteger helpercount = new AtomicInteger(0);

   private final DatasourceService datasourceService;
   private final ConfigService configService;
   private final DbPoolService<Connection> dbPoolService;

   @Inject
   public TempTableServiceImpl(DatasourceService datasourceService,
         @SuppressWarnings("rawtypes") DbPoolService dbPoolService, ConfigService configService) {

      this.datasourceService = datasourceService;
      this.dbPoolService = dbPoolService;
      this.configService = configService;
   }

   @Override
   public synchronized TempTableHelper getHelper(String requester) {
      TempTableHelper helper = helperCache.get(requester);
      if (null == helper) {
         helper = new TempTableHelper(helpercount.incrementAndGet());
         helperCache.put(requester, helper);
      }
      helper.incrementWriteRevision();
      return helperCache.get(requester);
   }

   @Override
   public void shutdown() {
      helperCache.values().forEach(TempTableHelper::cleanup);
   }

   @Override
   public void dropRSTMPtables() throws SQLException, InterruptedException, ExecutionException {
      String tablePattern = "(?i:rs_tmptbl_\\d+_\\d+_\\d+)";

      ConnectionPoolConfig cpcTemp = getConnectionConfig();
      Connection connTempdb = dbPoolService.getConnection(cpcTemp).get();

      try {
         ResultSet tables = connTempdb.getMetaData().getTables(connTempdb.getCatalog(), null, null,
               Arrays.asList("TABLE").toArray(new String[0]));
         while (tables.next()) {
            String tablename = tables.getString("TABLE_NAME");
            if (tablename.matches(tablePattern)) {
               connTempdb.prepareStatement("drop table " + tablename).execute();
               logger.info("drop table " + tablename);
            }
         }

      } finally {
         connTempdb.close();
      }
   }

   @Override
   public ConnectionPoolConfig getConnectionConfig() {
      return getInternalDbDatasource().getConnectionConfig();
   }

   @Override
   public DatabaseDatasource getInternalDbDatasource() {
      String dsName = configService.getConfigFailsafe(InternalDbModule.CONFIG_FILE)
            .getString(TempTableService.PROPERTY_KEY_DEFAULT_DATASOURCE, "ReportServer Data Source");
      return ((DatabaseDatasource) datasourceService.getDatasourceByName(dsName));
   }

}
