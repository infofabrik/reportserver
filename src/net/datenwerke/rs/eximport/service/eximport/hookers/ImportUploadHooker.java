package net.datenwerke.rs.eximport.service.eximport.hookers;

import java.io.IOException;
import java.util.Map;
import java.util.zip.ZipEntry;

import net.datenwerke.eximport.exceptions.InvalidImportDocumentException;
import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.eximport.client.eximport.RsExImportUiModule;
import net.datenwerke.rs.eximport.service.eximport.im.http.HttpImportService;
import net.datenwerke.rs.eximport.service.genrights.ExportSecurityTarget;
import net.datenwerke.rs.utils.zip.ZipExtractionConfig;
import net.datenwerke.rs.utils.zip.ZipUtilsService;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Execute;
import net.datenwerke.rs.eximport.service.eximport.locale.ExImportMessages;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class ImportUploadHooker implements FileUploadHandlerHook {
	
	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<HttpImportService> httpImportServiceProvider;
	private final Provider<ZipUtilsService> zipUtilsProvider;

	@Inject
	public ImportUploadHooker(
			Provider<SecurityService> securityServiceProvider, 
			Provider<HttpImportService> httpImportServiceProvider,
			Provider<ZipUtilsService> zipUtilsProvider
			
	) {
		this.securityServiceProvider = securityServiceProvider;
		this.httpImportServiceProvider  = httpImportServiceProvider;
		this.zipUtilsProvider  = zipUtilsProvider;
	}
	
	@Override
	public boolean consumes(String handler) {
		return RsExImportUiModule.UPLOAD_HANDLER_ID.equals(handler);
	}

	@Override
	public String uploadOccured(UploadedFile uploadedFile) {
		Map<String, String> metadataMap = uploadedFile.getMetadata();
		
		String fileName = uploadedFile.getFileName();
		byte[] fileContents = uploadedFile.getFileBytes();
		
		if(null == fileName || null == fileContents || fileContents.length == 0 || fileName.isEmpty())
			throw new IllegalArgumentException(ExImportMessages.INSTANCE.fileSeemsEmpty());
		
		if(! fileName.endsWith(".xml") && ! fileName.endsWith(".zip"))
			throw new IllegalArgumentException(ExImportMessages.INSTANCE.expectedXmlOrZip());

		securityServiceProvider.get().assertRights(ExportSecurityTarget.class, Execute.class);
		
		final HttpImportService importService = httpImportServiceProvider.get();
		importService.createNewConfig();
		
		if(fileName.endsWith(".xml")){
			try{
				importService.setData(new String(fileContents));
			} catch (InvalidImportDocumentException e) {
				throw new IllegalArgumentException(ExImportMessages.INSTANCE.parseException(e.getMessage()));
			} catch(RuntimeException e){
				throw new IllegalArgumentException(ExImportMessages.INSTANCE.parseException(e.getMessage()));
			}
		} else { /* zip */
			ZipUtilsService zipUtils = zipUtilsProvider.get();
			try {
				zipUtils.extractZip(fileContents, new ZipExtractionConfig() {
					boolean processed = false;
					@Override
					public void processContent(ZipEntry entry, byte[] content){
						if(processed)
							throw new IllegalArgumentException(ExImportMessages.INSTANCE.zipShouldContainOnlyOne());
						try{
							importService.setData(new String(content));
						} catch (InvalidImportDocumentException e) {
							throw new IllegalStateException(e);
						}
						
						processed = true;
					}
				});
			} catch (IOException e) {
				throw new IllegalStateException(e);
			}
		}
		
		return null;
	}


}
