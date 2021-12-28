package net.datenwerke.rs.tabletemplate.client.engines.velocity;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.velocity.hookers.VelocityTemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

public class VelocityTemplateUIStartup {

	@Inject
	public VelocityTemplateUIStartup(
		HookHandlerService hookHandler,
		VelocityTemplateClientProvider templateProvider
		){
		
		
		hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider);
	}
}