package net.datenwerke.rs.transport.service.transport.terminal.commands;

import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

import org.apache.commons.lang3.tuple.ImmutablePair;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.eximport.im.ImportResult.ImportResultExtraData;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
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
import net.datenwerke.rs.transport.service.transport.PreconditionResult;
import net.datenwerke.rs.transport.service.transport.TransportApplyService;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.entities.Transport;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.rs.utils.file.RsFileTreeUtils;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.string.Emoji;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class TransportApplySubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "apply";
   
   private final Provider<TransportService> transportServiceProvider;
   private final Provider<TransportApplyService> transportApplyServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<SecurityService> securityServiceProvider;
   
   @Inject
   public TransportApplySubcommand(
         Provider<TransportService> transportServiceProvider,
         Provider<TransportApplyService> transportApplyServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<SecurityService> securityServiceProvider
         ) {
      this.transportServiceProvider = transportServiceProvider;
      this.transportApplyServiceProvider = transportApplyServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.securityServiceProvider = securityServiceProvider;
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
         description = "commandTransportApply_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "transport", 
                     description = "commandTransportApply_target",
                     mandatory = true
               )
         }, args = {
               @Argument(
                     flag = "c", 
                     hasValue = false, 
                     valueName = "check", 
                     description = "commandTransportApply_flagC", 
                     mandatory = false
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (1 != arguments.size())
         throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Exactly one argument expected");

      final VirtualFileSystemDeamon vfs = session.getFileSystem();
      
      final String argStr = "c";
      final boolean check = parser.hasOption("c", argStr);
      
      try {
         Collection<VFSLocation> resolvedTarget = vfs.getLocation(arguments.get(0)).resolveWildcards(vfs);
         if (resolvedTarget.size()!=1)
            throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Exactly one transport expected.");
         VFSLocation target = resolvedTarget.iterator().next();
         
         AbstractNode<?> transportTarget = target.getFilesystemManager().getNodeByLocation(target);
         if (!(transportTarget instanceof Transport))
            throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + 
                  "Target is not a transport or transport not found: '" + arguments.get(0) + "'");
         
         if (!target.exists())
            throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Transport does not exist: '" + arguments.get(0) + "'");
         if (target.isFolder())
            throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Target is a folder.");
         
         Transport transport = (Transport) transportTarget;
         
         securityServiceProvider.get().assertRights(transport, Execute.class);
         
         if (check)
            return createCheckCommandResult(transport);
         
         Instant start = Instant.now();
         
         Optional<ImmutablePair<ImportResult, Exception>> result = transportApplyServiceProvider.get().applyTransport(transport);
         
         if (!result.isPresent()) 
            return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Preconditions are not met. Details can be found in the apply log.");
         
         Instant end = Instant.now();
         
         if (result.get().getLeft() != null) {
            RsFileTreeUtils<ImportResultExtraData> fileTreeUtils = new RsFileTreeUtils<ImportResultExtraData>(result.get().getLeft().getExtraData());
            Map<String,ImportResultExtraData> objectTree = fileTreeUtils.buildObjectTree();
            
            Map<String,Object> resultsMap = new LinkedHashMap<>();
            resultsMap.put("Status", TransportService.Status.APPLIED.name());
            resultsMap.put("Duration", Duration.between(start, end).toString());
            resultsMap.put("Entities", objectTree.size());
            resultsMap.put("Date", DateUtils.format(result.get().getLeft().getDate()));

            CommandResult commandResult = new CommandResult();
            
            final RSTableModel resultsTable = terminalServiceProvider.get().convertSimpleMapToTableModel(resultsMap);
            commandResult.addResultTable(resultsTable);
                        
            final RSTableModel detailsTable = resultExtraDataToModel(objectTree);
            commandResult.addResultTable(detailsTable);
            
            return commandResult;
            
         } else if (result.get().getRight() != null) {
            return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Error while applying. Check transport logs for more information.");
         } else {
            return new CommandResult(Emoji.SMILING_FACE_TEAR.getEmoji(" ") + "Something went wrong");
         }
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }
   
   private RSTableModel resultExtraDataToModel(Map<String, ImportResultExtraData> map) {
      RSTableModel model = new RSTableModel();
      TableDefinition tableDef = new TableDefinition(Arrays.asList("Path", "Strategy", "Node"), Arrays.asList(String.class, String.class, String.class));
      
      tableDef.setDisplaySizes(Arrays.asList(180, getMaxCharWidth(map), 0));
      model.setTableDefinition(tableDef);
      
      for (Entry<String, ImportResultExtraData> entry : map.entrySet()) {
         String key = entry.getKey();
         ImportResultExtraData val = entry.getValue();
         model.addDataRow(new RSStringTableRow(key, val.getStrategy(), val.object.toString()));
      }
      return model;
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
   
   private int getMaxCharWidth(Map<String, ImportResultExtraData> map) {
      int longestStringLength = map.values().stream()
            .mapToInt(value -> value.getStrategy().length())
            .max()
            .orElse(0);
      
      int baseWidth = (int) Math.ceil(("Strategy".length() * TerminalService.CHAR_WIDTH) / 5) * 5;
      
      if (longestStringLength <= 0) return baseWidth;
      
      return Math.max((int) Math.ceil((longestStringLength * TerminalService.CHAR_WIDTH) / 5) * 5, baseWidth);
   }
   
   private CommandResult createCheckCommandResult(Transport transport) {
      
      Map<String, PreconditionResult> analysisResults = transportServiceProvider.get().analyzeApplyPreconditions(transport);
      
      RSTableModel model = new RSTableModel();
      TableDefinition tableDef = new TableDefinition(Arrays.asList("Key", "Result", "Error message", "Error stack trace"),
            Arrays.asList(String.class, String.class, String.class, String.class));
      model.setTableDefinition(tableDef);
      
      analysisResults.keySet()
         .stream()
         .sorted()
         .map(key -> {
            PreconditionResult val = analysisResults.get(key);
               return new RSStringTableRow(key, val.getResult().name(),
                     val.getErrorMsg().isPresent() ? val.getErrorMsg().get() : "");
            })
         .forEach(row -> model.addDataRow(row));
      
      CommandResult result = new CommandResult();
      result.addResultTable(model);
      
      return result;
   }
}
