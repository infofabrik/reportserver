package net.datenwerke.rs.core.service.internaldb;

import com.google.inject.AbstractModule;
import com.google.inject.Singleton;

import net.datenwerke.rs.resultcache.ResultCacheService;
import net.datenwerke.rs.resultcache.ResultCacheServiceImpl;

public class InternalDbModule extends AbstractModule {

   static final String CONFIG_FILE = "datasources/internaldb.cf";

   @Override
   protected void configure() {
      bind(TempTableService.class).to(TempTableServiceImpl.class).in(Singleton.class);
      bind(ResultCacheService.class).to(ResultCacheServiceImpl.class).in(Singleton.class);
      requestStaticInjection(TempTableHelper.class);
      bind(InternalDbStartup.class).asEagerSingleton();
   }

}
