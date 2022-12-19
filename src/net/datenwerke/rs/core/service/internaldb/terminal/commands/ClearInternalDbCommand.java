package net.datenwerke.rs.core.service.internaldb.terminal.commands;

import com.google.inject.Inject;

import net.datenwerke.rs.core.service.internaldb.TempTableService;
import net.datenwerke.rs.resultcache.ResultCacheService;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public class ClearInternalDbCommand implements TerminalCommandHook {

   public static final String BASE_COMMAND = "clearInternalDbCache";

   private TempTableService tempTableService;
   private ResultCacheService resultCacheService;

   @Inject
   public ClearInternalDbCommand(TempTableService tempTableService, ResultCacheService resultCacheService) {
      this.tempTableService = tempTableService;
      this.resultCacheService = resultCacheService;
   }

   @Override
   public boolean consumes(CommandParser parser, TerminalSession session) {
      return BASE_COMMAND.equals(parser.getBaseCommand());
   }

   @Override
   public CommandResult execute(CommandParser parser, TerminalSession session) {
      tempTableService.shutdown();
      resultCacheService.flush();
      return new CommandResult("cache cleared");
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
      autocompleteHelper.autocompleteBaseCommand(BASE_COMMAND);
   }

}
