package net.datenwerke.rs.ftp.service.ftp.hooker;

import java.util.Map;

import com.google.inject.Inject;
import com.google.inject.Provider;

import net.datenwerke.gf.service.upload.UploadedFile;
import net.datenwerke.gf.service.upload.hooks.FileUploadHandlerHook;
import net.datenwerke.rs.core.service.datasinkmanager.DatasinkTreeService;
import net.datenwerke.rs.core.service.datasinkmanager.entities.AbstractDatasinkManagerNode;
import net.datenwerke.rs.ftp.client.ftp.FtpUiModule;
import net.datenwerke.rs.ftp.service.ftp.definitions.SftpDatasink;
import net.datenwerke.security.service.security.SecurityService;
import net.datenwerke.security.service.security.rights.Write;
import net.datenwerke.security.service.treedb.actions.UpdateAction;

public class SftpPrivateKeyUploadHooker implements FileUploadHandlerHook {
	
	private final Provider<SecurityService> securityServiceProvider;
	private final Provider<DatasinkTreeService> datasinkServiceProvider;

	@Inject
	public SftpPrivateKeyUploadHooker(
			Provider<SecurityService> securityServiceProvider, 
			Provider<DatasinkTreeService> datasinkServiceProvider
	) {
		this.securityServiceProvider = securityServiceProvider;
		this.datasinkServiceProvider = datasinkServiceProvider;
	}
	
	@Override
	public boolean consumes(String handler) {
		return FtpUiModule.SFTP_PRIVATE_KEY_UPLOAD_HANDLER_ID.equals(handler);
	}

	@Override
	public String uploadOccured(UploadedFile uploadedFile) {
		Map<String, String> metadataMap = uploadedFile.getMetadata();
		
		long datasinkId = Long.valueOf(metadataMap.get(FtpUiModule.SFTP_UPLOAD_DATASINK_ID_FIELD));
		byte[] privateKey = uploadedFile.getFileBytes();

		if(null == privateKey || 0 == privateKey.length)
			return null;

		SecurityService securityService = securityServiceProvider.get();
		securityService.assertUserLoggedIn();

		DatasinkTreeService datasinkService = datasinkServiceProvider.get();
		AbstractDatasinkManagerNode rmn = datasinkService.getNodeById(datasinkId);

		securityService.assertActions(rmn, UpdateAction.class);
		securityService.assertRights(rmn, Write.class);

		if(rmn instanceof SftpDatasink){
		   SftpDatasink sftpDatasink = (SftpDatasink) rmn;
		   sftpDatasink.setPrivateKey(privateKey);

			/* merge into datasink */
			datasinkService.merge(sftpDatasink);
		}
		
		return null;
	}


}
