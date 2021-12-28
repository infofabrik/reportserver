package net.datenwerke.rs.terminal.service.terminal.hooks;

import java.io.Serializable;

import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;

public interface InteractiveCommandHook extends TerminalCommandHook, Serializable {

   public boolean isKeepInteractiveSession();

   public CommandResult executeSubsequent(String command);

   public CommandResult ctrlC();
}
