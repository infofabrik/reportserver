package net.datenwerke.rs.tabletemplate.client.engines.xsl;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.xsl.hookers.XslTemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

public class XslTemplateUIStartup {

   @Inject
   public XslTemplateUIStartup(HookHandlerService hookHandler, XslTemplateClientProvider templateProvider) {

      hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider);
   }
}