package net.datenwerke.rs.utils.entitymerge.service.terminal.commands;

import java.util.Collection;
import java.util.List;
import java.util.Objects;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.vfs.VFSLocation;
import net.datenwerke.rs.terminal.service.terminal.vfs.VirtualFileSystemDeamon;
import net.datenwerke.rs.terminal.service.terminal.vfs.exceptions.VFSException;
import net.datenwerke.rs.utils.entitymerge.service.EntityMergeService;
import net.datenwerke.rs.utils.entitymerge.service.locale.EntityMergeMessages;
import net.datenwerke.treedb.service.treedb.AbstractNode;

public class EntityMergeCommand implements TerminalCommandHook{
   public static final String BASE_COMMAND = "entitymerge";
   private final Provider<EntityMergeService> mergeService;
   
   @Inject
   public EntityMergeCommand(Provider<EntityMergeService> mergeService) {
      this.mergeService = mergeService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = EntityMergeMessages.class, 
         name = BASE_COMMAND, 
         description = "commandEntityMerge_desc",
         nonOptArgs = {
               @NonOptArgument(
                     name = "target", 
                     description = "commandEntityMerge_targetEntity",
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "values", 
                     description = "commandEntityMerge_valueEntity",
                     mandatory = true
               )
         }
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (2 != arguments.size())
         throw new IllegalArgumentException("Exactly two arguments expected");
      final VirtualFileSystemDeamon vfs = session.getFileSystem();
      try {
         AbstractNode<?> oldNode = getTargetNode(vfs, arguments.get(0));
         AbstractNode<?> newNode = getTargetNode(vfs, arguments.get(1));
         mergeService.get().mergeEntity(oldNode, newNode);
      } catch (Exception e) {
         throw new IllegalArgumentException(e);
      }
      return new CommandResult("done");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }
   
   private AbstractNode<?> getTargetNode(VirtualFileSystemDeamon vfs, String arg) throws VFSException {
      Collection<VFSLocation> resolvedTarget = vfs.getLocation(arg).resolveWildcards(vfs);
      if (resolvedTarget.size()!=1)
         throw new IllegalArgumentException("Exactly one object expected.");
      VFSLocation target = resolvedTarget.iterator().next(); 
      AbstractNode<?> node = target.getFilesystemManager().getNodeByLocation(target);
      if(Objects.isNull(node))
         throw new IllegalArgumentException(String.format("Object '%s' is null", arg));
      return node;
   }

}
