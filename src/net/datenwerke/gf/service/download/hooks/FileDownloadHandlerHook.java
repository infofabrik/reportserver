package net.datenwerke.gf.service.download.hooks;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface FileDownloadHandlerHook extends Hook {

   public boolean consumes(String handler);

   public void processDownload(String id, String handler, Map<String, String> metadata, HttpServletResponse response)
         throws IOException;

}
