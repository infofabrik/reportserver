package net.datenwerke.rs.resultcache;

public interface ResultCacheService {

   CacheableResult getCachedResult(ResultCacheKey ds);

   void addToResultCache(ResultCacheKey datasource, CacheableResult result);

   void removeFromResultCache(ResultCacheKey datasource);

   void maintainCache();

   void flush();

}
