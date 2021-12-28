package net.datenwerke.rs.scriptreport.client.scriptreport;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gxtdto.client.waitonevent.SynchronousCallbackOnEventTrigger;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventTicket;
import net.datenwerke.gxtdto.client.waitonevent.WaitOnEventUIService;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.parameters.config.ParameterConfigurator;
import net.datenwerke.rs.core.client.parameters.hooks.ParameterProviderHook;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportViewHook;
import net.datenwerke.rs.core.client.reportexporter.hooks.ReportExporterProviderHook;
import net.datenwerke.rs.core.client.reportmanager.hooks.ReportTypeConfigHook;
import net.datenwerke.rs.dashboard.client.dashboard.hooks.ReportDadgetExportHook;
import net.datenwerke.rs.dashboard.client.dashboard.security.DashboardViewGenericTargetIdentifier;
import net.datenwerke.rs.scriptreport.client.scriptreport.execute.ScriptReportExporter;
import net.datenwerke.rs.scriptreport.client.scriptreport.hookers.ScriptReportConfigHooker;
import net.datenwerke.rs.scriptreport.client.scriptreport.hookers.ScriptReportDadgetExporter;
import net.datenwerke.rs.scriptreport.client.scriptreport.parameters.ScriptParameterConfigurator;
import net.datenwerke.rs.scriptreport.client.scriptreport.ui.ScriptReportPreviewViewFactory;
import net.datenwerke.security.client.security.SecurityUIService;
import net.datenwerke.security.client.security.dto.ReadDto;

public class ScriptReportUiStartup implements ParameterProviderHook {

   @SuppressWarnings("unchecked")
   private final List<Provider<? extends ParameterConfigurator>> parameters;

   @Inject
   public ScriptReportUiStartup(final HookHandlerService hookHandler, final WaitOnEventUIService waitOnEventService,
         final SecurityUIService securityService,

         ScriptReportConfigHooker reportConfigHooker, ScriptReportPreviewViewFactory reportPreviewViewFactory,

         Provider<ScriptParameterConfigurator> scriptParameter,

         Provider<ScriptReportExporter> exporterProvider,

         final Provider<ScriptReportDadgetExporter> reportDadgetExporterProvider) {

      /* store parameters */
      parameters = new ArrayList<Provider<? extends ParameterConfigurator>>();
      parameters.add(scriptParameter);
      hookHandler.attachHooker(ParameterProviderHook.class, this);

      hookHandler.attachHooker(ReportTypeConfigHook.class, reportConfigHooker, 90);
      hookHandler.attachHooker(ReportViewHook.class, new ReportViewHook(reportPreviewViewFactory),
            HookHandlerService.PRIORITY_LOW);

      hookHandler.attachHooker(ReportExporterProviderHook.class, exporterProvider);

      /* request callback after login and check for rights */
      waitOnEventService.callbackOnEvent(SecurityUIService.REPORTSERVER_EVENT_GENERIC_RIGHTS_LOADED,
            new SynchronousCallbackOnEventTrigger() {

               public void execute(final WaitOnEventTicket ticket) {
                  if (securityService.hasRight(DashboardViewGenericTargetIdentifier.class, ReadDto.class)) {
                     hookHandler.attachHooker(ReportDadgetExportHook.class, reportDadgetExporterProvider);
                  }

                  waitOnEventService.signalProcessingDone(ticket);
               }
            });
   }

   @Override
   public Collection<ParameterConfigurator> parameterProviderHook_getConfigurators() {
      List<ParameterConfigurator> configurations = new ArrayList<ParameterConfigurator>();

      for (Provider<? extends ParameterConfigurator> provider : parameters)
         configurations.add(provider.get());

      return configurations;
   }
}
