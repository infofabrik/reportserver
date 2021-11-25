package net.datenwerke.rs.configservice.service.configservice.terminal;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.config.ConfigService;

public class DiffconfigfilesCreatallCommand implements DiffconfigfilesSubCommandHook {
   private static final String BASE_COMMAND = "createall";
   private static final String TMP_DIR = "tmp";
   private final HistoryService historyService;
   private final FileServerService fileServerService;
   private final ConfigService configService;

   @Inject
   public DiffconfigfilesCreatallCommand(
         HistoryService historyService,
         FileServerService fileServerService,
         ConfigService configService) {
      this.historyService = historyService;
      this.fileServerService = fileServerService;
      this.configService = configService;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return parser.getBaseCommand().equals(BASE_COMMAND);
   }

   @CliHelpMessage(
         messageClass = ConfigMessages.class, 
         name = BASE_COMMAND, 
         description = "commandDiffConfigFiles_sub_createall_description",
         nonOptArgs = {
               @NonOptArgument(name="folder", description="commandDiffConfigFiles_sub_createall_folderArgument", mandatory = false)
           })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {

      HistoryLink link = null;
      FileServerFolder tmpConfigFolder = null;
      String folderName = (null != parser.getArgumentNr(1)) ? parser.getArgumentNr(1) : TMP_DIR;

      try {
         tmpConfigFolder = configService.extractBasicConfigFilesTo(folderName);
         removeFolders(folderName);
         link = historyService.buildLinksFor(tmpConfigFolder).get(0);
      } catch (Exception e) {
         throw new TerminalException("the config files could not be copied to " + folderName + ".", e);
      } 
      return new CommandResult().addResultHyperLink(link.getObjectCaption() + " (" + link.getHistoryLinkBuilderId() + ")", link.getLink());
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
   
   private void removeFolders(String folderName) {
      List<FileServerFolder> foldersToRemove = new ArrayList<>();
      foldersToRemove.add((FileServerFolder)fileServerService.getNodeByPath("/"+ folderName + "/bin", false));
      foldersToRemove.add((FileServerFolder)fileServerService.getNodeByPath("/"+ folderName + "/resources", false));
      foldersToRemove.forEach(folder -> fileServerService.forceRemove(folder));
   }
}
