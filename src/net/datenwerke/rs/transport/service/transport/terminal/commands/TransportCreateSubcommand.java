package net.datenwerke.rs.transport.service.transport.terminal.commands;

import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.ext.service.locale.RsBaseExtMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.TransportFolder;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TransportCreateSubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "create";
   
   private final Provider<TransportService> transportServiceProvider;
   
   @Inject
   public TransportCreateSubcommand(
         Provider<TransportService> transportServiceProvider
         ) {
      this.transportServiceProvider = transportServiceProvider;
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
      if (2 != arguments.size())
         throw new IllegalArgumentException("Exactly two arguments expected");
      
      final VirtualFileSystemDeamon vfs = session.getFileSystem();
      
      try {
         Collection<VFSLocation> resolvedTarget = vfs.getLocation(arguments.get(0)).resolveWildcards(vfs);
         if (resolvedTarget.size()!=1)
            throw new IllegalArgumentException("Exactly one target folder expected.");
         VFSLocation target = resolvedTarget.iterator().next();
         
         if (null == target.getFilesystemManager())
            throw new IllegalArgumentException("cannot create transport in root");
         if (!target.exists())
            throw new IllegalArgumentException("Target folder does not exist.");
         if (!target.isFolder())
            throw new IllegalArgumentException("Target is not a folder.");
         AbstractNode<?> parent = target.getFilesystemManager().getNodeByLocation(target);
         if (! (parent instanceof TransportFolder))
            throw new IllegalArgumentException("Target is not a transport folder.");
         
         final String transport = transportServiceProvider.get().createTransport(arguments.get(1), (TransportFolder) parent);
         
         return new CommandResult("Transport successfuly created: '" + transport + "'");
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
