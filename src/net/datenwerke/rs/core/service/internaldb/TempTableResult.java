package net.datenwerke.rs.core.service.internaldb;

import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.rs.resultcache.CacheableResult;
import net.datenwerke.rs.resultcache.ResultCacheKey;

public class TempTableResult implements CacheableResult {

   private TempTableHelper tableHelper;
   private ConnectionPoolConfig poolConfig;
   private String query;

   private boolean cleanedUp = false;

   private long created;
   private Long timeout;
   private Long grace = 60 * 60 * 1000L;
   private int rev;

   public TempTableResult(TempTableHelper tableHelper, ConnectionPoolConfig cpc, String query) {
      this.setTableHelper(tableHelper);
      this.setPoolConfig(cpc);
      this.setQuery(query);
      this.created = System.currentTimeMillis();
      this.rev = tableHelper.getWriteRev();
   }

   @Override
   public boolean validate() {
      if (0 == timeout || (timeout > -1 && created + timeout < System.currentTimeMillis())) {
         return false;
      } else {
         return true;
      }
   }

   @Override
   public boolean cleanup(boolean force) {
      if (cleanedUp)
         return true;

      if (force || created + timeout + grace < System.currentTimeMillis()) {
         getTableHelper().cleanup(rev);
         cleanedUp = true;
         return true;
      }
      return false;
   }

   @Override
   public boolean cleanup() {
      return cleanup(false);
   }

   public String getRawQuery() {
      return query;
   }

   public String getFinalQuery() {
      if (0 == timeout)
         return tableHelper.processQuery(query, rev);
      return tableHelper.processQuery(query);
   }

   public void setQuery(String query) {
      this.query = query;
   }

   public TempTableHelper getTableHelper() {
      return tableHelper;
   }

   public void setTableHelper(TempTableHelper tableHelper) {
      this.tableHelper = tableHelper;
   }

   public ConnectionPoolConfig getPoolConfig() {
      return poolConfig;
   }

   public void setPoolConfig(ConnectionPoolConfig poolConfig) {
      this.poolConfig = poolConfig;
   }

   @Override
   public Long getTimeout() {
      return timeout;
   }

   @Override
   public void setTimeout(Long timeout) {
      this.timeout = timeout;
   }

   public long getCreated() {
      return created;
   }

   public void setCreated(long created) {
      this.created = created;
   }

   public Long getGracePeriod() {
      return this.grace;
   }

   @Override
   public void setGracePeriod(Long grace) {
      this.grace = grace;

   }

   @Override
   public boolean consumes(ResultCacheKey cacheKey) {
      return true;
   }
}
