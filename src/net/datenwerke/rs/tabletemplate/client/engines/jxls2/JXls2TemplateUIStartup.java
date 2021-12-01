package net.datenwerke.rs.tabletemplate.client.engines.jxls2;

import com.google.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.tabletemplate.client.engines.jxls2.hookers.JXls2TemplateClientProvider;
import net.datenwerke.rs.tabletemplate.client.tabletemplate.hooks.TableTemplateClientProviderHook;

public class JXls2TemplateUIStartup {

    @Inject
    public JXls2TemplateUIStartup(HookHandlerService hookHandler, JXls2TemplateClientProvider templateProvider) {

        hookHandler.attachHooker(TableTemplateClientProviderHook.class, templateProvider, HookHandlerService.PRIORITY_HIGH);
    }
}
