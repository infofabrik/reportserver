package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfs;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocationInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSObjectInfo;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;

public class CreateTextFileCommand implements TerminalCommandHook {

   private static final String BASE_COMMAND = "createTextFile";

   private final FileServerService fileService;

   @Inject
   public CreateTextFileCommand(FileServerService fileService) {

      /* store objects */
      this.fileService = fileService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = FileserverMessages.class, 
         name = BASE_COMMAND, 
         description = "commandCreateTextFile_description",
         nonOptArgs = {
               @NonOptArgument(
                     name = "file", 
                     description = "commandCreateTextFile_file", 
                     mandatory = true
               )
         }
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      String completePath = parser.getArgumentNr(1);

      if (null == completePath)
         throw new IllegalArgumentException("Expected filename");

      PathHelper helper = new PathHelper(completePath);
      String path = helper.getPath();
      String fileName = helper.getLastPathway();

      if (null == fileName || "".equals(fileName.trim()))
         throw new IllegalArgumentException("Expected filename");

      VFSLocation location;
      if ("".equals(path))
         location = session.getFileSystem().getCurrentLocation();
      else {
         try {
            location = session.getFileSystem().getLocation(path);
         } catch (VFSException e1) {
            throw new IllegalArgumentException("illegal path: " + path);
         }
      }

      if (!(location.getFilesystemManager() instanceof FileServerVfs))
         return new CommandResult("wrong filesystem");

      /* check if file exists */
      VirtualFileSystemDeamon vfs = session.getFileSystem();
      VFSLocationInfo locationInfo = vfs.getLocationInfo(location);
      for (VFSObjectInfo info : locationInfo.getChildInfos()) {
         if (fileName.equals(info.getName()))
            return new CommandResult("A file or folder with this name already exists");
      }

      AbstractFileServerNode parent = null;
      try {
         parent = (AbstractFileServerNode) location.getObject();
      } catch (VFSException e) {
         return new CommandResult(e.getMessage());
      }

      FileServerFile textFile = new FileServerFile();
      textFile.setName(fileName);
      if (null != parent)
         parent.addChild(textFile);

      fileService.persist(textFile);
      if (null != parent)
         fileService.merge(parent);

      CommandResult result = new CommandResult("file created");
      EditCommandResultExtension ext = new EditCommandResultExtension();
      ext.setFile(textFile);
      ext.setData("");
      result.addExtension(ext);

      return result;
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
