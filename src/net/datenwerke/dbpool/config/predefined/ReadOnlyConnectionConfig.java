package net.datenwerke.dbpool.config.predefined;

import java.sql.Connection;

import net.datenwerke.dbpool.config.ConnectionConfig;

public class ReadOnlyConnectionConfig implements ConnectionConfig {

   @Override
   public boolean isReadOnly() {
      return true;
   }

   @Override
   public Integer getIsolationLevel() {
      return Connection.TRANSACTION_READ_UNCOMMITTED;
   }

}
