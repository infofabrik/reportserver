package net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.output.generator;

import javax.inject.Inject;
import javax.inject.Singleton;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.service.reportmanager.output.AbstractReportOutputGeneratorManager;
import net.datenwerke.rs.jxlsreport.service.jxlsreport.reportengine.hooks.JxlsOutputGeneratorProviderHook;

@Singleton
public class JxlsOutputGeneratorManager extends AbstractReportOutputGeneratorManager<JxlsOutputGenerator> {

   @Inject
   public JxlsOutputGeneratorManager(HookHandlerService hookHandler) {
      super(hookHandler, JxlsOutputGeneratorProviderHook.class);
   }

}
