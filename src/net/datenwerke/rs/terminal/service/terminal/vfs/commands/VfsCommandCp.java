package net.datenwerke.rs.terminal.service.terminal.vfs.commands;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.reportmanager.entities.reports.Report;
import net.datenwerke.rs.core.service.reportmanager.interfaces.ReportVariant;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.terminal.service.terminal.vfs.locale.VfsMessages;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.SecurityTarget;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;

public class VfsCommandCp implements TerminalCommandHook {

   public static final String BASE_COMMAND = "cp";

   private final SecurityService securityService;

   @Inject
   public VfsCommandCp(SecurityService securityService) {

      /* store objects */
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(messageClass = VfsMessages.class, name = BASE_COMMAND, description = "commandCp_description", args = {
         @Argument(flag = "r", description = "commandCp_rFlag") }, nonOptArgs = {
               @NonOptArgument(name = "sourcefile", description = "commandCp_source"),
               @NonOptArgument(name = "targetfile", description = "commandCp_target") })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      VirtualFileSystemDeamon vfs = session.getFileSystem();

      VFSLocation workingDirectory = vfs.getCurrentLocation();

      List<String> arguments = parser.getNonOptionArguments();
      if (arguments.size() != 2)
         throw new IllegalArgumentException();

      String sourceStr = arguments.get(0);
      String targetStr = arguments.get(1);

      try {
         /* load source */
         VFSLocation source = vfs.getLocation(sourceStr);
         if (source.isVirtualLocation())
            throw new IllegalArgumentException("Source is virtual location");
         if (!source.exists() && !source.isWildcardLocation())
            throw new IllegalArgumentException("Could not find " + sourceStr);

         String targetFileName = null;

         VFSLocation target = vfs.getLocation(targetStr);

         Collection<VFSLocation> targetLocations = target.resolveWildcards(vfs);
         if (targetLocations.size() != 1)
            throw new IllegalArgumentException("Target must be resolved to exactly one element.");

         target = targetLocations.iterator().next();

         Iterator<VFSLocation> sourceLocations = source.resolveWildcards(vfs).iterator();
         while (sourceLocations.hasNext()) {

            VFSLocation sourceLocation = sourceLocations.next();

            /* load objects */
            Object sourceObject = sourceLocation.getObject();
            Object targetObject = target.getObject();

            if (target.exists() && !target.isFolder())
               if (!(sourceObject instanceof ReportVariant && targetObject instanceof Report
                     && !(targetObject instanceof ReportVariant)))
                  throw new IllegalArgumentException("Target file already exists.");

            if (!target.exists()) {
               targetFileName = target.getPathHelper().getLastPathway();
               target = target.getParentLocation();
            }

            if (target.isFolder() && !target.exists())
               throw new IllegalArgumentException("Target folder does not exist.");
            if (target.isVirtualLocation())
               throw new IllegalArgumentException("Target is virtual location");

            if (sourceObject instanceof SecurityService)
               securityService.assertRights((SecurityTarget) sourceObject, Read.class);
            if (targetObject instanceof SecurityService)
               securityService.assertRights((SecurityTarget) targetObject, Read.class, Write.class);

            boolean deepCopy = parser.hasOption("r");

            /* perform copy */
            List<VFSLocation> copiedFileLocations = target.getFilesystemManager().copyFilesTo(sourceLocation, target,
                  deepCopy);

            if (null != targetFileName && copiedFileLocations.size() != 1)
               throw new IllegalArgumentException("Cannot copy multiple files into one file.");

            if (null != targetFileName) {
               VFSLocation copiedFile = copiedFileLocations.get(0);
               copiedFile.rename(targetFileName);
            } else {
               if (target.equals(workingDirectory)) {
                  for (VFSLocation loc : copiedFileLocations) {
                     String name = loc.getFilesystemManager().getNameFor(loc);
                     loc.rename(name + " (copy)");
                  }
               }
            }
         }

         return new CommandResult();
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
}
