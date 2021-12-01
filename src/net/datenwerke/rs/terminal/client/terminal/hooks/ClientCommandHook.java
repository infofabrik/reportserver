package net.datenwerke.rs.terminal.client.terminal.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.client.terminal.ui.TerminalWindow;

public interface ClientCommandHook extends Hook {

	public boolean consumes(String command);
	
	public boolean execute(String command, TerminalWindow terminal);
}
