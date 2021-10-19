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

}
