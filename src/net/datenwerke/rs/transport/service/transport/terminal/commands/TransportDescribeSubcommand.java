package net.datenwerke.rs.transport.service.transport.terminal.commands;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
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

public class TransportDescribeSubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "describe";
   
   private final Provider<TransportService> transportServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   
   @Inject
   public TransportDescribeSubcommand(
         Provider<TransportService> transportServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.transportServiceProvider = transportServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
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
         description = "commandTransportDescribe_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "transport", 
                     description = "commandTransportDescribe_transport",
                     mandatory = true
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (1 != arguments.size())
         throw new IllegalArgumentException("Exactly one argument expected");
      
      final VirtualFileSystemDeamon vfs = session.getFileSystem();
      
      try {
         Collection<VFSLocation> resolvedTarget = vfs.getLocation(arguments.get(0)).resolveWildcards(vfs);
         if (resolvedTarget.size()!=1)
            throw new IllegalArgumentException("Exactly one transport expected.");
         VFSLocation target = resolvedTarget.iterator().next();
         
         AbstractNode<?> transportTarget = target.getFilesystemManager().getNodeByLocation(target);
         if (! (transportTarget instanceof Transport))
            throw new IllegalArgumentException(
                  "Target is not a transport or transport not found: '" + arguments.get(0) + "'");
         
         if (!target.exists())
            throw new IllegalArgumentException("Transport does not exist: '" + arguments.get(0) + "'");
         if (target.isFolder())
            throw new IllegalArgumentException("Target is a folder.");
         
         Transport transport = (Transport) transportTarget;
         
         final TerminalService terminalService = terminalServiceProvider.get();
         final TransportService transportService = transportServiceProvider.get();
         final CommandResult commandResult = new CommandResult();
         
         TableDefinition metadataTableDef = new TableDefinition(Arrays.asList("Metadata", ""),
               Arrays.asList(String.class, String.class));
         RSTableModel metadata = terminalService
               .convertSimpleMapToTableModel(transportService.getMetadata(transport));
         metadata.setTableDefinition(metadataTableDef);
         commandResult.addResultTable(metadata);
         
         Map<String, List<Map<String,String>>> elements = transportService.getElements(transport);
         elements.keySet().stream().forEach(key -> {
            commandResult.addResultLine(key);
            commandResult.addResultTable(terminalService.convertSimpleMapListToTableModel("No properties",
                  elements.get(key), Collections.emptyList(), Collections.emptyMap()));
         });

         return commandResult;
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
