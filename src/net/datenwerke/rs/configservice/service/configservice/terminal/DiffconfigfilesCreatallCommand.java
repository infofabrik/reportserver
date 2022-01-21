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
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public class DiffconfigfilesCreatallCommand extends DiffconfigfilesSubCommand {
   private static final String BASE_COMMAND = "createall";

   @Inject
   public DiffconfigfilesCreatallCommand(HistoryService historyService, FileServerService fileServerService,
         ConfigService configService, SecurityService securityService) {
      super(historyService, fileServerService, configService, securityService, BASE_COMMAND);
   }

   @CliHelpMessage(
         messageClass = ConfigMessages.class, 
         name = BASE_COMMAND, 
         description = "commandDiffConfigFiles_sub_createall_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "folder", 
                     description = "commandDiffConfigFiles_sub_createall_folderArgument", 
                     mandatory = true
                     ) 
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      HistoryLink linkToDstFolder = null;
      VirtualFileSystemDeamon vfs = session.getFileSystem();
      String dstAbsolutPath = getDstAbsolutPath(parser, vfs);
      AbstractFileServerNode dstFolder = getDstFolder(dstAbsolutPath);

      FileServerFolder root = (FileServerFolder) fileServerService.getRoots().get(0);
      securityService.assertRights((SecurityTarget) root, Read.class);
      securityService.assertRights((SecurityTarget) dstFolder, Read.class, Write.class);

      try {
         createTmpConfigFolderAndSetFolderNameAndPath();
         configService.extractBasicConfigFilesTo(tmpDirName);
         FileServerFolder etcFolder = tmpConfigFolder.getSubfolderByName("etc");
         fileServerService.copy(etcFolder, dstFolder, true);
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

   private AbstractFileServerNode getDstFolder(String dstAbsolutPath) {
      if (dstAbsolutPath.startsWith("/fileserver/")) {
         dstAbsolutPath = dstAbsolutPath.replace("/fileserver/", "");
      } else {
         throw new IllegalArgumentException(
               dstAbsolutPath + " is not within the fileserver filesystem. Choose a folder present in the fileserver");
      }
      AbstractFileServerNode dstFolder = fileServerService.getNodeByPath(dstAbsolutPath, false);
      if (null == dstFolder)
         throw new IllegalArgumentException("No folder with path: " + dstAbsolutPath
               + " exists. Please ensure your folder is already present in the file server system.");
      return dstFolder;
   }

   private String getDstAbsolutPath(CommandParser parser, VirtualFileSystemDeamon vfs) {
      if (parser.getNonOptionArguments().size() != 1)
         throw new IllegalArgumentException("Please enter a folder path");
      String dstString = parser.getNonOptionArguments().get(0);
      String dstAbsolutPath;
      try {
         VFSLocation source = vfs.getLocation(dstString);
         dstAbsolutPath = source.prettyPrint();
      } catch (VFSException e1) {
         throw new IllegalArgumentException("The following location does not exist: " + dstString, e1);
      }
      return dstAbsolutPath;
   }
}
