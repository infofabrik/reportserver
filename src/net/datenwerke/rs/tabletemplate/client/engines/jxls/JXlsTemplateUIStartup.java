package net.datenwerke.rs.tabletemplate.client.engines.jxls;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.jxls.hookers.JXlsTemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

import com.google.inject.Inject;

public class JXlsTemplateUIStartup {

	@Inject
	public JXlsTemplateUIStartup(
		HookHandlerService hookHandler,
		JXlsTemplateClientProvider templateProvider
		){
		
		
		hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider, HookHandlerService.PRIORITY_HIGH + 10);
	}
}
