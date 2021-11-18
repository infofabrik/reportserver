package net.datenwerke.rs.configservice.service.configservice.terminal;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.google.inject.Inject;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSStringTableRow;
import net.datenwerke.rs.base.service.reportengines.table.output.object.RSTableModel;
import net.datenwerke.rs.base.service.reportengines.table.output.object.TableDefinition;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.configservice.service.configservice.terminal.helper.VfsHelper;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class DiffconfigfilesShowmissingCommand implements DiffconfigfilesSubCommandHook {

   private static final String BASE_COMMAND = "showmissing";
   private final VfsHelper vfsHelper;
   private final String tmpFolderName = "tmp";
   private final String pathForBaseConfig = "/tmp";

   @Inject
   public DiffconfigfilesShowmissingCommand(
         VfsHelper vfsHelper) {
      this.vfsHelper = vfsHelper;
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
      vfsHelper.unzipAndCopyBaseConfigPackageTo(pathForBaseConfig);
      List<HistoryLink> missingConfigFiles = findMissingConfigFiles();
      vfsHelper.removeTmpFolder(tmpFolderName);
      return generateCommandResult(missingConfigFiles);
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

   /**
    * Compares two configuration folder asserting if files are missing. Actual
    * configuration := Root/* without Root/_tmpFolderName_ .Expected configuration
    * := Root/_tmpFolderName_/*
    * 
    * @return a list of files which is missing in actual configuration
    */
   private List<HistoryLink> findMissingConfigFiles() {
      List<FileServerFile> expectedConfigFiles = vfsHelper.getExpectedConfigFileList(tmpFolderName);
      List<FileServerFile> actualConfigFiles = vfsHelper.getActualConfigFileList(tmpFolderName);
      List<HistoryLink> fileLinksExpectedConfig = vfsHelper.getHistoryLinks(expectedConfigFiles);
      List<HistoryLink> fileLinksActualConfig = vfsHelper.getHistoryLinks(actualConfigFiles);
      fileLinksExpectedConfig = removeBinFolderFiles(fileLinksExpectedConfig);

      return fileLinksExpectedConfig
            .stream()
            .filter(fileLinkExpectedConfig -> !containsLink(fileLinkExpectedConfig, fileLinksActualConfig))
            .collect(Collectors.toList());
   }

   private boolean containsLink(HistoryLink fileLinkExpectedConfig, List<HistoryLink> fileLinksActualConfig) {
      Optional<HistoryLink> matchingLink = fileLinksActualConfig
            .stream()
            .filter(link -> link.getObjectCaption()
                  .equals(fileLinkExpectedConfig.getObjectCaption().replace(pathForBaseConfig, "")))
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
      String filterBinFolder = "^((?!/bin).)*$";
      return fileLinksExpectedConfig
            .stream()
            .filter(link -> link.getObjectCaption().matches(filterBinFolder))
            .collect(Collectors.toList());
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
                        linkString.replace(pathForBaseConfig, ""),
                        "missing")));

      return new CommandResult(rsTableModel);
   }
}
