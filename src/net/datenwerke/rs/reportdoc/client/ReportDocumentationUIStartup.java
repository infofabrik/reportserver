package net.datenwerke.rs.reportdoc.client;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.core.client.reportexecutor.hooks.ReportExecutorViewToolbarHook;
import net.datenwerke.rs.core.client.urlview.hooks.UrlViewSpecialViewHandler;
import net.datenwerke.rs.reportdoc.client.hooker.ReportDocumentationObjectInfo;
import net.datenwerke.rs.reportdoc.client.hooker.ReportViewDocumentationHooker;
import net.datenwerke.rs.reportdoc.client.terminal.commandproc.DeployAnalyzeTerminalCommandProcessor;
import net.datenwerke.rs.reportdoc.client.terminal.commandproc.VariantTestTerminalCommandProcessor;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

/**
 * 
 *
 */
public class ReportDocumentationUIStartup {

   @Inject
   public ReportDocumentationUIStartup(HookHandlerService hookHandler,
         ReportViewDocumentationHooker reportViewDocumentationHooker,

         Provider<ReportDocumentationObjectInfo> repordDocumentationObjectInfo,
         Provider<DeployAnalyzeTerminalCommandProcessor> commandProcessor,
         Provider<VariantTestTerminalCommandProcessor> variantTestProcessor

   ) {

      hookHandler.attachHooker(ReportExecutorViewToolbarHook.class, reportViewDocumentationHooker,
            HookHandlerService.PRIORITY_HIGH);

      /* terminal commands */
      hookHandler.attachHooker(CommandResultProcessorHook.class, commandProcessor);
      hookHandler.attachHooker(CommandResultProcessorHook.class, variantTestProcessor);

      /* attach object info hooks */
      hookHandler.attachHooker(UrlViewSpecialViewHandler.class, repordDocumentationObjectInfo);
   }
}
