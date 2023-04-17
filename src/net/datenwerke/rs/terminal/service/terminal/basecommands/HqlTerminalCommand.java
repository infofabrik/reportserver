package net.datenwerke.rs.terminal.service.terminal.basecommands;

import static java.util.stream.Collectors.toList;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.apache.commons.lang3.StringUtils;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.rs.terminal.service.terminal.TerminalService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.Argument;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.locale.TerminalMessages;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.obj.DisplayMode;

public class HqlTerminalCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "hql";

   private final Provider<EntityManager> entityManagerProvider;
   private final TerminalService terminalService;

   @Inject
   public HqlTerminalCommand(
         Provider<EntityManager> entityManagerProvider, 
         TerminalService terminalService
         ) {

      /* store objects */
      this.entityManagerProvider = entityManagerProvider;
      this.terminalService = terminalService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @CliHelpMessage(
         messageClass = TerminalMessages.class, 
         name = BASE_COMMAND, 
         description = "commandHql_description", 
         args = {
               @Argument(
                     flag = "w", 
                     description = "commandHql_wFlag",
                     mandatory = false
               ) 
         }, 
         nonOptArgs = {
               @NonOptArgument(
                     name = "query", 
                     description = "commandHql_hqlArgument",
                     mandatory = true
               )
         }
   )
   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      String arg = StringUtils.join(parser.getNonOptionArguments(), " ");

      try {
         /* execute query */
         List<?> resultList = entityManagerProvider.get().createQuery(arg).getResultList();

         /* prepare result */
         CommandResult result = new CommandResult();

         /* simple result */
         if (!resultList.isEmpty()) {
            if (resultList.get(0) instanceof Object[]) {
               List<List<String>> results = new ArrayList<>();
               for (Object objArr : resultList) {
                  final List<String> row = Arrays.stream((Object[]) objArr)
                     .map(obj -> null == obj? "null" : obj.toString())
                     .collect(toList());
                  results.add(row);
               }

               result = terminalService.convertListOfListsToCommandResult("Results", results);
            } else {
               List<String> stringResults = resultList.stream()
                     .map(obj -> null == obj ? "null" : obj.toString())
                     .collect(toList());
               result = terminalService.convertSimpleListToCommandResult("Results", stringResults);
            }
         } 

         if (parser.hasOption("w"))
            result.setDisplayMode(DisplayMode.WINDOW);

         return result;
      } catch (NoResultException e) {
         return new CommandResult("No result found");
      } catch (Exception e) {
         return new CommandResult("Could not execute query: " + e.getMessage());
      }
   }
   
   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
