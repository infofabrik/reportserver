package net.datenwerke.rs.scheduler.service.scheduler.terminal.commands;

import java.util.List;

import org.apache.commons.lang3.exception.ExceptionUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.scheduler.service.scheduler.locale.SchedulerMessages;
import net.datenwerke.rs.scheduler.service.scheduler.terminal.hooks.SchedulerSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.exceptions.TerminalException;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.scheduler.service.scheduler.SchedulerHelperService;
import net.datenwerke.security.service.security.rights.Read;
import net.datenwerke.security.service.usermanager.entities.User;

public class SchedulerReplaceUserSubCommand implements SchedulerSubCommandHook {

   public static final String BASE_COMMAND = "replaceUser";

   private final Provider<SchedulerHelperService> schedulerHelperServiceProvider;
   private final Provider<TerminalService> terminalServiceProvider;

   @Inject
   public SchedulerReplaceUserSubCommand(
         Provider<SchedulerHelperService> schedulerHelperServiceProvider,
         Provider<TerminalService> terminalServiceProvider
         ) {
      this.schedulerHelperServiceProvider = schedulerHelperServiceProvider;
      this.terminalServiceProvider = terminalServiceProvider;
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
      
      final TerminalService terminalService = terminalServiceProvider.get();
      final User oldUser = terminalService.getSingleObjectOfTypeByQuery(User.class, arguments.get(0), session, Read.class);
      final User newUser = terminalService.getSingleObjectOfTypeByQuery(User.class, arguments.get(1), session, Read.class);
      try {
         schedulerHelperServiceProvider.get().replaceSchedulerUser(oldUser, newUser);
      } catch (Exception e) {
         throw new TerminalException(ExceptionUtils.getRootCauseMessage(e), e);
      }
      
      return new CommandResult("User '" + oldUser + "' changed to '" + newUser + "' in all active scheduler jobs");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
