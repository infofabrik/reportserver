package net.datenwerke.rs.core.service.internaldb;

import java.sql.SQLException;
import java.util.concurrent.ExecutionException;

import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.base.service.datasources.definitions.DatabaseDatasource;

public interface TempTableService {

   String PROPERTY_KEY_DEFAULT_DATASOURCE = "internaldb.datasource";

   /**
    * Ensure to call {@link TempTableHelper#writeOperationCompleted()} after
    * completing your write operation.
    * 
    * @param requester
    */
   public TempTableHelper getHelper(String requester);

   public void shutdown();

   public ConnectionPoolConfig getConnectionConfig();

   void dropRSTMPtables() throws SQLException, InterruptedException, ExecutionException;

   DatabaseDatasource getInternalDbDatasource();
}
