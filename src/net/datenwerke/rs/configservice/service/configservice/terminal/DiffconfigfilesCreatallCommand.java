package net.datenwerke.rs.configservice.service.configservice.terminal;

import java.util.ArrayList;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class DiffconfigfilesCreatallCommand extends DiffconfigfilesSubCommand {
   private static final String BASE_COMMAND = "createall";

   @Inject
   public DiffconfigfilesCreatallCommand(
         HistoryService historyService,
         FileServerService fileServerService,
         ConfigService configService) {
      super(historyService, fileServerService, configService, BASE_COMMAND);
   }

   @CliHelpMessage(
         messageClass = ConfigMessages.class, 
         name = BASE_COMMAND, 
         description = "commandDiffConfigFiles_sub_createall_description",
         nonOptArgs = {
               @NonOptArgument(name="folder", description="commandDiffConfigFiles_sub_createall_folderArgument", mandatory = true)
           })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {

      HistoryLink link = null;
      if (parser.getNonOptionArguments().size() != 1)
         throw new IllegalArgumentException("Please enter a folder");
      String folderName = parser.getNonOptionArguments().get(0);

      try {
         tmpConfigFolder = configService.extractBasicConfigFilesTo(folderName);
         removeNonConfigFolders(folderName);
         link = historyService.buildLinksFor(tmpConfigFolder).get(0);
      } catch (Exception e) {
         throw new TerminalException("the config files could not be copied to " + folderName + ".", e);
      } 
      return new CommandResult().addResultHyperLink(link.getObjectCaption() + " (" + link.getHistoryLinkBuilderId() + ")", link.getLink());
   }

   private void removeNonConfigFolders(String cfgFolderName) {
      List<FileServerFolder> foldersToRemove = new ArrayList<>();
      foldersToRemove.add((FileServerFolder) fileServerService.getNodeByPath("/" + cfgFolderName + "/bin", false));
      foldersToRemove.add((FileServerFolder) fileServerService.getNodeByPath("/" + cfgFolderName + "/resources", false));
      foldersToRemove.forEach(folder -> fileServerService.forceRemove(folder));
   }
}
