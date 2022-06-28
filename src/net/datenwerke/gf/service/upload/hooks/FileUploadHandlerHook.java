package net.datenwerke.gf.service.upload.hooks;

import java.util.Map;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.hookhandler.shared.hookhandler.interfaces.Hook;

public interface FileUploadHandlerHook extends Hook {

   public String uploadOccured(UploadedFile uploadedFile, Map<String,String> context);

   public boolean consumes(String handler);
}
