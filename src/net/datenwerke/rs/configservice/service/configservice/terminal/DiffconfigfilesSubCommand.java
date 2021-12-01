package net.datenwerke.rs.configservice.service.configservice.terminal;

import static java.util.stream.Collectors.toList;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import net.datenwerke.gf.service.history.HistoryLink;
import net.datenwerke.gf.service.history.HistoryService;
import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.AbstractFileServerNode;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;

public abstract class DiffconfigfilesSubCommand implements DiffconfigfilesSubCommandHook {

   protected final String BASE_COMMAND;
   protected final HistoryService historyService;
   protected final FileServerService fileServerService;
   protected final ConfigService configService;
   protected FileServerFolder tmpConfigFolder;
   protected String tmpDirName;
   protected String tmpDirPath;

   public DiffconfigfilesSubCommand(
         HistoryService historyService,
         FileServerService fileServerService,
         ConfigService configService,
         String BASE_COMMAND) {
      this.historyService = historyService;
      this.fileServerService = fileServerService;
      this.configService = configService;
      this.BASE_COMMAND = BASE_COMMAND;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return parser.getBaseCommand().equals(BASE_COMMAND);
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

   protected List<HistoryLink> findMissingConfigFiles(FileServerFolder tmpConfigFolder) {
      FileServerFolder currentConfigFolder = (FileServerFolder) fileServerService.getNodeByPath("/etc");
      if (null == currentConfigFolder)
         throw new IllegalStateException("Config files were not found!");

      List<FileServerFile> expectedConfigFiles = tmpConfigFolder
            .getSubfolderByName("etc")
            .getDescendants(FileServerFile.class);
      List<FileServerFile> actualConfigFiles = currentConfigFolder.getDescendants(FileServerFile.class);
      List<HistoryLink> fileLinksExpectedConfig = historyService.buildLinksForList(expectedConfigFiles);
      List<HistoryLink> fileLinksActualConfig = historyService.buildLinksForList(actualConfigFiles);

      return fileLinksExpectedConfig
            .stream()
            .filter(fileLinkExpectedConfig -> !containsLink(fileLinkExpectedConfig, fileLinksActualConfig))
            .collect(toList());
   }

   private boolean containsLink(HistoryLink fileLinkExpectedConfig, List<HistoryLink> fileLinksActualConfig) {
      Optional<HistoryLink> matchingLink = fileLinksActualConfig
            .stream()
            .filter(link -> link.getObjectCaption()
                  .equals(fileLinkExpectedConfig.getObjectCaption().replace(tmpDirPath, "")))
            .findAny();
      return matchingLink.isPresent();
   }

   protected void createTmpConfigFolderAndSetFolderNameAndPath() throws TerminalException {
      for (int i = 0; i < 10; i++) {
         String name = UUID.randomUUID().toString();
         if (null == fileServerService.getNodeByPath("/" + name, false)) {
            tmpDirName = name;
            tmpDirPath = "/" + tmpDirName;
            tmpConfigFolder = new FileServerFolder(tmpDirName);
            AbstractFileServerNode root = fileServerService.getRoots().get(0);
            root.addChild(tmpConfigFolder);
            fileServerService.persist(tmpConfigFolder);
            fileServerService.merge(root);
            return;
         }
      }
      throw new TerminalException("Could not generate an unused name for config files folder");
   }
   
   protected void removeTmpConfigFolder() {
      fileServerService.forceRemove(tmpConfigFolder);
   }
}