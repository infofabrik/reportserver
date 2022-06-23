package net.datenwerke.dbpool.config;

import java.util.Date;
import java.util.Properties;

public interface ConnectionPoolConfig {

   String getDriver();

   String getUsername();

   String getPassword();

   String getJdbcUrl();

   Long getDatasourceId();

   String getDatasourceName();

   boolean isPoolable();

   boolean isMightChange();

   Properties getProperties();

   Date getLastUpdated();

   Properties getJdbcProperties();

   /**
    * Some dbs, e.g. SQLite, cannot change the readOnly flag after establishing a
    * connection. This has to be set before the connection is created, e.g. with
    * open_mode=1 JDBC property.
    * 
    * @return true if readOnly flag can be change after connection establishment.
    */
   boolean canChangeReadOnlyFlagAfterConnectionCreation();

}
