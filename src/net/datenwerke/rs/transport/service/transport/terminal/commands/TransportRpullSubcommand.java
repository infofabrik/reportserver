package net.datenwerke.rs.transport.service.transport.terminal.commands;

import static java.util.stream.Collectors.toList;

import java.time.Duration;
import java.time.Instant;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.configuration2.Configuration;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.im.ImportResult;
import net.datenwerke.rs.base.ext.service.RemoteEntityImporterServiceImpl;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.remotersrestserver.service.remotersrestserver.entities.RemoteRsRestServer;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.RemoteServerTreeService;
import net.datenwerke.rs.remoteserver.service.remoteservermanager.entities.RemoteServerDefinition;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.transport.service.transport.TransportService;
import net.datenwerke.rs.transport.service.transport.locale.TransportManagerMessages;
import net.datenwerke.rs.utils.misc.DateUtils;
import net.datenwerke.rs.utils.string.Emoji;
import net.datenwerke.security.service.security.rights.Read;

public class TransportRpullSubcommand implements TransportSubCommandHook {
   
   public static final String BASE_COMMAND = "rpull";
   
   private final Provider<TransportService> transportServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;
   private final Provider<ConfigService> configServiceProvider;
   private final Provider<RemoteServerTreeService> remoteServerTreeServiceProvider;
   
   @Inject
   public TransportRpullSubcommand(
         Provider<TransportService> transportServiceProvider,
         Provider<TerminalService> terminalServiceProvider,
         Provider<ConfigService> configServiceProvider,
         Provider<RemoteServerTreeService> remoteServerTreeServiceProvider
         ) {
      this.transportServiceProvider = transportServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
      this.configServiceProvider = configServiceProvider;
      this.remoteServerTreeServiceProvider = remoteServerTreeServiceProvider;
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
         description = "commandTransportRpull_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "remote server", 
                     description = "commandTransportRpull_remoteServer",
                     mandatory = false
               ),
               @NonOptArgument(
                     name = "target", 
                     description = "commandTransportRpull_target",
                     mandatory = false
               )
         }
   )
   @Override 
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (0 != arguments.size() && 2 != arguments.size())
         throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Exactly zero or two arguments expected");
      
      Configuration config = configServiceProvider.get().getConfigFailsafe(TransportService.CONFIG_FILE);
      String target = "";
      RemoteRsRestServer remoteRsServer = null;
      if (0 == arguments.size()) {
         String remoteServerKey = config.getString("import.remote", "REMOTE_SERVER");
         target = config.getString("import.target", "/transports/import");
         
         RemoteServerDefinition remoteServerDef = remoteServerTreeServiceProvider.get()
               .getRemoteServerByKey(remoteServerKey);
         if (!(remoteServerDef instanceof RemoteRsRestServer))
            throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "Remote server is not a REST server");
         remoteRsServer = (RemoteRsRestServer) remoteServerDef;
      } else {
         String remoteServerPath = arguments.get(0);
         target = arguments.get(1);
         remoteRsServer = terminalServiceProvider.get()
               .getSingleObjectOfTypeByQuery(RemoteRsRestServer.class, remoteServerPath, session, Read.class);
      }
      
      Instant start = Instant.now();
      ImportResult result = transportServiceProvider.get().rpull(remoteRsServer, target);
      Instant end = Instant.now();
      CommandResult commandResult = new CommandResult(Emoji.FLYING_SAUCER.getEmoji());
      
      Map<String,Object> resultsMap = new LinkedHashMap<>();
      resultsMap.put(RemoteEntityImporterServiceImpl.STATUS, RemoteEntityImporterServiceImpl.STATUS_OK);
      resultsMap.put("Duration", Duration.between(start, end).toString());
      resultsMap.put("Imported objects", result.getImportedObjects().size());
      resultsMap.put("Import date", DateUtils.format(result.getDate()));
      resultsMap.put("Name", result.getName());
      
      final RSTableModel resultsTable = terminalServiceProvider.get().convertSimpleMapToTableModel(resultsMap);
      commandResult.addResultTable(resultsTable);
      
      final RSTableModel detailsTable = terminalServiceProvider.get().convertSimpleListToTableModel(
            "Imported objects (" + result.getImportedObjects().size() + ")",
            result.getImportedObjects().values()
               .stream()
               .map(Object::toString)
               .collect(toList()));
      commandResult.addResultTable(detailsTable);
      
      return commandResult;
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
