package net.datenwerke.rs.core.service.reportmanager;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import com.google.inject.Inject;
import com.google.inject.Provides;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.guice.AbstractReportServerModule;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.parameters.entities.ParameterInstance;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerParameter;
import net.datenwerke.rs.core.service.reportmanager.annotations.ReportServerReportTypes;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngine;
import net.datenwerke.rs.core.service.reportmanager.engine.ReportEngines;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
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
		
		/* static injection */
		requestStaticInjection(
			ParameterDefinition.class,
			ParameterInstance.class,
			ParameterSet.class,
			Report.class
		);
		
		/* startup */
		bind(ReportManagerStartup.class).asEagerSingleton();
	}
	
	@Provides @ReportEngines @Inject
	protected Set<Class<? extends ReportEngine>> provideReportEngines(HookHandlerService hookHandler){
		Set<Class<? extends ReportEngine>> engines = new HashSet<Class<? extends ReportEngine>>();
		
		for(ReportEngineProviderHook engineProvider : hookHandler.getHookers(ReportEngineProviderHook.class))
			engines.addAll(engineProvider.getReportEngines());

		return engines;
	}
	

	/**
	 * Register Parameters.
	 *
	 */
	@Inject @Provides @ReportServerParameter
	public Set<Class<? extends ParameterDefinition>> provideParameters(HookHandlerService hookHandler){
		Set<Class<? extends ParameterDefinition>> definitions = new HashSet<Class<? extends ParameterDefinition>>();
		
		for(ParameterProviderHook parameterProvider : hookHandler.getHookers(ParameterProviderHook.class))
			definitions.addAll(parameterProvider.getParameterDefinitions());
		
		return definitions;
	}
	
	/**
	 * Register Report types
	 * 
	 */
	@Provides @ReportServerReportTypes @Inject
	public Set<Class<? extends Report>> provideReportServerReportTypes(HookHandlerService hookHandler){
		Set<Class<? extends Report>> types = new HashSet<Class<? extends Report>>();
		
		for(ReportTypeProviderHook provider : hookHandler.getHookers(ReportTypeProviderHook.class))
			types.addAll(provider.getReportTypes());
		
		return types;
	}

	@Provides @Inject
	public Collection<ParameterSetReplacementProvider> providerReplacementProviders(
			HookHandlerService hookHandler
		){
		Set<ParameterSetReplacementProvider> providers = new HashSet<ParameterSetReplacementProvider>();
		
		for(ParameterSetReplacementProviderHook provider : hookHandler.getHookers(ParameterSetReplacementProviderHook.class))
			providers.addAll(provider.getProviders());
		
		return providers;
	}
}
