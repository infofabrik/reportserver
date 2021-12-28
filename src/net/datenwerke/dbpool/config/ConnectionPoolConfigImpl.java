package net.datenwerke.dbpool.config;

import java.util.Date;
import java.util.Iterator;
import java.util.Properties;

import org.apache.commons.configuration2.HierarchicalConfiguration;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;

import net.datenwerke.dbpool.annotations.ConnectionPoolConfigFile;
import net.datenwerke.rs.utils.misc.Nullable;

public class ConnectionPoolConfigImpl implements ConnectionPoolConfig {

   protected final Long id;

   private Long datasourceId;
   private String datasourceName;

   protected String driver;

   protected Properties properties;

   protected Properties jdbcProperties;

   protected String username;
   protected String password = "";
   protected String jdbcUrl;

   protected boolean mightChange = true;

   protected boolean poolable;

   private Date lastUpdated;

   @Inject
   public ConnectionPoolConfigImpl(
         @Nullable @ConnectionPoolConfigFile HierarchicalConfiguration config,
         @Nullable @Assisted Long id
         ) {
      this.id = id;
      this.setPoolable(null != id);

      properties = new Properties();

      /* try to set default values */
      if (null != config) {
         try {
            if (! config.configurationsAt("pool.defaultconfig").isEmpty()) {
               HierarchicalConfiguration defaultCon = config.configurationAt("pool.defaultconfig");
               if (null != defaultCon) {
                  Iterator<String> keys = defaultCon.getKeys();
                  while (keys.hasNext()) {
                     String key = keys.next();
                     properties.setProperty(key, defaultCon.getString(key));
                  }
               }
            }
         } catch (Exception ignore) {
         }
         if (null == properties)
            properties = new Properties();

         try {
            if (! config.configurationsAt("pool.pool").isEmpty()) {
               HierarchicalConfiguration specCon = config.configurationAt("pool.pool" + id);
               if (null != specCon) {
                  Iterator<String> keys = specCon.getKeys();
                  while (keys.hasNext()) {
                     String key = keys.next();
                     properties.setProperty(key, specCon.getString(key));
                  }
               }
            }
         } catch (Exception ignore) {
         }

      }
   }

   public Long getId() {
      return id;
   }

   @Override
   public String getDriver() {
      return driver;
   }

   public void setDriver(String driver) {
      this.driver = driver;
   }

   @Override
   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   @Override
   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   @Override
   public String getJdbcUrl() {
      return jdbcUrl;
   }

   public void setJdbcUrl(String jdbcUrl) {
      this.jdbcUrl = jdbcUrl;
   }

   public void setMightChange(boolean mightChange) {
      this.mightChange = mightChange;
   }

   @Override
   public boolean isMightChange() {
      return mightChange;
   }

   public void setPoolable(boolean poolable) {
      this.poolable = poolable;
   }

   @Override
   public boolean isPoolable() {
      return poolable;
   }

   @Override
   public Date getLastUpdated() {
      return lastUpdated;
   }

   @Override
   public int hashCode() {
      return id.hashCode();
   }

   @Override
   public boolean equals(Object obj) {
      if (!(obj instanceof ConnectionPoolConfigImpl))
         return false;
      if (null == id)
         return false;
      return id.equals(((ConnectionPoolConfigImpl) obj).id);
   }

   public void setLastUpdated(Date lastUpdated) {
      this.lastUpdated = lastUpdated;
   }

   @Override
   public Properties getProperties() {
      return properties;
   }

   public void setProperty(String key, int i) {
      properties.setProperty(key, String.valueOf(i));
   }

   public void setProperty(String key, String value) {
      properties.setProperty(key, value);
   }

   public void setJdbcProperties(Properties jdbcProperties) {
      this.jdbcProperties = jdbcProperties;
   }

   @Override
   public Properties getJdbcProperties() {
      return jdbcProperties;
   }

   @Override
   public Long getDatasourceId() {
      return datasourceId;
   }

   public void setDatasourceId(Long datasourceId) {
      this.datasourceId = datasourceId;
   }

   @Override
   public String getDatasourceName() {
      return datasourceName;
   }

   public void setDatasourceName(String datasourceName) {
      this.datasourceName = datasourceName;
   }

}
