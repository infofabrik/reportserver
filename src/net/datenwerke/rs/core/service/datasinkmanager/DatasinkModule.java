package net.datenwerke.rs.core.service.datasinkmanager;

import static java.util.stream.Collectors.toSet;

import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.datasinkmanager.annotations.ReportServerDatasinkDefinitions;
import net.datenwerke.rs.core.service.datasinkmanager.entities.DatasinkDefinition;
import net.datenwerke.rs.core.service.datasinkmanager.hookers.factory.DatasinkDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.datasinkmanager.hooks.DatasinkProviderHook;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;

public class DatasinkModule extends AbstractReportServerModule {

   public static final String CONFIG_FILE = "datasinks/datasinks.cf";
   public static final String PROPERTY_DEFAULT_DATASINK_ID = "defaultDatasinkId";
   public static final String PROPERTY_DEFAULT_DATASINK_NAME = "defaultDatasinkName";
   public static final String PROPERTY_DEFAULT_DATASINK_KEY = "defaultDatasinkKey";
   public static final String PROPERTY_DEFAULT_DISABLED = "[@disabled]";
   public static final String PROPERTY_DEFAULT_SCHEDULING_ENABLED = "[@supportsScheduling]";
   
   public static final String PROPERTY_SUBJECT = "subject";
   public static final String PROPERTY_TEXT = "text";
   public static final String PROPERTY_DISABLED = "disabled";
   public static final String PROPERTY_HTML = "html";
   public static final String PROPERTY_TRANSLATIONS_SUBJECT = "translations.subject";
   public static final String PROPERTY_TRANSLATIONS_TEXT = "translations.text";
   
   @Override
   protected void configure() {
      bind(DatasinkTreeService.class).to(DatasinkTreeServiceImpl.class).in(Scopes.SINGLETON);
      bind(DatasinkService.class).to(DatasinkServiceImpl.class);
      
      /* entity merge */
      install(new FactoryModuleBuilder().build(DatasinkDefaultMergeHookerFactory.class));

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
      return hookHandler.getHookers(DatasinkProviderHook.class)
            .stream()
            .flatMap(dsProvider -> dsProvider.getDatasinks().stream())
            .collect(toSet());
   }

}
