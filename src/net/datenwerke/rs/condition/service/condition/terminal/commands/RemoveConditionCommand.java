package net.datenwerke.rs.condition.service.condition.terminal.commands;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.NoResultException;

import com.google.inject.Inject;

import net.datenwerke.rs.condition.service.condition.ConditionService;
import net.datenwerke.rs.condition.service.condition.entity.ReportCondition;
import net.datenwerke.rs.condition.service.condition.locale.ConditionMessages;
import net.datenwerke.rs.condition.service.condition.terminal.commands.hooks.ConditionSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.NonOptArgument;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Read;

public class RemoveConditionCommand implements ConditionSubCommandHook {

   public static final String BASE_COMMAND = "remove";

   private final ConditionService conditionService;

   @Inject
   public RemoveConditionCommand(ConditionService conditionService) {

      /* store objects */
      this.conditionService = conditionService;
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
   @CliHelpMessage(messageClass = ConditionMessages.class, name = BASE_COMMAND, description = "commandRcondition_sub_remove_desc", nonOptArgs = {
         @NonOptArgument(name = "condition", description = "commandRcondition_sub_remove_par_condition", mandatory = true) })
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      List<String> args = parser.getNonOptionArguments();
      if (args.size() != 1)
         throw new IllegalArgumentException("Expect one argument");

      String query = args.get(0);

      Collection<Object> resolvedObjects = session.getObjectResolver().getObjects(query, Read.class);
      if (null != resolvedObjects && resolvedObjects.size() != 0) {
         List<?> searchResults;
         if (resolvedObjects instanceof List)
            searchResults = (List<?>) resolvedObjects;
         else
            searchResults = new ArrayList(resolvedObjects);

         if (null != searchResults && searchResults.size() != 0) {
            if (searchResults.size() > 1)
               return new CommandResult("The object resolver query returned more than one result: " + query);

            Object result = searchResults.get(0);
            if (!(result instanceof ReportCondition))
               return new CommandResult("Could not find condition with object resolver query: " + query);

            ReportCondition cond = (ReportCondition) searchResults.get(0);
            conditionService.remove(cond);

            return new CommandResult("Condition removed.");
         }

      }

      Long id = null;
      try {
         id = Long.valueOf(args.get(0));
         ReportCondition cond = conditionService.getReportConditionById(id);
         if (null == cond)
            return new CommandResult("No condition with id " + id + " found.");
         conditionService.remove(cond);
      } catch (NumberFormatException e) {
         return new CommandResult("No condition found: " + args.get(0));
      } catch (NoResultException e) {
         return new CommandResult("No condition with id " + id + " found.");
      }

      return new CommandResult("Condition removed.");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }

}
