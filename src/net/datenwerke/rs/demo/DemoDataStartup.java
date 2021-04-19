package net.datenwerke.rs.demo;

import com.google.inject.Inject;

import net.datenwerke.dbpool.hooks.JdbcUrlAdapterHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.utils.properties.ApplicationPropertiesService;

public class DemoDataStartup {

	@Inject
	public DemoDataStartup(
		HookHandlerService hookHandler,	
		DemoDataJdbcUrlAdapter urlAdapter,
		ApplicationPropertiesService propertiesService
		) {

		if("true".equals(propertiesService.getString("rs.install.demodata", "true")))
			hookHandler.attachHooker(JdbcUrlAdapterHook.class, urlAdapter);
		
	}
}
