package net.datenwerke.rs.configservice.service.configservice.terminal;

import static java.util.stream.Collectors.toList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.utils.config.ConfigService;

public class DiffconfigfilesShowmissingCommand implements DiffconfigfilesSubCommandHook {

   private static final String BASE_COMMAND = "showmissing";
   private static final String TMP_DIR = "/tmp";
   private final HistoryService historyService;
   private final FileServerService fileServerService;
   private final ConfigService configService;

   @Inject
   public DiffconfigfilesShowmissingCommand(
         HistoryService historyService,
         FileServerService fileServerService,
         ConfigService configService
         ) {
      this.historyService = historyService;
      this.fileServerService = fileServerService;
      this.configService = configService;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return parser.getBaseCommand().equals(BASE_COMMAND);
   }

   @CliHelpMessage(
         messageClass = ConfigMessages.class, 
         name = BASE_COMMAND, 
         description = "commandDiffConfigFiles_sub_showmissing_description")
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<HistoryLink> missingConfigFiles = null;
      FileServerFolder tmpConfigFolder = null;
      try {
         configService.extractBasicConfigFilesTo(TMP_DIR);
      
         tmpConfigFolder = (FileServerFolder) fileServerService.getNodeByPath("/tmp");
         if (null == tmpConfigFolder)
            throw new TerminalException("tmp folder was not created correctly");
         
         missingConfigFiles = findMissingConfigFiles(tmpConfigFolder);
      } catch (Exception e) {
         throw new TerminalException("the config files could not be calculated: " + e);
      } finally {
         if (null != tmpConfigFolder)
            fileServerService.remove(tmpConfigFolder);
      }
      return generateCommandResult(missingConfigFiles);
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

   /**
    * Compares two configuration folders asserting if files are missing. Actual
    * configuration := Root/* without Root/_tmpFolderName_ .Expected configuration
    * := Root/_tmpFolderName_/*
    * 
    * @return a list of files which is missing in actual configuration
    */
   private List<HistoryLink> findMissingConfigFiles(FileServerFolder tmpConfigFolder) {
      List<FileServerFile> expectedConfigFiles = tmpConfigFolder
            .getSubfolderByName("etc")
            .getDescendants(FileServerFile.class);
      
      FileServerFolder currentConfigFolder = (FileServerFolder) fileServerService.getNodeByPath("/etc");
      if (null == currentConfigFolder)
         throw new IllegalStateException("Config files were not found!");
      
      List<FileServerFile> actualConfigFiles = currentConfigFolder.getDescendants(FileServerFile.class);
      List<HistoryLink> fileLinksExpectedConfig = historyService.buildLinksForList(expectedConfigFiles);
      List<HistoryLink> fileLinksActualConfig = historyService.buildLinksForList(actualConfigFiles);
      fileLinksExpectedConfig = removeBinFolderFiles(fileLinksExpectedConfig);

      return fileLinksExpectedConfig
            .stream()
            .filter(fileLinkExpectedConfig -> !containsLink(fileLinkExpectedConfig, fileLinksActualConfig))
            .collect(toList());
   }

   private boolean containsLink(HistoryLink fileLinkExpectedConfig, List<HistoryLink> fileLinksActualConfig) {
      Optional<HistoryLink> matchingLink = fileLinksActualConfig
            .stream()
            .filter(link -> link.getObjectCaption()
                  .equals(fileLinkExpectedConfig.getObjectCaption().replace(TMP_DIR, "")))
            .findAny();
      return matchingLink.isPresent();
   }

   /**
    * Removes fileLinks containing the /bin sequence in their path
    * 
    * @param fileLinksExpectedConfig List which should be filtered
    * @return a filtered list
    */
   private List<HistoryLink> removeBinFolderFiles(List<HistoryLink> fileLinksExpectedConfig) {
      final String filterBinFolder = "^((?!/bin).)*$";
      return fileLinksExpectedConfig
            .stream()
            .filter(link -> link.getObjectCaption().matches(filterBinFolder))
            .collect(toList());
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
                        linkString.replace(TMP_DIR, ""),
                        "missing")));

      return new CommandResult(rsTableModel);
   }
}
