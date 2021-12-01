package net.datenwerke.rs.terminal.service.terminal.basecommands.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface CatCommandHandlerHook extends Hook {

   boolean consumes(Object object, CommandParser parser);

   CommandResult cat(Object object, CommandParser parser);

}
