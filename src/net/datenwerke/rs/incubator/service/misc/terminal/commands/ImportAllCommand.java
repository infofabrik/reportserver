package net.datenwerke.rs.incubator.service.misc.terminal.commands;

import net.datenwerke.eximport.ExportDataProviderImpl;
import net.datenwerke.eximport.ImportService;
import net.datenwerke.eximport.im.ImportConfig;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.hooks.ImportAllHook;
import net.datenwerke.rs.eximport.service.eximport.terminal.hooks.ImportSubCommandHook;
import net.datenwerke.rs.fileserver.service.fileserver.entities.FileServerFile;
import net.datenwerke.rs.terminal.service.terminal.TerminalSession;
import net.datenwerke.rs.terminal.service.terminal.helpers.AutocompleteHelper;
import net.datenwerke.rs.terminal.service.terminal.helpers.CommandParser;
import net.datenwerke.rs.terminal.service.terminal.obj.CommandResult;
import net.datenwerke.rs.terminal.service.terminal.objresolver.exceptions.ObjectResolverException;
import net.datenwerke.security.service.security.rights.Read;

import com.google.inject.Inject;

public class ImportAllCommand implements ImportSubCommandHook {

	public static final String BASE_COMMAND = "all";
	
	private final ImportService importService;
	private final HookHandlerService hookHandler;

	@Inject
	public ImportAllCommand(
		ImportService importService,
		HookHandlerService hookHandler
		){
		
		/* store objects */
		this.importService = importService;
		this.hookHandler = hookHandler;
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
	public CommandResult execute(CommandParser parser, TerminalSession session) throws ObjectResolverException {
		String args = parser.getArgumentString();
		final FileServerFile file = (FileServerFile) session.getObjectResolver().getObject(args.trim(), Read.class);
		
		ImportConfig config;
		try {
			config = new ImportConfig(new ExportDataProviderImpl(file.getData()));
		} catch(Exception e){
			throw new IllegalArgumentException(e);
		}
		
		for(ImportAllHook exporter : hookHandler.getHookers(ImportAllHook.class)){
			exporter.configure(config);
		}
		
		importService.importData(config);
		
		return new CommandResult();
	}

	@Override
	public void addAutoCompletEntries(AutocompleteHelper autocompleteHelper,
			TerminalSession session) {
		
	}


}
