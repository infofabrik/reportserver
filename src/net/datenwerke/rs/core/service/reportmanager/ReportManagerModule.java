package net.datenwerke.rs.core.service.reportmanager;

import static java.util.stream.Collectors.toSet;

import java.util.Collection;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerParameter;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerReportTypes;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerReportVariantTypes;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngines;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.hookers.factory.ReportDefaultMergeHookerFactory;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ParameterSetReplacementProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSet;
import net.datenwerke.rs.core.service.reportmanager.parameters.ParameterSetReplacementProvider;

/**
 * 
 *
 */
public class ReportManagerModule extends AbstractReportServerModule {

   @Override
   protected void configure() {
      /* bind services */
      bind(ReportService.class).to(ReportServiceImpl.class);
      bind(ReportDtoService.class).to(ReportDtoServiceImpl.class);
      bind(ReportExecutorService.class).to(ReportExecutorServiceImpl.class);
      bind(ReportParameterService.class).to(ReportParameterServiceImpl.class);
      
      /* entity merge */
      install(new FactoryModuleBuilder().build(ReportDefaultMergeHookerFactory.class));

      /* static injection */
      requestStaticInjection(ParameterDefinition.class, ParameterInstance.class, ParameterSet.class, Report.class);

      /* startup */
      bind(ReportManagerStartup.class).asEagerSingleton();
   }

   @Provides
   @ReportEngines
   @Inject
   protected Set<Class<? extends ReportEngine>> provideReportEngines(HookHandlerService hookHandler) {
      return hookHandler.getHookers(ReportEngineProviderHook.class)
         .stream()
         .map(ReportEngineProviderHook::getReportEngines)
         .flatMap(Collection::stream)
         .collect(toSet());
   }

   /**
    * Register Parameters.
    *
    */
   @Inject
   @Provides
   @ReportServerParameter
   public Set<Class<? extends ParameterDefinition>> provideParameters(HookHandlerService hookHandler) {
      return hookHandler.getHookers(ParameterProviderHook.class)
         .stream()
         .map(ParameterProviderHook::getParameterDefinitions)
         .flatMap(Collection::stream)
         .collect(toSet());
   }

   /**
    * Register Report types
    * 
    */
   @Provides
   @ReportServerReportTypes
   @Inject
   public Set<Class<? extends Report>> provideReportServerReportTypes(HookHandlerService hookHandler) {
      return hookHandler.getHookers(ReportTypeProviderHook.class)
         .stream()
         .map(ReportTypeProviderHook::getReportTypes)
         .flatMap(Collection::stream)
         .collect(toSet());
   }
   
   /**
    * Register Report Variant types
    * 
    */
   @Provides
   @ReportServerReportVariantTypes
   @Inject
   public Set<Class<? extends Report>> provideReportServerReportVariantTypes(HookHandlerService hookHandler) {
      return hookHandler.getHookers(ReportTypeProviderHook.class)
         .stream()
         .map(ReportTypeProviderHook::getReportVariantTypes)
         .flatMap(Collection::stream)
         .collect(toSet());
   }

   @Provides
   @Inject
   public Collection<ParameterSetReplacementProvider> providerReplacementProviders(HookHandlerService hookHandler) {
      return hookHandler.getHookers(ParameterSetReplacementProviderHook.class)
         .stream()
         .map(ParameterSetReplacementProviderHook::getProviders)
         .flatMap(Collection::stream)
         .collect(toSet());
   }
}
