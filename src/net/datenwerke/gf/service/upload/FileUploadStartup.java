package net.datenwerke.gf.service.upload;

import net.datenwerke.gf.service.upload.hookers.InterimFileUploadHandler;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class FileUploadStartup {

	@Inject
	public FileUploadStartup(
		Provider<InterimFileUploadHandler> interimFileUploadHandler,
		
		HookHandlerService hookHandlerService
		){
		
		
		hookHandlerService.attachHooker(FileUploadHandlerHook.class, interimFileUploadHandler);
	}
}
