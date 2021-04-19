package net.datenwerke.rs.eximport.service;

import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;
import net.datenwerke.rs.eximport.service.eximport.hookers.ImportUploadHooker;
import net.datenwerke.rs.eximport.service.eximport.terminal.commands.ExportAllCommand;
import net.datenwerke.rs.eximport.service.eximport.terminal.commands.ExportCommand;
import net.datenwerke.rs.eximport.service.eximport.terminal.commands.ImportCommand;
import net.datenwerke.rs.eximport.service.eximport.terminal.hooks.ExportSubCommandHook;
import net.datenwerke.rs.terminal.service.terminal.hooks.TerminalCommandHook;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class RsExImportStartup {

	@Inject
	public RsExImportStartup(
		HookHandlerService hookHandler,
		
		Provider<ExportCommand> exportCommand,
		Provider<ImportCommand> importCommand,
		
		Provider<ExportAllCommand> exportAllCommand,
		
		Provider<ImportUploadHooker> importUploader
		){
		
		hookHandler.attachHooker(TerminalCommandHook.class, exportCommand);
		hookHandler.attachHooker(TerminalCommandHook.class, importCommand);
		
		hookHandler.attachHooker(ExportSubCommandHook.class, exportAllCommand);
		
		hookHandler.attachHooker(FileUploadHandlerHook.class, importUploader);
	}
}
