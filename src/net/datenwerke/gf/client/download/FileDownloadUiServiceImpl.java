package net.datenwerke.gf.client.download;

import com.google.gwt.core.client.GWT;

import net.datenwerke.gf.client.download.dto.DownloadProperties;
import net.datenwerke.gf.client.uiutils.ClientDownloadHelper;

public class FileDownloadUiServiceImpl implements FileDownloadUiService {

	@Override
	public void triggerDownload(DownloadProperties properties) {
		StringBuilder url = new StringBuilder().append(GWT.getModuleBaseURL()).append("filedownload?id=").append(properties.getId());
		url.append("&handler=").append(properties.getHandler());
		for(String key : properties.getMetadata().keySet()){
			String value = properties.getMetadata().get(key);
			url.append("&").append(FileDownloadUiModule.META_FIELD_PREFIX).append("=").append(value);
		}
		
		ClientDownloadHelper.triggerDownload(url.toString());		
	}

}
