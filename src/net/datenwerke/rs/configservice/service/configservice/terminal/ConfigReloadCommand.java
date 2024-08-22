package net.datenwerke.rs.configservice.service.configservice.terminal;

import com.google.inject.Inject;

import net.datenwerke.rs.configservice.service.configservice.ConfigService;
import net.datenwerke.rs.configservice.service.configservice.locale.ConfigMessages;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.helpmessenger.annotations.CliHelpMessage;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.rs.utils.string.Emoji;

public class ConfigReloadCommand implements ConfigSubCommandHook {

   public static final String BASE_COMMAND = "reload";

   private final ConfigService configService;

   @Inject
   public ConfigReloadCommand(ConfigService configService) {
      this.configService = configService;
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
   @CliHelpMessage(messageClass = ConfigMessages.class, name = BASE_COMMAND, description = "commandConfig_sub_reload_description")
   public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
      configService.clearCache();
      
      Emoji[] emojis = new Emoji[] {Emoji.CLINKING_BEER_MUGS, Emoji.BEER_MUG, Emoji.ALIEN, Emoji.BOMB, Emoji.ALEMBIC};
      
      return new CommandResult(emojis[(int) (Math.random() * emojis.length)].getEmoji(" ") + ConfigMessages.INSTANCE.configReloaded());
   }

   @Override
   public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper, TerminalSession session) {
   }
}
