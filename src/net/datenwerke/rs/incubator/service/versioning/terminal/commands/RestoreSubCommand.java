package net.datenwerke.rs.incubator.service.versioning.terminal.commands;

import java.util.List;

import com.google.inject.Inject;

import net.datenwerke.rs.incubator.service.versioning.VersioningService;
import net.datenwerke.rs.incubator.service.versioning.hooks.RevSubCommandHook;
import net.datenwerke.rs.incubator.service.versioning.locale.VersioningMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.utils.entitycloner.EntityClonerService;
import net.datenwerke.rs.utils.jpa.EntityUtils;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class RestoreSubCommand implements RevSubCommandHook {

   public static final String BASE_COMMAND = "restore";

   private final VersioningService versioningService;
   private final SecurityService securityService;
   private final EntityClonerService entityClonerService;
   private final EntityUtils entityUtils;

   @Inject
   public RestoreSubCommand(VersioningService versioningService, SecurityService securityService,
         EntityClonerService entityClonerService, EntityUtils entityUtils

   ) {

      this.versioningService = versioningService;
      this.securityService = securityService;
      this.entityClonerService = entityClonerService;
      this.entityUtils = entityUtils;
   }

   @Override
   public String getBaseCommand() {
      return BASE_COMMAND;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   @CliHelpMessage(messageClass = VersioningMessages.class, name = BASE_COMMAND, description = "commandRev_sub_restore_description", nonOptArgs = {
         @NonOptArgument(name = "entity", description = "commandRev_sub_restore_arg1", mandatory = true),
         @NonOptArgument(name = "revision", description = "commandRev_sub_restore_arg2", mandatory = true),
         @NonOptArgument(name = "targetLocation", description = "commandRev_sub_restore_arg3", mandatory = true) })
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (arguments.size() != 3)
         throw new IllegalArgumentException();

      String locationStr = arguments.get(0);
      String[] parts = locationStr.split(":");
      String entityName = parts[1 - (parts.length == 2 ? 1 : 0)];
      String id = parts[2 - (parts.length == 2 ? 1 : 0)];

      Class<?> type = entityUtils.getEntityBySimpleName(entityName);
      Number revision = Integer.valueOf(arguments.get(1));
      String targetStr = arguments.get(2);

      try {
         VirtualFileSystemDeamon vfs = session.getFileSystem();
         VFSLocation target = vfs.getLocation(targetStr);
         if (!target.isFolder())
            throw new IllegalArgumentException("target is not a folder");

         Object targetFolder = target.getObject();
         if (targetFolder instanceof SecurityService)
            securityService.assertRights(targetFolder, Read.class, Write.class);

         Object objectAtRevision = versioningService.getAtRevision(type, (long) Long.valueOf(id), revision);

         CommandResult cr = new CommandResult();

         if (targetFolder instanceof AbstractNode) {
            AbstractNode node = (AbstractNode) targetFolder;

            Object clonedEntity = entityClonerService.cloneEntity(objectAtRevision);

            if (clonedEntity instanceof AbstractNode) {
               node.addChild((AbstractNode) clonedEntity);
               cr.addResultLine("Restored.");
            }

            target.getFilesystemManager().getTreeDBManagerProvider().get().merge(node);
         }

         return cr;
      } catch (VFSException e) {
         throw new IllegalArgumentException(e);
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
