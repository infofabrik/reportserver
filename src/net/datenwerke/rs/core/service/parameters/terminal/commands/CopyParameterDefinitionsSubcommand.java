package net.datenwerke.rs.core.service.parameters.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.core.service.parameters.ParameterHelperService;
import net.datenwerke.rs.core.service.parameters.entities.ParameterDefinition;
import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.terminal.service.terminal.CopyResultType;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.basecommands.CopySubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public class CopyParameterDefinitionsSubcommand implements CopySubCommandHook {
   
   public static final String BASE_COMMAND = "parameterDefinitions";
   
   private final Provider<ParameterHelperService> parameterHelperServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   
   @Inject
   public CopyParameterDefinitionsSubcommand(
         Provider<ParameterHelperService> parameterHelperServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.parameterHelperServiceProvider = parameterHelperServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }
   
   @CliHelpMessage(
         messageClass = TerminalMessages.class, 
         name = BASE_COMMAND, 
         description = "commandCopyParameterDefinitions_desc",
         nonOptArgs = {
               @NonOptArgument(
                  name = "origin", 
                  description = "commandCopyParameterDefinitions_from",
                  mandatory = true
               ),
               @NonOptArgument(
                  name = "target", 
                  description = "commandCopyParameterDefinitions_to",
                  mandatory = true
               ),
               @NonOptArgument(
                  name = "replaceExistingParameters", 
                  description = "commandCopyParameterDefinitions_replaceExistingParameters",
                  mandatory = true
               ),
         }
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (3 != arguments.size())
         throw new IllegalArgumentException("Exactly three arguments expected");
      
      final TerminalService terminalService = terminalServiceProvider.get();
      final Report from = terminalService.getSingleObjectOfTypeByQuery(Report.class, arguments.get(0), session,
            Read.class);
      final Report to = terminalService.getSingleObjectOfTypeByQuery(Report.class, arguments.get(1), session,
            Read.class, Write.class);
      try {
         final boolean replaceExistingParameters = Boolean.parseBoolean(arguments.get(2));
         Map<CopyResultType, List<ParameterDefinition>> result = parameterHelperServiceProvider.get().copyParameterDefinitions(from,
               to, replaceExistingParameters);
         RSTableModel copied = terminalService.convertSimpleListToTableModel("Copied parameter definitions",
               result.get(CopyResultType.COPYIED)
                  .stream()
                  .map(ParameterDefinition::toString)
                  .collect(toList()));
         RSTableModel existing = terminalService.convertSimpleListToTableModel(
               (replaceExistingParameters ? "Replaced" : "Ignored") + " parameter definitions",
               result.get(CopyResultType.EXISTING)
                  .stream()
                  .map(ParameterDefinition::toString)
                  .collect(toList()));
         
         CommandResult commandResult = new CommandResult();
         commandResult.addResultTable(copied);
         commandResult.addResultTable(existing);
         
         return commandResult;
      } catch (Exception e) {
         throw new TerminalException(ExceptionUtils.getRootCauseMessage(e));
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
