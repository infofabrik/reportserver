package net.datenwerke.rs.base.service.reportengines;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.base.service.reportengines.hookers.AdjustBaseReportForExecutionHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.BaseReportEngineProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.BaseReportTypeProviderHooker;
import net.datenwerke.rs.base.service.reportengines.hookers.ConfigureBaseReportViaRequestHooker;
import net.datenwerke.rs.core.service.reportmanager.hooks.AdjustReportForExecutionHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHistoryLocationHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ConfigureReportViaHttpRequestHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportEngineProviderHook;
import net.datenwerke.rs.core.service.reportmanager.hooks.ReportTypeProviderHook;

public class BaseReportEnginesStartup {

   @Inject
   public BaseReportEnginesStartup(HookHandlerService hookHandler,

         Provider<AdjustBaseReportForExecutionHooker> adjustReportForExecutionProvider,
         Provider<ConfigureBaseReportViaRequestHooker> adjustReportViaRequestProvider,
         Provider<BaseReportEngineProviderHooker> reportEngineProvider,
         Provider<BaseReportTypeProviderHooker> reportTypeProvider) {

      hookHandler.attachHooker(AdjustReportForExecutionHook.class, adjustReportForExecutionProvider);
      hookHandler.attachHooker(ConfigureReportViaHttpRequestHook.class, adjustReportViaRequestProvider);
      hookHandler.attachHooker(ConfigureReportViaHistoryLocationHook.class, adjustReportViaRequestProvider);
      hookHandler.attachHooker(ReportEngineProviderHook.class, reportEngineProvider);
      hookHandler.attachHooker(ReportTypeProviderHook.class, reportTypeProvider);
   }
}
