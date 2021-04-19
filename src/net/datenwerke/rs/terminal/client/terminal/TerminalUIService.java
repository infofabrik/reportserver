package net.datenwerke.rs.terminal.client.terminal;

import net.datenwerke.rs.terminal.client.terminal.dto.CommandResultDto;

public interface TerminalUIService {

	public void initTerminal();

	void displayTerminalWindow();

	boolean isInitialized();

	public void processExternalResult(CommandResultDto result);

	public void displayResult(CommandResultDto result);
}
