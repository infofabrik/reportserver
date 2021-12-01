package net.datenwerke.rs.configservice.service.configservice.terminal;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;

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

      HistoryLink linkToDstFolder = null;
      String dstAbsolutPath = "";
      VirtualFileSystemDeamon vfs = session.getFileSystem();
      
      if (parser.getNonOptionArguments().size() != 1)
         throw new IllegalArgumentException("Please enter a folder path");
      String dstString = parser.getNonOptionArguments().get(0);

      try {
         VFSLocation source = vfs.getLocation(dstString);
         dstAbsolutPath = source.prettyPrint();
      } catch (VFSException e1) {
         throw new IllegalArgumentException("The following location does not exist: " + dstString, e1);
      }

      if (dstAbsolutPath.startsWith("/fileserver/")) {
         dstAbsolutPath = dstAbsolutPath.replace("/fileserver/", "");
      } else {
         throw new IllegalArgumentException(dstAbsolutPath
               + " is not within the fileserver filesystem. Choose a folder present in the fileserver");
      }
      AbstractFileServerNode dstFolder = fileServerService.getNodeByPath(dstAbsolutPath, false);
      if (null == dstFolder)
         throw new IllegalArgumentException(
               "No folder with path: "
                     + dstAbsolutPath
                     + " exists. Please ensure your folder is already present in the file server system.");
      try {
         createTmpConfigFolderAndSetFolderNameAndPath();
         configService.extractBasicConfigFilesTo(tmpDirName);
         FileServerFolder etcFolder = tmpConfigFolder.getSubfolderByName("etc");
         fileServerService.move(etcFolder, dstFolder);
         linkToDstFolder = historyService.buildLinksFor(dstFolder).get(0);
      } catch (Exception e) {
         throw new TerminalException("the config files could not be copied to " + dstAbsolutPath + ".", e);
      } finally {
         removeTmpConfigFolder();
      }
      return new CommandResult().addResultHyperLink(
            linkToDstFolder.getObjectCaption() + " (" + linkToDstFolder.getHistoryLinkBuilderId() + ")",
            linkToDstFolder.getLink());
   }
}
