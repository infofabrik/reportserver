package net.datenwerke.rs.terminal.service.terminal.vfs.commands;

import java.util.Iterator;
import java.util.List;

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
import net.datenwerke.rs.utils.string.Emoji;

public class VfsCommandRm implements TerminalCommandHook {

   public static final String BASE_COMMAND = "rm";

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(messageClass = VfsMessages.class, name = BASE_COMMAND, description = "commandRm_description", args = {
         @Argument(flag = "r", description = "commandRm_rArgument"),
         @Argument(flag = "f", description = "commandRm_fArgument") }, nonOptArgs = {
               @NonOptArgument(name = "dir", description = "commandRm_dirArgument") })
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      VirtualFileSystemDeamon vfs = session.getFileSystem();

      try {
         List<String> arguments = parser.getNonOptionArguments();
         if (arguments.size() < 1)
            throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji());

         boolean recursive = parser.hasOption("r", "r?f?");
         boolean force = parser.hasOption("f", "r?f?");

         for (String arg : arguments) {
            VFSLocation locations = vfs.getLocation(arg);

            if (locations.isVirtualLocation())
               throw new IllegalArgumentException(Emoji.exceptionEmoji().getEmoji(" ") + "location is virtual");

            Iterator<VFSLocation> locationIt = locations.resolveWildcards(vfs).iterator();
            while (locationIt.hasNext())
               vfs.remove(locationIt.next(), recursive, force);
         }

         CommandResult result = new CommandResult();
         result.setCommitTransaction(true);
         return result;
      } catch (VFSException e) {
         return new CommandResult(Emoji.exceptionEmoji().getEmoji(" ") + e.getMessage());
      }
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
