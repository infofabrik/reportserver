package net.datenwerke.rs.terminal.service.terminal.hooks;

import java.io.Serializable;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.obj.AutocompleteResult;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface TerminalSessionHijackHook extends Hook, Serializable {

   AutocompleteResult autocomplete(TerminalSession terminalSession, String command, int cursorPosition,
         boolean forceResult);

   CommandResult execute(TerminalSession terminalSession, String command);

   boolean consumes(TerminalSession terminalSession, CommandResult result);

   CommandResult adapt(TerminalSession terminalSession, CommandResult result);

   CommandResult ctrlC(TerminalSession terminalSession);

   boolean wantsToContinue(TerminalSession terminalSession, String command);

}
