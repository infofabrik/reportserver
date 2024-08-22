package net.datenwerke.rs.adminutils.service.logs.terminal.commands;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.stream.Stream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.adminutils.service.locale.AdminUtilsMessages;
import net.datenwerke.rs.adminutils.service.logs.LogFilesService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class ViewLogFileCommand implements TerminalCommandHook {

   private static final String BASE_COMMAND = "viewlogfile";

   private final Provider<LogFilesService> logFilesServiceProvider;

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   @Inject
   public ViewLogFileCommand(
         Provider<LogFilesService> logFilesServiceProvider
         ) {
      this.logFilesServiceProvider = logFilesServiceProvider;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = AdminUtilsMessages.class, name = BASE_COMMAND, description = "commandViewLogFile_description", nonOptArgs = {
         @NonOptArgument(name = "logFilename", description = "commandViewLogFile_logFile", mandatory = true) })
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      Path logPath = Paths.get(logFilesServiceProvider.get().getLogDirectory());
      if (!Files.exists(logPath))
         throw new IllegalArgumentException("no valid log file directory configured");

      String filename = parser.getArgumentNr(1);

      if (null == filename)
         throw new IllegalArgumentException("Expected log filename");

      Path file = Paths.get(logPath.toAbsolutePath() + "/" + filename);

      try {
         logFilesServiceProvider.get().checkLogFiles(Arrays.asList(file));
      } catch (IOException e) {
         throw new TerminalException("Could not read data: ", e);
      }

      CommandResult result = new CommandResult();
      ViewLogFileCommandResultExtension ext = new ViewLogFileCommandResultExtension();
      ext.setFilename(file.getFileName().toString());
      try {
         ext.setData(logFilesServiceProvider.get().readLastLines(file.getFileName().toString()));
      } catch (IOException e) {
         throw new TerminalException("Could not read data: ", e);
      }
      result.addExtension(ext);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
      if (consumes(autocompleteHelper.getParser(), session)) {
         Path logPath = Paths.get(logFilesServiceProvider.get().getLogDirectory());
         if (!Files.exists(logPath))
            return;

         try (Stream<Path> stream = Files.list(logPath)) {
            stream.filter(f -> !Files.isDirectory(f))
                  .forEach(f -> autocompleteHelper.addAutocompleteNamesForToken(2, f.getFileName().toString()));
         } catch (IOException e) {
            logger.warn(e.getMessage(), e);
         }
      }
   }

}
