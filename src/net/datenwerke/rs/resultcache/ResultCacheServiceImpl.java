package net.datenwerke.rs.resultcache;

import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;

@Singleton
public class ResultCacheServiceImpl implements ResultCacheService {

   private final Map<ResultCacheKey, List<CacheableResult>> resultCache;
   private final List<CacheableResult> cleanupPending;
   private long lastCacheMaintainance = System.currentTimeMillis();

   public ResultCacheServiceImpl() {
      resultCache = new ConcurrentHashMap<ResultCacheKey, List<CacheableResult>>();
      cleanupPending = Collections.synchronizedList(new LinkedList<CacheableResult>());
   }

   @Override
   public synchronized void maintainCache() {
      if (lastCacheMaintainance < System.currentTimeMillis() - 60 * 1000) {
         for (ResultCacheKey ds : resultCache.keySet()) {
            Iterator<CacheableResult> it = resultCache.get(ds).iterator();
            while (it.hasNext()) {
               CacheableResult sdsRes = it.next();
               if (!sdsRes.validate()) {
                  cleanupPending.add(sdsRes);
                  it.remove();
               }
            }
         }

         doCleanups();
         lastCacheMaintainance = System.currentTimeMillis();
      }
   }

   private void doCleanups() {
      Iterator<CacheableResult> it = cleanupPending.iterator();
      while (it.hasNext()) {
         if (it.next().cleanup(false)) {
            it.remove();
         }
      }
   }

   @Override
   public void addToResultCache(ResultCacheKey cacheKey, CacheableResult result) {
      maintainCache();

      if (!resultCache.containsKey(cacheKey)) {
         resultCache.put(cacheKey, new LinkedList<CacheableResult>());
      }

      CacheableResult existing = getCachedResult(cacheKey);
      if (null != existing) {
         resultCache.get(cacheKey).remove(existing);
      }

      if (result.validate())
         resultCache.get(cacheKey).add(result);
      else {
         /*
          * mark result as having to be cleaned up by responsible handler (e.g., script)
          */
         cleanupPending.add(result);
      }
   }

   @Override
   public void removeFromResultCache(ResultCacheKey cacheKey) {
      Iterator<ResultCacheKey> iterator = resultCache.keySet().iterator();
      while (iterator.hasNext()) {
         ResultCacheKey cachedResultKey = iterator.next();

         if (cachedResultKey.matches(cacheKey)) {
            List<CacheableResult> removed = resultCache.remove(cachedResultKey);
            if (null != removed && !removed.isEmpty()) {
               cleanupPending.addAll(removed);
            }
         }
      }
      doCleanups();
   }

   @Override
   public void flush() {
      for (ResultCacheKey ds : resultCache.keySet()) {
         Iterator<CacheableResult> it = resultCache.get(ds).iterator();
         while (it.hasNext()) {
            CacheableResult sdsRes = it.next();
            cleanupPending.add(sdsRes);
            it.remove();
         }
      }

      doCleanups();
      lastCacheMaintainance = System.currentTimeMillis();
   }

   @Override
   public CacheableResult getCachedResult(ResultCacheKey cacheKey) {
      maintainCache();

      List<CacheableResult> list = resultCache.get(cacheKey);
      if (null != list) {
         for (CacheableResult res : list) {
            if (res.validate() && res.consumes(cacheKey)) {
               return res;
            }
         }
      }
      return null;
   }
}
