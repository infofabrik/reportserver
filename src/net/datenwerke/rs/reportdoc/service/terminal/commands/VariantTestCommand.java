package net.datenwerke.rs.reportdoc.service.terminal.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.datasourcemanager.entities.DatasourceDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.dsbundle.service.dsbundle.SimpleDatasourceBundleService;
import net.datenwerke.rs.reportdoc.service.locale.ReportDocumentationMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.security.service.security.rights.Read;

public class VariantTestCommand implements TerminalCommandHook {
   public static final String BASE_COMMAND = "variantTest";
   private final SimpleDatasourceBundleService bundleService;

   @Inject
   public VariantTestCommand(SimpleDatasourceBundleService datasourceBundleService) {
      this.bundleService = datasourceBundleService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = ReportDocumentationMessages.class, 
         name = BASE_COMMAND, 
         description = "commandVariantTest_description", 
         nonOptArgs = {
            @NonOptArgument(
                  name = "datasource", 
                  description = "commandVariantTest_description_arg_datasource", 
                  varArgs = true
                  ),
            @NonOptArgument(
                  name = "report", 
                  description = "commandVariantTest_description_arg_report", 
                  mandatory = true
                  ) 
            }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      CommandResult result = new CommandResult(ReportDocumentationMessages.INSTANCE.creatingPdf());
      List<String> arguments = parser.getNonOptionArguments();
      String reportQuery = null;
      List<String> datasourceQueries = new ArrayList<>();
      if (arguments.isEmpty())
         throw new IllegalArgumentException("Please enter arguments");
      if (1 == arguments.size())
         reportQuery = arguments.get(0);
      else if (arguments.size() > 1) {
         reportQuery = arguments.get(arguments.size() - 1);
         for (String arg : arguments)
            if (arguments.get(arguments.size() - 1) != arg)
               datasourceQueries.add(arg);
      }

      Collection<Object> resolvedReports = session.getObjectResolver().getObjects(reportQuery, Read.class,
            Execute.class);

      if (null == resolvedReports || 1 != resolvedReports.size())
         throw new IllegalArgumentException("Report resolver query must resolve to exactly one report entity");

      Object repObj = resolvedReports.iterator().next();
      if (!(repObj instanceof Report))
         throw new IllegalArgumentException("Report resolver query must resolve to exactly one report entity");

      Report report = (Report) repObj;

      Collection<Object> resolvedDatasources = new ArrayList<>();
      List<DatasourceDefinition> datasources = new ArrayList<>();
      for (String datasourceQuery : datasourceQueries) {
         resolvedDatasources = session.getObjectResolver().getObjects(datasourceQuery, Read.class);

         if (null == resolvedDatasources || resolvedDatasources.isEmpty())
            throw new IllegalArgumentException("No valid datasources found: " + datasourceQuery);

         Set<DatasourceDefinition> allDataSources = bundleService.getAllDatasources(report,
               bundleService.getAvailableBundleKeys());

         for (Object resolvedDatasource : resolvedDatasources) {
            if (!(resolvedDatasource instanceof DatasourceDefinition))
               throw new IllegalArgumentException("Not a DatasourceDefinition object");

            DatasourceDefinition datasource = (DatasourceDefinition) resolvedDatasource;
            datasources.add(datasource);
         }

         if (!allDataSources.containsAll(datasources))
            throw new IllegalArgumentException("The given datasources are not valid for this report");
      }

      VariantTestCommandResultExtension ext = new VariantTestCommandResultExtension();
      ext.setReport(report);
      ext.setDatasources(datasources);
      result.addExtension(ext);
      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
