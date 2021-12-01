package net.datenwerke.rs.tabletemplate.client.engines.xdoc;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.xdoc.hookers.XdocTemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

import com.google.inject.Inject;

public class XdocTemplateUIStartup {

	@Inject
	public XdocTemplateUIStartup(
		HookHandlerService hookHandler,
		XdocTemplateClientProvider templateProvider
		){
		
		
		hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider);
	}
}
