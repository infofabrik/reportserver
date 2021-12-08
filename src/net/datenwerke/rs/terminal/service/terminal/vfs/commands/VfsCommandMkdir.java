package net.datenwerke.rs.terminal.service.terminal.vfs.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfs;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.helper.PathHelper;
import net.datenwerke.rs.terminal.service.terminal.vfs.locale.VfsMessages;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public class VfsCommandMkdir implements TerminalCommandHook {

   public static final String BASE_COMMAND = "mkdir";

   private final SecurityService securityService;

   @Inject
   public VfsCommandMkdir(SecurityService securityService) {

      /* store objects */
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = VfsMessages.class, 
         name = BASE_COMMAND, 
         description = "commandMkdir_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "dir", 
                     description = "commandMkdir_argument"
                     ) 
               }
         )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      VirtualFileSystemDeamon vfs = session.getFileSystem();

      List<String> arguments = parser.getNonOptionArguments();
      if (arguments.isEmpty())
         throw new IllegalArgumentException();
      String pathStr = arguments.get(0);
      PathHelper path = new PathHelper(pathStr);

      try {
         VFSLocation location = vfs.getLocation(path.getPath());

         if (location.isVirtualLocation())
            throw new IllegalArgumentException("location is virtual");
         
         if (null == location.getFilesystemManager())
            throw new IllegalArgumentException("cannot create dir in root");

         Object object = location.getObject();
         if (object instanceof SecurityTarget)
            securityService.checkRights((SecurityTarget) object, Read.class, Write.class);
         else {
            // what now?
         }

         vfs.createFolderIn(location, path.getLastPathway());

         return new CommandResult();
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
}
