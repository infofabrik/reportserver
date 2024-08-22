package net.datenwerke.rs.saiku.service.saiku;

import java.io.Serializable;
import java.util.concurrent.TimeUnit;

import org.saiku.service.util.QueryContext;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.inject.Inject;
import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class SaikuContextMap implements Serializable {

   /**
    * 
    */
   private static final long serialVersionUID = 1L;

   private Cache<String, QueryContext> cache;

   @Inject
   public SaikuContextMap(SaikuService saikuService) {
      cache = CacheBuilder.newBuilder().maximumSize(saikuService.getContextMaxSize())
            .expireAfterWrite(saikuService.getContextExpiresAfter(), TimeUnit.MINUTES).build();
   }

   public boolean containsKey(String name) {
      return null != cache.getIfPresent(name);
   }

   public void put(String name, QueryContext qt) {
      cache.put(name, qt);
   }

   public QueryContext get(String name) {
      return cache.getIfPresent(name);
   }
}
