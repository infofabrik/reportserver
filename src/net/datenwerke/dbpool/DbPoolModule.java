package net.datenwerke.dbpool;

import org.apache.commons.configuration2.HierarchicalConfiguration;

import com.google.inject.AbstractModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.dbpool.annotations.ConnectionPoolConfigFile;
import net.datenwerke.dbpool.annotations.PoolC3P0;
import net.datenwerke.dbpool.annotations.UseConnectionPool;
import net.datenwerke.dbpool.config.ConnectionPoolConfig;
import net.datenwerke.dbpool.config.ConnectionPoolConfigFactory;
import net.datenwerke.dbpool.config.ConnectionPoolConfigImpl;
import net.datenwerke.dbpool.hooks.C3p0ConnectionHook;
import net.datenwerke.rs.utils.config.ConfigFileNotFoundException;
import net.datenwerke.rs.utils.config.ConfigService;

public class DbPoolModule extends AbstractModule {

   public static final String CONFIG_FILE = "datasources/pool.cf";

   @Override
   protected void configure() {
      bind(DbPoolStartup.class).asEagerSingleton();

      bind(DbPoolService.class).annotatedWith(PoolC3P0.class).to(DbC3p0PoolServiceImpl.class).in(Singleton.class);

      bind(DbPoolService.class).to(MetaPoolService.class).in(Singleton.class);

      install(new FactoryModuleBuilder().implement(ConnectionPoolConfig.class, ConnectionPoolConfigImpl.class)
            .build(ConnectionPoolConfigFactory.class));

      requestStaticInjection(C3p0ConnectionHook.class);
   }

   @Inject
   @Provides
   @ConnectionPoolConfigFile
   public HierarchicalConfiguration provideConfig(ConfigService service) {
      try {
         return (HierarchicalConfiguration) service.getConfig(CONFIG_FILE);
      } catch (ConfigFileNotFoundException e) {
         return null;
      }
   }

   @Provides
   @Inject
   @UseConnectionPool
   public Boolean provideUseConnectionPool(@ConnectionPoolConfigFile HierarchicalConfiguration config) {
      try {
         if (null != config) {
            String disablePool = config.getString("pool[@disable]", "false");
            return !"true".equals(disablePool);
         }
      } catch (Exception ignore) {
      }
      return true;
   }

}
