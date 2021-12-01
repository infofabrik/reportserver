package net.datenwerke.rs.core.client.parameters;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.inject.Inject;
import com.google.inject.Provides;
import com.google.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.hooks.ParameterProviderHook;
import net.datenwerke.rs.core.client.parameters.propertywidgets.ParameterView;

/**
 * 
 *
 */
public class ParameterUIModule extends AbstractGinModule {


	@Override
	protected void configure() {
		/* bind service */
		bind(ParameterUIService.class).to(ParameterUIServiceImpl.class).in(Singleton.class);
		
		/* bind startup */
		bind(ParameterUIStartup.class).asEagerSingleton();
		
		/* request static injection */
		requestStaticInjection(ParameterView.class);
	}
	
	@SuppressWarnings("unchecked")
	@Inject
	@Provides
	public List<ParameterConfigurator> provideParameterConfigurators(
		HookHandlerService hookHandler
		){
		/* instantiate list */
		List<ParameterConfigurator> configurator = new ArrayList<ParameterConfigurator>();
		
		List<ParameterProviderHook> providers = hookHandler.getHookers(ParameterProviderHook.class);
		
		/* add parameters to list */
		for(ParameterProviderHook provider : providers)
			configurator.addAll(provider.parameterProviderHook_getConfigurators());
		
		/* return list */
		return configurator;
	}

}
