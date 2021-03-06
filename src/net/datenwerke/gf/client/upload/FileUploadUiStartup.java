package net.datenwerke.gf.client.upload;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.client.upload.simpleform.FileUploadProvider;
import net.datenwerke.gxtdto.client.forms.simpleform.hooks.FormFieldProviderHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class FileUploadUiStartup {

   @Inject
   public FileUploadUiStartup(HookHandlerService hookHandler, Provider<FileUploadProvider> fileUploadProvider) {

      hookHandler.attachHooker(FormFieldProviderHook.class, fileUploadProvider, HookHandlerService.PRIORITY_LOW);

   }
}
