package net.datenwerke.rs.transport.service.transport.terminal.commands;

import java.util.Collection;
import java.util.List;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TransportAddSubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "add";
   
   private final Provider<TransportService> transportServiceProvider;
   
   @Inject
   public TransportAddSubcommand(
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
         messageClass = TransportManagerMessages.class, 
         name = BASE_COMMAND, 
         description = "commandTransportCreate_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "target", 
                     description = "commandTransportCreate_target",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "description", 
                     description = "commandTransportCreate_description",
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
            throw new IllegalArgumentException("Exactly one transport expected.");
         VFSLocation target = resolvedTarget.iterator().next();
         
         AbstractNode<?> transportTarget = target.getFilesystemManager().getNodeByLocation(target);
         if (! (transportTarget instanceof Transport))
            throw new IllegalArgumentException("Target is not a transport.");
         
         if (!target.exists())
            throw new IllegalArgumentException("Target transport does not exist.");
         if (target.isFolder())
            throw new IllegalArgumentException("Target is a folder.");
         
         Transport transport = (Transport) transportTarget;
         if (transport.isClosed())
            throw new IllegalArgumentException("Cannot add to closed transport");
         
         Collection<VFSLocation> resolvedElement = vfs.getLocation(arguments.get(1)).resolveWildcards(vfs);
         if (resolvedElement.size()!=1)
            throw new IllegalArgumentException("Exactly one element expected.");
         VFSLocation elementLoc = resolvedElement.iterator().next();
         
         AbstractNode<?> element = elementLoc.getFilesystemManager().getNodeByLocation(elementLoc);
         if (element.isFolder())
            throw new IllegalArgumentException("Cannot add a folder to transport.");
         
         transportServiceProvider.get().addElement(transport, element, false);
         
         return new CommandResult("Added to transport '" + transport + "': '" + element + "'");
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}