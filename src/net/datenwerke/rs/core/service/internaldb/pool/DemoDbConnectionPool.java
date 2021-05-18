package net.datenwerke.rs.core.service.internaldb.pool;

import java.util.Date;
import java.util.Properties;

import net.datenwerke.dbpool.config.ConnectionPoolConfig;

public class DemoDbConnectionPool implements ConnectionPoolConfig {

   private final String jdbcUrl;

   public DemoDbConnectionPool(String jdbcUrl) {
      this.jdbcUrl = jdbcUrl;
   }

   @Override
   public String getDriver() {
      return "org.h2.Driver";
   }

   @Override
   public String getUsername() {
      return "demo";
   }

   @Override
   public String getPassword() {
      return "demo";
   }

   @Override
   public String getJdbcUrl() {
      return jdbcUrl;
   }

   @Override
   public boolean isPoolable() {
      return false;
   }

   @Override
   public boolean isMightChange() {
      return false;
   }

   @Override
   public Properties getProperties() {
      return new Properties();
   }

   @Override
   public Date getLastUpdated() {
      return null;
   }

   @Override
   public Properties getJdbcProperties() {
      return null;
   }
}
