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

public class VfsCommandMv implements TerminalCommandHook {

   public static final String BASE_COMMAND = "mv";

   private final SecurityService securityService;

   @Inject
   public VfsCommandMv(SecurityService securityService) {

      /* store objects */
      this.securityService = securityService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(messageClass = VfsMessages.class, name = BASE_COMMAND, description = "commandMv_description", nonOptArgs = {
         @NonOptArgument(name = "sourcefile", description = "commandMv_source"),
         @NonOptArgument(name = "targetfile", description = "commandMv_target") })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      VirtualFileSystemDeamon vfs = session.getFileSystem();

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

            if (target.exists() && !target.isFolder()) {
               if (!(sourceObject instanceof ReportVariant && targetObject instanceof Report
                     && !(targetObject instanceof ReportVariant)))
                  throw new IllegalArgumentException("Target file already exists.");
            }

            if (!target.exists()) {
               targetFileName = target.getPathHelper().getLastPathway();
               target = target.getParentLocation();
            }

            if (target.isFolder() && !target.exists())
               throw new IllegalArgumentException("Target folder does not exist.");
            if (target.isVirtualLocation())
               throw new IllegalArgumentException("Target is virtual location");

            if (sourceObject instanceof SecurityService)
               securityService.assertRights((SecurityTarget) sourceObject, Read.class, Write.class);
            if (targetObject instanceof SecurityService)
               securityService.assertRights((SecurityTarget) targetObject, Read.class, Write.class);

            /* perform move */
            List<VFSLocation> movedFileLocations = target.getFilesystemManager().moveFilesTo(sourceLocation, target);

            if (null != targetFileName && movedFileLocations.size() != 1)
               throw new IllegalArgumentException("Cannot rename multiple files.");

            if (null != targetFileName) {
               VFSLocation copiedFile = movedFileLocations.get(0);
               copiedFile.rename(targetFileName);
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
