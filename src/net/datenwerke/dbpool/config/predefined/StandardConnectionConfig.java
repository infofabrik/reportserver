package net.datenwerke.dbpool.config.predefined;

import net.datenwerke.dbpool.config.ConnectionConfig;

public class StandardConnectionConfig implements ConnectionConfig {

   @Override
   public boolean isReadOnly() {
      return false;
   }

   @Override
   public Integer getIsolationLevel() {
      return null;
   }

}
