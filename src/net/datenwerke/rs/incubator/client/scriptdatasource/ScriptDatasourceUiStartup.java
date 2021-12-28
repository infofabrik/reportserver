package net.datenwerke.rs.incubator.client.scriptdatasource;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.datasourcemanager.hooks.DatasourceDefinitionConfigProviderHook;
import net.datenwerke.rs.incubator.client.scriptdatasource.hooker.ScriptDatasourceConfigProviderHooker;

public class ScriptDatasourceUiStartup {

	@Inject
	public ScriptDatasourceUiStartup(
		HookHandlerService hookHandler,
		Provider<ScriptDatasourceConfigProviderHooker> configProvider
		){
		
		hookHandler.attachHooker(DatasourceDefinitionConfigProviderHook.class, configProvider, 50);
	}
}
