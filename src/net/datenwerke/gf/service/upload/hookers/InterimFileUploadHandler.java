package net.datenwerke.gf.service.upload.hookers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.inject.Inject;

import net.datenwerke.gf.client.upload.FileUploadUIModule;
import net.datenwerke.gf.client.upload.dto.UploadResponse;
import net.datenwerke.gf.service.upload.FileUploadService;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;

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
			
			ObjectMapper mapper = new ObjectMapper();
			ObjectNode node = mapper.createObjectNode();
			node.put("id", uploadInterimFile.getId());
			node.put("name", uploadInterimFile.getName());
			
			String json = mapper.writeValueAsString(node);
			
			return json;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}


}
