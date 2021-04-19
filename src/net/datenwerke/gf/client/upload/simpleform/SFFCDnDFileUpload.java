package net.datenwerke.gf.client.upload.simpleform;

import java.util.List;

import net.datenwerke.gf.client.upload.dto.FileToUpload;
import net.datenwerke.gxtdto.client.forms.simpleform.SimpleFormFieldConfiguration;


public interface SFFCDnDFileUpload extends SimpleFormFieldConfiguration {

	void filesUploaded(List<FileToUpload> list);

}
