package net.datenwerke.rs.pkg.service.pkg.terminal.commands;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Collection;

import javax.inject.Inject;
import javax.inject.Provider;

import net.datenwerke.rs.fileserver.service.fileserver.FileServerService;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFolder;
import net.datenwerke.rs.pkg.service.pkg.PackagedScriptHelperService;
import net.datenwerke.rs.pkg.service.pkg.locale.PkgMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.security.service.security.rights.Read;

public class PkgInstallSubCommand implements SubCommand {

   private static final String BASE_COMMAND = "install";
   private Provider<PackagedScriptHelperService> packageScriptHelper;
   private FileServerService fileServerService;

   @Inject
   public PkgInstallSubCommand(Provider<PackagedScriptHelperService> packageScriptHelper,
         FileServerService fileServerService) {
      this.packageScriptHelper = packageScriptHelper;
      this.fileServerService = fileServerService;

   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(
         messageClass = PkgMessages.class, 
         name = BASE_COMMAND, 
         description = "pkg_sub_install_description",
         args = {
               @Argument(
                     flag = "d", 
                     hasValue = false, 
                     valueName = "local file system", 
                     description = "commandPkg_sub_install_flagD", 
                     mandatory = false
               )
         },
         nonOptArgs = { 
               @NonOptArgument(
                     name = "packageName",
                     mandatory = true,
                     description = "commandPkg_sub_install_packageName"
               )
         }
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      String packageName = parser.getNonOptionArguments().get(0);
      PackagedScriptHelperService packagedScriptHelper = packageScriptHelper.get();
      if (parser.hasOption("d")) {
         File packageFile = new File(packagedScriptHelper.getPackageDirectory(), packageName);
         if (!packageFile.exists()) {
            throw new IllegalArgumentException("no such package " + packageName);
         }
         FileServerFolder uploadPackage = null;
         try {
            uploadPackage = packagedScriptHelper.extractPackageTemporarily(new FileInputStream(packageFile));
            String result = packagedScriptHelper.executePackage(uploadPackage,
                  getScriptArguments(parser.getArguments()));

            CommandResult cr = new CommandResult(null == result ? "ok" : result);
            return cr;
         } catch (Exception e) {
            throw new IllegalArgumentException(e);
         } finally {
            fileServerService.forceRemove(uploadPackage);
         }
      } else {
         Collection<Object> objects = session.getObjectResolver().getObjects(packageName, Read.class);
         if (objects.size() != 1)
            throw new IllegalArgumentException("expression must resolve to exactly one object.");

         Object pkgObj = objects.iterator().next();
         if (pkgObj instanceof FileServerFile) {
            FileServerFile pkgFile = (FileServerFile) pkgObj;
            if (!packagedScriptHelper.validateZip(new ByteArrayInputStream(pkgFile.getData()), false)) {
               throw new IllegalArgumentException("not a valid package");
            }
            FileServerFolder uploadPackage = null;
            try {
               uploadPackage = packagedScriptHelper
                     .extractPackageTemporarily(new ByteArrayInputStream(pkgFile.getData()));

               String result = packagedScriptHelper.executePackage(uploadPackage,
                     getScriptArguments(parser.getArguments()));

               CommandResult cr = new CommandResult(null == result ? "ok" : result);
               return cr;
            } catch (Exception e) {
               throw new IllegalArgumentException(e);
            } finally {
               fileServerService.forceRemove(uploadPackage);
            }

         } else {
            throw new IllegalArgumentException("no such package");
         }

      }

   }

   private String getScriptArguments(String[] opts) {
      boolean seenEndOfOptions = false;

      for (int i = 0; i < opts.length; i++) {
         if (seenEndOfOptions)
            return opts[i];

         if ("--".equals(opts[i]))
            seenEndOfOptions = true;

      }

      return "";
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      if (autocompleteHelper.getParser().hasOption("d"))
         packageScriptHelper.get().listPackages().stream()
               .forEach(f -> autocompleteHelper.addAutocompleteNamesForToken(4, f.getName()));
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

}
