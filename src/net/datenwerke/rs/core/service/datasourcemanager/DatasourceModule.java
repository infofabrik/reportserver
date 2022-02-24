package net.datenwerke.rs.core.service.datasourcemanager;

import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.DatasourceModuleProperties;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.DefaultDatasource;
import net.datenwerke.rs.core.service.datasourcemanager.annotations.ReportServerDatasourceDefinitions;
import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.datasourcemanager.hooks.DatasourceProviderHook;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasourceModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "datasources/datasources.cf";
   public static final String PROPERTY_DEFAULT_DATASOURCE_ID = "datasource.defaultDatasource";
   public static final String PROPERTY_DEFAULT_DATASOURCE_NAME = "datasource.defaultDatasourceName";

   @Override
   protected void configure() {
      bind(DatasourceService.class).to(DatasourceServiceImpl.class).in(Scopes.SINGLETON);
      bind(DatasourceHelperService.class).to(DatasourceHelperServiceImpl.class);

      /* startup */
      bind(DatasourceStartup.class).asEagerSingleton();
   }

   /**
    * Register DatasourceDefinitions
    * 
    */
   @Provides
   @ReportServerDatasourceDefinitions
   @Inject
   public Set<Class<? extends DatasourceDefinition>> provideDataSourceDefinitions(HookHandlerService hookHandler) {
      return hookHandler.getHookers(DatasourceProviderHook.class).stream()
            .flatMap(dsProvider -> dsProvider.getDatasources().stream()).collect(Collectors.toSet());
   }

   @Provides
   @Inject
   @DatasourceModuleProperties
   public Configuration provideConfig(ConfigService configService) {
      return configService.getConfig(CONFIG_FILE);
   }

   @Provides
   @Inject
   @DefaultDatasource
   public String provideDefaultDatasourceId(@DatasourceModuleProperties Configuration config,
         DatasourceService dsService) {
      String id = config.getString(DatasourceModule.PROPERTY_DEFAULT_DATASOURCE_ID);
      if (null == id) {
         String name = config.getString(DatasourceModule.PROPERTY_DEFAULT_DATASOURCE_NAME);
         if (null != name) {
            try {
               DatasourceDefinition ds = dsService.getDatasourceByName(name);
               if (null != ds)
                  id = String.valueOf(ds.getId());
            } catch (Exception e) {
            }
         }
      }
      return id;
   }
}
