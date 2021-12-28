package net.datenwerke.rs.resultcache;

public abstract class ResultCacheKey {

   public boolean matches(ResultCacheKey cacheKey) {
      return true;
   }

}
