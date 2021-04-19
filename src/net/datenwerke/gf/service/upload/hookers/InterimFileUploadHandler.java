package net.datenwerke.gf.service.upload.hookers;

import java.io.IOException;

import org.json.JSONObject;

import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.service.upload.FileUploadService;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;

import com.google.inject.Inject;

public class InterimFileUploadHandler implements FileUploadHandlerHook {

	private final FileUploadService fileUploadService;
	
	@Inject
	public InterimFileUploadHandler(
		FileUploadService fileUploadService
		) {
		this.fileUploadService = fileUploadService;
	}

	@Override
	public boolean consumes(String handler) {
		return FileUploadUIModule.INTERIM_FILE_UPLOAD_HANDLER.equals(handler);
	}
	
	@Override
	public String uploadOccured(UploadedFile uploadedFile) {
		try {
			UploadResponse uploadInterimFile = fileUploadService.uploadInterimFile(uploadedFile);
			
			String json = new JSONObject().put("id", uploadInterimFile.getId()).put("name", uploadInterimFile.getName()).toString();
			
			return json;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}


}
