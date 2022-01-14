package net.datenwerke.gf.client.upload.simpleform;

import java.util.List;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gf.client.upload.dto.UploadProperties;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;

public interface SFFCFileUpload extends SimpleFormFieldConfiguration {

   UploadProperties getProperties();

   boolean enableHTML5();

   void filesUploaded(List<FileToUpload> list);

}
