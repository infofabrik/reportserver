package net.datenwerke.rs.scheduler.service.scheduler.terminal.commands;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.hooks.SchedulerSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.ObjectResolverDeamon;
import net.datenwerke.scheduler.service.scheduler.SchedulerHelperService;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.entities.User;

public class SchedulerReplaceUserSubCommand implements SchedulerSubCommandHook {

   public static final String BASE_COMMAND = "replaceUser";

   private final Provider<SchedulerHelperService> schedulerHelperService;

   @Inject
   public SchedulerReplaceUserSubCommand(
         Provider<SchedulerHelperService> schedulerHelperService
         ) {
      this.schedulerHelperService = schedulerHelperService;
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
   @CliHelpMessage(
         messageClass = SchedulerMessages.class, 
         name = BASE_COMMAND, 
         description = "commandScheduler_sub_replaceUser_description", 
         nonOptArgs = {
               @NonOptArgument(
                     name = "oldUser", 
                     description = "commandScheduler_sub_replaceUser_oldUser", 
                     mandatory = true
               ),
               @NonOptArgument(
                     name = "newUser", 
                     description = "commandScheduler_sub_replaceUser_newUser", 
                     mandatory = true
               )
         }
   )
   public CommandResult execute(CommandParser parser, TerminalSession session) throws TerminalException {
      List<String> arguments = parser.getNonOptionArguments();
      if (2 != arguments.size())
         throw new IllegalArgumentException("Exactly two user arguments expected");
      
      final ObjectResolverDeamon objectResolver = session.getObjectResolver();
      
      final List<Object> oldUserList = objectResolver.getObjects(arguments.get(0), Read.class);
      final List<Object> newUserList = objectResolver.getObjects(arguments.get(1), Read.class);
      if (1 != oldUserList.size())
         throw new IllegalArgumentException(
               "Exactly one user expected, but " + oldUserList.size() + " found: '" + arguments.get(0) + "'");
      if (1 != newUserList.size())
         throw new IllegalArgumentException(
               "Exactly one user expected, but " + newUserList.size() + " found: '" + arguments.get(1) + "'");
      if (!(oldUserList.get(0) instanceof User))
         throw new IllegalArgumentException("Has to be a user: " + oldUserList.get(0).getClass());
      if (!(newUserList.get(0) instanceof User))
         throw new IllegalArgumentException("Has to be a user: " + newUserList.get(0).getClass());
      
      final User oldUser = (User) oldUserList.get(0);
      final User newUser = (User) newUserList.get(0);
      try {
         schedulerHelperService.get().replaceSchedulerUser(oldUser, newUser);
      } catch (Exception e) {
         throw new TerminalException(ExceptionUtils.getRootCauseMessage(e), e);
      }
      
      return new CommandResult("User '" + oldUser + "' changed to '" + newUser + "' in all active scheduler jobs");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
