package net.datenwerke.rs.terminal.client.terminal.hooks;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;
import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

public interface CommandResultProcessorHook extends Hook {

	void process(CommandResultDto result);

}
