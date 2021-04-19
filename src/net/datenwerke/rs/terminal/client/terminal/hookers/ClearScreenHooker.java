package net.datenwerke.rs.terminal.client.terminal.hookers;

import net.datenwerke.rs.terminal.client.terminal.hooks.ClientCommandHook;
import net.datenwerke.rs.terminal.client.terminal.ui.TerminalWindow;

public class ClearScreenHooker implements ClientCommandHook {

	@Override
	public boolean consumes(String command) {
		return null != command && "clear".equals(command.replaceAll(" ", ""));
	}

	@Override
	public boolean execute(String command, TerminalWindow terminal) {
		terminal.clearHistory();
		return true;
	}

}
