package net.datenwerke.gf.service.upload;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.hookers.InterimFileUploadHandler;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class FileUploadStartup {

   @Inject
   public FileUploadStartup(Provider<InterimFileUploadHandler> interimFileUploadHandler,

         HookHandlerService hookHandlerService) {

      hookHandlerService.attachHooker(FileUploadHandlerHook.class, interimFileUploadHandler);
   }
}
