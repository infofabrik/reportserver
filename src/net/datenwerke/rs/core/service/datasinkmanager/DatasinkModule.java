package net.datenwerke.rs.core.service.datasinkmanager;

import static java.util.stream.Collectors.toList;

import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.annotations.ReportServerDatasinkDefinitions;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasinkModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "datasinks/datasinks.cf";
   
   @Override
   protected void configure() {
      bind(DatasinkService.class).to(DatasinkServiceImpl.class).in(Scopes.SINGLETON);

      /* startup */
      bind(DatasinkStartup.class).asEagerSingleton();
   }

   /**
    * Register DatasinkDefinitions
    * 
    */
   @Provides
   @ReportServerDatasinkDefinitions
   @Inject
   public Set<Class<? extends DatasinkDefinition>> provideDatasinkDefinitions(HookHandlerService hookHandler) {
      Set<Class<? extends DatasinkDefinition>> definitions = new HashSet<Class<? extends DatasinkDefinition>>();

      definitions.addAll(hookHandler.getHookers(DatasinkProviderHook.class)
            .stream()
            .flatMap(dsProvider -> dsProvider.getDatasinks().stream())
            .collect(toList()));

      return definitions;
   }

}
