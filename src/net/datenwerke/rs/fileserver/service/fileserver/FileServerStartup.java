package net.datenwerke.rs.fileserver.service.fileserver;

import java.lang.reflect.Field;
import java.net.URL;
import java.net.URLStreamHandler;
import java.util.Hashtable;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.eximport.hooks.ExporterProviderHook;
import net.datenwerke.eximport.hooks.ImporterProviderHook;
import net.datenwerke.gf.service.history.hooks.HistoryUrlBuilderHook;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ExportAllHook;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.eximport.service.eximport.im.http.hooks.HttpImportConfigurationProviderHook;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.FileServerExporter;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.FileServerImporter;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.HttpFileServerImportConfigurationHooker;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.hookers.ExportAllFilesHooker;
import net.datenwerke.rs.fileserver.service.fileserver.eximport.hookers.ImportAllFilesHooker;
import net.datenwerke.rs.fileserver.service.fileserver.hookers.FileServerFileUploadHooker;
import net.datenwerke.rs.fileserver.service.fileserver.hookers.FileServerHistoryUrlBuilderHooker;
import net.datenwerke.rs.fileserver.service.fileserver.rsfs.RsfsUrlStreamHandler;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.CreateTextFileCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.DirModCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.DirModCommandHook;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.EditTextFileCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.UnzipCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.WebAccessSubCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.ZipCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs.LfsCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs.LfsExportSubCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs.LfsImportSubCommand;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs.LfsSubCommandHook;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.hookers.CatCommandHandlerHooker;
import net.datenwerke.rs.fileserver.service.fileserver.terminal.operators.WriteIntoFileOperator;
import net.datenwerke.rs.terminal.service.terminal.basecommands.hooks.CatCommandHandlerHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.operator.TerminalCommandOperator;
import net.datenwerke.security.service.security.SecurityService;

public class FileServerStartup {

   @Inject
   public FileServerStartup(
         HookHandlerService hookHandler, 
         SecurityService securityService,

         Provider<FileServerExporter> exporterProvider, 
         Provider<FileServerImporter> importerProvider,
         Provider<ExportAllFilesHooker> exportAllFiles, 
         Provider<ImportAllFilesHooker> importAllFiles,

         Provider<HttpFileServerImportConfigurationHooker> httpImport,

         Provider<CreateTextFileCommand> createTextFileCommand, 
         Provider<EditTextFileCommand> editTextFileCommand,
         Provider<ZipCommand> zipCommand, 
         Provider<UnzipCommand> unzipCommand,

         Provider<DirModCommand> dirModCommandProvider, 
         Provider<WebAccessSubCommand> webAccessCommandProvider,

         Provider<LfsCommand> lfsCommand, 
         Provider<LfsExportSubCommand> lfsExportCommand,
         Provider<LfsImportSubCommand> lfsImportCommand,

         Provider<FileServerHistoryUrlBuilderHooker> urlBuilder,

         Provider<FileServerFileUploadHooker> fileServerFileUploader,

         Provider<WriteIntoFileOperator> writeIntoFileOperator, 
         RsfsUrlStreamHandler rsfsUrlStreamHandler,

         CatCommandHandlerHooker catCommandHooker
         ) {

      hookHandler.attachHooker(ExporterProviderHook.class, new ExporterProviderHook(exporterProvider));
      hookHandler.attachHooker(ImporterProviderHook.class, new ImporterProviderHook(importerProvider));
      hookHandler.attachHooker(ExportAllHook.class, exportAllFiles);
      hookHandler.attachHooker(ImportAllHook.class, importAllFiles);
      hookHandler.attachHooker(HttpImportConfigurationProviderHook.class, httpImport);

      hookHandler.attachHooker(TerminalCommandHook.class, createTextFileCommand);
      hookHandler.attachHooker(TerminalCommandHook.class, editTextFileCommand);

      hookHandler.attachHooker(TerminalCommandHook.class, zipCommand);
      hookHandler.attachHooker(TerminalCommandHook.class, unzipCommand);

      hookHandler.attachHooker(TerminalCommandHook.class, dirModCommandProvider);
      hookHandler.attachHooker(DirModCommandHook.class, webAccessCommandProvider);

      hookHandler.attachHooker(TerminalCommandHook.class, lfsCommand);
      hookHandler.attachHooker(LfsSubCommandHook.class, lfsExportCommand);
      hookHandler.attachHooker(LfsSubCommandHook.class, lfsImportCommand);

      hookHandler.attachHooker(TerminalCommandOperator.class, writeIntoFileOperator);

      hookHandler.attachHooker(HistoryUrlBuilderHook.class, urlBuilder);

      hookHandler.attachHooker(CatCommandHandlerHook.class, catCommandHooker);

      hookHandler.attachHooker(FileUploadHandlerHook.class, fileServerFileUploader);

      registerSecurityTargets(securityService);

      /*
       * register streamhandler this is done via reflection as - setting
       * URlStreamHandlerFactory, is not available from within a webapp context -
       * creating a handler class in the right package only works if the handler class
       * can be placed into the system classloaders classpath - setting the handler on
       * each instantiation of URL is infeasible http://www.unicon.net/node/776
       */
      try {
         Field handlersFields = URL.class.getDeclaredField("handlers");
         handlersFields.setAccessible(true);

         Hashtable<String, URLStreamHandler> handlers = (Hashtable<String, URLStreamHandler>) handlersFields.get(null);
         handlers.put("rsfs", rsfsUrlStreamHandler);
      } catch (NoSuchFieldException e) {
         e.printStackTrace();
      } catch (IllegalArgumentException e) {
         e.printStackTrace();
      } catch (IllegalAccessException e) {
         e.printStackTrace();
      }
   }

   /**
    * Registers all entities as security targets
    * 
    * @param securityService
    */
   private void registerSecurityTargets(SecurityService securityService) {
      /* secure folder */
      securityService.registerSecurityTarget(FileServerFolder.class);
      securityService.registerSecurityTarget(FileServerFile.class);
   }
}
