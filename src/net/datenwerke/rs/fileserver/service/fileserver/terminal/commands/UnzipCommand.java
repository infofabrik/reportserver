package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Inject;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.locale.FileserverMessages;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.unzip.BasepathZipExtractConfigFactory;
import net.datenwerke.rs.fileserver.service.fileserver.vfs.FileServerVfs;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.utils.zip.ZipExtractionConfig;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.service.security.rights.Read;

public class UnzipCommand implements TerminalCommandHook {

   private final Logger logger = LoggerFactory.getLogger(getClass().getName());

   private static final String BASE_COMMAND = "unzip";
   private final FileServerService fileServerService;
   private final ZipUtilsService zipUtilsService;
   private final BasepathZipExtractConfigFactory extractConfigFactory;

   @Inject
   public UnzipCommand(FileServerService fileServerService, ZipUtilsService zipUtilsService,
         BasepathZipExtractConfigFactory extractConfigFactory) {
      this.fileServerService = fileServerService;
      this.zipUtilsService = zipUtilsService;
      this.extractConfigFactory = extractConfigFactory;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = FileserverMessages.class, name = BASE_COMMAND, description = "commandUnzip_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      try {
         Object archive = session.getObjectResolver().getObject(parser.getArgumentNr(1), Read.class);

         VFSLocation location = session.getFileSystem().getCurrentLocation();

         if (!(location.getFilesystemManager() instanceof FileServerVfs))
            return new CommandResult("wrong filesystem");

         FileServerFolder parent = null;
         try {
            parent = (FileServerFolder) location.getObject();
         } catch (VFSException e) {
            return new CommandResult(e.getMessage());
         } catch (ClassCastException e) {
            return new CommandResult("Can only unzip to folder");
         }

         ZipExtractionConfig zec = extractConfigFactory.create(parent);

         if (archive instanceof FileServerFile) {
            zipUtilsService.extractZip(((FileServerFile) archive).getData(), zec);
         }
      } catch (IOException e) {
         logger.warn(e.getMessage(), e);
      }

      return new CommandResult("");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
