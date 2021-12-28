package net.datenwerke.rs.reportdoc.client.terminal.commandproc;

import com.google.inject.Inject;

import net.datenwerke.rs.core.client.reportmanager.dto.reports.ReportDto;
import net.datenwerke.rs.reportdoc.client.ReportDocumentationUiService;
import net.datenwerke.rs.reportdoc.client.dto.DeployAnalyzeCommandResultExtensionDto;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;
import net.datenwerke.rs.terminal.client.terminal.hooks.CommandResultProcessorHook;

public class DeployAnalyzeTerminalCommandProcessor implements CommandResultProcessorHook {

   private final ReportDocumentationUiService reportDocService;

   @Inject
   public DeployAnalyzeTerminalCommandProcessor(ReportDocumentationUiService reportDocService) {
      this.reportDocService = reportDocService;
   }

   @Override
   public void process(CommandResultDto result) {
      if (result.getExtensions().size() == 1
            && result.getExtensions().get(0) instanceof DeployAnalyzeCommandResultExtensionDto) {
         final DeployAnalyzeCommandResultExtensionDto analyzeCmd = (DeployAnalyzeCommandResultExtensionDto) result
               .getExtensions().get(0);
         final ReportDto leftReport = analyzeCmd.getLeftReport();
         final ReportDto rightReport = analyzeCmd.getRightReport();
         final boolean ignoreCase = analyzeCmd.isIgnoreCase();

         reportDocService.openDeployAnalyzeForopen(leftReport, rightReport, ignoreCase);

      }
   }

}
