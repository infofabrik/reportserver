package net.datenwerke.rs.base.ext.service.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.annotations.CommitFlushMode;
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterService;
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl;
import net.datenwerke.rs.base.ext.service.locale.RsBaseExtMessages;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.string.Emoji;
import net.datenwerke.security.service.security.rights.Read;

public class RpullCopySubcommand implements RpullSubCommandHook {
   
   public static final String BASE_COMMAND = "copy";
   
   private final Provider<RemoteEntityImporterService> remoteEntityImporterServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   
   @Inject
   public RpullCopySubcommand(
         Provider<RemoteEntityImporterService> remoteEntityImporterServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.remoteEntityImporterServiceProvider = remoteEntityImporterServiceProvider;
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
         messageClass = RsBaseExtMessages.class, 
         name = BASE_COMMAND, 
         description = "commandRpullCopy_desc",
         args = {
               @Argument(
                     flag = "c", 
                     hasValue = false, 
                     valueName = "check", 
                     description = "commandRpullCopy_flagC", 
                     mandatory = false
               ),
               @Argument(
                     flag = "v", 
                     hasValue = false, 
                     valueName = "variants", 
                     description = "commandRpullCopy_flagV", 
                     mandatory = false
               )
         },
         nonOptArgs = {
               @NonOptArgument(
                     name = "remoteServer", 
                     description = "commandRpullCopy_remoteServer",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "remoteEntityPath", 
                     description = "commandRpullCopy_remoteEntityPath",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "localTarget", 
                     description = "commandRpullCopy_localTarget",
                     mandatory = true
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (3 != arguments.size())
         throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Exactly three arguments expected");
      
      final String argStr = "cv";
      final boolean check = parser.hasOption("c", argStr);
      final boolean includeVariants = parser.hasOption("v", argStr);
      final RemoteRsRestServer remoteRsServer = terminalServiceProvider.get()
            .getSingleObjectOfTypeByQuery(RemoteRsRestServer.class, arguments.get(0), session, Read.class);

      if (null == remoteRsServer.getUrl())
         throw new TerminalException(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Remote RS REST server has no REST URL");
      
      if (! remoteRsServer.getUrl().trim().startsWith("http://") && ! remoteRsServer.getUrl().trim().startsWith("https://"))
         throw new TerminalException(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "URL contains no protocol: '" + remoteRsServer.getUrl() + "'");
      
      final Map<String, Object> errors = new LinkedHashMap<>();
      if (check) {
         try {
            return checkEntities(remoteRsServer, arguments.get(1), arguments.get(2), includeVariants, errors);
         } catch (Exception e) {
            RemoteEntityImporterServiceImpl.handleError(true,
                  ExceptionUtils.getRootCauseMessage(e), errors,
                  ExceptionUtils.getRootCause(e).getClass());
            return terminalServiceProvider.get().convertSimpleMapToCommandResult(Arrays.asList("Test results"),
                  Emoji.BEER_MUG.getEmoji(" ") + "No errors found", errors);
         }
      } else {
         try {
            return importEntities(remoteRsServer, arguments.get(1), arguments.get(2), includeVariants);
         } catch (Exception e) {
            throw new TerminalException(Emoji.exceptionEmoji().getEmoji(" ") + ExceptionUtils.getRootCauseMessage(e), e);
         }
      }
   }
   
   private CommandResult checkEntities(RemoteRsRestServer remoteRsServer, String remoteEntityPath,
         String localTarget, boolean includeVariants, Map<String, Object> errors) {
      Map<String, Object> results = remoteEntityImporterServiceProvider.get().checkImportRemoteEntities(remoteRsServer,
            remoteEntityPath, localTarget, includeVariants, false, errors);
      return terminalServiceProvider.get().convertSimpleMapToCommandResult(Arrays.asList("Test results"), Emoji.BEER_MUG.getEmoji(" ") + "No errors found", results);
   }
   
   @CommitFlushMode
   @Transactional(rollbackOn = { Exception.class })
   protected CommandResult importEntities(RemoteRsRestServer remoteRsServer, String remoteEntityPath,
         String localTarget, boolean includeVariants) {
      final TerminalService terminalService = terminalServiceProvider.get();
      Instant start = Instant.now();
      ImportResult result = remoteEntityImporterServiceProvider.get().importRemoteEntities(remoteRsServer,
            remoteEntityPath, localTarget, includeVariants, false);
      Instant end = Instant.now();
      CommandResult commandResult = new CommandResult(Emoji.FLYING_SAUCER.getEmoji());
      
      Map<String,Object> resultsMap = new LinkedHashMap<>();
      resultsMap.put(RemoteEntityImporterServiceImpl.STATUS, RemoteEntityImporterServiceImpl.STATUS_OK);
      resultsMap.put("Duration", Duration.between(start, end).toString());
      resultsMap.put("Imported objects", result.getImportedObjects().size());
      resultsMap.put("Import date", DateUtils.format(result.getDate()));
      resultsMap.put("Name", result.getName());
      
      final RSTableModel resultsTable = terminalService.convertSimpleMapToTableModel(resultsMap);
      commandResult.addResultTable(resultsTable);
      
      final RSTableModel detailsTable = terminalService.convertSimpleListToTableModel(
            "Imported objects (" + result.getImportedObjects().size() + ")",
            result.getImportedObjects().values()
               .stream()
               .map(Object::toString)
               .collect(toList()));
      commandResult.addResultTable(detailsTable);
      
      return commandResult;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
