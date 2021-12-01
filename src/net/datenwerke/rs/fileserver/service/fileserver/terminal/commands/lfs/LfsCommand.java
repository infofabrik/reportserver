package net.datenwerke.rs.fileserver.service.fileserver.terminal.commands.lfs;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommand;
import net.datenwerke.rs.terminal.service.terminal.hooks.SubCommandContainerImpl;

public class LfsCommand extends SubCommandContainerImpl {
	
	public static final String BASE_COMMAND = "lfs";
	
	private HookHandlerService hookHandler;
	
	@Inject
	public LfsCommand(HookHandlerService hookHandler) {
		this.hookHandler = hookHandler;
	}

	@Override
	public String getBaseCommand() {
		return BASE_COMMAND;
	}

	@Override
	public List<SubCommand> getSubCommands() {
		List<LfsSubCommandHook> list =  hookHandler.getHookers(LfsSubCommandHook.class);
		return new ArrayList<SubCommand>(list);
	}

}
