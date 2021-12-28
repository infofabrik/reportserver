package net.datenwerke.rs.tabletemplate.client.engines.xdoc;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.xdoc.hookers.XdocTemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

public class XdocTemplateUIStartup {

   @Inject
   public XdocTemplateUIStartup(HookHandlerService hookHandler, XdocTemplateClientProvider templateProvider) {

      hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider);
   }
}
