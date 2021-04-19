package net.datenwerke.rs.terminal.service.terminal.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;

public interface TerminalSessionDeamonHook extends Hook {

	void init(TerminalSession terminalSession);

	void autocomplete(AutocompleteHelper autoHelper);

}
