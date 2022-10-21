package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands;

import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Read;

public class WebAccessSubCommand implements DirModCommandHook {
   private final Logger logger = LoggerFactory.getLogger(getClass().getName());
   public static final String BASE_COMMAND = "webaccess";

   private final FileServerService fileServerService;
   private final HistoryService historyService;

   @Inject
   public WebAccessSubCommand(FileServerService fileServerService, HistoryService historyService) {
      /* store objects */
      this.fileServerService = fileServerService;
      this.historyService = historyService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(messageClass = FileserverMessages.class, name = BASE_COMMAND, description = "commandWebAccess_description", nonOptArgs = {
         @NonOptArgument(name = "directory", description = "commandDirMod_description_arg_dirpath", mandatory = true),
         @NonOptArgument(name = "access", description = "commandDirMod_description_arg_webaccess", mandatory = true) })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      final CommandResult cr = new CommandResult();
      List<String> arguments = parser.getNonOptionArguments();
      if (arguments.isEmpty() || arguments.size() != 2)
         throw new IllegalArgumentException("Please enter valid number of arguments");

      String path = arguments.get(0);
      final String webAccess = arguments.get(1);

      if (!"true".equals(webAccess) && !"false".equals(webAccess))
         throw new IllegalArgumentException("Please add valid webaccess value (true / false)");

      final Collection<?> resolvedObjects = session.getObjectResolver().getObjects(path, Read.class);
      final List<?> searchResults = (List<?>) resolvedObjects;

      if (0 == resolvedObjects.size()) {
         cr.addResultLine("No FileServerFolder objects found");
         return cr;
      }

      if (searchResults.stream().anyMatch(item -> !(item instanceof FileServerFolder)))
         throw new IllegalArgumentException("Only FileServerFolder objects can be modified");

      cr.addResultLine("Web access permission changed for the following directories:");
      searchResults.forEach(item -> {
         FileServerFolder folder = (FileServerFolder) item;
         folder.setPubliclyAccessible(Boolean.parseBoolean(webAccess));
         fileServerService.persist(folder);
         try {
            historyService.buildLinksFor(item)
                  .forEach(link -> cr.addResultHyperLink(link.getObjectCaption(), link.getLink()));
         } catch (IllegalArgumentException e) {
            logger.warn(e.getMessage(), e);
         }
      });
      return cr;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }
}