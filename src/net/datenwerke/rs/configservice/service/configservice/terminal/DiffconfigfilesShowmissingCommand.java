package net.datenwerke.rs.configservice.service.configservice.terminal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class DiffconfigfilesShowmissingCommand extends DiffconfigfilesSubCommand {

   private static final String BASE_COMMAND = "showmissing";
   @Inject
   public DiffconfigfilesShowmissingCommand(
         HistoryService historyService,
         FileServerService fileServerService,
         ConfigService configService) {
      super(historyService, fileServerService, configService, BASE_COMMAND);
   }

   @CliHelpMessage(
         messageClass = ConfigMessages.class, 
         name = BASE_COMMAND, 
         description = "commandDiffConfigFiles_sub_showmissing_description")
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<HistoryLink> missingConfigFiles = null;

      try {
         createTmpConfigFolderAndSetFolderNameAndPath();
         configService.extractBasicConfigFilesTo(tmpDirName);

         missingConfigFiles = findMissingConfigFiles(tmpConfigFolder);
      } catch (Exception e) {
         throw new TerminalException("the config files could not be calculated.", e);
      } finally {
         if (null != tmpConfigFolder)
            fileServerService.remove(tmpConfigFolder);
      }
      return generateCommandResult(missingConfigFiles);
   }


   private CommandResult generateCommandResult(List<HistoryLink> missingConfigFiles) {
      if (missingConfigFiles.isEmpty())
         return new CommandResult("No missing files detected");

      RSTableModel rsTableModel = new RSTableModel();
      TableDefinition td = new TableDefinition(
            Arrays.asList("File Path", "Status"),
            Arrays.asList(String.class, String.class));
      rsTableModel.setTableDefinition(td);

      List<String> missingLinksAsString = missingConfigFiles
            .stream()
            .map(link -> link.getObjectCaption())
            .collect(Collectors.toList());
      missingLinksAsString.sort(Comparator.comparing(String::toString));
      missingLinksAsString.forEach(
            linkString -> rsTableModel.addDataRow(
                  new RSStringTableRow(
                        linkString.replace(tmpDirPath, ""),
                        "missing")));

      return new CommandResult(rsTableModel);
   }
}
