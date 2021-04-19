package net.datenwerke.gf.service.upload;

import java.io.IOException;
import java.util.List;

import net.datenwerke.gf.client.fileselection.dto.SelectedFileWrapper;
import net.datenwerke.gf.client.upload.dto.UploadResponse;




public interface FileUploadService {

	public String uploadOccured(UploadedFile uploadedFile);

	public String extractContentTypeFromHtml5Upload(String data);

	byte[] extractContentFromHtml5Upload(String data);

	UploadResponse uploadInterimFile(UploadedFile file) throws IOException;
	
	public List<UploadedFile> extractUploadedFilesFrom(List<SelectedFileWrapper> selectedFiles);

	List<UploadedFile> extractUploadedFilesFrom(List<SelectedFileWrapper> data,
			boolean remove);

	UploadedFile extractUploadedFileFrom(SelectedFileWrapper file);

	UploadedFile extractUploadedFileFrom(SelectedFileWrapper file,
			boolean remove);
	
}
