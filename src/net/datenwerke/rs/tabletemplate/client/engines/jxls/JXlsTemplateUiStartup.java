package net.datenwerke.rs.tabletemplate.client.engines.jxls;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.jxls.hookers.JXlsTemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

public class JXlsTemplateUiStartup {

   @Inject
   public JXlsTemplateUiStartup(HookHandlerService hookHandler, JXlsTemplateClientProvider templateProvider) {

      hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider,
            HookHandlerService.PRIORITY_HIGH);
   }
}