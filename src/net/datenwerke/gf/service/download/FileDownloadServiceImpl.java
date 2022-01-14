package net.datenwerke.gf.service.download;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;

import net.datenwerke.gf.service.download.hooks.FileDownloadHandlerHook;
import net.datenwerke.hookhandler.shared.hookhandler.HookHandlerService;

public class FileDownloadServiceImpl implements FileDownloadService {

   private final HookHandlerService hookHandlerService;

   @Inject
   public FileDownloadServiceImpl(HookHandlerService hookHandlerService) {
      this.hookHandlerService = hookHandlerService;
   }

   @Override
   public void processDownload(String id, String handler, Map<String, String> metadata, HttpServletResponse response) {
      for (FileDownloadHandlerHook hooker : hookHandlerService.getHookers(FileDownloadHandlerHook.class)) {
         if (hooker.consumes(handler)) {
            try {
               hooker.processDownload(id, handler, metadata, response);
            } catch (IOException e) {
               throw new IllegalStateException(e);
            }
            break;
         }
      }
   }

}
